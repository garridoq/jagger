public class VisitorEval extends DefaultVisitor {
	private double doubleRes;
	private String strRes;
	private String toPrint;
	private String currentType;

	public VisitorEval(){
		this.doubleRes = 0;
		this.strRes = "";
		this.currentType ="";
	}

	public void printEval(){
		switch(this.currentType){
			case "Number":
				System.out.println(this.doubleRes);
				break;
			case "Str":
				System.out.println(this.strRes);
				break;
			default:
				System.out.println("Unrecognized");
		}
	}

	public void visitBinOpNumber(BinOp b){
		double temp = this.doubleRes;
		b.getRight().accept(this);
		switch(b.getOp()){
			case PLUS:
				this.doubleRes = temp + this.doubleRes;
				break;
			case MINUS:
				this.doubleRes = temp - this.doubleRes;
				break;
			case MUL:
				this.doubleRes = temp * this.doubleRes;
				break;
			case DIV:
				this.doubleRes = temp / this.doubleRes;
				break;
		
			case GT:
				this.doubleRes = temp > this.doubleRes ? 1 : 0;
				break;
			case GEQ:
				this.doubleRes = temp >=this.doubleRes ? 1 : 0;
				break;
			case LT:
				this.doubleRes = temp < this.doubleRes ? 1 : 0;
				break;
			case LEQ:
				this.doubleRes = temp <= this.doubleRes ? 1 : 0;
				break;
			case NEQ:
				this.doubleRes = temp != this.doubleRes ? 1 : 0;
				break;
			case EQ:
				this.doubleRes = temp == this.doubleRes ? 1 : 0;
				break;
			default:
				System.out.println("Error cannot evaluate");
		}
	}
	
	public void visitBinOpStr(BinOp b){
		String temp = this.strRes;
		b.getRight().accept(this);
		switch(b.getOp()){
			case PLUS:
				this.strRes = temp + this.strRes;
				break;
			case MINUS:
				break;
			case MUL:
				break;
			case DIV:
				break;
		
			case GT:
				this.doubleRes = temp.compareTo(this.strRes)>0 ? 1 : 0;
				this.currentType="Number";
				break;
			case GEQ:
				this.doubleRes = temp.compareTo(this.strRes)>=0 ? 1 : 0;
				this.currentType="Number";
				break;
			case LT:
				this.doubleRes = temp.compareTo(this.strRes)<0 ? 1 : 0;
				this.currentType="Number";
				break;
			case LEQ:
				this.doubleRes = temp.compareTo(this.strRes)<=0 ? 1 : 0;
				this.currentType="Number";
				break;
			case NEQ:
				this.doubleRes = !temp.equals(this.strRes) ? 1 : 0;
				this.currentType="Number";
				break;
			case EQ:
				this.doubleRes = temp.equals(this.strRes) ? 1 : 0;
				this.currentType="Number";
				break;
			default:
				System.out.println("Error cannot evaluate");
		}
	}


	public void visit(BinOp b){
		b.getLeft().accept(this);
		if(this.currentType == "Number"){
			visitBinOpNumber(b);
		}
		else if(this.currentType == "Str"){
			visitBinOpStr(b);
		}
	}

	public void visit(KeywordFunction f){
		f.getParameter().accept(this);
		switch(f.getOp()){
			case PRINT:
				System.out.print("Print: ");
				if(this.currentType.equals("Number"))
					System.out.print(this.doubleRes);
				if(this.currentType.equals("Str"))
					System.out.print(this.strRes);
				System.out.println();
				break;
			default:
				System.out.println("Error cannot evaluate");
		}
	
	}
	
	public void visit(Condition c){
		c.getCond().accept(this);
		if(this.doubleRes >= 0.0000001) // > 0 = True
			c.getIfTrue().accept(this);
		else
			c.getIfFalse().accept(this);
	}
	
	public void visit(Str n){
		this.strRes = n.getString();
		this.currentType= n.getClass().getSimpleName();
	}
	public void visit(Number n){
		this.doubleRes = n.getNum();
		this.currentType= n.getClass().getSimpleName();
	}


}

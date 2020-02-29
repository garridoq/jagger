public class VisitorEval extends DefaultVisitor {
	private double doubleRes;
	private String strRes;
	private String toPrint;

	public VisitorEval(){
		this.doubleRes = 0;
		this.strRes = "";
		this.toPrint = "";
	}

	public void printEval(){
		System.out.println(this.toPrint);
	}

	public void visit(BinOp b){
		b.getLeft().accept(this);
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

	public void visit(KeywordFunction f){
		f.getParameter().accept(this);
		switch(f.getOp()){
			case PRINT:
				System.out.print("Print: ");
				System.out.print(this.doubleRes);
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
	
	public void visit(Number n){
		this.doubleRes = n.getNum();
	}

}

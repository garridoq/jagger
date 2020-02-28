public class VisitorEval extends DefaultVisitor {
	private double res;

	public VisitorEval(){
		this.res = 0;
	}

	public void printEval(){
		System.out.println(this.res);
	}

	public void visit(BinOp b){
		b.getLeft().accept(this);
		double temp = this.res;
		b.getRight().accept(this);
		switch(b.getOp()){
			case PLUS:
				this.res = temp + this.res;
				break;
			case MINUS:
				this.res = temp - this.res;
				break;
			case MUL:
				this.res = temp * this.res;
				break;
			case DIV:
				this.res = temp / this.res;
				break;
		
			case GT:
				this.res = temp > this.res ? 1 : 0;
				break;
			case GEQ:
				this.res = temp >=this.res ? 1 : 0;
				break;
			case LT:
				this.res = temp < this.res ? 1 : 0;
				break;
			case LEQ:
				this.res = temp <= this.res ? 1 : 0;
				break;
			case NEQ:
				this.res = temp != this.res ? 1 : 0;
				break;
			case EQ:
				this.res = temp == this.res ? 1 : 0;
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
				System.out.print(this.res);
				this.res = 0;
				break;
			default:
				System.out.println("Error cannot evaluate");
		}
	
	}
	
	public void visit(Condition c){
		c.getCond().accept(this);
		if(this.res >= 0.0000001) // > 0 = True
			c.getIfTrue().accept(this);
		else
			c.getIfFalse().accept(this);
	}
	
	public void visit(Number n){
		this.res = n.getNum();
	}

}

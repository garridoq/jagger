public class VisitorEval extends DefaultVisitor {
	private String res;

	public VisitorEval(){
		this.res = "";
	}

	public void printEval(){
		System.out.println(this.res);
	}

	public void visit(BinOp b){
		b.getLeft().accept(this);
		String temp = this.res;
		b.getRight().accept(this);
		switch(b.getOp()){
			case PLUS:
				this.res = Double.toString((Double.parseDouble(temp) + Double.parseDouble(this.res)));
				break;
			case MINUS:
				this.res = Double.toString((Double.parseDouble(temp) - Double.parseDouble(this.res)));
				break;
			case MUL:
				this.res = Double.toString((Double.parseDouble(temp) * Double.parseDouble(this.res)));
				break;
			case DIV:
				this.res = Double.toString((Double.parseDouble(temp) / Double.parseDouble(this.res)));
				break;
		
			case GT:
				this.res = Boolean.toString((Double.parseDouble(temp) > Double.parseDouble(this.res)));
				break;
			case GEQ:
				this.res = Boolean.toString((Double.parseDouble(temp) >= Double.parseDouble(this.res)));
				break;
			case LT:
				this.res = Boolean.toString((Double.parseDouble(temp) < Double.parseDouble(this.res)));
				break;
			case LEQ:
				this.res = Boolean.toString((Double.parseDouble(temp) <= Double.parseDouble(this.res)));
				break;
			case NEQ:
				this.res = Boolean.toString((Double.parseDouble(temp) != Double.parseDouble(this.res)));
				break;
			case EQ:
				this.res = Boolean.toString((Double.parseDouble(temp) == Double.parseDouble(this.res)));
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
				this.res = "";
				break;
			default:
				System.out.println("Error cannot evaluate");
		}
	
	}

	public void visit(Number n){
		this.res = Double.toString(n.getNum());
	}

}

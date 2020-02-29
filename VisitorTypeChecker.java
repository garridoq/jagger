public class VisitorTypeChecker extends DefaultVisitor {
	
	private String type;
	private boolean errors;
	public VisitorTypeChecker(){
		this.type = "";
		this.errors = false;
	}


	public boolean hasErrors() {
		return this.errors;
	}

	public void visit(BinOp b){
		b.getLeft().accept(this);
		String temp = this.type;
		b.getRight().accept(this);
		if(!temp.equals(this.type)){
			System.out.println("Error, cannot apply operator " + b.getOp() + " with types " + temp + " and " + this.type);
			this.errors=true;
		}
	}

	public void visit(KeywordFunction f){
		// Cannot fail since we can print both Strings and Doubles
		f.getParameter().accept(this);	
	}
	
	public void visit(Condition c){
		c.getCond().accept(this);
		if(!this.type.equals("Number")){
			System.out.println("Error, cannot interpret : "+this.type +" as boolean");
			this.errors=true;
		}
		c.getIfTrue().accept(this);
		String temp = this.type;
		c.getIfFalse().accept(this);
		if(!temp.equals(this.type)){
			System.out.println("Error, incoherent return types " + temp + " and " + this.type);
			this.errors=true;
		}
	}
	
	public void visit(Number n){
		this.type = n.getClass().getSimpleName();
	}
	
	public void visit(Str n){
		this.type = n.getClass().getSimpleName();
	}

}

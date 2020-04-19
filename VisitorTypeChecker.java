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
		if(!temp.equals(this.type) || temp.equals("Void") || this.type.equals("Void")){		
			System.out.println("Error, cannot apply operator " + b.getOp() + " with types " + temp + " and " + this.type);
			this.errors=true;
		}
		//Only + can be used with strings and numbers
		if(b.getOp() != BOp.PLUS){
			this.type = "Number";	
		}
	}

	public void visit(KeywordFunction f){
		// Cannot fail since we can print both Strings and Doubles
		f.getParameter().accept(this);	
		if(this.type.equals("Void")){
			System.out.println("Error, trying to print data of type Void");
			this.errors=true;
		}
		this.type="Void";
	}
	
	public void visit(Condition c){
		c.getCond().accept(this);
		if(!this.type.equals("Number")){
			System.out.println("Error, cannot interpret : "+this.type +" as Boolean");
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

	public void visit(Scope s){
		//Add declaration types 
		for(VarDecl v : s.getVars().values()){
			v.accept(this);
		}
		for(Expression e : s.getInstructions()){
			e.accept(this);
		}
		this.type="Void";

	}
	public void visit(VarDecl v){
		v.getValue().accept(this);
		if(this.type.equals("Void")){
			System.out.println("Error, trying to assing type Void to variable declaration of " + v.getId().substring(0,v.getId().lastIndexOf('_')));
			this.errors=true;	
		}
		v.setType(this.type);
	}

	public void visit(Variable v){
		this.type = v.getDeclaration().getType();	
		//System.out.println("Variable " + v.getId() + " of type "+ this.type);
	}	
	
	public void visit(While w){
		w.getCond();
		for(Expression e : w.getInstructions()){
			e.accept(this);
		}
		this.type="Void";

	}
}

public class Variable implements Expression{
	
	private String id;
	private VarDecl declaration;

	public Variable(String id, VarDecl v){
		this.id = id;
		this.declaration = v;
	}

	public Variable(String id){
		this.id = id;
	}


	public String getId() {
		return this.id;
	}

	public VarDecl getDeclaration() {
		return this.declaration;
	}

	public void setDeclaration(VarDecl d) {
		this.declaration = d;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}

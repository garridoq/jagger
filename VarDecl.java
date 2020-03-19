public class VarDecl implements Expression{
	
	private String id;
	private String type;
	private Expression value;

	public VarDecl(String id, Expression value){
		this.id = id;
		this.value = value;
	}

	public Expression getValue() {
		return this.value;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}

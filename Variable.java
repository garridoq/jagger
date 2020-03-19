public class Variable implements Expression{
	
	private String id;
	private Expression value;

	public Variable(String id, Expression value){
		this.id = id;
		this.value = value;
	}

	public Expression getValue() {
		return this.value;
	}
	public String getId() {
		return this.id;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}

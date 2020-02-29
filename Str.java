public class Str implements Expression{
	
	private String string;

	public Str(String string){
		this.string = string;
	}

	public String getString() {
		return this.string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}

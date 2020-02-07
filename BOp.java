public enum BOp{
	PLUS("PLUS"),
	MINUS("MINUS"),
	MUL("MUL"),
	DIV("DIV");

	private String name = "";

	BOp(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}	
}

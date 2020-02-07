public enum BOp{
	//Arithmetic operators
	PLUS("PLUS"),
	MINUS("MINUS"),
	MUL("MUL"),
	DIV("DIV"),
	//Comparisons
	GT("GT"),
	GEQ("GEQ"),
	LT("LT"),
	LEQ("LEQ"),
	EQ("EQ"),
	NEQ("NEQ");

	private String name = "";

	BOp(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}	
}

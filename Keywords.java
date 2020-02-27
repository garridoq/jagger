public enum Keywords{
	//Arithmetic operators
	PRINT("PRINT");

	private String name = "";

	Keywords(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}	
}

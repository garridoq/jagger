public class Number implements Expression{
	
	private double num;

	public Number(double num){
		this.num = num;
	}

	public double getNum() {
		return this.num;
	}
	public void setNum(double num) {
		this.num = num;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}

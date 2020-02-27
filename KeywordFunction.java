public class KeywordFunction implements Expression{
	
	private Expression parameter;
	private Keywords op;

	public KeywordFunction(Keywords op, Expression parameter){
		this.parameter = parameter;
		this.op = op;
	}

	public Expression getParameter() {
		return this.parameter;
	}
	public Keywords getOp() {
		return this.op;
	}
	public void setParameter(Expression parameter) {
		this.parameter = parameter;
	}	
	public void setOp(Keywords op) {
		this.op = op;
	}	

	public void accept(Visitor v){
		v.visit(this);
	}
}



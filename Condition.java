public class Condition implements Expression{
	
	private Expression cond ;
	private Expression ifTrue;
	private Expression ifFalse;

	public Condition(Expression cond, Expression ifTrue, Expression ifFalse){
		this.cond = cond;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	public Expression getIfTrue() {
		return this.ifTrue;
	}
	public Expression getIfFalse() {
		return this.ifFalse;
	}
	public Expression getCond() {
		return this.cond;
	}
	public void setIfTrue(Expression ifTrue) {
		this.ifTrue = ifTrue;
	}
	public void setIfFalse(Expression ifFalse) {
		this.ifFalse = ifFalse;
	}	
	public void setCond(Expression cond) {
		this.cond = cond;
	}	

	public void accept(Visitor v){
		v.visit(this);
	}
}

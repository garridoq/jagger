public class BinOp implements Expression{
	
	private Expression left;
	private Expression right;
	private BOp op;

	public BinOp(Expression left, BOp op, Expression right){
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public Expression getLeft() {
		return this.left;
	}
	public Expression getRight() {
		return this.right;
	}
	public BOp getOp() {
		return this.op;
	}
	public void setLeft(Expression left) {
		this.left = left;
	}
	public void setRight(Expression right) {
		this.right = right;
	}	
	public void setOp(BOp op) {
		this.op = op;
	}	

	public void accept(Visitor v){
		v.visit(this);
	}
}



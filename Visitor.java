public interface Visitor{
	
	public void visit(BinOp b);
	public void visit(Number n);
	public void visit(Condition n);
	public void visit(KeywordFunction f);	

}

public interface Visitor{
	
	public void visit(BinOp b);
	public void visit(Number n);
	public void visit(Str n);
	public void visit(Condition n);
	public void visit(KeywordFunction f);	
	public void visit(Scope s);	
	public void visit(VarDecl v);	
	public void visit(Variable v);	
	public void visit(While v);	

}

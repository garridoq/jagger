public class VisitorPrettyPrinter extends DefaultVisitor {
	public void visit(BinOp b){
		System.out.print("(");
		b.getLeft().accept(this);
		System.out.print(" ");
		System.out.print(b.getOp());
		System.out.print(" ");
		b.getRight().accept(this);
		System.out.print(")");
	}

	public void visit(KeywordFunction f){
		System.out.print(f.getOp());
		System.out.print("(");
		f.getParameter().accept(this);
		System.out.print(")");
	}

	public void visit(Condition c){
		System.out.print("IF ");
		c.getCond().accept(this);
		System.out.print(" THEN ");
		c.getIfTrue().accept(this);
		System.out.print(" ELSE ");
		c.getIfFalse().accept(this);
	}

	public void visit(Number n){
		System.out.print(n.getNum());
	}
	
	public void visit(Str s){
		System.out.print("'");
		System.out.print(s.getString());
		System.out.print("'");
	}

}

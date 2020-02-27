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

	public void visit(Number n){
		System.out.print(n.getNum());
	}

}

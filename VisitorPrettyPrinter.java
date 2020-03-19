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
		System.out.print("(");
		System.out.print("IF(");
		c.getCond().accept(this);
		System.out.print(")");
		System.out.print(" THEN ");
		System.out.print("(");
		c.getIfTrue().accept(this);
		System.out.print(")");
		System.out.print(" ELSE ");
		System.out.print("(");
		c.getIfFalse().accept(this);
		System.out.print(")");
		System.out.print(")");
	}

	public void visit(Number n){
		System.out.print(n.getNum());
	}
	
	public void visit(Str s){
		System.out.print("'");
		System.out.print(s.getString());
		System.out.print("'");
	}

	public void visit(Variable v){
		System.out.print("var " + v.getId() + ":= ");
		v.getValue().accept(this);
		System.out.println();
	}

	public void visit(Scope s){
		System.out.println("LET");
		for(Variable v : s.getVars().values())
			v.accept(this);
		System.out.println("IN");
		for(Expression i : s.getInstructions()){
			i.accept(this);
			System.out.println();	
		}
		System.out.println("END");
	}

}

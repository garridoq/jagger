public class Main{
	public static void main(String[] args){
		Expression e = new BinOp(new Number(1),BOp.PLUS, new BinOp(new Number(2),BOp.MUL, new Number(3)));
		
		VisitorPrettyPrinter v = new VisitorPrettyPrinter(); 
		
		e.accept(v);
	}
}

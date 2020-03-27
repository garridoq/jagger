import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
public class VisitorRenamer extends DefaultVisitor{
	
	private int n;

	public VisitorRenamer(){
		this.n = 0;
	}

	//Do nothing
	public void visit(BinOp b){
		b.getLeft().accept(this);
		b.getRight().accept(this);
	}
	public void visit(Number b){
	}
	public void visit(Str b){
	}
	public void visit(KeywordFunction f){	
		f.getParameter().accept(this);
	}
	public void visit(Condition c){	
		c.getCond().accept(this);
		c.getIfTrue().accept(this);
		c.getIfFalse().accept(this);
	}

	//Do smth
	public void visit(Scope s){
		//Add all variables into the env
		for(VarDecl v : s.getVars().values()){
			v.accept(this);
		}
		for(Expression e : s.getInstructions()){
			e.accept(this);
		}

	}
	public void visit(VarDecl v){
		System.out.print("Renamed " + v.getId());
		v.setId(v.getId() + "_" + String.valueOf(n));
		System.out.println(" as "+v.getId());
		this.n++;
	}

	//Bind to the declaration through the declaration field
	public void visit(Variable v){
	}	
	
	public void visit(While w){	
		w.getCond().accept(this);
		for(Expression e : w.getInstructions()){
			e.accept(this);
		}
	}
}

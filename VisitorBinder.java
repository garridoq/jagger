import java.util.HashMap;
import java.util.Stack;
public class VisitorBinder implements Visitor{
	
	private Stack<HashMap<String,Variable>> envs;

	public VisitorBinder(){
		this.envs = new Stack<HashMap<String,Variable>>();
	}

	//Do nothing
	public void visit(BinOp b){
	}
	public void visit(Number b){
	}
	public void visit(Str b){
	}
	public void visit(KeywordFunction f){	
	}
	public void visit(Condition c){	
	}

	//Do smth
	public void visit(Scope s){

	}
	public void visit(Variable v){
	
	}	
}

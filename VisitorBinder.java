import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
public class VisitorBinder extends DefaultVisitor{
	
	private Stack<HashMap<String,VarDecl>> envs;

	public VisitorBinder(){
		this.envs = new Stack<HashMap<String,VarDecl>>();
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
		this.envs.push(new HashMap<String,VarDecl>());
		for(VarDecl v : s.getVars().values()){
			v.getValue().accept(this);
			this.envs.peek().put(v.getId(),v);
		}
		for(Expression e : s.getInstructions()){
			e.accept(this);
		}
		this.envs.pop();
	}
	public void visit(VarDecl v){
		
	}
	//Bind to the declaration through the declaration field
	public void visit(Variable v){
		Iterator<HashMap<String,VarDecl>> it = this.envs.iterator();
		boolean found = false;
		for(int i=this.envs.size()-1; i >=0; i--){
			HashMap<String,VarDecl> pair = this.envs.get(i);

			if(pair.containsKey(v.getId())){
				v.setDeclaration(pair.get(v.getId()));	
				found=true;
				break;
			}
		}
		if(!found)
			System.out.println("Symnol " + v.getId() + " is not defined.");
	}	
	
	public void visit(While w){
		w.getCond().accept(this);
		for(Expression e : w.getInstructions()){
			e.accept(this);
		}
	}
}

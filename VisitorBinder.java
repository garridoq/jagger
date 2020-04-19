import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Stack;
public class VisitorBinder extends DefaultVisitor{
	
	private Stack<LinkedHashMap<String,VarDecl>> envs;
	private boolean errors = false;

	public VisitorBinder(){
		this.envs = new Stack<LinkedHashMap<String,VarDecl>>();
	}

	public boolean hasErrors(){
		return this.errors;
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
		this.envs.push(new LinkedHashMap<String,VarDecl>());
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
		Iterator<LinkedHashMap<String,VarDecl>> it = this.envs.iterator();
		boolean found = false;
		for(int i=this.envs.size()-1; i >=0; i--){
			LinkedHashMap<String,VarDecl> pair = this.envs.get(i);
			
			for(String s : pair.keySet()){
				//System.out.println("Found variable " + s);
			}

			if(pair.containsKey(v.getId())){
				v.setDeclaration(pair.get(v.getId()));	
				found=true;
				break;
			}
		}
		if(!found){
			System.out.println("Error, symbol " + v.getId() + " is not defined.");
			this.errors=true;
		}
	}	
	
	public void visit(While w){
		w.getCond().accept(this);
		for(Expression e : w.getInstructions()){
			e.accept(this);
		}
	}
}

import java.util.HashMap;
import java.util.ArrayList;
public class Scope implements Expression{

	private HashMap<String, VarDecl> vars;
	private ArrayList<Expression> instructions;

	public Scope(){
		this.vars = new HashMap<String,VarDecl>();
		this.instructions = new ArrayList<Expression>();
	}

	public HashMap<String, VarDecl> getVars() {
		return this.vars;
	}

	public ArrayList<Expression> getInstructions() {
		return this.instructions;
	}

	public void addDeclaration(String id, VarDecl v){
		this.vars.put(id, v);
	}

	public void addInstruction(Expression e){
		this.instructions.add(e);
	}

	public void accept(Visitor v){
		v.visit(this);
	}

}

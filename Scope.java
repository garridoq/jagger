import java.util.LinkedHashMap;
import java.util.ArrayList;
public class Scope implements Expression{

	private LinkedHashMap<String, VarDecl> vars;
	private ArrayList<Expression> instructions;

	public Scope(){
		this.vars = new LinkedHashMap<String,VarDecl>();
		this.instructions = new ArrayList<Expression>();
	}

	public LinkedHashMap<String, VarDecl> getVars() {
		return this.vars;
	}

	public ArrayList<Expression> getInstructions() {
		return this.instructions;
	}

	public void addDeclaration(String id, VarDecl v){
		if(this.vars.containsKey(id))
			System.out.println("Error, trying to redefine " + id);
		else
			this.vars.put(id, v);
	}

	public void addInstruction(Expression e){
		this.instructions.add(e);
	}

	public void accept(Visitor v){
		v.visit(this);
	}

}

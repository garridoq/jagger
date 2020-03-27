import java.util.HashMap;
import java.util.ArrayList;
public class While implements Expression{

	private Expression cond;
	private ArrayList<Expression> instructions;

	public While(){
		this.instructions = new ArrayList<Expression>();
	}

	public Expression getCond() {
		return this.cond;
	}

	public ArrayList<Expression> getInstructions() {
		return this.instructions;
	}

	public void setCond(Expression cond){
		this.cond = cond;
	}

	public void addInstruction(Expression e){
		this.instructions.add(e);
	}

	public void accept(Visitor v){
		v.visit(this);
	}

}

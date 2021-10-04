package planning;

import representation.Variable;

import java.util.HashMap;
import java.util.Map;

public class BasicAction implements Action{
    protected Map<Variable, Object> precondition;
    protected Map<Variable, Object> effet;
    protected int cout;

    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effet, int cout) {
        this.precondition = precondition;
        this.effet = effet;
        this.cout = cout;
    }

    @Override
    public boolean isApplicable(Map<Variable, Object> etat) {
        if(this.precondition.isEmpty()){
            return true;
        }
        boolean bonoupas = true;
        for(Variable variable : this.precondition.keySet()) {
            bonoupas = bonoupas && etat.get(variable) == this.precondition.get(variable);
        }
        return bonoupas;
    }

    @Override
    public Map<Variable, Object> successor(Map<Variable, Object> etat) {
        Map<Variable,Object> etatprime = new HashMap<>();
        etatprime.putAll(etat);
        for(Variable var : this.effet.keySet()){
            etatprime.put(var,this.effet.get(var));
        }
        return etatprime;
    }

    @Override
    public int getCost() {
        return this.cout;
    }
}

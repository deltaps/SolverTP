package planning;

import representation.Variable;

import java.util.Map;

public class BasicGoal implements Goal{
    protected Map<Variable,Object> instanciation;

    public BasicGoal(Map<Variable, Object> instanciation) {
        this.instanciation = instanciation;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> etat) {
        boolean ok = true;
        for(Variable var : this.instanciation.keySet()){
            if(!(etat.containsKey(var))){
                return false;
            }
            else{
                ok = ok && etat.get(var) == this.instanciation.get(var);
            }
        }
        return ok;
    }

    @Override
    public String toString() {
        return "BasicGoal{" +
                "instanciation=" + instanciation +
                '}';
    }
}

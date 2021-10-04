package planning;

import representation.Variable;

import java.util.Map;

public interface Action {
    boolean isApplicable(Map<Variable,Object> etat);
    Map<Variable,Object> successor(Map<Variable,Object> etat);
    int getCost();
}

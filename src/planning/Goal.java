package planning;

import representation.Variable;

import java.util.Map;

public interface Goal {
    boolean isSatisfiedBy(Map<Variable,Object> etat);
}

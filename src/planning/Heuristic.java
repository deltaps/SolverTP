package planning;

import representation.Variable;

import java.util.Map;

public interface Heuristic {
    public float estimate(Map<Variable,Object> state);
}

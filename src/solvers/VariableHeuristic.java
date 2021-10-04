package solvers;

import representation.Variable;

import java.util.Map;
import java.util.Set;

public interface VariableHeuristic {
    Variable best(Set<Variable> var, Map<Variable, Set<Object>> domaine);
}

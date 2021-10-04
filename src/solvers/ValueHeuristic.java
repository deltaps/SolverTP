package solvers;

import representation.Variable;

import java.util.List;
import java.util.Set;

public interface ValueHeuristic {
    List<Object> ordering(Variable variable, Set<Object> domain);
}

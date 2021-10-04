package planning;

import representation.Variable;

import java.util.*;

public interface Planner {
    List<Action> plan();
    Map<Variable,Object> getInitialState();
    Set<Action> getActions();
    Goal getGoal();
}

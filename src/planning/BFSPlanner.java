package planning;

import representation.Variable;

import java.util.*;

public class BFDPlanner implements Planner{
    protected Map<Variable,Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;

    public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }
}

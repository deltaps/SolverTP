package planning;

import representation.Variable;

import java.util.*;

public class DFSPlanner implements Planner{
    protected Map<Variable,Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;

    public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    @Override
    public List<Action> plan() {
        //TODO implémenté la méthode plan
        return null;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return this.initialState;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }

}

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
        Map<Variable,Object> instanciation = new HashMap<Variable,Object>(this.initialState);
        LinkedList<Action> plan = new LinkedList<>();
        Set<Map<Variable,Object>> closed = new HashSet<>();
        closed.add(this.initialState);
        return dfsRec(instanciation,plan,closed);
    }
    public List<Action> dfsRec(Map<Variable,Object> instanciation, LinkedList<Action> plan, Set<Map<Variable,Object>> closed){
        if(this.goal.isSatisfiedBy(instanciation)){
            return plan;
        }
        else{
            for(Action action : this.actions){
                if(action.isApplicable(instanciation)){
                    Map<Variable, Object> next = new HashMap<>(action.successor(instanciation));
                    if(!(closed.contains(next))){
                        plan.add(action);
                        closed.add(next);
                        List<Action> subPlan = dfsRec(next,plan,closed);
                        if(!(subPlan == null)){
                            return subPlan;
                        }
                        else{
                            plan.pop();
                        }
                    }
                }
            }
            return null;
        }
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

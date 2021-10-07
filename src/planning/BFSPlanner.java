package planning;

import representation.Variable;

import java.util.*;

public class BFSPlanner implements Planner{
    protected Map<Variable,Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;

    public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    @Override
    public List<Action> plan(){
        Map<Map<Variable,Object>,Map<Variable,Object>> father = new HashMap<>();
        Map<Map<Variable,Object>,Action> plan = new HashMap<>();
        return bfs(father,plan);
    }

    public List<Action> bfs(Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Action> plan){
        Set<Map<Variable,Object>> closed = new HashSet<>();
        closed.add(this.initialState);
        ArrayList<Map<Variable,Object>> open = new ArrayList<>();
        open.add(this.initialState);
        father.put(this.initialState,null);
        if(this.goal.isSatisfiedBy(this.initialState)){
            return new LinkedList<>();
        }
        while(!(open.isEmpty())){
            Map<Variable,Object> instanciation = open.remove(0);
            closed.add(instanciation);
            for(Action action : this.actions){
                if(action.isApplicable(instanciation)){
                    Map<Variable,Object> next = action.successor(instanciation);
                    if(!(closed.contains(next)) && !(open.contains(next))){
                        father.put(next,instanciation);
                        plan.put(next,action);
                        if(this.goal.isSatisfiedBy(next)){
                            return getbfsplan(father,plan,next);
                        }
                        else{
                            open.add(next);
                        }
                    }
                }
            }
        }
        return null;
    }
    public List<Action> getbfsplan(Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Action> plan,Map<Variable,Object> goal){
        List<Action> bfsPlan = new LinkedList<>();
        while(goal != this.initialState){
            bfsPlan.add(plan.get(goal));
            goal = father.get(goal);
        }
        Collections.reverse(bfsPlan);
        return bfsPlan;
    }

    @Override
    public Map<Variable, Object> getInitialState(){
        return this.initialState;
    }

    @Override
    public Set<Action> getActions(){
        return this.actions;
    }

    @Override
    public Goal getGoal(){
        return this.goal;
    }
}

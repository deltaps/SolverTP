package planning;

import representation.Variable;

import java.net.Inet4Address;
import java.util.*;

public class AStarPlanner implements Planner{
    public Map<Variable,Object> initialState;
    public Set<Action> actions;
    public Goal goal;
    public Heuristic heuristic;
    public AStarPlanner(Map<Variable,Object> initialState, Set<Action> actions, Goal but, Heuristic heuristic) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = but;
        this.heuristic = heuristic;
    }

    @Override
    public List<Action> plan() {
        Map<Map<Variable,Object>,Action> plan = new HashMap<>();
        Map<Map<Variable,Object>,Map<Variable,Object>> father = new HashMap<>();
        Map<Map<Variable,Object>, Integer> distance = new HashMap<>();
        Map<Map<Variable,Object>,Float> value = new HashMap<>();
        return aStar(plan,father,distance,value);
    }

    public List<Action> aStar(Map<Map<Variable,Object>,Action> plan , Map<Map<Variable,Object>,Map<Variable,Object>> father,Map<Map<Variable,Object>, Integer> distance,Map<Map<Variable,Object>,Float> value){
        Set<Map<Variable,Object>> open = new HashSet<>();
        open.add(this.initialState);
        father.put(this.initialState,null);
        distance.put(this.initialState,0);
        value.put(this.initialState,this.heuristic.estimate(this.initialState));
        Map<Variable,Object> instantiation = new HashMap<>();
        Map<Variable,Object> next = new HashMap<Variable,Object>();
        while(!open.isEmpty()){
            int argmin = 999999999;
            for(Map<Variable,Object> node : open){
                if(distance.get(node) < argmin){
                    argmin = distance.get(node);
                    instantiation = node;
                }
            }
            if(this.goal.isSatisfiedBy(instantiation)){
                return getbfsplan(father,plan,instantiation);
            }
            else{
                open.remove(instantiation);
                for(Action action : this.actions){
                    if(action.isApplicable(instantiation)){
                        next = action.successor(instantiation);
                        if(!distance.containsKey(next)){
                            distance.put(next,9999999);
                        }
                        if(distance.get(next) > distance.get(instantiation) + action.getCost()){
                            distance.put(next,distance.get(instantiation) + action.getCost());
                            value.put(next,distance.get(instantiation) + this.heuristic.estimate(next));
                            father.put(next,instantiation);
                            plan.put(next,action);
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

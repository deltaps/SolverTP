package planning;

import representation.Constraint;
import representation.Variable;

import java.util.*;

public class DijkstraPlanner implements Planner{
    protected Map<Variable,Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;

    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }


    @Override
    public List<Action> plan(){
        Map<Map<Variable,Object>,Action> plan = new HashMap<Map<Variable,Object>,Action>();
        Map<Map<Variable,Object>,Integer> distance = new HashMap<Map<Variable,Object>,Integer>();
        Map<Map<Variable,Object>,Map<Variable,Object>> father = new HashMap<Map<Variable,Object>,Map<Variable,Object>>();
        return dijkstra(plan,distance,father);
    }
    public List<Action> dijkstra(Map<Map<Variable,Object>,Action> plan,Map<Map<Variable,Object>,Integer> distance,Map<Map<Variable,Object>,Map<Variable,Object>> father){
        Set<Map<Variable,Object>> goals = new HashSet<Map<Variable,Object>>();
        father.put(this.initialState,null);
        distance.put(this.initialState, 0);
        Set<Map<Variable,Object>> open = new HashSet<Map<Variable,Object>>();
        open.add(this.initialState);
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
            open.remove(instantiation);
            if(this.goal.isSatisfiedBy(instantiation)){
                goals.add(instantiation);
            }
            for(Action action : this.actions){
                if(action.isApplicable(instantiation)){
                    next = action.successor(instantiation);
                    if(!distance.containsKey(next)){
                        distance.put(next,9999999);
                    }
                    if(distance.get(next) > distance.get(instantiation) + action.getCost()){
                        distance.put(next,distance.get(instantiation) + action.getCost());
                        father.put(next,instantiation);
                        plan.put(next,action);
                        open.add(next);
                    }
                }
            }
        }
        if(goals.isEmpty()){
            return null;
        }
        else{
            return getDijkstraPlan(father,plan,goals,distance);
        }
    }

    public List<Action> getDijkstraPlan(Map<Map<Variable,Object>,Map<Variable,Object>> father,Map<Map<Variable,Object>,Action> plan,Set<Map<Variable,Object>> goals,Map<Map<Variable,Object>,Integer> distance){
        List<Action> DIJ_plan = new ArrayList<Action>();
        Map<Variable,Object> goal = new HashMap<Variable,Object>();
        int argmin = 999999999;
        for(Map<Variable,Object> node : goals){
            if(distance.get(node) < argmin){
                argmin = distance.get(node);
                goal = node;
            }
        }
        while(goal != null){
            if(plan.get(goal) != null){
                DIJ_plan.add(plan.get(goal));
            }
            goal = father.get(goal);
        }
        Collections.reverse(DIJ_plan);
        return DIJ_plan;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return null;
    }

    @Override
    public Set<Action> getActions() {
        return null;
    }

    @Override
    public Goal getGoal() {
        return null;
    }
}

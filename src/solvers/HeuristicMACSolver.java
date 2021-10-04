package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.*;

public class HeuristicMACSolver extends AbstractSolver{
    protected VariableHeuristic heuristicVar;
    protected ValueHeuristic heuristicVal;
    public HeuristicMACSolver(Set<Variable> var, Set<Constraint> cons, VariableHeuristic heuristicVar, ValueHeuristic heuristicVal) {
        super(var, cons);
        this.heuristicVar = heuristicVar;
        this.heuristicVal = heuristicVal;
    }

    @Override
    public Map<Variable,Object> solve(){
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable v : this.var){
            domaines.put(v, new HashSet<>(v.getDomain()));
        }
        return MACSolver(new HashMap<Variable, Object>(), new LinkedList<>(this.var), domaines);
    }
    public Map<Variable,Object> MACSolver(Map<Variable,Object> I,LinkedList<Variable> V,Map<Variable,Set<Object>> ED){
        if(V.isEmpty()){
            return I;
        }
        else{
            Map<Variable, Set<Object>> domainesCopies = new HashMap<>();
            ArcConsistency arcConsistency = new ArcConsistency(this.cons);
            boolean res = arcConsistency.enforceNodeConsistency(domainesCopies);
            if(!res){
                return null;
            }

            //Si toutes les variables sont instanciees
            for(Variable v : V){
                for(Map.Entry<Variable, Set<Object>> entry : ED.entrySet()){
                    if(entry.getKey().equals(v)){
                        Set<Object> object = new HashSet<>(entry.getValue());
                        domainesCopies.put(v, object);
                    }
                }
            }

            //On recupere la meilleure variable heuristique
            Variable v = this.heuristicVar.best(new HashSet<>(V), domainesCopies);
            for(Map.Entry<Variable, Set<Object>> entry : domainesCopies.entrySet()){
                if(entry.getKey().equals(v)){
                    for(Object o : this.heuristicVal.ordering(v, domainesCopies.get(v))){
                        I.put(v, o);
                        if(this.isConsistent(I)){
                            Set<Object> tmpDomaines = new HashSet<>();
                            tmpDomaines.add(o);
                            domainesCopies.put(v, tmpDomaines);
                            if (I.keySet().containsAll(this.var)) {
                                return I;
                            }
                            Map<Variable, Object> newInstanciation = new HashMap<>();
                            newInstanciation = this.MACSolver(I, V, domainesCopies);
                            // Solution trouvee
                            if(newInstanciation != null){
                                return newInstanciation;
                            }
                        }
                        I.remove(v);
                    }
                }
            }
            V.add(v);
        }
        return null;
    }
}

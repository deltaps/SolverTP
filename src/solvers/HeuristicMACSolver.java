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
    //TODO revoir
    // I = instanciation, V = variables, ED = domaine
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
                for(Variable allVar : ED.keySet()){
                    if(allVar.equals(v)){
                        Set<Object> domaineVar = new HashSet<>(ED.get(allVar));
                        domainesCopies.put(v,domaineVar);
                    }
                }
            }

            //On recupere la meilleure variable avec la meilleure heuristique
            Variable v = this.heuristicVar.best(new HashSet<>(V), domainesCopies);
            for(Variable entry : domainesCopies.keySet()){
                if(entry.equals(v)){ // On regarde seulement pour la variable avec la meilleure heuristique
                    for(Object o : this.heuristicVal.ordering(v,domainesCopies.get(v))){ // heuristicVal est une valueHeuristique, on peu donc l'ordonnée avec la méthode ordering
                        I.put(v,o);
                        if(this.isConsistent(I)){
                            Set<Object> tmpDomaines = new HashSet<>();
                            tmpDomaines.add(o);
                            domainesCopies.put(v,tmpDomaines);
                            if(I.keySet().containsAll(this.var)){
                                return I;
                            }
                            Map<Variable,Object> nouvelleInstanciation = this.MACSolver(I,V,domainesCopies);//Appel récursif
                            if(nouvelleInstanciation != null){
                                return nouvelleInstanciation;
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

package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArcConsistency{
    protected Set<Constraint> constraints;

    public ArcConsistency(Set<Constraint> cons) {
        for(Constraint cons2 : cons){
            if(cons2.getScope().size() > 2) {
                throw new IllegalArgumentException("Scope supérieur a 2");
            }
        }
        this.constraints = cons;
    }
    // Méthode faite aidée par l'encadrant de tp
    public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domains){
        Map<Variable,Set<Object>> Vfinal = new HashMap<>();
        for(Variable xi: domains.keySet()) {
            Set<Object> V = new HashSet<>();
            for (Object vi : domains.get(xi)) {
                Map<Variable, Object> partialInstantiation = new HashMap<>();
                partialInstantiation.put(xi,vi);
                for(Constraint constraint: this.constraints){
                    if(constraint.getScope().size() == 1 && constraint.getScope().contains(xi)){
                        if(!constraint.isSatisfiedBy(partialInstantiation)){
                            V.add(vi);
                        }

                    }
                }

            }
            Vfinal.put(xi,V);
        }
        for (Map.Entry<Variable, Set<Object>> entry : Vfinal.entrySet()) {
            domains.get(entry.getKey()).removeAll(entry.getValue());
        }
        for (Map.Entry<Variable, Set<Object>> entry : domains.entrySet()){
            if (entry.getValue().isEmpty()){
                return false;
            }
        }
        return true;
    }
    public boolean revise(Variable xi, Set<Object> domainexi, Variable xj, Set<Object> domainexj){
        boolean del = false;
        Set<Object> supprimerDuDomaine = new HashSet<>();
        for(Object vi : domainexi){
            boolean viable = false;
            for(Object vj : domainexj){
                boolean toutSatisfait = true;
                for(Constraint c : this.constraints) {
                    if (c.getScope().contains(xi) && c.getScope().contains(xj) && c.getScope().size() == 2){
                        Map<Variable, Object> U = new HashMap<Variable, Object>();
                        U.put(xi, vi);
                        U.put(xj, vj);
                        if (!(c.isSatisfiedBy(U))) {
                            toutSatisfait = false;
                            break;
                        }
                    }
                }
                if (toutSatisfait) {
                    viable = true;
                    break;
                }
            }
            if(!viable){
                supprimerDuDomaine.add(vi);
                del = true;
            }
        }
        domainexi.removeAll(supprimerDuDomaine);
        return del;
    }
    public boolean ac1(Map<Variable, Set<Object>> ED){
        if(!(enforceNodeConsistency(ED))){
            return false;
        }
        boolean change;
        do{
            change = false;
            for(Variable xi : ED.keySet()){
                for(Variable xj : ED.keySet()){
                    if(xj != xi){
                        if(revise(xi, ED.get(xi),xj, ED.get(xj))){
                            change = true;
                        }
                    }
                }
            }
        }
        while(change == true);
        for(Variable x : ED.keySet()){
            if(ED.get(x).size() == 0){
                return false;
            }
        }
        return true;
    }
}

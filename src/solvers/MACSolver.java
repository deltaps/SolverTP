package solvers;
import representation.*;

import java.util.*;

public class MACSolver extends AbstractSolver {
    private ArcConsistency arc;
    public MACSolver(Set<Variable> var, Set<Constraint> cons) {
        super(var, cons);
        this.arc = new ArcConsistency(this.cons);
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
        if(V.size() == 0){
            return I;
        }
        else{
            ArcConsistency ac = new ArcConsistency(this.cons);
            if(!(ac.ac1(ED))){
                return null;
            }
            Variable xi = V.remove();
            for(Object vi : xi.getDomain()){
                Map<Variable,Object> N = new HashMap<Variable,Object>();
                N.putAll(I);
                N.put(xi,vi);
                if(isConsistent(N)){
                    Map<Variable, Object> R = new HashMap<Variable,Object>();
                    R = MACSolver(N,V,ED);
                    if(R != null){
                        return R;
                    }
                }
            }
            V.add(xi);
            return null;
        }
    }
}

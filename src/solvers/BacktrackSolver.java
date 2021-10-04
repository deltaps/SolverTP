package solvers;
import representation.*;
import java.util.*;

public class BacktrackSolver extends AbstractSolver{
  protected Set<Variable> var;
  protected Set<Constraint> cons;
  public BacktrackSolver(Set<Variable> var,Set<Constraint> cons){
    super(var,cons);
  }
  @Override
  public Map<Variable,Object> solve(){
    Map<Variable, Object> map = new HashMap<>();
    LinkedList<Variable> var = new LinkedList<>(super.var);
    return solverec(map,var);
  }
  public Map<Variable,Object> solverec(Map<Variable,Object> solu ,LinkedList<Variable> var){
    if(var.size() == 0){
      return solu;
    }
    Variable varc = var.remove();
    for(Object v : varc.getDomain()){
       Map<Variable, Object> N = new HashMap<>(solu);
       N.put(varc,v);
       if(super.isConsistent(N)){
         LinkedList<Variable> newVar = new LinkedList<>(var);
         Map<Variable, Object> R = solverec(N,newVar);
         if(R != null){
           return R;
         }
       }
    }
    var.push(varc);
    return null;
  }
}

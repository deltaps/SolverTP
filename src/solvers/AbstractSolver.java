package solvers;
import representation.*;
import java.util.*;

public abstract class AbstractSolver implements Solver{
  protected Set<Variable> var;
  protected Set<Constraint> cons;
  public AbstractSolver(Set<Variable> var,Set<Constraint> cons){
    this.var = var;
    this.cons = cons;
  }
  public boolean isConsistent(Map<Variable,Object> map){
    for(Constraint c : this.cons){
      if(map.keySet().containsAll(c.getScope())){
        if (!c.isSatisfiedBy(map)) {
          return false;
        }
      }
    }
    return true;
  }
}

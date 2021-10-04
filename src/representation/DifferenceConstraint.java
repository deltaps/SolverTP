package representation;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class DifferenceConstraint implements Constraint{
  Variable v1;
  Variable v2;

  public DifferenceConstraint(Variable v1, Variable v2){
    this.v1 = v1;
    this.v2 = v2;
  }
  @Override
  public Set<Variable> getScope(){
    Set<Variable> variable = new HashSet();
    variable.add(this.v1);
    variable.add(this.v2);
    return variable;
  }
  @Override
  public boolean isSatisfiedBy(Map<Variable, Object> var){
    if(var.containsKey(this.v1) && var.containsKey(this.v2)){
      return var.get(this.v1) != var.get(this.v2);
    }
    else{
      throw new IllegalArgumentException("Passe pas");
    }
  }

}

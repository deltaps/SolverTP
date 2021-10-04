package representation;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class Implication implements Constraint{
  BooleanVariable l1;
  boolean v1;
  BooleanVariable l2;
  boolean v2;

  public Implication(BooleanVariable l1, boolean v1, BooleanVariable l2, boolean v2){
    this.l1 = l1;
    this.v1 = v1;
    this.l2 = l2;
    this.v2 = v2;
  }

  @Override
  public Set<Variable> getScope(){
    Set<Variable> variable = new HashSet();
    variable.add(this.l1);
    variable.add(this.l2);
    return variable;
  }
  @Override
  public boolean isSatisfiedBy(Map<Variable, Object> var){
    if(var.containsKey(this.l1) && var.containsKey(this.l2)){
      boolean test1 = var.get(l1).equals(v1);
      boolean test2 = var.get(l2).equals(v2);
      return !test1 || test2;
    }
    else{
      throw new IllegalArgumentException("Passe pas");
    }
  }
}

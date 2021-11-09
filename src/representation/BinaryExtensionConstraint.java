package representation;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.*;

public class BinaryExtensionConstraint implements Constraint{
  Variable v1;
  Variable v2;
  Set<BinaryTuple> t1 = new HashSet<>();
  public BinaryExtensionConstraint(Variable v1, Variable v2){
    this.v1 = v1;
    this.v2 = v2;
  }

  public void addTuple(Object o1, Object o2){
    t1.add(new BinaryTuple(o1,o2));
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
      addTuple(this.v1,this.v2);
      return t1.contains(new BinaryTuple(var.get(this.v1),var.get(this.v2)));   
    }
      else{
        throw new IllegalArgumentException("Passe pas");
      }
    }

}

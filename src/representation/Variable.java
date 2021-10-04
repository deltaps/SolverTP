package representation;
import java.util.Set;

public class Variable{
  String nom;
  Set<Object> domaine;

  public Variable(String nom, Set<Object> domaine){
    this.nom = nom;
    this.domaine = domaine;
  }

  @Override
  public boolean equals(Object obj){
    if(this == obj){
      return true;
    }
    else if(obj instanceof Variable){
      Variable var = (Variable)obj;
      if(this.nom.equals(var.nom)){
        return true;
      }
    }
    return false;
  }
  @Override
  public int hashCode(){
    return this.nom.hashCode();
  }

  public String getName(){
    return this.nom;
  }
  public Set<Object> getDomain(){
    return this.domaine;
  }

  @Override
  public String toString() {
    return nom;
  }
}

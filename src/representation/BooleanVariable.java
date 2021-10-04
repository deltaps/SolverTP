package representation;
import java.util.Set;
import java.util.HashSet;

public class BooleanVariable extends Variable{
  String nom;
  static Set<Object> domaine = new HashSet<>();
  static{
    domaine.add(true);
    domaine.add(false);
  }

  public BooleanVariable(String nom){
    super(nom,BooleanVariable.domaine);
  }


}

package datamining;

import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

public class Database {
    protected Set<Variable> variables;
    protected List<Map<Variable,Object>> instances;

    public Database(Set<Variable> variables) {
        this.variables = variables;
        this.instances = new ArrayList<>();
    }

    public void add(Map<Variable,Object> add){
        this.instances.add(add);
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public List<Map<Variable, Object>> getInstances() {
        return instances;
    }
    //TODO a revoir
    public Map<Variable,Map<Object, BooleanVariable>> itemTable(){
        Map<Variable,Map<Object,BooleanVariable>> retourne = new HashMap<>();
        for(Variable variable : this.variables){
            Map<Object,BooleanVariable> add = new HashMap<>();
            for(Object domaine : variable.getDomain()){
                if(domaine instanceof Boolean){
                    BooleanVariable trueVariable = new BooleanVariable(variable.getName());
                    if(domaine.equals(false)){
                        add.put(domaine,null);
                    }
                    else{
                        add.put(domaine,trueVariable);
                    }
                }
                else{
                    BooleanVariable trueVariable = new BooleanVariable(variable.getName()+domaine);
                    add.put(domaine,trueVariable);
                }
            }
            retourne.put(variable,add);
        }
        return retourne;
    }
}

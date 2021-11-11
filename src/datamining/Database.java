package datamining;

import representation.BooleanVariable;
import representation.Variable;

import javax.swing.text.StyledEditorKit;
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
    //BUT : tranformer notre list d'item (this.itemTable) en BooleanDataBase
    public BooleanDatabase propositionalize(){
        Map<Variable,Map<Object, BooleanVariable>> items = this.itemTable();
        Set<BooleanVariable> allBooleanVariable = new HashSet<>();
        //Dans un premier temps on récupère toutes le BooleanVariable de notre itemTable.
        for(Map<Object,BooleanVariable> allMap : items.values()){
            for(BooleanVariable allBool : allMap.values()){
                if(allBool != null){
                    allBooleanVariable.add(allBool);
                }
            }
        }
        //On crée notre dataBase en ajoutant nos BooleanVariable précédement récupérée.
        BooleanDatabase dataBase = new BooleanDatabase(allBooleanVariable);
        //On vas ensuite récupéré toute les variables de notre instances, qui sont des variable booleen (avec instanceof)
        //Pour cela, on boucle sur toute nos instances, on récupère les variable de celle ci, et on vérifie si il sagit de variable booléen, auqel cas on l'ajoute a notre liste de variable booleen,
        //puis, a la fin, nous allons ajouter toute ces liste dans notre dataBase.
        for(Map<Variable,Object> instance : this.instances){
            Set<BooleanVariable> addBooleanVariable = new HashSet<>();
            for(Variable mapInstance : instance.keySet()){
                if(items.get(mapInstance).get(instance.get(mapInstance)) instanceof BooleanVariable){
                    BooleanVariable add = items.get(mapInstance).get(instance.get(mapInstance));
                    addBooleanVariable.add(add);
                }
            }
            dataBase.add(addBooleanVariable);
        }
        return dataBase;
    }

}

package examples;

import datamining.AssociationRule;
import datamining.BooleanDatabase;
import datamining.BruteForceAssociationRuleMiner;
import datamining.Database;
import representation.Variable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseDataMining {
    protected HouseRepresentation house;
    protected Set<Map<Variable,Object>> solutions;

    public HouseDataMining(HouseRepresentation house, Set<Map<Variable, Object>> solutions) {
        this.house = house;
        this.solutions = solutions;
    }

    public Set<AssociationRule> mining(){
        Set<Variable> setVariable = new HashSet<>();
        setVariable.addAll(this.house.getListeVariable());
        Database database = new Database(setVariable); // On crée notre nouvelle base avec notre liste de variables.
        for(Map<Variable,Object> solution : this.solutions){
            database.add(solution); // On ajoute à cette base toute les instances possibles.
        }
        BooleanDatabase base = database.propositionalize(); // On "convertie" cette base en base boolean.
        BruteForceAssociationRuleMiner bruteForceAssociationRuleMiner = new BruteForceAssociationRuleMiner(base); // On vas extraire les informations avec notre class.
        return bruteForceAssociationRuleMiner.extract(1,2);//Frequence et confiance mise arbitrairement
    }
}

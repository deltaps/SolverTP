package datamining;

import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAssociationRuleMiner {

    protected BooleanDatabase database;

    public AbstractAssociationRuleMiner(BooleanDatabase database){
        this.database = database;
    }

    public BooleanDatabase getDatabase() {
        return database;
    }

    public static float frequency(Set<BooleanVariable> items,Set<Itemset> frequent){
        for(Itemset allItem : frequent){
            if(allItem.getItems().equals(items)){
                return allItem.getFrequency();
            }
        }
        throw new IllegalArgumentException("L'item n'est pas présent dans l'itemset");
    }

    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> frequent){
        Set<BooleanVariable> all = new HashSet<>();
        all.addAll(premise);
        all.addAll(conclusion);
        float frequence = frequency(all,frequent);
        float frequenceOfPremise = frequency(premise,frequent);
        float frequenceFinal = frequence/frequenceOfPremise;
        return frequenceFinal;
    }
}

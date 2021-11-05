package datamining;

import representation.BooleanVariable;

import java.util.*;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {
    protected BooleanDatabase base;

    public BruteForceAssociationRuleMiner(BooleanDatabase base){
        super(base);
    }

    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items){
        // On prend toute les sous liste (sauf la liste vide)
        List<List<BooleanVariable>> sets = new ArrayList<>();
        for (BooleanVariable element : items) {
            for (ListIterator<List<BooleanVariable>> setsIterator = sets.listIterator(); setsIterator.hasNext(); ) {
                List<BooleanVariable> newSet = new ArrayList<>(setsIterator.next());
                newSet.add(element);
                setsIterator.add(newSet);
            }
            sets.add(new ArrayList<>(Arrays.asList(element)));
        }
        // On "converti" en set
        Set<Set<BooleanVariable>> result = new HashSet<>();
        for(List<BooleanVariable> list : sets){
            Set<BooleanVariable> sousList = new HashSet<>();
            for(BooleanVariable all : list){
                sousList.add(all);
            }
            result.add(sousList);
        }
        // On supprime l'ensemble items
        result.remove(items);
        return result;
    }

    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {
        return null;
    }
}

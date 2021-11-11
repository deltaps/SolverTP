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
    //TODO compliqué donc a bien recomprendre (idem pour le extrac de Apriori)
    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence){
        Set<AssociationRule> retourne = new HashSet<>();
        ItemsetMiner apriori = new Apriori(this.getDatabase());
        Set<Itemset> list = apriori.extract(minFrequency); // On récupère tout les item avec une fréquence minimal
        for(Itemset item : list){
            float itemFrequency = item.getFrequency();
            Set<Set<BooleanVariable>> premises = BruteForceAssociationRuleMiner.allCandidatePremises(item.getItems()); // On récupère toute les sous listes de la liset d'items
            for(Set<BooleanVariable> premise : premises){ // On boucle donc sur toutes ces sous liste
                Set<BooleanVariable> conclusion = new HashSet<>(item.getItems()); // On recrée une liste possédant touts les item de notre liste d'item avec frequence minimal
                conclusion.removeAll(premise); // On supprime de cette liste l'item que l'on est en train de regarder (pas besoin de le vérifier avec lui même)
                float itemConfidence = confidence(premise, conclusion, list); // On regarde la confiance de notre item
                if(itemConfidence >= minConfidence){ // Si la confiance est bonne, on ajoute a notre liste la nouvelle règle d'association correspondante.
                    AssociationRule rule = new AssociationRule(premise, conclusion, itemFrequency, itemConfidence);
                    retourne.add(rule);
                }
            }
        }
        return retourne;
    }
}

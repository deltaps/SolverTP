package datamining;

import dataminingtests.ItemsetMinerTests;
import representation.BooleanVariable;

import java.util.*;

public class Apriori extends AbstractItemsetMiner{
    public Apriori(BooleanDatabase base) {
        super(base);
    }

    public Set<Itemset> frequentSingletons(float frequence){
        Set<Itemset> liste = new HashSet<Itemset>();
        for(BooleanVariable item : this.base.getItems()){
            SortedSet<BooleanVariable> listeTrie = new TreeSet<>(COMPARATOR);
            listeTrie.add(item);
            float frequency = frequency(listeTrie);
            if(frequence <= frequency){
                liste.add(new Itemset(listeTrie ,frequency));
            }
        }
        return liste;
    }
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>l1, SortedSet<BooleanVariable>l2) {
        Iterator<BooleanVariable> it1 = l1.iterator();
        Iterator<BooleanVariable> it2 = l2.iterator();
        SortedSet<BooleanVariable> result = new TreeSet<>(COMPARATOR);
        int taille = l1.size() -1;
        if(l1.size() == 0 || l2.size() == 0){
            return null;
        }
        if(l1.size() == l2.size()){
            for(int i = 0; i < taille; i++){
                BooleanVariable item1 = it1.next();
                BooleanVariable item2 = it2.next();
                if(item1.equals(item2)){
                    result.add(item1);
                    result.add(item2);
                }
                else{
                    return null;
                }
            }
            BooleanVariable item1 = it1.next();
            BooleanVariable item2 = it2.next();
            if(item1.equals(item2)){
                return null;
            }
            else{
                result.add(item1);
                result.add(item2);
                return result;
            }
        }
        return null;
    }

    public static boolean allSubsetsFrequent(Set<BooleanVariable> items, Collection<SortedSet<BooleanVariable>> collectionItem){
        Set<BooleanVariable> copieItems = new HashSet<>(items); // Il faut faire une copie sinon on ne peu pas changer dans la boucle la valeur de items
        for(BooleanVariable item : copieItems){
            items.remove(item);
            if(!(collectionItem.contains(items))){
                return false;
            }
            items.add(item);
        }
        return true;
    }
    @Override
    public BooleanDatabase getDataBase() {
        return null;
    }

    @Override
    public Set<Itemset> extract(float minFrequence) {
        Set<Itemset> result = new HashSet<>();
        ArrayList<SortedSet<BooleanVariable>> levelKminus1 = new ArrayList<>();
        Set<Itemset> singletons = new HashSet<>(this.frequentSingletons(minFrequence));
        result.addAll(singletons);
        for(Itemset single : singletons){
            SortedSet<BooleanVariable> convertir = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
            convertir.addAll(single.getItems());
            levelKminus1.add(convertir);
            result.add(single);
        }
        for(int k = 2; k <= this.base.getItems().size(); k++){ // On part de deux car on à déjà fait les singletons
            ArrayList<SortedSet<BooleanVariable>> levelK = new ArrayList<>();
            for(int i = 0; i < levelKminus1.size();i++){
                for(int j = i+1; j < levelKminus1.size();j++){
                    SortedSet<BooleanVariable> liste = this.combine(levelKminus1.get(i),levelKminus1.get(j));
                    if(liste != null && allSubsetsFrequent(liste,levelKminus1)){
                        float frequence = frequency(liste);
                        if(frequence >= minFrequence){
                            result.add(new Itemset(liste,frequence));
                            levelK.add(liste);
                        }
                    }
                }
            }
            levelKminus1 = levelK;
        }
        return result;
    }

}

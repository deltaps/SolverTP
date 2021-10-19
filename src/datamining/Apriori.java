package datamining;

import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

    public SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> items1,SortedSet<BooleanVariable> items2){
        if(items1.size() == items2.size()){
            for(int k = 0; k < items1.size(); k++){

            }
        }
        return null;
    }

    @Override
    public BooleanDatabase getDataBase() {
        return null;
    }

    @Override
    public Set<Itemset> extract(Float frequence) {
        return null;
    }

}

package datamining;

import representation.BooleanVariable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractItemsetMiner implements ItemsetMiner{
    public BooleanDatabase base;
    public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());

    public AbstractItemsetMiner(BooleanDatabase base) {
        this.base = base;
    }

    public BooleanDatabase getBase() {
        return base;
    }

    public float frequency(Set<BooleanVariable> items){
        float frequence = 0;
        for(Set<BooleanVariable> item : this.base.getTransactions()){
            if(item.containsAll(items)){
                frequence++;
            }
        }
        frequence = frequence / this.base.getTransactions().size();
        return frequence;
    }
}

package datamining;

import representation.BooleanVariable;

import java.util.Set;

public class Itemset {
    public Set<BooleanVariable> items;
    public float frequence;

    public Itemset(Set<BooleanVariable> items, float frequence) {
        this.items = items;
        this.frequence = frequence;
    }

    public Set<BooleanVariable> getItems() {
        return items;
    }

    public float getFrequence() {
        return frequence;
    }
}

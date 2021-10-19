package datamining;

import java.util.Set;

public interface ItemsetMiner {
    public BooleanDatabase getDataBase();
    public Set<Itemset> extract(Float frequence);
}

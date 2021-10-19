package datamining;

import representation.BooleanVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BooleanDatabase {
    public Set<BooleanVariable> items;
    public List<Set<BooleanVariable>> transaction;

    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        this.transaction = new ArrayList<>();
    }

    public void add(Set<BooleanVariable> transaction){
        this.transaction.add(transaction);
    }

    public List<Set<BooleanVariable>> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Set<BooleanVariable>> transaction) {
        this.transaction = transaction;
    }
}

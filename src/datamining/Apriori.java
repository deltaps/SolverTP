package datamining;

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
        int taille = l1.size();
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
    /*
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>l1, SortedSet<BooleanVariable>l2) {
        SortedSet<BooleanVariable> result = new TreeSet<>(COMPARATOR);
        if(l1.size() == 0 || l2.size() == 0){
            return null;
        }
        if(l1.size() == l2.size()){
            int taille = l1.size();
            if(!(l1.last().equals(l2.last()))){
                if(taille == 1){
                    result.add(l1.first());
                    result.add(l2.first());
                    return result;
                }
                for(int k = 0; k < taille; k++){
                    if(l1.first().equals(l2.first())){
                        result.add(l1.first());
                        result.add(l2.first());
                        l1.remove(l1.first());
                        l2.remove(l2.first());
                    }
                    else{
                        return null;
                    }
                }
                result.add(l1.first());
                result.add(l2.first());
                return result;
            }
            else{
                System.out.println(3);
                return null;
            }
        }
        System.out.println(4);
        return null;
    }
    /*
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> items1,SortedSet<BooleanVariable> items2){
        SortedSet<BooleanVariable> copieItems1 = items1;
        SortedSet<BooleanVariable> copieItems2 = items2;
        if(items1.size() == 0 || items2.size() == 0){
            return null;
        }
        if(items1.size() == items2.size()){
            SortedSet<BooleanVariable> result = new TreeSet<>(COMPARATOR);
            if(items1.size() == 1){
                if(items1.first() != items2.first()){
                    result.add(items1.first());
                    result.add(items2.first());
                    return result;
                }
            }
            BooleanVariable item1;
            BooleanVariable item2;
            for(int k = 0; k < items1.size(); k++){
                if(copieItems1.first() != copieItems2.first()){
                    return null;
                }
                item1 = copieItems1.first();
                item2 = copieItems2.first();
                result.add(item1);
                result.add(item2);
                copieItems1.remove(item1);
                copieItems2.remove(item2);
                System.out.println(items1.size());
            }
            if(copieItems1.first() != copieItems2.first()){
                result.add(copieItems1.first());
                result.add(copieItems2.first());
                return result;
            }
        }
        return null;
    }
    /*
    public SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> items1,SortedSet<BooleanVariable> items2){
        TreeSet<BooleanVariable> resultat = new TreeSet<>(COMPARATOR);
        if(items1.size() == items2.size()){
            if(items1.last() == items2.last()){
                if(items1.subSet(items1.iterator().next(), items1.last()).equals(items2.subSet(items2.iterator().next(), items2.last()))){
                    resultat.addAll(items1);
                    resultat.addAll(items2);
                    return resultat;
                }
                else{
                    return null;
                }
            }
           else{
               return null;
            }
            /*
            for(int k = 0; k < items1.size(); k++){
                if(items1.first() == items2.first()){
                    items1.remove(k);
                    items2.remove(k);
                }
                else{
                    return null;
                }
            }

        }
        else{
            return null;
        }
    }

     */
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
    public Set<Itemset> extract(Float frequence) {
        return null;
    }

}

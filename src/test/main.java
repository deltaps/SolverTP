package test;

import representation.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class main {
    public static void main(String[] args){
        Map<String,Integer> listenode1 = new HashMap<>();
        listenode1.put("Bonjour1",2);
        listenode1.put("Bonjour2",2);
        listenode1.put("Bonjour3",2);
        listenode1.put("Bonjour4",2);
        Map<String,Integer> listenode2 = new HashMap<>();
        listenode2.put("Bonjour5",1);
        listenode2.put("Bonjour6",1);
        listenode2.put("Bonjour7",1);
        listenode2.put("Bonjour8",1);
        Set<Map<String,Integer>> open = new HashSet<>();
        open.add(listenode1);
        open.add(listenode2);
        Map<String,Integer> instantiation = new HashMap<>();
        int argmin = 999999999;
        for(Map<String,Integer> node : open){
            for(String key : node.keySet()) {
                if(node.get(key) < argmin) {
                    argmin = node.get(key);
                    instantiation.clear();
                    instantiation.putAll(node);
                }
            }
        }
        System.out.println(instantiation);
    }
}

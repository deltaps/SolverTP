package solvers;

import representation.Variable;

import java.util.*;

public class RandomValueHeuristic implements ValueHeuristic{
    protected Random random;

    public RandomValueHeuristic(Random random) {
        this.random = random;
    }

    @Override
    public List<Object> ordering(Variable variable, Set<Object> domain) {
        //retourne une liste de domaine
        ArrayList<Object> liste = new ArrayList<>(domain);
        Collections.shuffle(liste); // J'ai pas tout compris pourquoi Collections, mais random ordonné de liste.
        return liste;
    }
}

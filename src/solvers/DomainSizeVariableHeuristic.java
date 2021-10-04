package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DomainSizeVariableHeuristic implements VariableHeuristic{
    protected boolean plusGrandDomaine;
    public DomainSizeVariableHeuristic(boolean plusGrandDomaine){
        this.plusGrandDomaine = plusGrandDomaine;
    }
    @Override
    public Variable best(Set<Variable> var, Map<Variable, Set<Object>> domaine) {
        Variable resultat = null;
        int meilleureVariable = 0;
        for(Variable eachVar : var){ //On regarde toute les variables
            int tailleVar = (domaine.get(eachVar)).size();
            if((this.plusGrandDomaine && tailleVar > meilleureVariable) || (!this.plusGrandDomaine && (tailleVar < meilleureVariable || resultat == null))){ //On choisie la variable qui est la plus (ou la moins) présentes dans les contraintes (resultat == null pour la première itération)
                resultat = eachVar;
                meilleureVariable = tailleVar;
            }
        }
        return resultat;
    }
}

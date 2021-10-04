package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.Map;
import java.util.Set;

public class NbConstraintsVariableHeuristic implements VariableHeuristic{
    protected Set<Constraint> contraintes;
    protected boolean lePlusDansContrainte;

    public NbConstraintsVariableHeuristic(Set<Constraint> contraintes, boolean affiche) {
        this.contraintes = contraintes;
        this.lePlusDansContrainte = affiche;
    }

    @Override
    public Variable best(Set<Variable> var, Map<Variable, Set<Object>> domaine){
        Variable resultat = null;
        int meilleureVariable = 0;
        for(Variable eachVar : var){ //On regarde toute les variables
            int dansContrainte = 0;
            for(Constraint tmpConstraints : this.contraintes){ // On regarde chaque contraintes, et on vérifie si la variable est dans le scope de la contraintes, auquel cas on incrémente
                if(tmpConstraints.getScope().contains(eachVar)){
                    dansContrainte++;
                }
            }
            if((this.lePlusDansContrainte && dansContrainte > meilleureVariable) || (!this.lePlusDansContrainte && (dansContrainte < meilleureVariable || resultat == null))){ //On choisie la variable qui est la plus (ou la moins) présentes dans les contraintes
                resultat = eachVar;
                meilleureVariable = dansContrainte;
            }
        }
        return resultat;
    }
}

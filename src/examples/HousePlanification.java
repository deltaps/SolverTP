package examples;

import planning.*;
import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

//Le but de la planificationes est de trouver l'ensemble d'action a réaliser pour arriver a la solution trouvé avec notre HouseSolver.
//On vas donc donner en argument notre representation du problème, ainsi que la solution trouvé.
//Nous allons ensuite crée un ensemble d'action a réalisé, et avec un algorithme de recherche et notre solution,
//trouver la liste des action a effectuer pour arrivé à notre solution.
public class HousePlanification {
    protected Set<Action> listeAction;
    protected HouseRepresentation house;
    protected Map<Variable,Object> solution;

    public HousePlanification(HouseRepresentation house, Map<Variable,Object> solution) {
        this.house = house;
        this.listeAction = new HashSet<>();
        this.solution = solution;
    }

    public List<Action> resolve(String typeResolve){
        //Point de départ : Maison vide:
        Map<Variable,Object> depart = new HashMap<>();
        for(Variable var : this.house.getListeVariable()){
            depart.put(var,null);
        }
        //Seulement une maison est vide, mais pour commencer ça construction, il ne faut pas que la dalle soit coulé
        depart.put(house.getDalleCoulee(),false);
        //But
        Goal goal = new BasicGoal(this.solution);
        //Il faut maintenant crée les actions:

        //Couler une dalle:
        Map<Variable,Object> precondition = new HashMap<>();
        Map<Variable,Object> effet = new HashMap<>();
        effet.put(house.dalleCoulee,true);
        effet.put(house.getSolHumide(),true);
        this.listeAction.add(new BasicAction(precondition,effet,1));//Le cout est placé arbitrairement

        //Attendre que le sol soit sec
        precondition = new HashMap<>();
        effet = new HashMap<>();
        precondition.put(house.getDalleCoulee(),true);
        precondition.put(house.getSolHumide(),true);
        effet.put(house.getSolHumide(),false);
        this.listeAction.add(new BasicAction(precondition,effet,3));

        //Elever les murs
        precondition = new HashMap<>();
        effet = new HashMap<>();
        precondition.put(house.getDalleCoulee(),true);
        precondition.put(house.getSolHumide(),false);
        precondition.put(house.getMurElevee(),false);
        effet.put(house.getMurElevee(),true);
        this.listeAction.add(new BasicAction(precondition,effet,1));

        //Terminer la toiture
        precondition = new HashMap<>();
        effet = new HashMap<>();
        precondition.put(house.getDalleCoulee(),true);
        precondition.put(house.getMurElevee(),true);
        precondition.put(house.getToitureTermine(),false);
        effet.put(house.getToitureTermine(),true);
        this.listeAction.add(new BasicAction(precondition,effet,1));

        //Aménager les pièces (toute les action possible pour chaque position recevant n'importe qu'elle pièce)
        for(Variable var : this.house.getListeVariable()){
            for(Object piece : this.house.getDomaine()){
                if(!(var instanceof BooleanVariable)){ // On regarde si la variables est bien une pièce
                    precondition = new HashMap<>();
                    effet = new HashMap<>();
                    precondition.put(this.house.getDalleCoulee(),true);
                    precondition.put(this.house.getSolHumide(),false);// On ne place une pièce seulement si le sol est prêt
                    precondition.put(var,null);// La position ne doit pas déjà contenir une pièce.
                    effet.put(var,piece);// L'effet est donc de donné la pièce dans la position
                    this.listeAction.add(new BasicAction(precondition,effet,0));
                }
            }
        }
        Planner planner;
        switch (typeResolve){
            case "AStarPlanner":
                planner = new AStarPlanner(depart, this.listeAction, goal, new Heuristic() {
                    @Override
                    public float estimate(Map<Variable, Object> state) {
                        int heuristique = 0;
                        for(Variable var : state.keySet()){
                            if(state.get(var) != null){
                                heuristique++; // L'heuristique est égale a chaque variable instancié
                            }
                        }
                        return heuristique;
                    }
                });
                return planner.plan();
            case "DijkstraPlanner":
                planner = new DijkstraPlanner(depart,this.listeAction,goal);
                return planner.plan();
            case "BFSPlanner":
                planner = new BFSPlanner(depart,this.listeAction,goal);
                return planner.plan();
            case "DFSPlanner":
                planner = new DFSPlanner(depart,this.listeAction,goal);
                return planner.plan();

        }
        return null;
    }

}

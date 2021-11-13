package examples;

import representation.Constraint;
import representation.Variable;

import java.util.*;

public class HouseDemo {
    public static void main(String[] args) {

        Set<String> pieceEau = new HashSet<String>();
        pieceEau.add("salle de bain1");
        pieceEau.add("salle de bain2");
        pieceEau.add("salle de bain3");
        pieceEau.add("WC1");
        pieceEau.add("WC2");
        pieceEau.add("WC3");
        pieceEau.add("cuisine");

        Set<String> piecesAutres = new HashSet<String>();
        piecesAutres.add("chambre1");
        piecesAutres.add("chambre2");
        piecesAutres.add("chambre3");
        piecesAutres.add("chambre4");
        piecesAutres.add("chambre5");
        piecesAutres.add("salon");
        piecesAutres.add("buanderie");
        piecesAutres.add("garage");
        piecesAutres.add("salleDeSport");

        HouseExample maison = new HouseExample(2,2,pieceEau,piecesAutres);
        HouseRepresentation maisonRepresentation = new HouseRepresentation(2,2, piecesAutres, pieceEau);
        List<Variable> listeVariables = maisonRepresentation.getListeVariable();
        Set<Constraint> listeContraintes = new HashSet<>(maisonRepresentation.getContrainte());
        Set<Variable> setVariables = new HashSet<>();
        setVariables.addAll(listeVariables);
        HouseSolvers solverMaison = new HouseSolvers(setVariables,listeContraintes);

        Map<Variable,Object> result1 = new HashMap<>();
        solverMaison.macSolve(); //Notre représentation doit être fausse, car tout nos solver ne trouve aucune solution.
        solverMaison.backTrackSolve();
        solverMaison.heuristicAndMacSolve();

        HousePlanification housePlanner = new HousePlanification(maisonRepresentation,solverMaison.macSolve());
        //housePlanner.resolve("AStarPlanner"); //Notre représentation étant fausse, nous ne pouvons pas resolve.

        Set<Map<Variable,Object>> allSolve = new HashSet<>();
        allSolve.add(solverMaison.macSolve());
        allSolve.add(solverMaison.backTrackSolve());
        allSolve.add(solverMaison.heuristicAndMacSolve());
        HouseDataMining houseMining = new HouseDataMining(maisonRepresentation,allSolve);
        //houseMining.mining(); //Encore une fois, notre représentation étant fausse, nous ne pouvons aps mine.
    }
}

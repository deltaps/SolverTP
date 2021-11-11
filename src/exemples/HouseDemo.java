package exemples;

import representation.Constraint;
import representation.Variable;

import java.util.*;

public class HouseDemo {
    public static void main(String[] args) {

        Set<String> pieceEau = new HashSet<String>();
        pieceEau.add("salle de bain1");
        pieceEau.add("salle de bain2");
        pieceEau.add("WC1");
        pieceEau.add("WC2");

        Set<String> piecesAutres = new HashSet<String>();
        piecesAutres.add("chambre1");
        piecesAutres.add("chambre2");
        piecesAutres.add("chambre3");
        piecesAutres.add("chambre4");
        piecesAutres.add("chambre5");
        piecesAutres.add("salon");
        piecesAutres.add("cuisine");
        piecesAutres.add("buanderie");
        piecesAutres.add("couloir1");
        piecesAutres.add("couloir2");
        piecesAutres.add("garage");
        piecesAutres.add("salleDeSport");

        HouseExample maison = new HouseExample(2,2,pieceEau,piecesAutres);
        HouseRepresentation maisonRepresentation = new HouseRepresentation(4,4, piecesAutres, pieceEau);
        List<Variable> listeVariables = maisonRepresentation.getListeVariable();
        Set<Constraint> listeContraintes = new HashSet<>(maisonRepresentation.getContrainte());
        Set<Variable> setVariables = new HashSet<>();
        setVariables.addAll(listeVariables);
        HouseSolvers solverMaison = new HouseSolvers(setVariables,listeContraintes);

        Map<Variable,Object> result1 = new HashMap<>();
        solverMaison.macSolve();
        solverMaison.backTrackSolve();
        solverMaison.heuristicAndMacSolve();

    }
}

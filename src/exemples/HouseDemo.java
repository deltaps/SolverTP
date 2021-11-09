package exemples;

import java.util.HashSet;
import java.util.Set;

public class HouseDemo {
    public static void main(String[] args) {

        Set<String> pieceEau = new HashSet<String>();
        pieceEau.add("salle de bain");
        pieceEau.add("WC");

        Set<String> piecesAutres = new HashSet<String>();
        piecesAutres.add("chambre");
        piecesAutres.add("salon");
        piecesAutres.add("cuisine");

        HouseExample maison = new HouseExample(2,2,pieceEau,piecesAutres);
        HouseRepresentation maisonRepresentation = new HouseRepresentation(5,5, piecesAutres, pieceEau);

    }
}

package examples;

import representation.*;

import java.util.HashSet;
import java.util.Set;

public class HouseExample {

    public int longueur;
    public int largeur;
    public Set<String> piecesEau;
    public Set<String> piecesAutres;

    //public Set<Variable> ensVariables;
    //public Set<Constraint> ensContraints;

    public HouseExample(int longueur, int largeur, Set<String> pieceEau, Set<String> piecesAutres) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.piecesEau = pieceEau;
        this.piecesAutres = piecesAutres;

        //this.ensVariables = new ArrayList<>();
        //this.ensContraints = new ArrayList<>();
    }

    public Set<Variable> getVariables() {
        Set<Variable> set = new HashSet<Variable>();
        for(String str : this.piecesEau) {
            //set.add(new Variable(str, ));
        }
        return set;
    }

    public Set<Constraint> getConstraints() {
        Set<Constraint> set = new HashSet<Constraint>();

        return set;
    }
}

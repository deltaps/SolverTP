package examples;

import representation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseRepresentation {
    protected Variable[][] grille;
    protected Set<String> pieceNormal;
    protected Set<String> pieceEau;
    protected Set<Object> domaine;
    protected Set<Constraint> listeContrainte;
    protected BooleanVariable dalleCoulee;
    protected BooleanVariable solHumide;
    protected BooleanVariable murElevee;
    protected BooleanVariable toitureTermine;
    protected List<Variable> listeVariable;
    protected int i;
    protected int j;
    protected final int[] HAUT = new int[]{-1,0};
    protected final int[] BAS = new int[]{1,0};
    protected final int[] GAUCHE = new int[]{0,-1};
    protected final int[] DROITE = new int[]{0,1};
    public HouseRepresentation(int i, int j, Set<String> pieceNormal, Set<String> pieceEau){
        this.i = i;
        this.j = j;
        this.grille = new Variable[i][j];
        this.pieceEau = pieceEau;
        this.pieceNormal = pieceNormal;
        this.domaine = new HashSet<>();
        this.domaine.addAll(pieceEau);
        this.domaine.addAll(pieceNormal);
        this.listeVariable = new ArrayList<>();
        for(int li = 0; li < i; li++){
            for(int lj = 0; lj < j; lj++){
                this.grille[li][lj] = new Variable("pièce" + li + lj, this.domaine);
                this.listeVariable.add(this.grille[li][lj]);
            }
        }
        this.dalleCoulee = new BooleanVariable("dalleCoulee");
        this.solHumide = new BooleanVariable("solHumide");
        this.murElevee = new BooleanVariable("murElevee");
        this.toitureTermine = new BooleanVariable("toitureTermine");
        this.listeVariable.add(this.dalleCoulee);
        this.listeVariable.add(this.solHumide);
        this.listeVariable.add(this.murElevee);
        this.listeVariable.add(this.toitureTermine);
        this.listeContrainte = new HashSet<>();
        this.makeConstraint();
    }

    public void makeConstraint(){
        //Contrainte pour que chaque pièce soit différentes des autre
        for(int i1 = 0; i1 < this.i; i1++){
            for(int j1 = 0; j1 < this.j; j1++){
                for(int i2 = 0; i2 < this.j; i2++){
                    for(int j2 = 0; j2 < this.j; j2++){
                        Variable var1 = this.grille[i1][j1];
                        Variable var2 = this.grille[i2][j2];
                        //Variable var1 = new Variable("pièce" + i1 + j1,this.domaine);
                        //Variable var2 = new Variable("pièce" + i2 + j2, this.domaine);
                        DifferenceConstraint contrainte = new DifferenceConstraint(var1,var2);
                        this.listeContrainte.add(contrainte);
                    }
                }
            }
        }
        //Contraintes pour que les pièces d'eau soient côte a côte
        for(int i = 0; i < this.i; i++){
            for(int j = 0; j < this.j; j++){
                int[][] voisins = new int[][]{HAUT,BAS,GAUCHE,DROITE};
                for(int[] voisin : voisins){
                    if(j == this.j-1 && voisin == DROITE){
                        continue;
                    }
                    if(i == this.i-1 && voisin == BAS){
                        continue;
                    }
                    if(j == 0 && voisin == GAUCHE){
                        continue;
                    }
                    if(i == 0 && voisin == HAUT){
                        continue;
                    }
                    Variable varActu = this.grille[i][j];
                    Variable varVoisin = this.grille[i + voisin[0]][j + voisin[1]];
                    BinaryExtensionConstraint contrainte = new BinaryExtensionConstraint(varActu,varVoisin);
                    for(Object pieceActu : varActu.getDomain()){
                        for(Object pieceVoisin : varVoisin.getDomain()){
                            String pieceActuString = (String) pieceActu;
                            String pieceVoisinString = (String) pieceVoisin;
                            if(!this.pieceEau.contains(pieceActuString) || !this.pieceEau.contains(pieceVoisinString)){
                               contrainte.addTuple(pieceActuString,pieceVoisinString);
                            }
                        }
                    }
                    this.listeContrainte.add(contrainte);
                }
            }
        }
        //Contrainte pour que toute les étape soit faite les une après les autres
        Constraint contrainte1 = new Implication(this.dalleCoulee,false,this.solHumide,false);
        this.listeContrainte.add(contrainte1);
        Constraint contrainte2 = new Implication(this.dalleCoulee,true,this.solHumide,true);
        this.listeContrainte.add(contrainte2);
        Constraint contrainte3 = new Implication(this.solHumide,true,this.murElevee,false);
        this.listeContrainte.add(contrainte3);
        Constraint contrainte4 = new Implication(this.murElevee,true,this.solHumide,false);
        this.listeContrainte.add(contrainte4);
        Constraint contrainte5 = new Implication(this.murElevee,false,this.toitureTermine,false);
        this.listeContrainte.add(contrainte5);
        Constraint contrainte6 = new Implication(this.toitureTermine,true,this.murElevee,true);
        this.listeContrainte.add(contrainte6);
        Constraint contrainte7 = new Implication(this.murElevee,true,this.dalleCoulee,true);
        this.listeContrainte.add(contrainte7);
    }

    @Override
    public String toString(){
        return "";
    }

    public List<Variable> getListeVariable() {
        return listeVariable;
    }

    public Set<Constraint> getContrainte(){
        return this.listeContrainte;
    }

    public BooleanVariable getDalleCoulee() {
        return dalleCoulee;
    }

    public BooleanVariable getSolHumide() {
        return solHumide;
    }

    public BooleanVariable getMurElevee() {
        return murElevee;
    }

    public BooleanVariable getToitureTermine() {
        return toitureTermine;
    }

    public Set<Object> getDomaine() {
        return domaine;
    }
}

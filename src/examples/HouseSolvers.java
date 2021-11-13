package examples;

import representation.Constraint;
import representation.Variable;
import solvers.*;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HouseSolvers {

    protected Set<Variable> variables;
    protected Set<Constraint> contraintes;

    public HouseSolvers(Set<Variable> variables, Set<Constraint> contraintes) {
        this.variables = variables;
        this.contraintes = contraintes;
    }

    public Map<Variable,Object> backTrackSolve(){
        BacktrackSolver backTrack = new BacktrackSolver(this.variables,this.contraintes);
        return backTrack.solve();
    }

    public Map<Variable,Object> macSolve(){
        MACSolver solver = new MACSolver(this.variables,this.contraintes);
        return solver.solve();
    }

    public Map<Variable,Object> heuristicAndMacSolve(){
        VariableHeuristic varHeuristique = new NbConstraintsVariableHeuristic(this.contraintes,true);
        ValueHeuristic valeurHeuristique = new RandomValueHeuristic(new Random());
        HeuristicMACSolver solver = new HeuristicMACSolver(this.variables,this.contraintes,varHeuristique,valeurHeuristique);
        return solver.solve();
    }


}

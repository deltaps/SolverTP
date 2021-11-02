package datamining;

import representation.BooleanVariable;

import java.util.Set;

public class AssociationRule {

    private Set<BooleanVariable> premise;
    private Set<BooleanVariable> conclusion;
    private float confidence;
    private float frequency;

    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion ,float confidence, float frequency) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.confidence = confidence;
        this.frequency = frequency;
    }

    public Set<BooleanVariable> getPremise() {
        return premise;
    }

    public Set<BooleanVariable> getConclusion() {
        return conclusion;
    }

    public float getConfidence() {
        return confidence;
    }

    public float getFrequency() {
        return frequency;
    }
}

package com.mobiquityinc.model;

import java.util.ArrayList;
import java.util.List;
/***
 * This class is like {@link PackageChoices}
 * The aim of design is to holding possible solution while processing and
 * have totalWeight and totalCost of current solution.
 * having these two attributes avoids duplicated processes.
 * @author Falaki
 */
public class PossibleSolution {
    private float totalWeight;
    private int totalCost;
    private List<PackageItem> selectedPackageItems = new ArrayList<>();

    public float getTotalWeight() {
        return totalWeight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void addToTotalCost(int cost) {
        this.totalCost = totalCost + cost;
    }

    public void addToTotalWeight(float weight) {
        this.totalWeight = totalWeight + weight;
    }

    public List<PackageItem> getSelectedPackageItems() {
        return selectedPackageItems;
    }

}

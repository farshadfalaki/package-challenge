package com.mobiquityinc.model;

/***
 * This class contains each package data
 * @author Falaki
 */
public class PackageItem {
    private int index;
    private float weight;
    private int cost;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PackageItem(int index, float weight, int cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageItem that = (PackageItem) o;
        return index == that.index &&
                Float.compare(that.weight, weight) == 0 &&
                cost == that.cost;
    }


}

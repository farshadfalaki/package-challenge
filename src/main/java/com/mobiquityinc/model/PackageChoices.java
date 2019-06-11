package com.mobiquityinc.model;

import java.util.List;
    /***
    * This class contains list of package data and max weight value
    * @author Falaki
    */
public class PackageChoices {
    private int maxWeight;
    private List<PackageItem> packageItems;

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<PackageItem> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(List<PackageItem> packageItems) {
        this.packageItems = packageItems;
    }
}

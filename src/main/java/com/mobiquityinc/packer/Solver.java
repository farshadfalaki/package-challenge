package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;

import java.util.List;

/**
 * * @author Falaki
 */
public interface Solver {
    List<PackageItem> solve(PackageChoices packageChoices);
}

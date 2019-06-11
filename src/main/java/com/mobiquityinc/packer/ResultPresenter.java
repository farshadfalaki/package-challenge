package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageItem;

import java.util.List;
/**
 * * @author Falaki
 */
public interface ResultPresenter {
    String present(List<PackageItem> packageItems);
}

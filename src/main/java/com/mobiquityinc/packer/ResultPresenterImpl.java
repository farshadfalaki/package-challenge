package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageItem;

import java.util.List;

/**
 * This class is responsible for displaying final solution.
 * * @author Falaki
 */
public class ResultPresenterImpl implements ResultPresenter {
    /**
     *
     * @param packageItems as final solution
     * @return for each item prints packageItem's index and separate them with comma, if list is empty return hyphen character
     */
    public String present(List<PackageItem> packageItems) {
        StringBuilder stringBuilder = new StringBuilder();
        if(packageItems==null || packageItems.size()==0){
            stringBuilder.append("-");
        }else {
            stringBuilder.append(packageItems.get(0).getIndex());
            packageItems.stream().skip(1).forEach(e -> stringBuilder.append(",").append(e.getIndex()));
        }
        return stringBuilder.toString();
    }


}

package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
/**
 * * @author Falaki
 */
public interface LineValidator {
    void isFormatValid(String line) throws APIException;
    void validatePackageItemDataRange(PackageItem packageItem) throws APIException;
    void validatePackageChoicesCheckMaxWeight(int packageChoicesMaxWeight) throws APIException;
    void validatePackageChoicesItemsCount(PackageChoices packageChoices) throws APIException;
}

package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;

/**
 * This class is responsible for check validation rules
 * * @author Falaki
 */
public class LineValidatorImpl implements LineValidator {
    private String validationRegex = "^\\d+\\s*?:\\s*\\(\\s*\\d+\\s*,\\s*\\d*\\.{0,1}\\d+\\s*,\\s*€\\d*\\.{0,1}\\d+\\s*\\).*$";

    /**
     * checks format of whole line
     * @param line as each line content
     * @throws APIException if line content does not match format
     */
    @Override
    public void isFormatValid(String line) throws APIException{
        if(line==null || !line.matches(validationRegex))
            throw new APIException("Not valid format");
    }

    /**
     * check two rules, each packageItem cost and weight should not be be more than 100
     * @param packageItem
     * @throws APIException if any rule checking fails
     */
    @Override
    public void validatePackageItemDataRange(PackageItem packageItem) throws APIException{
        if(packageItem==null)
            throw new APIException("Package is null");
        if(packageItem.getCost()>100)
            throw new APIException("Package cost is more than 100 (" + packageItem.getCost() + ")");
        if(packageItem.getWeight()>100)
            throw new APIException("Package weight is more than 100 (" + packageItem.getWeight() + ")");
    }

    /**
     * check the rule of max package weight should not be be more than 100
     * @param packageChoicesMaxWeight
     * @throws APIException if rule checking fails
     */
    @Override
    public void validatePackageChoicesCheckMaxWeight(int packageChoicesMaxWeight) throws APIException{
        if(packageChoicesMaxWeight>100)
            throw new APIException("Max package weight is more than 100 (" + packageChoicesMaxWeight + ")");
    }

    /**
     * check the rule of max packageItem count should not be be more than 15
     * @param packageChoices
     * @throws APIException if rule checking fails
     */
    @Override
    public void validatePackageChoicesItemsCount(PackageChoices packageChoices) throws APIException{
        if(packageChoices==null)
            throw new APIException("packageChoices is null");
        if(packageChoices.getPackageItems()==null)
            throw new APIException("packageChoices items is null");
        if(packageChoices.getPackageItems().size()>15)
            throw new APIException("More than 15 items in row (" + packageChoices.getPackageItems() + ")");
    }

}

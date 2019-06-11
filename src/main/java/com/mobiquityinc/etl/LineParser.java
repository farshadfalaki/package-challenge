package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
/**
 * * @author Falaki
 */
public interface LineParser {
    PackageChoices parseLine(String line) throws APIException;
}

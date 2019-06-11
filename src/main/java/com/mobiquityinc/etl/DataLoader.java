package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;

import java.util.List;
/**
 * * @author Falaki
 */
public interface DataLoader {
    List<PackageChoices> loadPackageChoices(String filePath) throws APIException;
}

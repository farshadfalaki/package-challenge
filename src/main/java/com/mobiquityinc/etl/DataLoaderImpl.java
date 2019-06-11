package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * This class is responsible to load data from file.
 * If any of the constraints is not met, an APIException with the details
 * will be thrown and exit.
 * @author Falaki
 */
public class DataLoaderImpl implements DataLoader{
    private LineParser lineParser;

    public DataLoaderImpl(){}
    public DataLoaderImpl(LineParser lineParser) {
        this.lineParser = lineParser;
    }

    /**
     * @param filePath as input file
     * @return List<PackageChoices>
     * @throws APIException when the input file path is invalid or when data format is invalid.
     */
    public List<PackageChoices> loadPackageChoices(String filePath) throws APIException {
        if(filePath == null){
            throw new APIException("FilePath is null.");
        }
        List<PackageChoices> packageChoicesList = new ArrayList<>();
       File inputFile = new File(filePath);
        try (Scanner scanner = new Scanner(inputFile,"UTF-8")) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                packageChoicesList.add(lineParser.parseLine(inputLine));
            }
        } catch (IOException e) {
            throw new APIException("Cannot read the input file.", e);
        }
        return packageChoicesList;
    }
}

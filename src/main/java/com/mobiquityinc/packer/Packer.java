package com.mobiquityinc.packer;

import com.mobiquityinc.etl.DataLoader;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;

import java.util.List;

/**
 * This is responsible for solving Package Challenge.
 * The job is divided to 3 parts.
 * 1-load data from file which is done by {@link DataLoader} interface
 * 2-solving problem which is done by {@link Solver} interface
 * 3-preparing final solution data format which is done by {@link ResultPresenter} interface
 ** @author Falaki
 */
public class Packer {
    private static DataLoader dataLoader;
    private static Solver solver;
    private static ResultPresenter resultPresenter;

    /**
     * @param filePath as input file full path
     * @return a String as solution of package problem
     * @throws APIException if input file path or data format are invalid or any constraints fails
     */
    public static String pack(String filePath) throws APIException {
        StringBuilder stringBuilder = new StringBuilder();
        List<PackageChoices> packageChoicesList  = dataLoader.loadPackageChoices(filePath);
        for (PackageChoices packageChoices:packageChoicesList) {
            List<PackageItem> resultPackageItems = solver.solve(packageChoices);
            stringBuilder.append(resultPresenter.present(resultPackageItems));
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }

    public static void setDataLoader(DataLoader dataLoader) {
        Packer.dataLoader = dataLoader;
    }

    public static void setSolver(Solver solver) {
        Packer.solver = solver;
    }

    public static void setResultPresenter(ResultPresenter resultPresenter) {
        Packer.resultPresenter = resultPresenter;
    }
}

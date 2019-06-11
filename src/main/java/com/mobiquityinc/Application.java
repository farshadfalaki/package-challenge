package com.mobiquityinc;

import com.mobiquityinc.etl.DataLoaderImpl;
import com.mobiquityinc.etl.LineParserImpl;
import com.mobiquityinc.etl.LineValidatorImpl;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.ResultPresenterImpl;
import com.mobiquityinc.packer.SolverImpl;

/**
 * Running point of solving Package Challenge.
 * You want to send your friend a package with different things.
 * Each thing you put inside the package has such parameters as index number, weight and cost. The
 * package has a weight limit. Your goal is to determine which things to put into the package so that the
 * total weight is less than or equal to the package limit and the total cost is as large as possible.
 * You would prefer to send a package which weighs less in case there is more than one package with the
 * same price.
 * @author Falaki
 */
public class Application {
    public static void main(String[] args) throws APIException {
        initContext();
        if (args.length == 0) {
            System.out.println("First parameter must be input file path!");
        } else {
            String result = Packer.pack(args[0]);
            System.out.println(result);
        }
    }
    public static void initContext(){
        Packer.setDataLoader(new DataLoaderImpl(new LineParserImpl(new LineValidatorImpl())));
        Packer.setSolver(new SolverImpl()); ;
        Packer.setResultPresenter(new ResultPresenterImpl()); ;
    }

}

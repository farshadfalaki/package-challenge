package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
import com.mobiquityinc.model.PossibleSolution;
import java.util.List;
/*
* An implementation to solve package challenge
 * @author Falaki
 */
public class SolverImpl implements Solver{
    public List<PackageItem> solve(PackageChoices packageChoices) {
        int choicesCount = packageChoices.getPackageItems().size();
        // for 3 packages we have 2^3-1 = 7 possible solution [001,010,011,100,101,110,111]
        // each [0|1] is a flag for presence of that packageItem in current solution
        int conditionsCount = (int) (Math.pow(2,choicesCount)-1);
        PossibleSolution finalSolution = new PossibleSolution();
        for (int i = 1; i <= conditionsCount ; i++) {
            PossibleSolution currentSolution = new PossibleSolution();
            String presenceBitMap = padLeftWithZero(Integer.toBinaryString(i), choicesCount);
            for (int j = 0; j < presenceBitMap.length(); j++) {
                // according to possible solution map , if current index is present (bit flag is '1') in solution , we add it
                if (presenceBitMap.charAt(j) == '1') {
                    currentSolution.getSelectedPackageItems().add(packageChoices.getPackageItems().get(j));
                    currentSolution.addToTotalCost(packageChoices.getPackageItems().get(j).getCost());
                    currentSolution.addToTotalWeight(packageChoices.getPackageItems().get(j).getWeight());
                    if (currentSolution.getTotalWeight() > packageChoices.getMaxWeight()) {
                        // current total weight exceeds from max weight, it means this solution is not correct and we leave it , pass to next solution
                        break;
                    }
                }
            }
            if (currentSolution.getTotalWeight() <= packageChoices.getMaxWeight()) {
                // now this current solution is correct , but in order to find max possible cost , we have to compare with max-cost solution
                // if current solution's cost is more than max solution's , replace max solution with current solution
                if (currentSolution.getTotalCost() > finalSolution.getTotalCost()) {
                    finalSolution = currentSolution;
                }else if (currentSolution.getTotalCost() == finalSolution.getTotalCost()) {
                    // in cases total cost are the same, the solution with less weight is chosen
                    if (currentSolution.getTotalWeight() < finalSolution.getTotalWeight()) {
                        finalSolution = currentSolution;
                    }
                }
             }
        }
        return finalSolution.getSelectedPackageItems();
    }

    /**
     * This method is used left padding string with 0
     * for size 4 if input is 11 , it returns 0011
     * @param input
     * @param size
     * @return padded string with zero. returns null for null input
     */
    public static String padLeftWithZero(String input,int size){
        String paddedInput = input;
        if(input!=null) {
            int delta = size - input.length();
            if (delta > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < delta; i++) {
                    stringBuilder.append("0");
                }
                paddedInput = stringBuilder.toString() + paddedInput;
            }
        }
        return paddedInput;
    }
}

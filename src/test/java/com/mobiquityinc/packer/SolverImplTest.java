package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SolverImplTest {
    private Solver solver = new SolverImpl();
    @Test
    public void solve_case1_shouldReturnPackageItemWithIndex4(){
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setMaxWeight(81);
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,53.38f,45),
                        new PackageItem(2,88.62f,98),
                        new PackageItem(3,78.48f, 3),
                        new PackageItem(4,72.30f,76),
                        new PackageItem(5,30.18f, 9),
                        new PackageItem(6,46.34f,48)
                )
        );
        //When
        List<PackageItem> packageItemList = solver.solve(packageChoices);
        //Then
        Assert.assertEquals(1,packageItemList.size());
        Assert.assertEquals(4,packageItemList.get(0).getIndex());
    }
    @Test
    public void solve_case2_shouldReturnNoPackageItem(){
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setMaxWeight(8);
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,15.3f,34)
                )
        );
        //When
        List<PackageItem> packageItemList = solver.solve(packageChoices);
        //Then
        Assert.assertEquals(0,packageItemList.size());
    }

    @Test
    public void solve_case3_shouldReturnPackageItemWithIndex2And7(){
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setMaxWeight(75);
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,85.31f,29),
                        new PackageItem(2,14.55f,74),
                        new PackageItem(3,3.98f,16),
                        new PackageItem(4,26.24f,55),
                        new PackageItem(5,63.69f,52),
                        new PackageItem(6,76.25f,75),
                        new PackageItem(7,60.02f,74),
                        new PackageItem(8,93.18f,35),
                        new PackageItem(9,89.95f,78)
                )
        );
        //When
        List<PackageItem> packageItemList = solver.solve(packageChoices);
        //Then
        Assert.assertEquals(2,packageItemList.size());
        Assert.assertEquals(2,packageItemList.get(0).getIndex());
        Assert.assertEquals(7,packageItemList.get(1).getIndex());
    }

    @Test
    public void solve_case4_shouldReturnPackageItemWithIndex8And9(){
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setMaxWeight(56);
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,90.72f,13),
                        new PackageItem(2,33.80f,40),
                        new PackageItem(3,43.15f,10),
                        new PackageItem(4,37.97f,16),
                        new PackageItem(5,46.81f,36),
                        new PackageItem(6,48.77f,79),
                        new PackageItem(7,81.80f,45),
                        new PackageItem(8,19.36f,79),
                        new PackageItem(9,6.76f,64)
                )
        );
        //When
        List<PackageItem> packageItemList = solver.solve(packageChoices);
        //Then
        Assert.assertEquals(2,packageItemList.size());
        Assert.assertEquals(8,packageItemList.get(0).getIndex());
        Assert.assertEquals(9,packageItemList.get(1).getIndex());
    }

    @Test
    public void padLeftWithZero_inputLenIs4Size4_shouldReturnSameInput(){
        //Given
        String input = "1010";
        String expected = input;
        int size = 4;
        //When
        String result = SolverImpl.padLeftWithZero(input,size);
        //Then
        Assert.assertEquals(expected,result);
    }

    @Test
    public void padLeftWithZero_inputLenIs4Size6_shouldReturnSameInputWith00Prefixed(){
        //Given
        String input = "1010";
        String expected = "00" + input;
        int size = 6;
        //When
        String result = SolverImpl.padLeftWithZero(input,size);
        //Then
        Assert.assertEquals(expected,result);
    }

    @Test
    public void padLeftWithZero_inputLenIs4Size3_shouldReturnSameInput(){
        //Given
        String input = "1010";
        String expected = input;
        int size = 3;
        //When
        String result = SolverImpl.padLeftWithZero(input,size);
        //Then
        Assert.assertEquals(expected,result);
    }

    @Test
    public void padLeftWithZero_inputLenIs0Size3_shouldReturnSameInput(){
        //Given
        String input = "";
        String expected = "000";
        int size = 3;
        //When
        String result = SolverImpl.padLeftWithZero(input,size);
        //Then
        Assert.assertEquals(expected,result);
    }

    @Test
    public void padLeftWithZero_inputLenIsNullSize3_shouldReturnNull(){
        //Given
        String input = null;
        String expected = null;
        int size = 3;
        //When
        String result = SolverImpl.padLeftWithZero(input,size);
        //Then
        Assert.assertEquals(expected,result);
    }


}

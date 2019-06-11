package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class LineValidatorImplTest {
    private LineValidator lineValidator;

    @Before
    public void init(){
        lineValidator = new LineValidatorImpl();
    }
    @Test(expected = APIException.class)
    public void isFormatValid_withNullString_shouldThrowAPIException() throws APIException {
        //Given
        String input = null;
        //When
        lineValidator.isFormatValid(input);
    }
    @Test(expected = APIException.class)
    public void isFormatValid_withInvalidFormatString_shouldThrowAPIException() throws APIException {
        //Given : € is omitted
        String input = "8 : (1,15.3,34)";
        //When
        lineValidator.isFormatValid(input);
    }
    @Test
    public void isFormatValid_withValidFormatString_nothing() throws APIException {
        //Given : € is omitted
        String input = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36)(6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        //When
        lineValidator.isFormatValid(input);
    }
    @Test(expected = APIException.class)
    public void validatePackageItemDataRange_withNullPackageItem_shouldThrowAPIException() throws APIException {
        //Given
        PackageItem packageItem = null;
        //When
        lineValidator.validatePackageItemDataRange(packageItem);
    }
    @Test(expected = APIException.class)
    public void validatePackageItemDataRange_withPackageItemCostMoreThan100WeightMoreThan100_shouldThrowAPIException() throws APIException {
        //Given
        PackageItem packageItem = new PackageItem(1,101,101);
        //When
        lineValidator.validatePackageItemDataRange(packageItem);
    }
    @Test(expected = APIException.class)
    public void validatePackageItemDataRange_withPackageItemCostMoreThan100WeightLessThan100_shouldThrowAPIException() throws APIException {
        //Given
        PackageItem packageItem = new PackageItem(1,99,101);
        //When
        lineValidator.validatePackageItemDataRange(packageItem);
    }
    @Test(expected = APIException.class)
    public void validatePackageItemDataRange_withPackageItemCostLessThan100WeightMoreThan100_shouldThrowAPIException() throws APIException {
        //Given
        PackageItem packageItem = new PackageItem(1,101,99);
        //When
        lineValidator.validatePackageItemDataRange(packageItem);
    }
    @Test
    public void validatePackageItemDataRange_withPackageItemCostLessThan100WeightLessThan100_shouldThrowAPIException() throws APIException {
        //Given
        PackageItem packageItem = new PackageItem(1,10,99);
        //When
        lineValidator.validatePackageItemDataRange(packageItem);
    }
    @Test(expected = APIException.class)
    public void validatePackageChoicesItemsCount_withNullPackageChoices_shouldThrowAPIException() throws APIException {
        //Given
        PackageChoices packageChoices = null;
        //When
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
    }
    @Test(expected = APIException.class)
    public void validatePackageChoicesItemsCount_withNullPackageItems_shouldThrowAPIException() throws APIException {
        //Given
        PackageChoices packageChoices = new PackageChoices();
        //When
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
    }
    @Test(expected = APIException.class)
    public void validatePackageChoicesItemsCount_withMoreThan15PackageItems_shouldThrowAPIException() throws APIException {
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,9.72f,13),
                        new PackageItem(2,33.80f,40),
                        new PackageItem(3,43.15f,10),
                        new PackageItem(4,1.97f,16),
                        new PackageItem(5,6.81f,36),
                        new PackageItem(6,4.77f,79),
                        new PackageItem(7,1.80f,45),
                        new PackageItem(8,19.36f,79),
                        new PackageItem(9,6.76f,64),
                        new PackageItem(10,6.76f,64),
                        new PackageItem(11,1.1f,64),
                        new PackageItem(12,2.1f,64),
                        new PackageItem(13,3.6f,64),
                        new PackageItem(14,3.7f,64),
                        new PackageItem(15,2.76f,64),
                        new PackageItem(16,1.6f,64)
                )
        );
        //When
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
    }
    @Test
    public void validatePackageChoicesItemsCount_with15PackageItems_shouldThrowAPIException() throws APIException {
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,9.72f,13),
                        new PackageItem(2,33.80f,40),
                        new PackageItem(3,43.15f,10),
                        new PackageItem(4,1.97f,16),
                        new PackageItem(5,6.81f,36),
                        new PackageItem(6,4.77f,79),
                        new PackageItem(7,1.80f,45),
                        new PackageItem(8,19.36f,79),
                        new PackageItem(9,6.76f,64),
                        new PackageItem(10,6.76f,64),
                        new PackageItem(11,1.1f,64),
                        new PackageItem(12,2.1f,64),
                        new PackageItem(13,3.6f,64),
                        new PackageItem(14,3.7f,64),
                        new PackageItem(15,2.76f,64)
                )
        );
        //When
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
    }
    @Test
    public void validatePackageChoicesItemsCount_withLessThan15PackageItems_shouldThrowAPIException() throws APIException {
        //Given
        PackageChoices packageChoices = new PackageChoices();
        packageChoices.setPackageItems(
                Arrays.asList(
                        new PackageItem(1,9.72f,13),
                        new PackageItem(2,33.80f,40),
                        new PackageItem(3,43.15f,10),
                        new PackageItem(4,1.97f,16),
                        new PackageItem(5,6.81f,36),
                        new PackageItem(6,4.77f,79),
                        new PackageItem(7,1.80f,45),
                        new PackageItem(8,19.36f,79)

                        )
        );
        //When
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
    }
    @Test(expected = APIException.class)
    public void validatePackageChoicesCheckMaxWeight_withMoreThan100_shouldThrowAPIException() throws APIException {
        //Given
        int maxWeight = 101;
        //When
        lineValidator.validatePackageChoicesCheckMaxWeight(maxWeight);
    }
    @Test
    public void validatePackageChoicesCheckMaxWeight_with100_nothing() throws APIException {
        //Given
        int maxWeight = 100;
        //When
        lineValidator.validatePackageChoicesCheckMaxWeight(maxWeight);
    }
    @Test
    public void validatePackageChoicesCheckMaxWeight_withLessThan100_nothing() throws APIException {
        //Given
        int maxWeight = 99;
        //When
        lineValidator.validatePackageChoicesCheckMaxWeight(maxWeight);
    }

}

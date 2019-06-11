package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineParserImplTest {
    @Mock
    private LineValidator lineValidator;
    @InjectMocks
    private LineParser lineParser  = new LineParserImpl();

    @Test(expected = APIException.class)
    public void parseLine_withNull_shouldThrowAPIException() throws APIException {
        //Given
        String line = null;
        doThrow(new APIException("line is null")).when(lineValidator).isFormatValid(line);
        //When
        lineParser.parseLine(line);
    }
    @Test(expected = APIException.class)
    public void parseLine_withInvalidFormat_shouldThrowAPIException() throws APIException {
        //Given
        String line = "8 : (1,15.3,34)";
        doThrow(new APIException("Not valid format")).when(lineValidator).isFormatValid(line);
        //When
        lineParser.parseLine(line);
    }
    @Test
    public void parseLine_withValidFormat_shouldReturnAPackageChoicesWith9PackageItemAndMaxWeight56() throws APIException {
        //Given
        String line = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36)(6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        //When
        PackageChoices packageChoices = lineParser.parseLine(line);
        //Then
        Assert.assertEquals(56,packageChoices.getMaxWeight());
        Assert.assertEquals(9,packageChoices.getPackageItems().size());
        Assert.assertEquals(new PackageItem(1,90.72f,13),packageChoices.getPackageItems().get(0));
        Assert.assertEquals(new PackageItem(2,33.80f,40),packageChoices.getPackageItems().get(1));
        Assert.assertEquals(new PackageItem(3,43.15f,10),packageChoices.getPackageItems().get(2));
        Assert.assertEquals(new PackageItem(4,37.97f,16),packageChoices.getPackageItems().get(3));
        Assert.assertEquals(new PackageItem(5,46.81f,36),packageChoices.getPackageItems().get(4));
        Assert.assertEquals(new PackageItem(6,48.77f,79),packageChoices.getPackageItems().get(5));
        Assert.assertEquals(new PackageItem(7,81.80f,45),packageChoices.getPackageItems().get(6));
        Assert.assertEquals(new PackageItem(8,19.36f,79),packageChoices.getPackageItems().get(7));
        Assert.assertEquals(new PackageItem(9,6.76f,64),packageChoices.getPackageItems().get(8));
        verify(lineValidator,times(1)).isFormatValid(line);
        verify(lineValidator,times(1)).validatePackageChoicesItemsCount(packageChoices);
        verify(lineValidator,times(1)).validatePackageChoicesCheckMaxWeight(packageChoices.getMaxWeight());
        verify(lineValidator,times(9)).validatePackageItemDataRange(any());

    }

}

package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataLoaderImplTest {
    @Mock
    private LineParser lineParser;
    @InjectMocks
    private DataLoader dataLoader  = new DataLoaderImpl();

    @Test(expected = APIException.class)
    public void loadPackageChoices_nullFilePath_shouldThrowAPIException() throws APIException {
        //Given
        String fileName = null;
        //When
        dataLoader.loadPackageChoices(fileName);
        verify(lineParser,never()).parseLine(any());
    }

    @Test(expected = APIException.class)
    public void loadPackageChoices_invalidFilePath_shouldThrowAPIException() throws APIException {
        //Given
        String fileName = "x:/sometxt.txt";
        //When
        dataLoader.loadPackageChoices(fileName);
        verify(lineParser,never()).parseLine(any());
    }

    @Test
    public void loadPackageChoices_validFile_shouldReturnListWith3PackageChoices() throws APIException {
        //Given
        String fileName = "src/test/resources/sampleTestCases.txt";
        String line1 = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9)(6,46.34,€48)";
        String line2 = "8 : (1,15.3,€34)";
        String line3 = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52)(6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
        String line4 = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36)(6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        //When
        List<PackageChoices> packageChoicesList = dataLoader.loadPackageChoices(fileName);
        Assert.assertEquals(4,packageChoicesList.size());
        verify(lineParser,times(1)).parseLine(line1);
        verify(lineParser,times(1)).parseLine(line2);
        verify(lineParser,times(1)).parseLine(line3);
        verify(lineParser,times(1)).parseLine(line4);
    }
}

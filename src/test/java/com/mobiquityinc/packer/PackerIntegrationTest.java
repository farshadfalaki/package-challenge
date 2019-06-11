package com.mobiquityinc.packer;

import com.mobiquityinc.Application;
import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PackerIntegrationTest {
    @Before
    public void init(){
        Application.initContext();
    }
    @Test
    public void packIt_validFilePathWith4Lines_shouldReturn4Lines4AndHyphenAndTwoCommaSevenAndEightCommaNine() throws APIException {
        //Given
        String filePath = "src/test/resources/sampleTestCases.txt";
        String expectedResponse = "4\n\r-\n\r2,7\n\r8,9\n\r";
        //when
        String result = Packer.pack(filePath);
        //then
        Assert.assertEquals(expectedResponse,result);
    }
}

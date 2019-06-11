package com.mobiquityinc.packer;

import com.mobiquityinc.model.PackageItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ResultPresenterImplTest {
    private ResultPresenter resultPresenter = new ResultPresenterImpl();

    @Test
    public void present_withNullList_shouldReturnHyphenCharacter() {
        //Given
        List<PackageItem> packageItemsList = null;
        //When
        String result = resultPresenter.present(packageItemsList);
        //Then
        Assert.assertEquals("-",result);
    }
    @Test
    public void present_withEmptyList_shouldReturnHyphenCharacter() {
        //Given
        List<PackageItem> packageItemsList = new ArrayList<>();
        //When
        String result = resultPresenter.present(packageItemsList);
        //Then
        Assert.assertEquals("-",result);
    }
    @Test
    public void present_withListContainingOneElementIndex3_shouldReturn3() {
        //Given
        List<PackageItem> packageItemsList = new ArrayList<>();
        packageItemsList.add(new PackageItem(3,13.20f,40));
        //When
        String result = resultPresenter.present(packageItemsList);
        //Then
        Assert.assertEquals("3",result);
    }

    @Test
    public void present_withListContainingTwoElementIndex2And6_shouldReturn2Comma6() {
        //Given
        List<PackageItem> packageItemsList = new ArrayList<>();
        packageItemsList.add(new PackageItem(2,33.80f,40));
        packageItemsList.add(new PackageItem(6,43.80f,20));
        //When
        String result = resultPresenter.present(packageItemsList);
        //Then
        Assert.assertEquals("2,6",result);
    }

}

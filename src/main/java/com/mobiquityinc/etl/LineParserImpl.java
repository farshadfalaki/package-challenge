package com.mobiquityinc.etl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.PackageChoices;
import com.mobiquityinc.model.PackageItem;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible to parsing each line
 * @author Falaki
 */
public class LineParserImpl implements LineParser{
    private LineValidator lineValidator;

    public LineParserImpl(LineValidator lineValidator) {
        this.lineValidator = lineValidator;
    }
    public LineParserImpl(){}

    private static Pattern itemRegex = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),(\\d+)\\)");

    /**
     *
     * @param line as each line data
     * @return PackageChoices that contains max weight of package and a list of {@link PackageItem}
     * @throws APIException if line content has invalid format or any validation rules fails
     */
    @Override
    public PackageChoices parseLine(String line) throws APIException {
        lineValidator.isFormatValid(line);
        String parts[] = line.replaceAll("[ €]", "").split(":");
        PackageChoices packageChoices = new PackageChoices();
        // Parse and check max package weight constraint
        int maxPackageWeight = Integer.parseInt(parts[0]);
        packageChoices.setMaxWeight(maxPackageWeight);
        lineValidator.validatePackageChoicesCheckMaxWeight(maxPackageWeight);
        // Create new list to store the package items
        List<PackageItem> items = new ArrayList<>();
        Matcher matcher = itemRegex.matcher(parts[1]);
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            float weight = Float.parseFloat(matcher.group(2));
            int cost = Integer.parseInt(matcher.group(3));
            PackageItem packageItem = new PackageItem(index,weight,cost);
            lineValidator.validatePackageItemDataRange(packageItem);
            items.add(packageItem);
        }
        packageChoices.setPackageItems(items);
        lineValidator.validatePackageChoicesItemsCount(packageChoices);
        return packageChoices;
    }
}

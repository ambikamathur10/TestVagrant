package org.example.utils;

import org.testng.Assert;

public class DataComparator {

    public void compareTemperature(String frontendTemperature, String backendTemperature) {
        Assert.assertEquals(getTemperatureInCelsius(backendTemperature), frontendTemperature.substring(0,
                frontendTemperature.length() - 2), "Compare if the current temperature displayed on UI " +
                "is same as received via API response");
    }


    private String getTemperatureInCelsius(String temp) {
        float kelvin = Float.parseFloat(temp);
        float celsius = kelvin - 273.15F;
        return String.valueOf(Math.round(celsius));
    }

    public boolean variance(String frontendTemperature, String backendTemperature) throws VarianceBreachException {
        int tempGui=Integer.valueOf(frontendTemperature.substring(0,
                frontendTemperature.length() - 2));
        int tempApi=Integer.valueOf(getTemperatureInCelsius(backendTemperature));
        if(tempApi>=tempGui && (tempApi-tempGui<=2) )
        {
            return true;
        }else if(tempApi<=tempGui && (tempGui-tempApi<=2))
        {
            return true;
        }
        throw  new VarianceBreachException("Variance range breached - Temp difference is more than 0-2");
    }
}

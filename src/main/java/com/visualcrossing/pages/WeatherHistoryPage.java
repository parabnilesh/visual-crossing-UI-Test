package com.visualcrossing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WeatherHistoryPage {
	
	private final WebDriver driver;
    private final By forecastLink = By.linkText("15-Day Forecast");

    public WeatherHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public WeatherForecastPage clickForecastLink() {
        driver.findElement(forecastLink).click();
        return new WeatherForecastPage(driver);
    }

}

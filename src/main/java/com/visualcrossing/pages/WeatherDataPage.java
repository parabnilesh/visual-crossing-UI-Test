package com.visualcrossing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WeatherDataPage {
	
	private final WebDriver driver;
    private final By locationInput = By.id("wxlocation");
    private final By searchButton = By.xpath("//button[text()='Search']");

    public WeatherDataPage(WebDriver driver) {
        this.driver = driver;
    }

    public WeatherHistoryPage searchForLocation(String location) {
        driver.findElement(locationInput).sendKeys(location);
        driver.findElement(searchButton).click();
        return new WeatherHistoryPage(driver);
    }

}

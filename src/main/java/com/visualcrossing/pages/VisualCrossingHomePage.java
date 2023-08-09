package com.visualcrossing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.visualcrossing.util.ReadPropertiesUtil;

public class VisualCrossingHomePage {
	
	private final WebDriver driver;
	private final By acceptCookiesButton = By.xpath("//button[text()='Accept all cookies']");
    private final By weatherDataMenuLink = By.linkText("Weather Data");

    public VisualCrossingHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(ReadPropertiesUtil readPropertiesUtil) {
        driver.get(readPropertiesUtil.getUrl());
    }
    
    public void clickAcceptCookiesButton() {
        driver.findElement(acceptCookiesButton).click();
    }

    public WeatherDataPage clickWeatherDataMenu() {
        driver.findElement(weatherDataMenuLink).click();
        return new WeatherDataPage(driver);
    }

}

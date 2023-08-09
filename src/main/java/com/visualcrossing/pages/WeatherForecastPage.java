package com.visualcrossing.pages;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeatherForecastPage {
	
	private final WebDriver driver;
	WebDriverWait wait ;
	LocalDate currentDate = LocalDate.now();
	private final By weatherForecastHeaderText = By.xpath("//div[@id='weatherSummary']//div[@class='d-flex align-items-center']//h3");
	private final By kpiSummaryTemp = By.xpath("//div[@id='weatherSummary']//div[contains(text(), 'Temp')]//following-sibling::div");
	private final By kpiSummaryFeelsLike = By.xpath("//div[contains(text(), 'Feels')]/following-sibling::div");
	private final By kpiSummaryRain = By.xpath("//div[@class='current maingrid']//div[contains(text(), 'Rain')]/following-sibling::div");
	private final By kpiSummaryWind = By.xpath("//div[@class='current maingrid']//div[contains(text(), 'Wind')]/following-sibling::div");
	private final By btnFahrenheit = By.xpath("//button[text()='F']");
	private final By outlookPanels = By.xpath("//div[@class='widget threecols']//div[@class='maingrid']");
	private final By outlookDates = By.xpath("//div[@class='widget threecols']//div[@class='period']");
	
    public WeatherForecastPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public String getWeatherForecastHeaderText() {
    	return driver.findElement(weatherForecastHeaderText).getText();
    }

    public boolean isForecastDisplayedForLocation(String location) {
    	String headerToBeValidated = "Weather Forecast for " + location;
    	return headerToBeValidated.equalsIgnoreCase(getWeatherForecastHeaderText());
    }
    
    public String getChars(By element,int numberOfChars) {
    	WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
    	String convertedText = ele.getText();
    	return convertedText.substring(convertedText.length()-numberOfChars);
    }
    
    public String getKpiSummaryTempValue() {
    	return getChars(kpiSummaryTemp,1);
    }
    public String getKpiSummaryFeelsLike() {
    	return getChars(kpiSummaryFeelsLike,1);
    }
    public String getKpiSummaryRain() {
    	return getChars(kpiSummaryRain,2);
    }
    public String getKpiSummaryWind() {
    	return getChars(kpiSummaryWind,3);
    }
    public void clickBtnFahrenheit() {
    	driver.findElement(btnFahrenheit).click();
    }
    public List<WebElement> getOutlookPanels() {
    	return driver.findElements(outlookPanels);
    }
    public List<WebElement> getOutlookDates() {
    	return driver.findElements(outlookDates);
    }
    public String dateFormatter(LocalDate date) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy");
        return formatter.format(date);
    }
    public String displayCurrentDate() {
        return dateFormatter(currentDate);
    }
    public String addDaysToDate(int days) {
    	return dateFormatter(currentDate.plusDays(days));
    }

}

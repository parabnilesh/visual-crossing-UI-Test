package com.visualcrossing.tests;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.visualcrossing.pages.VisualCrossingHomePage;
import com.visualcrossing.pages.WeatherDataPage;
import com.visualcrossing.pages.WeatherForecastPage;
import com.visualcrossing.pages.WeatherHistoryPage;
import com.visualcrossing.util.ReadPropertiesUtil;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class VisualCrossingUITest {
	
	private WebDriver driver;
	ReadPropertiesUtil readPropertiesUtil =  new ReadPropertiesUtil();
	VisualCrossingHomePage homePage;
    WeatherDataPage weatherPage;
    WeatherHistoryPage historyPage;
    WeatherForecastPage forecastPage;
    private String location = readPropertiesUtil.getLocation();

   
	@BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		homePage = new VisualCrossingHomePage(driver);
		homePage.open(readPropertiesUtil);
		homePage.clickAcceptCookiesButton();
    }

    //Test to check the weather forecast is displayed for the entered city
    @Test(priority = 1)
    public void testWeatherForecast() {
        weatherPage = new VisualCrossingHomePage(driver).clickWeatherDataMenu();
        historyPage = weatherPage.searchForLocation(location);
        forecastPage = historyPage.clickForecastLink();
        boolean isForecastDisplayed = forecastPage.isForecastDisplayedForLocation(location);
        forecastPage.getWeatherForecastHeaderText();
        Assert.assertTrue(isForecastDisplayed, "Forecast for " + location + " is not displayed.");
    }
    
    //Test to check the default value of the "Temp" field when "C" (Celsius) is selected by default
    @Test(enabled=true, priority = 2)
    public void testDefaultValueOfTemp() {
    	forecastPage = new WeatherForecastPage(driver);
    	Assert.assertEquals(forecastPage.getKpiSummaryTempValue(), "℃");
    }
    
    //Test to check the default value of the "Feels like" field when "C" (Celsius) is selected by default
    @Test(enabled=true, priority = 3)
    public void testDefaultValueOfFeelsLike() {
    	Assert.assertEquals(forecastPage.getKpiSummaryFeelsLike(), "℃");
    }
    
    //Test to check the default value of the "Rain" field when "C" (Celsius) is selected by default
    @Test(enabled=true, priority = 4)
    public void testDefaultValueOfRain() {
    	Assert.assertEquals(forecastPage.getKpiSummaryRain(), "mm");
    }
    
    //Test to check the default value of the "Wind" field when "C" (Celsius) is selected by default
    @Test(enabled=true, priority = 5)
    public void testDefaultValueOfWind() {
    	Assert.assertEquals(forecastPage.getKpiSummaryWind(), "kph");
    }
    
    //Test to check the values of the "Temp, Feels like, Rain, Wind" fields when "F" (Fahrenheit) is selected by the user
    @Test(priority = 6)
    public void testValuesAfterClickingBtnFahrenheit() {
    	forecastPage.clickBtnFahrenheit();
    	Assert.assertEquals(forecastPage.getKpiSummaryTempValue(), "℉","Error "+forecastPage.getKpiSummaryTempValue());
    	Assert.assertEquals(forecastPage.getKpiSummaryFeelsLike(), "℉","Error "+forecastPage.getKpiSummaryFeelsLike());
    	Assert.assertEquals(forecastPage.getKpiSummaryRain(), "in","Error "+forecastPage.getKpiSummaryRain());
    	Assert.assertEquals(forecastPage.getKpiSummaryWind(), "mph","Error "+forecastPage.getKpiSummaryWind());
    }
    
    //Test to check the count of panels under 3 Day outlook
    @Test(priority = 7)
    public void testOutlookPanelsCount() {
    	List<WebElement> outlookPanels = forecastPage.getOutlookPanels();
    	Assert.assertEquals(outlookPanels.size(), 3);   
    }
    
    //Test to verify the dates from the 3 Day outlook panels. Should be consecutive and starting from current date
    @Test(priority = 8)
    public void testOutlookDates() {
    	List<WebElement> outlookDates = forecastPage.getOutlookDates();
    	int i=0;
    	for(WebElement date : outlookDates) {
    		Assert.assertEquals(date.getText(), forecastPage.addDaysToDate(i));
    		i++;	
    	}
    }
    

    
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

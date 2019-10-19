package com.epam.automation.driver.decorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class CustomDriverDecorator implements WebDriver {
	private final Logger logger = Logger.getLogger(CustomDriverDecorator.class);

	protected WebDriver driver;

	public CustomDriverDecorator(WebDriver driver) {
		this.driver = driver;
	}

	public void get(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		logger.info("Current page title is: " + driver.getTitle());
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public WebElement findElement(By by) {
		logger.debug(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()));
		return driver.findElement(by);
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		logger.info("Closing browser and ending the session.");
		driver.quit();
		destroyDriver();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public Options manage() {
		return driver.manage();
	}

	private void destroyDriver() {
		driver = null;
	}
}

package com.juaracoding.swaglabs.screens;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginScreen { 
  private AndroidDriver driver; 

  private By username = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
  private By password = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
  private By button = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]");
  private By errMessageContainer = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]");
  
  public LoginScreen(AndroidDriver driver) {
    this.driver = driver;
  }

  public void setUsername(String username) {
    driver.findElement(this.username).sendKeys(username);
  }

  public void setPassword(String password) {
    driver.findElement(this.password).sendKeys(password);
  }

  public void click() {
    driver.findElement(button).click();
  }

  public void login(String username, String password) {
    setUsername(username);
    setPassword(password);
    click();
  }

  public void login() {
    login("standard_user", "secret_sauce");
  }
  
  public String getErrMessage() {
    try {
      By errText = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]//android.widget.TextView");
      return driver.findElement(errText).getText();
    } catch (Exception e) {
      return null; 
    }
  }

}

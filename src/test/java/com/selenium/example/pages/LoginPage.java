package com.selenium.example.pages;

import org.openqa.selenium.By;

public class LoginPage extends DriverActions {
    DriverActions driverActions = new DriverActions();
    public static By userName = By.id("username");

    public void doLogin(){
       driverActions.sendKeys(userName, "sudha");
    }

}

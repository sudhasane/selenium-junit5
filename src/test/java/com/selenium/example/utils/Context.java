package com.selenium.example.utils;

import com.selenium.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class Context extends DriverManager {
    private LoginPage loginPage;

    public Context() {

    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

}

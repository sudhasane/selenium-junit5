package com.selenium.example.steps;

import com.selenium.example.utils.Context;
import io.cucumber.java.en.Given;
import com.selenium.example.pages.DriverActions;
import com.selenium.example.pages.LoginPage;

public class TestSteps extends DriverActions {
    Context context;
    LoginPage loginPage;

    public TestSteps(Context context){
        this.context = context;
        loginPage = context.getLoginPage();
    }


    @Given("the Acme login page is displayed")
    public void theAcmeLoginPageIsDisplayed() {
        loginPage.doLogin();

    }
}

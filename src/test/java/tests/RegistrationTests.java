package tests;

import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }


    @Test(groups = {"smoke"})
    public void regPositiveTest() {

//        1. open login/registration form
        app.getUser().openLoginRegistrationForm();

//        2. fill login/registration form
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "@mail.com";
        String password = "$Abcd1234";
        app.getUser().fillLoginRegistrationForm(email, password);

//        3. submit by click registration button
        app.getUser().submitRegistration();

//        4. assert
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[text()='ADD']")));
    }

    @Test(dataProvider = "regPositiveTestDtoCSV", dataProviderClass = ProviderData.class)
    public void regPositiveTestCSV(User data) {
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(data);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[text()='ADD']")));
    }


    @Test
    public void registrationWrongEmail() {
//        1. open login/registration form
        app.getUser().openLoginRegistrationForm();

//        2. fill login/registration form

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "mail.com";
        String password = "$Abcd1234";
        logger.info("registrationWrongEmail starts with " + email + " & " + password);
        app.getUser().fillLoginRegistrationForm(email, password);

//        3. submit by click registration button
        app.getUser().submitRegistration();

//        4. assert
        Assert.assertTrue(app.getUser().isAlertTextCorrect("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
//
//    @AfterMethod(alwaysRun = true)
//    public void postCondition() {
//    }
}

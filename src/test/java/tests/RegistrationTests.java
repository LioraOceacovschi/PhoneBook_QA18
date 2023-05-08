package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{


    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }



    @Test
    public void regPositiveTest(){

//        1. open login/registration form
        app.getUser().openLoginRegistrationForm();

//        2. fill login/registration form
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "@mail.com";
        String password = "$Abcd1234";
        app.getUser().fillLoginRegistrationForm(email, password);

//        3. submit by click registration button
        app.getUser().submitRegistration();

//        4. assert
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[text()='ADD']")));
    }


    @Test
    public void registrationWrongEmail(){
//        1. open login/registration form
        app.getUser().openLoginRegistrationForm();

//        2. fill login/registration form

        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "mail.com";
        String password = "$Abcd1234";
        logger.info("registrationWrongEmail starts with " + email + " & " + password);
        app.getUser().fillLoginRegistrationForm(email, password);

//        3. submit by click registration button
        app.getUser().submitRegistration();

//        4. assert
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @AfterMethod
    public void tearDown() {
//        wd.quit();
    }
}

package tests;

import manager.NGListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(NGListener.class)
public class LoginTests extends TestBase {

    @Test(invocationCount = 3,groups = {"smoke"})
    public void loginPositiveTest() {
        User user = User.builder()
                .email("abc@def.com")
                .password("$Abcdef12345")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test(groups = {"smoke","regress"})
    public void loginNegativeTestWrongEmail() {
        User user = User.builder()
                .email("abcdef.com")
                .password("$Abcdef12345")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
    }
    @Test(groups = {"regress"})
    public void loginNegativeTestWrongPassword() {
        User user = User.builder()
                .email("abc@def.com")
                .password("Abcdef12345")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
    }

}

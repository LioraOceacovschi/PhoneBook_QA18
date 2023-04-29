package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
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

    @Test
    public void loginNegativeTestWrongEmail() {
        User user = User.builder()
                .email("abcdef.com")
                .password("$Abcdef12345")
                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
    }

}

package tests;

import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(NGListener.class)
public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(groups = {"smoke"},dataProvider = "loginModelDto",dataProviderClass = ProviderData.class)
    public void loginPositiveTest(User data) {
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(data);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLogged());
    }
    @Test
    public void loginPositiveTestConfig() {
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(app.getEmail(), app.getPassword());
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
        Assert.assertTrue(app.getUser().isAlertTextCorrect("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());
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
        Assert.assertTrue(app.getUser().isAlertTextCorrect("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}

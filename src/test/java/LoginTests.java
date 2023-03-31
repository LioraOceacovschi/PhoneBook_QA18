import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(isLogged()){
            logout();
        }
    }

    @Test
    public void loginPositiveTest(){
        String email = "abc@def.com";
        String password = "$Abcdef12345";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email, password);
        submitLogin();
        Assert.assertTrue(isLogged());
    }

    @Test
    public void loginNegativeTest1(){

        String email = "abcdef.com";
        String password = "$Abcdef12345";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email, password);
        submitLogin();
    }

    @Test
    public void loginNegativeTest2(){

        String email = "abc@def.com";
        String password = "Abcdef12345";
        openLoginRegistrationForm();
        fillLoginRegistrationForm(email, password);
        submitLogin();
    }


}

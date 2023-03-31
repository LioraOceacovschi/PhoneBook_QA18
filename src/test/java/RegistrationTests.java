import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{


    @BeforeMethod
    public void preCondition(){
        if(isLogged()){
            logout();
        }
    }
    @Test
    public void regPositiveTest(){

//        1. open login/registration form
        openLoginRegistrationForm();

//        2. fill login/registration form
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "@mail.com";
        String password = "$Abcd1234";
        fillLoginRegistrationForm(email, password);

//        3. submit by click registration button
        submitRegistration();

//        4. assert
        Assert.assertTrue(wd.findElement(By.xpath("//a[text()='ADD']")).getText().equals("ADD"));
    }


    @Test
    public void registrationWrongEmail(){
//        1. open login/registration form
        openLoginRegistrationForm();
//        2. fill login/registration form
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        String email = "name" + i + "@mail.c";
        String password = "$Abcd1234";
        fillLoginRegistrationForm(email,password);

//        3. submit by click registration button
        submitRegistration();
//        4. assert
//        Assert.assertTrue(wd.findElement(By.xpath("//a[text()='ADD']")).getText().equals("ADD"));


    }

    @AfterMethod
    public void tearDown() {
//        wd.quit();
    }
}

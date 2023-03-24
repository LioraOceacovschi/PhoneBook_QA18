import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class RegistrationTests {

    WebDriver wd;



    @BeforeMethod
    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://telranedu.web.app/home");

          //Implicit wait:
        //  1 . wait for WebElement
            //  wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //  2. wait for loading page
            //  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);


    }


    @Test
    public void regPositiveTest(){

        // 1. open login/registration form
        WebElement loginBtn = wd.findElement(By.xpath("//a[@href='/login']"));
        loginBtn.click();

        // 2. fill login/registration form
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("lso"+i+"@abc.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("$Abcd1234");


        // 3. submit by click registration button
        wd.findElement(By.xpath("//button[2]")).click();


       // pause(5);
        // 4. assert
        //Assert.assertTrue(wd.findElement(By.xpath("//a[text()='ADD']")).getText().equals("ADD"));

        // Explicit wait
        WebElement elementAdd = (new WebDriverWait(wd,5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='ADD']"))));
        Assert.assertTrue(elementAdd.getText().equals("ADD"));

    }

    public void pause(int time){
        // Wait  Option 1 
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        // Wait  Option 2
//        FluentWait<WebDriver> wait = new FluentWait<>(wd);
//        wait.withTimeout(Duration.ofSeconds(time));

    }

    @AfterMethod
    public void tearDown(){
    //    wd.quit();
    }

}

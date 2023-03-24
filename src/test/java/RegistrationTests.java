import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationTests {

    WebDriver wd;

    @BeforeMethod
    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://telranedu.web.app/home");
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

        // 4. assert
        Assert.assertTrue(wd.findElement(By.xpath("//a[text()='ADD']")).getText().equals("ADD"));


    }

    //HW_8 Создать 2 негативных
    // тестовых метода (неверный email, неверный password) на регистрацию в классе RegistrationTests

    @Test
    public void registrationWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        wd.findElement(By.xpath("//a[@href='/login']")).click();
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("ab" + i + "@m.r");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("ab@m.r");

        wd.findElement(By.xpath("//button[2]")).click();
    }
    @Test
    public void registrationWrongPass(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        wd.findElement(By.xpath("//a[@href='/login']")).click();
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("abcde" + i + "@mail.ru");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("1234");

        wd.findElement(By.xpath("//button[2]")).click();
    }


    @AfterMethod
    public void tearDown(){
    //    wd.quit();
    }

}

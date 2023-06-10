package manager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    // WebDriver wd;
    String browser;
    EventFiringWebDriver wd;
    HelperUser user;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    Properties properties;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        // wd = new ChromeDriver();
        String config = System.getProperty("configuration","config");
        properties.load( new FileReader(new File(String.format("src/test/resources/%s.properties",config))));
        if (browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Testing on Chrome Driver");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Testing on Firefox Driver");
        }
        wd.register(new MyListener());
        user = new HelperUser(wd);
       // wd.navigate().to("https://telranedu.web.app/home");
        wd.navigate().to(properties.getProperty("web.baseUrl"));
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void stop() {
         // wd.quit();
    }

    public HelperUser getUser() {
        return user;
    }

    public String getEmail(){
        return properties.getProperty("web.email");
    }
    public String getPassword(){
        return properties.getProperty("web.password");
    }
}

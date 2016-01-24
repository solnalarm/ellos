package pageFactory;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by reford on 17.01.16.
 */
public class PageFactoryLoginTest {

    WebDriver driver;
    PageFactoryLoginPage pageFactoryLoginPage;
    PageFactoryMainPage pageFactoryMainPage;

    //@Before
    public void setUp(){
        driver = new FirefoxDriver();
        System.out.println("Browser open successfull");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.ellos.se/");
        System.out.println("Start test");
    }

    //@Test

    public  void  test1() throws IOException {
        pageFactoryMainPage = new PageFactoryMainPage(driver);
        pageFactoryMainPage.clickLogo();
        pageFactoryMainPage.switchToLoginPage();

        pageFactoryLoginPage = new PageFactoryLoginPage(driver);
        pageFactoryLoginPage.fillEmailField("admin@gmail.com");
        pageFactoryLoginPage.fillPasswordField("Password01");
        pageFactoryLoginPage.pressLoginButton();

        Assert.assertTrue("Error is Not present", pageFactoryLoginPage.checkErrorShown());
    }

        //@After

        public void tearDown(){
        System.out.println("End test");
        driver.quit();

    }

}

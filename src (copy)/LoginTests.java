import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by reford on 17.01.16.
 */
public class LoginTests {

    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println("Browser open successful");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//magic
        driver.get("http://www.ellos.se/");
        System.out.println("Start test");

    }

    //@Test
    public  void negativeLogin() throws Exception{
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("admin@gmail.com");
        loginPage.fillPasswordfield("Password01");

        loginPage.pressLoginButton();

             Assert.assertTrue("Incorrect login to the system with fake log/pass",
                     loginPage.checkErrorShown("ErrorMess"));

    }

   // @Test
    public  void pozitiveLogin() throws Exception{
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("testsolnalarm@yandex.ru");
        loginPage.fillPasswordfield("1ElloS");

        loginPage.pressLoginButton();

        Assert.assertTrue("Ccorrect login to the system with fake log/pass",
                loginPage.checkErrorShown("Succesfull"));

    }
    @Test
    public  void emailIncorrect() throws Exception{
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("testsolnalarmyandex.ru");
        loginPage.fillPasswordfield("1ElloS");

        loginPage.pressLoginButton();

        Assert.assertFalse("Incorrect login to the system with fake log/pass",
                loginPage.checkErrorShown("ErrorMess"));

    }
    @After
    public  void  tearDown() throws Exception{
        System.out.println("End test");
        driver.quit();
    }

}

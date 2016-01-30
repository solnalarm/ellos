import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by reford on 17.01.16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LoginTests {

    private static  WebDriver driver;
    static  MainPage mainPage;
    static LoginPage loginPage;



    @BeforeClass
    public static void setUp() throws Exception {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println("Browser open successful");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//magic
        driver.get("http://www.ellos.se/");
        System.out.println("Start test");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

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
                     loginPage.isErrorShown("ErrorMess"));

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

        Assert.assertTrue("Incorrect login to the system with fake log/pass",
                loginPage.isErrorShown("ErrorMess"));

    }
    //@Test
    public  void test5_emailIncorrect() throws Exception{
        //mainPage = new MainPage(driver);
        //loginPage = new LoginPage(driver);

        //mainPage.clickLogo();
        //mainPage.switchToLoginPage();
        loginPage.fillEmailField("testsolnalarmyandex.ru");
        loginPage.fillPasswordfield("1El");

        loginPage.pressLoginButton();

        Assert.assertTrue("Incorrect login to the system with fake log/pass",
                loginPage.isErrorShown("ErrorMess"));

    }
    @Test
    public  void test6_passwordlIncorrect() throws Exception{
        mainPage = new MainPage(driver);
       loginPage = new LoginPage(driver);


        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("testsolnalarm@yandex.ru");
        loginPage.fillPasswordfield("ElloS");

        loginPage.pressLoginButton();

        Assert.assertTrue("Incorrect login to the system with fake log/pass",
                loginPage.isErrorShown("ErrorMess"));

    }

    @Test
    public  void test3_lengthFieldPassword() throws Exception{
        //mainPage = new MainPage(driver);
        //loginPage = new LoginPage(driver);

        //mainPage.clickLogo();
       // mainPage.switchToLoginPage();

        loginPage.fillEmailField("solnalarm@ukr.net");
        loginPage.fillPasswordfield("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");

        loginPage.pressLoginButton();

        Assert.assertTrue("Incorrect login to the system with fake log/pass",
                loginPage.isErrorShown("ErrorMess"));

    }

    @Test

    public void test4_blankEmailField() throws Exception{
        //mainPage = new MainPage(driver);
        //loginPage = new LoginPage(driver);

        //mainPage.clickLogo();
        //mainPage.switchToLoginPage();

        loginPage.fillEmailField("");
        loginPage.fillPasswordfield("1ElloS");
        loginPage.pressLoginButton();
        Assert.assertTrue("Error mass NOT shown in case blank Email Field",
                loginPage.isErrorShown("ErrorMessLogin"));
    }
     @Test

    public void test1_blankLoginPasswordFields() throws Exception{
        //mainPage = new MainPage(driver);
        //loginPage = new LoginPage(driver);

        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("");
        loginPage.fillPasswordfield("");
        loginPage.pressLoginButton();
        Assert.assertTrue("Error mass NOT shown in case blank Pass and Log Fields",
                loginPage.isErrorShown("EmptyLoginFields"));
    }

    @Test

    public void test2_blankPasswordField() throws Exception{
        //mainPage = new MainPage(driver);
        //loginPage = new LoginPage(driver);

        loginPage.fillEmailField("testsolnalarm@yandex.ru");
        loginPage.fillPasswordfield("");
        loginPage.pressLoginButton();
        Assert.assertTrue("Error mass NOT shown in case blank Pass and Log Fields",
                loginPage.isErrorShown("EmptyLoginFields"));
    }




        @AfterClass
    public  static void  tearDown() throws Exception{
        System.out.println("End test");
        driver.quit();
    }

}

package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Created by ViTaLES on 16.01.2016.
 */
public class PageFactoryMainPage {

    WebDriver driver;

    @FindBy(css = ".ellos.active")
    WebElement logo;

    @FindBy(css = "#showlogin>span")
    WebElement loginPageLink;

    @FindBy(css = "#ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername")
    WebElement loginField;

    public PageFactoryMainPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        //pageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }
    public void clickLogo() {
        logo.click();
    }

    public void switchToLoginPage() {
        loginPageLink.click();
        if (loginField.isDisplayed()) {
            System.out.println("SwitchTo Login Page was correct");
        } else {
            System.out.println("SwitchTo Login Page was Incorrect");
        }
    }

}

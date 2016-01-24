package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by reford on 17.01.16.
 */
public class PageFactoryLoginPage {

    WebDriver driver;

    @FindBy(xpath = ".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername']")
    WebElement emailField;

    @FindBy(xpath = ".//input[@id='LoginPasswordText']")
    WebElement passwordField;

    @FindBy(xpath = ".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']")
    WebElement loginButton;

    @FindBy(xpath = ".//div[@id='serverValidationErrors']/ul")
    WebElement errorMessage;

    public PageFactoryLoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public  void fillEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void fillPasswordField(String pass){
        passwordField.sendKeys(pass);
    }

    public void pressLoginButton(){
        loginButton.click();
    }

    public boolean checkErrorShown(){
        if (errorMessage.isDisplayed()){
            System.out.println("Error is present");
            return true;
        } else {
            System.out.println("Error is not present");
            return false;
        }
    }
}

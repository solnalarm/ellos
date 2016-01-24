import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

/**
 * Created by reford on 16.01.16.
 */
public class WebElementsActions {

    WebDriver driver;
    Logger log = Logger.getLogger(WebElementsActions.class);



    public WebElementsActions(WebDriver driver) {

        this.driver = driver;
    }

    public void openPage(String url){
        driver.get(url);
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) throws IOException {
        driver.findElement(ConfigData.ui(buttonLocator)).click();
        //log.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) throws IOException{
        driver.findElement(ConfigData.ui(linkLocator)).click();
        //log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) throws IOException {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        //log.info("Input in " + inputLocator + ", value - " + inputData);
    }

    public void clearAndInput(String inputLocator, String inputData) throws IOException{
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
    }

    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) throws IOException{
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.ENTER);
    }

    /**
     * Select/deselect the checkbox, the second parameter should be "Y" or "N"
     */
    public void selectCheckbox(String checkboxLocator, String ischeckboxSelect) throws IOException {
        if (driver.findElement(ConfigData.ui(checkboxLocator)).isSelected() & ischeckboxSelect.equals("N")) {
            driver.findElement(ConfigData.ui(checkboxLocator)).click();
        }
        if (!driver.findElement(ConfigData.ui(checkboxLocator)).isSelected() & ischeckboxSelect.equals("Y")) {
            driver.findElement(ConfigData.ui(checkboxLocator)).click();
        }
    }

    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) throws IOException {
       boolean isPresent=true;

        try {
            driver.findElement(ConfigData.ui(elementLocator)).isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("No element found",e);
            isPresent= false;
        }
        return isPresent;
    }
    /**
     * This method is used to agree messages on pop-up windows
     */
    public boolean isAlertPresentAndAccept() {
        boolean alertPresence = false;
        try {
            Alert alert = driver.switchTo().alert();
            alertPresence = true;
            alert.accept();
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
            return alertPresence;
        }
        return alertPresence;
    }



    /**
     * This method is used to get text from pop-up windows
     */
    public String getAlertText() {
        String alertText;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
            //log.info("Alert text: " + alertText);
        } catch (NoAlertPresentException ex) {
            alertText = "Alert is not found";
            //log.error("Alert is not found");
            ex.printStackTrace();
        }
        return alertText;
    }


    public void moveToElementAndClick(String moveToLocator, String clickToElement) throws IOException {
        WebElement webElement = null;
        webElement = driver.findElement(ConfigData.ui(moveToLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
        clickButton(clickToElement);

        //log.info("moved To Element " + moveToLocator + "and clicked on" + clickToElement);
    }

    /**
     *Method#1 for refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickElement(String s) throws IOException{
        driver.findElement(ConfigData.ui(s));
    }


}




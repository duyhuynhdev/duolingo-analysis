package jp.ac.jaist.Crawler.Action;

import jp.ac.jaist.Common.obj.DuolingoInfo;
import jp.ac.jaist.Common.obj.DuolingoObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 1:17 PM 7/3/17.
 */
public class Login extends MainAction {
    private String usernameTextBoxXPath = "//*[@id=\"top_login\"]";
    private String passwordTextBoxXPath = "//*[@id=\"top_password\"]";
    private String loginButtonXPath = "//*[@id=\"login-button\"]";
    private String openLoginDialogButtonXPath = "//*[@id=\"sign-in-btn\"]";

    @Override
    public void run(RemoteWebDriver driver, RemoteWebDriver driver1, RemoteWebDriver driver2, DuolingoObject parent) {
        login(driver);
        login(driver1);
        login(driver2);
    }

    public void login(RemoteWebDriver driver){
        WebElement usernameTextBox = driver.findElement(By.xpath(usernameTextBoxXPath));
        WebElement passwordTextBox = driver.findElement(By.xpath(passwordTextBoxXPath));
        WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));
        WebElement openLoginDialogButton = driver.findElement(By.xpath(openLoginDialogButtonXPath));
        openLoginDialogButton.click();
        breathe(1);
        //input username
        usernameTextBox.clear();
        usernameTextBox.sendKeys(DuolingoInfo.username);
        breathe(1);
        //input password
        passwordTextBox.clear();
        passwordTextBox.sendKeys(DuolingoInfo.password);
        breathe(1);
        //click login button
        loginButton.click();
        breathe(1);
    }
}

package online;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Connect {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ".\\libraries\\C96\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 180);

        driver.get("https://dominion.games");

        //wait for page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username-input\"]")));
        //username
        WebElement user = driver.findElement(By.xpath("//*[@id=\"username-input\"]"));
        user.sendKeys(args[0]);
        //password
        WebElement pass = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/input[2]"));
        pass.sendKeys(args[1]);
        //login
        WebElement log = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/input[3]"));
        log.click();
    }
}

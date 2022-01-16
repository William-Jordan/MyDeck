package online;

import offline.Main;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Connect {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ".\\libraries\\C97\\chromedriver.exe");
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

        //-------------------- _ --------------------

        //looks for default home or game console
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[2]/div/div/ul/li[1]/button")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[6]/div[1]/div/div[1]/div/div"))));

        //tries to auto join bot game
        try {
            WebElement match = driver.findElement(By.xpath("html/body/div[2]/div/div/ul/li[1]/button"));
            match.click();
            WebElement oneBot = driver.findElement(By.xpath("html/body/div[2]/div/div/div[1]/div/div/table/tbody/tr/td[1]/div/botmatch/div/fieldset/div[2]/button[1]"));
            oneBot.click();
        } catch (NoSuchElementException ignored){}

        //use getPageSource for image scraping and loading.
        //System.out.println(driver.getPageSource());

        //print out game log
        Main m = new Main();
        for (int i = 0; i < 10000; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[6]/div[1]/div/div["+(i+2)+"]/div/div")));
            WebElement line = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[6]/div[1]/div/div["+(i+2)+"]/div/div"));
            System.out.println("line " + (i+1) + ": " + line.getText());

            if (line.getText().length() > 0)
                m.readLine(line.getText());
            m.printPlayers();
        }
    }
}

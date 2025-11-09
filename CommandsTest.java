
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommandsTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://the-internet.herokuapp.com/dynamic_controls");
            System.out.println("Navigated to dynamic_controls page");

            WebElement enableButton = driver.findElement(By.cssSelector("#input-example button"));
            enableButton.click();
            System.out.println("Clicked enable button");

            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

            WebElement inputField = driver.findElement(By.cssSelector("#input-example input"));
            wait.until(ExpectedConditions.elementToBeClickable(inputField));

            if (inputField.isEnabled() && message.getText().equals("It's enabled!")) {
                System.out.println("Input field enabled and text visible");
            }

            if (enableButton.getText().equals("Disable")) {
                System.out.println("Button text changed");
            }

            inputField.sendKeys("Bootcamp");
            System.out.println("Entered bootcamp in the input field");
            inputField.clear();
            System.out.println("Cleared the input field");

            driver.get("http://the-internet.herokuapp.com/drag_and_drop");
            System.out.println("Navigated to dnd page");

            WebElement columnA = driver.findElement(By.id("column-a"));
            WebElement columnB = driver.findElement(By.id("column-b"));

            int yCoordinateA = columnA.getLocation().getY();
            int yCoordinateB = columnB.getLocation().getY();

            if (yCoordinateA == yCoordinateB) {
                System.out.println("Columns A and B aligned successfully");
            } else {
                System.out.println("Columns A and B have different Y coordinates");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}
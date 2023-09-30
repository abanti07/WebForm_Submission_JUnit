import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class WebForm {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Check if submission is done properly")

    @Test
    public void formSubmission() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        //accept
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        //name
        driver.findElement(By.id("edit-name")).sendKeys("Abanti Chawdhury");
        //phone_number
        driver.findElement(By.id("edit-number")).sendKeys("01412345687");
        //age
        driver.findElement(By.xpath("//label[normalize-space()='20-30']")).click();

        scroll(driver, 0, 500);

        //date
        driver.findElement(By.id("edit-date")).sendKeys("10");
        driver.findElement(By.id("edit-date")).sendKeys("07");
        driver.findElement(By.id("edit-date")).sendKeys("2023");

        //email
        driver.findElement(By.id("edit-email")).sendKeys("abantio7@gmail.com");

        //text_box
        WebElement textArea = driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
        textArea.sendKeys("I am always ready to learn");

        Thread.sleep(1000);
        scroll(driver, 500, 1000);

        //file_upload
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/sqa.png");

        Thread.sleep(1000);
        //click
        driver.findElement(By.className("form-checkbox")).click();
        //submit
        driver.findElement(By.id("edit-submit")).click();

        //Confirmation message
        String ExpectedMessage = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(ExpectedMessage.contains("Thank you for your submission!"));

    }

    @Test
    public void scroll(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }
}


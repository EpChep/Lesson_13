import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class MtsTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
        try {
            WebElement cookieAgreeButton = driver.findElement(By.cssSelector("#cookie-agree"));
            cookieAgreeButton.click();
        } catch (NoSuchElementException ignored) {

        }
    }

   @AfterEach
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void testBlockTitle() {

        WebElement blockTitle = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2"));
        assertTrue(blockTitle.isDisplayed());
    }

    @Test
    public void testPaymentSystemLogos() {

        for (int i = 1; i <= 6; i++) {
            WebElement logo = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[" + i + "]/img"));
            assertTrue(logo.isDisplayed());
        }
    }

    @Test
    public void testServiceDetailsLink() {

        WebElement serviceLink = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/a"));
        serviceLink.click();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());
    }

    @Test
    public void testPaymentProcess() {

        WebElement phoneNumberField = driver.findElement(By.cssSelector("#connection-phone"));
        phoneNumberField.sendKeys("297777777");

        WebElement sumField = driver.findElement(By.cssSelector("#connection-sum"));
        sumField.sendKeys("50");

        WebElement emailField = driver.findElement(By.cssSelector("#connection-email"));
        emailField.sendKeys("aqa@mail.ru");

        WebElement continueButton = driver.findElement(By.cssSelector("#pay-connection > button"));
        continueButton.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bepaid-iframe")));

    }
}

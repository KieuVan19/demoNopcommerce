package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        List<String> args = new ArrayList<>();

        // Common args (safe for all environments)
        args.add("--no-sandbox");
        args.add("--disable-dev-shm-usage");
        args.add("--disable-gpu");
        args.add("--remote-allow-origins=*");
        args.add("--window-size=1920,1080");
        args.add("--disable-notifications");
        args.add("--disable-extensions");

        // unique user data dir to avoid "already in use" error
        args.add("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-" + System.currentTimeMillis());

        // Run headless only in CI (so your local dev still shows browser)
        if (System.getenv("CI") != null) {
            args.add("--headless=new"); // new headless mode for Chrome 109+
        }

        options.addArguments(args);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

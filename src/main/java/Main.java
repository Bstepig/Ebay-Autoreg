import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 40000;

    public static void main(String[] args) {
        String proxy = getProxy();
        WebDriver driver = getChromeDriver(proxy);
        try {
            driver.get("https://gmx.net");
            driver.manage().window().maximize();
            driver.switchTo().frame("landingpage");

            WebElement iframe = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(drive -> drive.findElement(By.tagName("iframe")));
            driver.switchTo().frame(0);
            try {
                WebElement saveAllPur = new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(drive -> drive.findElement(By.id("save-all-pur")));
                saveAllPur.click();
            } catch (Exception e) {
                // yeah
            }
//            iframe = new WebDriverWait(driver, Duration.ofSeconds(15))
//                    .until(drive -> drive.findElement(By.tagName("iframe")));
//            driver.switchTo().frame(1);
            System.out.println(driver.getPageSource());
            WebElement registerButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(drive -> drive.findElement(By.xpath("a[@data-importance='ghost']")));
            registerButton.click();
        } finally {
//            try {
//                driver.wait(315135);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            // driver.quit();
        }
//        driver.
//        driver.get("https://www.google.com/");
//
//        SelendroidLauncher selendroidServer = null;
//        WebDriver driver = null;
//
////        SelendroidConfiguration config = new SelendroidConfiguration();
////
////        selendroidServer = new SelendroidLauncher(config);
////        selendroidServer.launchSelendroid();
//
//        SelendroidCapabilities sc = new SelendroidCapabilities();
//        sc.setBrowserName("android");
//        sc.setPlatform(Platform.ANDROID);
//        sc.setSerial("015d24a8394cAAAA");  //serial id of the device
//
//
//        try {
//            driver = new SelendroidDriver(sc);
//            driver.get("http://m.ebay.de");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public static WebDriver getChromeDriver(String proxyStr) {
        var proxy = new Proxy();
        proxy.setHttpProxy(proxyStr);
//        proxy.setSocksProxy(proxyStr);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "87");
        options.setCapability("platformName", "Windows 11");
        options.setCapability("proxy", proxy);
        return new ChromeDriver(options);
    }

    public static String getProxy() {
        return "<%s:%s>".formatted(HOST, PORT);
    }
}

/**
 * Created by winch on 13.04.16.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;
public class HighchartTest {

        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @Before
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "http://www.highcharts.com/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testEee() throws Exception {
            driver.get(baseUrl + "/component/content/article/2-news/146-highcharts-5th-anniversary");


            Thread.sleep(5000);

//            WebElement svgObj = driver.findElement(By.xpath("//*[@id='menu-main']/ul/li[3]/a"));
    //            Actions actionBuilder = new Actions(driver);
    //            actionBuilder.click(svgObj).build().perform();
//           String attr =  driver.findElement(By.xpath("/*[@id='highcharts-0']/*[name()='svg']/[name()='SVG OBJECT']/*[name()='g'][7]/*[name()='g'][6]/*[name()='path'][starts-with(@d, 'M 8 286.88')]")).getText();
           String attr =  driver.findElement(By.xpath("//*[@id='highcharts-0']/*[name()='svg']/*[name()='g'][7]/*[name()='g'][6]/*[name()='path'][starts-with(@d, 'M 8 286.88')]")).getText();
////
//            String cssSelector = "/*[@id='highcharts-0']/*[name()='svg']/*[name()='g'][7]/*[name()='g'][6]/*[name()='path'][starts-with(@d, 'M 8 286.88')]";
//            String attribute = "fill";
//            String script = "return window.getComputedStyle(document.querySelector("+cssSelector+").getPropertyValue(" + attribute + ")";
//
////            String script = "return window.getComputedStyle(document.evaluate('/*[@id='highcharts-0']/*[name()='svg']/*[name()='g'][7]/*[name()='g'][6]/*[name()='path'][starts-with(@d, 'M 8 286.88')]').getPropertyValue('fill')";
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            String content = (String) js.executeScript(script);
//            System.out.println(content);
            System.out.println(attr);
        }

        @After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }


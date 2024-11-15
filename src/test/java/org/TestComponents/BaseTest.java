package org.TestComponents;

import com.PageObjects.LoginPageObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
        public WebDriver driver;
        public LoginPageObject login;

        public WebDriver initializeDriver() throws IOException {

            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\Users\\ssj\\Framework1\\src\\test\\java\\org\\Resources\\GlobalData.properties");
            prop.load(fis);

            String browser =  System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
            //String browser = prop.getProperty("browser");
            if (browser.contains("Chrome")) {
                ChromeOptions options = new ChromeOptions();
                if (browser.contains("headless")) {

                    options.addArguments("headless");

                }
                driver= new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440,900));

            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            return driver;
        }
    public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {

        //json to string
        String jsonContent= FileUtils.readFileToString(new File(filePath));
        //string to hashmap using Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String> >data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

            }
            @BeforeMethod(alwaysRun = true)
            public LoginPageObject launchUrl() throws IOException {
                driver = initializeDriver();
                login = new LoginPageObject(driver);
                login.loginUrl();
                return login;

            }

            @AfterMethod(alwaysRun = true)
            public void tearDown()
            {
                driver.quit();
            }


             public String takescreenshotwhentestcasefail(String testcasename,WebDriver driver) throws IOException {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType. FILE);
            File file = new File(System.getProperty("user.dir")+"//Utility//screenshots"+ testcasename+".png");
            FileUtils.copyFile(src,file);
            return System.getProperty("user.dir")+"//Utility//screenshots"+ testcasename+".png";
        }
}




package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; 
	public Properties prop;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"sanity","regression","datadriven","master"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException {

		FileInputStream file=new FileInputStream("./src/test/resources/config.properties");
		prop=new Properties();
		prop.load(file);

		logger=LogManager.getLogger(this.getClass());

		if(prop.getProperty("execution_Env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities=new DesiredCapabilities();

			switch(os.toLowerCase()){
			case "windows" : capabilities.setPlatform(Platform.WIN11);break;
			case "mac" : capabilities.setPlatform(Platform.MAC);break;
			case "linux" : capabilities.setPlatform(Platform.LINUX);break;
			default: System.out.println("invalid os....");return;
			}

			switch(browser.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome");break;
			case "firefox" : capabilities.setBrowserName("firefox");break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("invalid browser....");return;
			}

			driver=new RemoteWebDriver(new URL("http://192.168.29.41:4444/wd/hub"),capabilities); ///http://172.18.0.4:5555 ///192.168.29.41:4444
		}
		else if(prop.getProperty("execution_Env").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {
			case "chrome" : driver=new ChromeDriver();break;
			case "firefox" : driver=new FirefoxDriver();break;
			case "edge" : driver=new EdgeDriver();break;
			default: System.out.println("invalid browser....");return;
			}

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@AfterClass(groups= {"sanity","regression","datadriven","master"})
	public void tearDown() {
		driver.close();
	}

	public String getRandomString() {
		@SuppressWarnings("deprecation")
		String random=RandomStringUtils.randomAlphabetic(5);
		return random;
	}

	public String getRandomNumber() {
		@SuppressWarnings("deprecation")
		String random=RandomStringUtils.randomNumeric(10);
		return random;
	}

	public String getRandomAlpahaNumeric() {
		@SuppressWarnings("deprecation")
		String random1=RandomStringUtils.randomAlphabetic(3);
		@SuppressWarnings("deprecation")
		String random=RandomStringUtils.randomNumeric(3);
		return (random1+"@"+random);
	}

	public String captureScreenShot(String testname) throws IOException {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String target=System.getProperty("user.dir")+"/screenshots/"+testname+"_"+timestamp+".png";
		File dst=new File(target);
		FileUtils.copyFile(src, dst);
		return target;
	}

}

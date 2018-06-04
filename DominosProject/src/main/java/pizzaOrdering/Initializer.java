package pizzaOrdering;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

/**
 * @author Karthika Prem
 *This class constructor will initialize the required url
 *and loads the data in .properties file in an object of properties class
 */
public class Initializer{
	public static WebDriver driver=null;
	public static Properties prop;
	public Initializer() throws IOException{

		FileInputStream io=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Data.properties");


		prop=new Properties();
		prop.load(io);


		driver=new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


}

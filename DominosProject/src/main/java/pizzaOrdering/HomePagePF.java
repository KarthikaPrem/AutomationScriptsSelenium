package pizzaOrdering;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HomePagePF {

	WebDriver driver;
	public HomePagePF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@id='Menu']")
	WebElement menu;
	@FindBy(xpath="//p[@id='wobble-close-text']")
	List<WebElement> notificationClose;


	public WebElement getMenuItems(){
		return menu;
	}

	public List<WebElement> getNotificationClose(){
		return notificationClose;
	}


}

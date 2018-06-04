package pizzaOrdering;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryMethodPF {
	WebDriver driver;
	public DeliveryMethodPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@id='delivery']")
	WebElement homeDelivery;

	@FindBy(xpath="//h2[contains(text(),'Deliver to')]")
	WebElement recentLoc;

	@FindBy(xpath="//div[@class='close']")
	List<WebElement> close;


	public WebElement getHomeDelivery(){
		return homeDelivery;
	}	
	public WebElement getRecentLoc(){
		return recentLoc;
	}

	public List<WebElement> getClose(){
		return close;
	}

}

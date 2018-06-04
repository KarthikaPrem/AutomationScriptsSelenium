package pizzaOrdering;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDateAndTimePF {
	WebDriver driver;
	public OrderDateAndTimePF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@id='asap']")
	WebElement orderNow;
	@FindBy(xpath="//select[@id='order_time_select']")
	WebElement selectTime;
	//select[@id='order_time_select']

	@FindBy(xpath="//button[@id='start-order-button']")
	WebElement orderTimeNextButton;

	//@FindBy(xpath="//button[@class='at-no btn btn-default regular']")
	//at-no btn btn-default regular




	@FindBy(xpath="//button[@class='at-no btn btn-default regular']")
	//at-no btn btn-default regular
	List<WebElement> noThanksButtons;

	@FindBy(xpath="//button[@class='at-no btn btn-default regular']")
	//at-no btn btn-default regular
	WebElement noThanksButton;

	public WebElement getOrderNowButton(){
		return orderNow;

	}
	public WebElement getSelectTime(){
		return selectTime;
	}
	public WebElement getOrderTimeNextButton(){
		return orderTimeNextButton;
	}

	public WebElement getNoThanksButton(){
		return noThanksButton;

	}
	public List<WebElement> getNoThanksButtons(){
		return noThanksButtons;

	}

	public WebElement getNoThanksButton2(){
		int size=driver.findElements(By.xpath("//button[@class='at-no btn btn-default regular']")).size();
		WebElement noThanksButton2=driver.findElements(By.xpath("//button[@class='at-no btn btn-default regular']")).get(size-1);
		return noThanksButton2;

	}

}
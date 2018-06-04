package pizzaOrdering;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangeOTCheckOutPF {
	WebDriver driver;
	public ChangeOTCheckOutPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@id='customer-name']")
	WebElement custName;

	@FindBy(xpath="//input[@id='customer-phone']")
	WebElement phone;

	@FindBy(xpath="//input[@id='customer-email']")
	WebElement email;

	@FindBy(xpath="//a[@class='store-result btn next']")
	WebElement continueButton;

	public WebElement getCustName(){
		return custName;
	}

	public WebElement getPhone(){
		return phone;
	}

	public WebElement getEmail(){
		return email;
	}

	public WebElement getContinueButton(){
		return continueButton;
	}
}

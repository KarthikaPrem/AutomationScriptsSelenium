package pizzaOrdering;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectPaymentPF {
	WebDriver driver;
	public SelectPaymentPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@id='payment_method_creditcard']")
	WebElement creditCardButton;

	@FindBy(xpath="//a[@id='payment_method_cash']")
	WebElement cashButton;


	public WebElement getCreditCardButton(){
		return creditCardButton;
	}

	public WebElement getCashButton(){
		return cashButton;
	}

}

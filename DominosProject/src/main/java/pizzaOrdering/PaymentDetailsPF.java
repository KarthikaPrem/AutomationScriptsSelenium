package pizzaOrdering;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentDetailsPF {
	WebDriver driver;
	public PaymentDetailsPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@id='Name']")
	WebElement nameInCard;

	@FindBy(xpath="//input[@id='Number']")
	WebElement cardNumber;

	@FindBy(xpath="//select[@id='ExpirationMonth']")
	WebElement expirationMonth;

	@FindBy(xpath="//select[@id='ExpirationYear']")
	WebElement expirationYear;

	@FindBy(xpath="//input[@id='VerificationCode']")
	WebElement cvv;

	@FindBy(xpath="//input[@id='requested-change-checkbox']")
	WebElement changecheckBox;

	@FindBy(xpath="//button[@id='place-order-button']")
	WebElement placeOrderButton;

	public WebElement getNameInCard(){
		return nameInCard;
	}

	public WebElement getCardNumber(){
		return cardNumber;

	}

	public WebElement getExpirationMonth(){
		return expirationMonth;
	}

	public WebElement getExpirationYear(){
		return expirationYear;
	}

	public WebElement getCVV(){
		return cvv;
	}

	public WebElement getChangeCheckBox(){
		return changecheckBox;
	}

	public WebElement getPlaceOrderButton(){
		return placeOrderButton;
	}


}

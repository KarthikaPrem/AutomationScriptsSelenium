package pizzaOrdering;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerDetailsPF {
	WebDriver driver;
	public CustomerDetailsPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//button[@id='order-time-button']")
	WebElement nextButton;

	@FindBy(xpath="//label[@for='ordertimenow']")
	WebElement orderNowCustomerDetails;

	@FindBy(xpath="//label[@for='ordertimelater']")
	WebElement orderLaterCustomerDetails;

	@FindBy(xpath="//a[@class='store-result btn next']")
	WebElement storedAddress;

	@FindBy(xpath="//input[@id='customer-unit-no']")
	WebElement unitNo;

	@FindBy(xpath="//input[@id='customer-street-no']")
	WebElement streetNo;

	@FindBy(xpath="//input[@id='customer-street-name']")
	WebElement streetName;


	@FindBy(xpath="//input[@id='customer-suburb']")
	WebElement suburb;

	@FindBy(xpath="//input[@id='customer-postcode']")
	WebElement postcode;

	@FindBy(xpath="//div[@class='modal-footer']/button[@id='no-button']")

	WebElement doNotSaveDetailsButton;

	public WebElement getNextButton(){
		return nextButton;
	}
	public WebElement getOrderNowCustomerDetails(){
		return orderNowCustomerDetails;
	}

	public WebElement getOrderLaterCustomerDetails(){
		return orderLaterCustomerDetails;
	}

	public WebElement getStoredAddressButton(){
		return storedAddress;
	}

	public WebElement getunitNo(){
		return unitNo 	 ;
	}

	public WebElement getStreetNo(){
		return streetNo;}
	public WebElement getStreetName(){
		return streetName;
	}
	public WebElement getSuburb(){
		return suburb;
	}
	public WebElement getPostcode(){
		return postcode;
	}


	public WebElement getDoNotSaveDetailsButton(){
		return doNotSaveDetailsButton ;
	}


}

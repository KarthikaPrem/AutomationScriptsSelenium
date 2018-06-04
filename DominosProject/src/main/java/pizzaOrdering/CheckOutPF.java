package pizzaOrdering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPF {
	WebDriver driver;
	public CheckOutPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//div[contains(text(),'Checkout')]")
	WebElement checkout;

	@FindBy(xpath="//input[@id='voucher_code']")
	WebElement voucherCode;

	@FindBy(xpath="//button[@id='apply_voucher']")
	WebElement applyVoucher;
	//button[@class='at-no btn btn-default regular']
	@FindBy(xpath="//input[@id='customer-name']")
	WebElement customerName;

	@FindBy(xpath="//input[@id='customer-phone']")
	WebElement customerPhone;

	@FindBy(xpath="//input[@id='customer-email']")
	WebElement customerEmail;

	@FindBy(xpath="//button[@id='customer-details-submit']")
	WebElement continueButton;


	public WebElement getVoucherCode(){
		return voucherCode;
	}
	public WebElement getApplyVoucherButton(){
		return applyVoucher;
	}
	public WebElement getCheckOut(){
		return checkout;
	}

	public WebElement getCustomerName(){
		return customerName;
	}

	public WebElement getCustomerPhone(){
		return customerPhone;
	}

	public WebElement getCustomerEmail(){
		return customerEmail;
	}

	public WebElement getContinueButton(){
		return continueButton;
	}

	public WebElement getDoNotSaveDetailsButton2(){
		int size=driver.findElements(By.xpath("//div[@class='modal-footer']/button[@id='no-button']")).size();
		System.out.println("Do not save details button size"+size);
		WebElement doNotSaveDetailsButton2=driver.findElement(By.xpath("//button[@id='no-button']"));
		return doNotSaveDetailsButton2;
	}
}

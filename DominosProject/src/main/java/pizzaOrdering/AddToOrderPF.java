package pizzaOrdering;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToOrderPF {
	WebDriver driver;
	public AddToOrderPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//button[@id='add-product-button']")
	WebElement addToOrder;

	@FindBy(xpath="//button[@class='close']")

	WebElement closeOffer;

	public WebElement getAddToOrder(){
		return addToOrder;
	}

	public WebElement getCloseOffer(){
		return closeOffer;
	}

}

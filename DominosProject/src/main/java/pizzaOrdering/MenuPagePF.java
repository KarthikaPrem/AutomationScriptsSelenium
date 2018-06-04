package pizzaOrdering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class MenuPagePF {
	WebDriver driver;
	Boolean addButtonCheck;
	public MenuPagePF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@id='order_P308']")
	WebElement meatLoverOrder;
	@FindBy(xpath="//a[@id='order_P067']")
	WebElement fireBreatherOrder;

	@FindBy(xpath="//div[@id='product-menu-pizza-premium-P308']/a/div[4]/div/div/div/button")

	WebElement selectMeatLover;

	@FindBy(xpath="//div[@class='submenu-wrapper']/div/div/a[2]")

	List<WebElement> pizzaMenus;


	@FindBy(xpath="//a[@id='MenuPizza']")

	WebElement dominosPizza;

	@FindBy(xpath="//div[@class='submenu-wrapper']/div/div/a[1]")
	List<WebElement> pizzaNames;


	@FindBy(xpath="//a[@id='MenuPayment']")
	WebElement checkOut;

	@FindBy(xpath="//span[contains(@id,'product-name-menu-')]")

	List<WebElement> listOfItemsWithSelectBtn;
	
	@FindBy(xpath="//button[@id='add-button']")
	List<WebElement> addButtons;



	public WebElement getMeatLoverOrder(){
		return meatLoverOrder;
	}
	public WebElement getFireBreatherOrder(){
		return fireBreatherOrder;
	}
	public WebElement getSelectmeatLover(){
		return selectMeatLover;
	}
	public WebElement getCheckOut(){
		return checkOut;
	}

	public WebElement getDominosPizza(){
		return dominosPizza;
	}
		public List<WebElement> getpizzaList(){

		List<WebElement> newmenu=driver.findElements(By.xpath("//div[@class='submenu-wrapper']/div/div/div/a"));
		return newmenu;
	}

	public List<WebElement> getPizzaNames(){
		return pizzaNames;
	}
	/**Function to get webelement for various product types(pizza,sandwich,sides etc) after for the first item 
	 * xpath for each product type is found during runtime by appending the product type in xpath
	 * @param String product Type
	 *  */
	public WebElement getFirstProductType(String productType){
		WebElement productTypeLink=driver.findElement(By.xpath("//a[@href='/menu-"+productType+"']"));
		return productTypeLink;
	}
	/**Function to get webelement for various product types(pizza,sandwich,sides etc) after ordering the first item 
	 * xpath for each product type is found during runtime by appending the product type in xpath
	 * @param String product Type
	 *  */
	public WebElement getProductType(String productType){
		String productTypeStartingLetter=productType.substring(0, 1).toUpperCase();
		String productTypeRest=productType.substring(1);
		productType=productTypeStartingLetter.concat(productTypeRest);
		WebElement productTypeLink2=driver.findElement(By.xpath("//a[@id='Menu"+productType+"']"));
		return productTypeLink2;
	}
	/**
	 * Function to get all menu items and its corresponding select button
	 * @return Map<ItemName,CorrespondingSelectButton>
	 * */
	public Map<String,WebElement> getNameSelectBtnKeyValuePair(){
		Map<String,WebElement> nameSelectKeyValuePair=new LinkedHashMap<String,WebElement>();
		 
		/* extracting product id from attribute id - Logic starts*/

		for(WebElement itemWithSelectButton:listOfItemsWithSelectBtn){
			String id=itemWithSelectButton.getAttribute("id");
			String[] idSubStrings=id.split("-");
			int idSplitSize=idSubStrings.length;
			String productId=idSubStrings[idSplitSize-1];
			/* extracting product id from attribute id - Logic starts*/
			if(addButtons.size()==0){
				addButtonCheck=false;
			nameSelectKeyValuePair.put(itemWithSelectButton.getText(), driver.findElement(By.xpath("//div[contains(@id,'"+productId+"')]/a/div[4]/div/div/div/button")));
			}
			else{
				nameSelectKeyValuePair.put(itemWithSelectButton.getText(), driver.findElement(By.xpath("//button[@data-prodcode='"+productId+"']")));
			addButtonCheck=true;
			}
			
			
		}
		return nameSelectKeyValuePair;
	}
	/**
	 * Function to get all menu items and its corresponding order button
	 * @return Map<ItemName,CorrespondingOrderNowButton>
	 * */
	public Map<String,WebElement> getNameOrderBtnKeyValuePair(){
		Map<String, WebElement> nameOrderBtnKeyValuePair=new LinkedHashMap<String,WebElement>();
		/* extracting product id from the hyperlink address- Logic starts*/
		for(WebElement pizzaName:this.getpizzaList()){
			
			String href=pizzaName.getAttribute("href").toString();


			String[] hrefSubstrings=href.split("/");
			int hrefSize=hrefSubstrings.length;
			String productName=hrefSubstrings[hrefSize-1];

			String[] productNameSplit=productName.split("-");
			int productNameSplitSize=productNameSplit.length;
			String productId=productNameSplit[productNameSplitSize-1];


			/* extracting product id from the hyperlink address- Logic ends*/

			nameOrderBtnKeyValuePair.put(pizzaName.getText(), driver.findElement(By.xpath("//a[@id='order_"+productId+"']")));
		}

		return nameOrderBtnKeyValuePair;
	}
}

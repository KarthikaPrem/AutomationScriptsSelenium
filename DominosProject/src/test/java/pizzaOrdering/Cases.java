package pizzaOrdering;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
public class Cases {

	WebDriver driver;
	HomePagePF hpf;
	MenuPagePF mpf;
	DeliveryMethodPF dmpf;
	OrderDateAndTimePF odtpf;
	AddToOrderPF atopf;
	CheckOutPF copf;
	DeliveryMethodPF dlmpf;
	CustomerDetailsPF cdpf;
	SelectPaymentPF sppf;
	PaymentDetailsPF pdpf;
	WebDriverWait wait;
	GetMenuListFromXLS objReader;
	SendGmailNotificationForFailedCases mail;
	UpdateValidMenuInXls writeToXls;
	Select s;
	Map<String,WebElement> nameOrderButtonPair;
	Map<String,WebElement> nameSelectBtnPair;
	Map<String, ArrayList<String>> itemsOrdered;



	@BeforeTest
	public void initializeDriver() throws IOException{
		/*Calling the Initializer class constructor to initializer the webdriver object and load the details in data.properties file*/
		Initializer i=new Initializer();
		hpf=new HomePagePF(Initializer.driver);
		mpf=new MenuPagePF(Initializer.driver);
		dmpf=new DeliveryMethodPF(Initializer.driver);
		odtpf=new OrderDateAndTimePF(Initializer.driver);
		atopf=new AddToOrderPF(Initializer.driver);
		copf=new CheckOutPF(Initializer.driver);
		dlmpf=new DeliveryMethodPF(Initializer.driver);
		cdpf=new CustomerDetailsPF(Initializer.driver);
		driver=Initializer.driver;
		sppf=new SelectPaymentPF(Initializer.driver);
		pdpf=new PaymentDetailsPF(Initializer.driver);
		objReader=new GetMenuListFromXLS();
		mail=new SendGmailNotificationForFailedCases();
		writeToXls=new UpdateValidMenuInXls();

	}

	@Test
	public void orderItems() throws IOException, InterruptedException{
		try{
		/*Handling randomly appearing notifications*/
		if(hpf.getNotificationClose().size()>0){
			for(WebElement notificationColse:hpf.getNotificationClose()){
				notificationColse.click();
			}
		}

		hpf.getMenuItems().click();
		//String filePath="C:\\Users\\Sreejith\\Selenium";
		//String FileName="Menu.xlsx";

		/*Calling function to get the list of iems ordered from xls*/
		itemsOrdered=objReader.readExcel(Initializer.prop.getProperty("PathMenuXls"),Initializer.prop.getProperty("MenXlsName"));


		boolean flag=true;
		Set<String> productTypes=itemsOrdered.keySet();
		/* Selecting the items based on the items selected in menu.xls*/
		for(String key:productTypes){
			ArrayList<String> itemsUnderProductType=itemsOrdered.get(key);
			for(String item:itemsUnderProductType){
				if(item.equalsIgnoreCase("END")){
					continue;
				}

				if(flag){

					if(!(key.equalsIgnoreCase("pizza"))){

						mpf.getFirstProductType(key).click();
					}
					nameOrderButtonPair=mpf.getNameOrderBtnKeyValuePair();
					if(nameOrderButtonPair.containsKey((Object)item)){

						nameOrderButtonPair.get(item).click();
						/*Dealing the notifications which appears randomly*/
						if(dlmpf.getClose().size()>0){
							for(WebElement closeBtn:dlmpf.getClose()){
								closeBtn.click();
							}
						}

						dlmpf.getHomeDelivery().click();
						cdpf.getOrderLaterCustomerDetails().click();
						cdpf.getunitNo().sendKeys(Initializer.prop.getProperty("UnitNo"));
						cdpf.getStreetNo().sendKeys(Initializer.prop.getProperty("StreetNo"));
						cdpf.getStreetName().sendKeys(Initializer.prop.getProperty("StreetName"));
						cdpf.getSuburb().sendKeys(Initializer.prop.getProperty("Suburb"));
						cdpf.getPostcode().sendKeys(Initializer.prop.getProperty("Postcode"));
						cdpf.getNextButton().click();


						wait=new WebDriverWait(Initializer.driver, 30);
						wait.until(ExpectedConditions.visibilityOf(cdpf.getDoNotSaveDetailsButton()));

						cdpf.getDoNotSaveDetailsButton().click();
						cdpf.getStoredAddressButton().click();
						s=new Select(odtpf.getSelectTime());
						s.selectByVisibleText(Initializer.prop.getProperty("Time"));
						odtpf.getOrderTimeNextButton().click();
						/*Handling randomly appearing notifications*/
						if(odtpf.getNoThanksButtons().size()>0){
							for(WebElement noThanksBtn:odtpf.getNoThanksButtons()){
								if(noThanksBtn.isDisplayed()){
									noThanksBtn.click();
								}
							}

						}


						flag=false;//Setting the flag to false once the first item is ordered.
						continue;
					}
					else{
						writeToXls.writeToExcel(System.getProperty("user.dir")+"\\src\\main\\java\\resources","ValidMenu.xlsx", "Sheet1", key, nameOrderButtonPair.keySet());

						Assert.assertTrue(false);
					}
				}

				//Thread.sleep(3000);
				mpf.getProductType(key).click();
				nameSelectBtnPair=mpf.getNameSelectBtnKeyValuePair();
				if(nameSelectBtnPair.containsKey(item)){
					/*Handling randomly appearing notifications*/
					if(odtpf.getNoThanksButtons().size()>0){
						for(WebElement noThanksBtn:odtpf.getNoThanksButtons()){
							if(noThanksBtn.isDisplayed()){
								noThanksBtn.click();
							}
						}

					}
					/*Wait until the select button is clickable*/
					wait.until(ExpectedConditions.elementToBeClickable(nameSelectBtnPair.get(item)));
					nameSelectBtnPair.get(item).click();
					/*Handling randomly appearing notifications*/
					if(odtpf.getNoThanksButtons().size()>0){
						for(WebElement noThanksBtn:odtpf.getNoThanksButtons()){
							System.out.println("Inside for loop  No Thanks: "+noThanksBtn.getAttribute("xpath"));
							if(noThanksBtn.isDisplayed()){
								noThanksBtn.click();
							}
						}
					}
					if(mpf.addButtonCheck==false){
					/*Wait until Add to order button is clickable*/
					wait.until(ExpectedConditions.elementToBeClickable(atopf.getAddToOrder()));

					atopf.getAddToOrder().click();
					}

				}
				else{
					/*As the selected item is not available in the actual menu, update the xls with the present valid menu to send to the end user as a notification*/
					writeToXls.writeToExcel(System.getProperty("user.dir")+"\\src\\main\\java\\resources","ValidMenu.xlsx", "Sheet1", key, nameSelectBtnPair.keySet());
					Assert.assertTrue(false);
				}
			}
		}
		/*Menu Selection Logic ends*/

		if(Initializer.prop.getProperty("VoucherCode")!=null){
			/*Wait until Voucher code input field is visible*/
			wait.until(ExpectedConditions.visibilityOf(copf.getVoucherCode()));
			copf.getVoucherCode().sendKeys(Initializer.prop.getProperty("VoucherCode"));
			copf.getApplyVoucherButton().click();
		}

		/*wait until checkout tab is clcikable*/
		wait.until(ExpectedConditions.elementToBeClickable(mpf.getCheckOut()));
		mpf.getCheckOut().click();
		copf.getCustomerName().sendKeys(Initializer.prop.getProperty("CustomerName"));
		copf.getCustomerPhone().sendKeys(Initializer.prop.getProperty("Phone"));
		copf.getCustomerEmail().sendKeys(Initializer.prop.getProperty("Email"));
		copf.getContinueButton().click();
		wait.until(ExpectedConditions.elementToBeClickable(copf.getDoNotSaveDetailsButton2()));
		copf.getDoNotSaveDetailsButton2().click();
		sppf.getCreditCardButton().click();
		pdpf.getCardNumber().sendKeys(Initializer.prop.getProperty("CardNumber"));

		s=new Select(pdpf.getExpirationMonth());
		s.selectByVisibleText(Initializer.prop.getProperty("ExpiryMonth"));
		s=new Select(pdpf.getExpirationYear());
		s.selectByVisibleText(Initializer.prop.getProperty("ExpiryYear")); 
		pdpf.getCVV().sendKeys(Initializer.prop.getProperty("CVV"));
		pdpf.getPlaceOrderButton().click();
		}
		finally{
			driver.close();
		}

	}
/*test case to list the valid menu to update the menu.xls*/
	@Test
	public void showMenuList(){
		try{
		/*Handling randomly appearing notifications*/
		if(hpf.getNotificationClose().size()>0){
			for(WebElement notificationColse:hpf.getNotificationClose()){
				notificationColse.click();
			}
		}

		hpf.getMenuItems().click();
		mpf.getFirstProductType("dessert").click();

		System.out.println("PizzaList"+mpf.getpizzaList().size());

		for(String key:mpf.getNameOrderBtnKeyValuePair().keySet()){

			System.out.println(key);
		}
		}
		finally{
			driver.close();
		}
	}

}

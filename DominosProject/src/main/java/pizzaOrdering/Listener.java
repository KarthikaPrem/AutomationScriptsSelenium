package pizzaOrdering;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pizzaOrdering.Initializer;
public class Listener implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		SendGmailNotificationForFailedCases.sendNotificationByGmail(Initializer.prop.getProperty("SenderMailId"),Initializer.prop.getProperty("Pwd"),Initializer.prop.getProperty("RecipientMailId"),"Order Failed: "+result.getName(),"");
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}

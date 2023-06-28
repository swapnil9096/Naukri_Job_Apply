package Naukri;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry implements IRetryAnalyzer{

	 int retryCount = 0;
	 int maxRetryCount = 1;
	
	@Override
	public boolean retry(ITestResult result) 
	{
		if(this.retryCount < maxRetryCount)
		{
			this.retryCount++;
			return true;
	}
		return false;
	}
}

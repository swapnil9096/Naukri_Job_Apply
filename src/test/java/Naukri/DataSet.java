package Naukri;

import org.testng.annotations.DataProvider;

public class DataSet {

	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = {
							{"Java Developer","pune"},
//							{"Quality Assurance","Mumbai"},
//							{"Quality Assurance","Banglore"}	
		
							
						
							};
		return data;
	}
	
}

# TestOps Center - Test_TestNG.IntegrationTests Reporter

### It should be added to the test file.
```Java
import  org.testng.annotations.Test;
import  org.testng.Assert;
import  org.testopscenter.org.testopscenter.TestNG_Report

@Listeners(Report.class)

public  class  Test_TestNG.IntegrationTests {

	@BeforeSuite()
	public  void  TestOpsCenter_Login() {
		// Compulsory
		Report.companyId  =  "<companyID>";
		Report.email  =  "<email>";
		
		// Optional
		Report.platform  =  "<test platform name>";
		Report.version  =  "<release version>";
	}

	// Description is required for the name of the test
	@Test(description  =  "Math Test")
	public  void  TS001(){
		Assert.assertEquals(3,3);
	}
}
```

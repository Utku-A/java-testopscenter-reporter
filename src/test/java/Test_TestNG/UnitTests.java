package Test_TestNG;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testopscenter.TestNG_Report;

@Listeners(TestNG_Report.class)
public class UnitTests implements ITestListener {

    @BeforeSuite(alwaysRun = true)
    public void TestOpsCenter_Login() {
        TestNG_Report.connect_report("team-oBk0k9lj1mDxfedcO1FeZiVPWDNE9gGX94dCr0uDdRh6o","Android","None");

    }

    @Test(description = "Başarılı Matematik Testi", enabled = false)
    public void TS005(){
        Assert.assertEquals(3,(2+1));
    }

    @Test(description = "Başarılı Matematik Testi", enabled = false)
    public void TS006(){
        Assert.assertEquals(3,(3));
    }

    @Test(description = "Başarılı Matematik Testi", enabled = false)
    public void TS007(){
        Assert.assertEquals(3,(2+1));
    }

    @Test(description = "Başarılı Matematik Testi", enabled = false)
    public void TS008(){
        Assert.assertEquals(3,(2+1));
    }

}

package Test_TestNG;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testopscenter.TestNG_Report;

@Listeners(TestNG_Report.class)
public class IntegrationTests implements ITestListener {

    @BeforeSuite(alwaysRun = true)
    public void TestOpsCenter_Login() {
        TestNG_Report.connect_report("team-oBk0k9lj1mDxfedcO1FeZiVPWDNE9gGX94dCr0uDdRh6o","Android");

    }

    @Test(description = "Başarılı Matematik Testi")
    public void TS001(){
        Assert.assertEquals(3,(2+1));
    }

    @Test(description = "Başarılı Matematik Testi")
    public void TS002(){
        Assert.assertEquals(3,(2));
    }

    @Test(description = "Başarılı Matematik Testi")
    public void TS003(){
        Assert.assertEquals(3,(2+1));
    }

    @Test(description = "Başarılı Matematik Testi") @Ignore
    public void TS004(){
        Assert.assertEquals(3,(2+1));
    }

}

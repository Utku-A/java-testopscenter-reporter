# TestOps Center - Tests Result Reporter

## JUnit 5
```Java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testopscenter.JUnit5_Report;


@ExtendWith(JUnit5_Report.class)
public class IntegrationTests {

    @BeforeAll()
    public static void set_up() {
        JUnit5_Report.connect_report("<team_spkey>","Android","3.2 Pre-prod");
    }

    @Test
    @DisplayName("Başarılı Matematik Testi")
    public void TS001(){
        Assert.assertEquals(3,(2+1));
    }

    @Test
    @DisplayName("Başarısız Matematik Testi")
    public void TS002(){
        Assert.assertEquals(3,(3));
    }

    @Test
    @DisplayName("Skip Test")
    @Ignore
    public void TS003(){
        Assert.assertEquals(3,(3));
    }


    @Test
    @DisplayName("Başarılı Matematik Testi")
    public void TS004(){
        Assert.assertEquals(3,(2+1));
    }


}
```

## TestNG
```Java
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
        TestNG_Report.connect_report("<team_spkey>","Android","3.12 Prod");

    }

    @Test(description = "Başarılı Matematik Testi")
    public void TS001(){
        Assert.assertEquals(3,(2+1));
    }

    @Test(description = "Başarılı Matematik Testi")
    public void TS002(){
        Assert.assertEquals(3,(3));
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
```


package Test_JUnit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testopscenter.JUnit5_Report;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(JUnit5_Report.class)
public class UnitTests {

    @BeforeAll()
    public static void set_up() {
        JUnit5_Report.connect_report("team-oBk0k9lj1mDxfedcO1FeZiVPWDNE9gGX94dCr0uDdRh6o","Android","Android - Stage V1.3");
    }
    @Test
    @DisplayName("Başarılı Matematik Testi")
    @Disabled
    public void TS005(){
        Assert.assertEquals(3,(2+1));
    }

    @Test
    @DisplayName("Başarısız Matematik Testi")
    @Disabled
    public void TS006(){
        Assert.assertEquals(3,(3));
    }

    @Test()
    @DisplayName("Skip Test")
    @Disabled()
    public void TS007(){
        assumeTrue(true,"Skip Test Deneme");
        Assert.assertEquals(3,(3));
    }


    @Test
    @DisplayName("Başarılı Matematik Testi")
    @Disabled
    public void TS008(){
        Assert.assertEquals(3,(2+1));
    }



}

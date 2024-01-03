package Test_JUnit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testopscenter.JUnit5_Report;
import org.testopscenter.Reporter;


@ExtendWith(JUnit5_Report.class)
public class IntegrationTests {

    @BeforeAll()
    public static void set_up() {
        JUnit5_Report.connect_report("team-oBk0k9lj1mDxfedcO1FeZiVPWDNE9gGX94dCr0uDdRh6o","Linux");
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
    public void TS003(){
        Assert.assertEquals(3,(3));
    }


    @Test
    @DisplayName("Başarılı Matematik Testi")
    public void TS004(){
        Assert.assertEquals(3,(2+1));
    }


}

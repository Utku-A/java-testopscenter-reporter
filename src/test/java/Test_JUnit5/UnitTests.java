package Test_JUnit5;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testopscenter.JUnit5_Report;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(JUnit5_Report.class)
public class UnitTests {

    @Test
    @DisplayName("Başarılı Matematik Testi")
    public void TS005(){
        Assert.assertEquals(3,(2+1));
    }

    @Test
    @DisplayName("Başarısız Matematik Testi")
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
    public void TS008(){
        Assert.assertEquals(3,(2+1));
    }



}

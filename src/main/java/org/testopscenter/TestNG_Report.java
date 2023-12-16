package org.testopscenter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestNG_Report implements ITestListener {
    static final String url = Reporter.url;

    public static void connect_report(String team_spkey, String platform, String version) {
        Reporter.connect_report(team_spkey,platform,version);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Done",testName,testDescription);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Fail",testName,testDescription);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Skip",testName,testDescription);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.viewTestResult();
    }


  
}

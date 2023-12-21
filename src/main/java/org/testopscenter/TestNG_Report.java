package org.testopscenter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestNG_Report implements ITestListener {
    static final String url = Reporter.url;

    public static void connect_report(String team_spkey, String platform, String version) {
        Reporter.connect_report(team_spkey,platform,version,"testng");
    }

    public static void connect_report(String team_spkey, String platform) {
        Reporter.connect_report(team_spkey,platform,"null","testng");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Done",testName,testDescription, "testng");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Fail",testName,testDescription, "testng");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        Reporter.saveTestResults("Skip",testName,testDescription, "testng");
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.stopTestRunningStatus("testng");
        Reporter.viewTestResult();
    }


  
}

package org.testopscenter;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

public class JUnit5_Report implements TestWatcher, AfterAllCallback {

    public static void connect_report(String team_spkey, String platform, String version) {
        Reporter.connect_report(team_spkey,platform,version,"junit5");
    }

    public static void connect_report(String team_spkey, String platform) {
        Reporter.connect_report(team_spkey,platform,"null","junit5");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Done", testName, testDescription, "junit5");

    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Fail",testName,testDescription, "junit5");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Skip",testName,testDescription, "junit5");
    }


    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Skip",testName,testDescription, "junit5");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        Reporter.stopTestRunningStatus("junit5");
        Reporter.viewTestResult();
    }

}

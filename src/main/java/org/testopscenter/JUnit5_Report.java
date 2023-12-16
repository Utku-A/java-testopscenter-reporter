package org.testopscenter;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

public class JUnit5_Report implements TestWatcher, AfterAllCallback {

    public static void connect_report(String team_spkey, String platform, String version) {
        Reporter.connect_report(team_spkey,platform,version);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Done", testName, testDescription);

    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Fail",testName,testDescription);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Skip",testName,testDescription);
    }


    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
        String testName = context.getTestMethod().get().getName();
        String testDescription = context.getDisplayName();
        Reporter.saveTestResults("Skip",testName,testDescription);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        Reporter.viewTestResult();
    }

}

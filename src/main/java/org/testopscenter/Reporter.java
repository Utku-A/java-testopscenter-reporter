package org.testopscenter;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Reporter {

    public static void connect_report(String team_key, String platform, String version, String tools) {
        session_id = getSessionID(team_key,platform,version,tools);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static String session_id;
    public static JSONObject testResultsJson = new JSONObject();
    static JSONArray testResultArrayJson = new JSONArray();
    static final String url = "http://127.0.0.1:3000/v1/";



    public static void saveTestResults(String testResult, String testName, String testDescription, String tools) {
        JSONObject jsonResult = new JSONObject();
        sendTestResult(testDescription,testResult,tools);

        jsonResult.put("test_name",testName +" - "+ testDescription);
        jsonResult.put("test_result",testResult);
        testResultArrayJson.put(jsonResult);
        testResultsJson.put("tests", testResultArrayJson);
    }

    public static String getSessionID(String team_key, String platform, String version, String tools) {
        JSONObject object = new JSONObject();
        object.put("team_key",team_key);
        object.put("platform",platform);
        object.put("version",version);

        String response = send_request_api("get_automation_session",object,tools);
        return extractSessionID(response);

    }

    private static String extractSessionID(String jsonResponse) {

        String sessionID = "None";
        try {
            sessionID = jsonResponse.split("\"Session_ID\"")[1].split(":")[1]
                    .replaceAll("[\", ]", "")
                    .replace("\n", "")
                    .replace("}", "")
                    .replace("Start_Time","");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Session_ID not found in the response.");
        }
        return sessionID;
    }

    public static void sendTestResult(String testName, String result, String tools) {
        JSONObject object = new JSONObject();
        object.put("session_id",session_id);
        object.put("test_name",testName);
        object.put("test_result",result);
        String response = send_request_api("save-test-result",object,tools);

    }

    public static void stopTestRunningStatus(String tools) {
        JSONObject object = new JSONObject();
        object.put("session_id",session_id);
        String response = send_request_api("stop-automation-session",object,tools);

    }


    public static void print(Object text) {
        System.out.println(text);
    }

    public static String send_request_api(String path, JSONObject data, String tools) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(data));
        String responseString = "";

        Request request = new Request.Builder()
                .url(url + path + "/"+ tools)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                responseString = response.body().string();
            }
        } catch (IOException e) {
            print("Server Error");
        }
        return responseString;
    }

    public static void viewTestResult() {
        print("");
        print("=========================    TestOps Center    =========================");
        print("");

        for (int i = 0; i < testResultArrayJson.length(); i++) {
            JSONObject test = testResultArrayJson.getJSONObject(i);
            String testResult = test.getString("test_result");
            String testName = test.getString("test_name");
            int count = i+1;

            if (Objects.equals(testResult, "Done")) {
                print(count+". "+ANSI_GREEN + testName + ANSI_RESET);
            } else if (Objects.equals(testResult, "Fail")) {
                print(count+". "+ANSI_RED + testName + ANSI_RESET);
            } else {
                print(count+". "+ANSI_YELLOW + testName + ANSI_RESET);
            }

        }

        print("");
        print("=========================================================================");
        print("");
    }




}

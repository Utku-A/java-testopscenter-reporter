package org.testopscenter;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Reporter {

    public static void connect_report(String team_key, String platform, String version) {
        session_id = getSessionID(team_key,platform,version);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static String session_id;
    public static JSONObject testResultsJson = new JSONObject();
    static JSONArray testResultArrayJson = new JSONArray();
    static final String url = "http://127.0.0.1:3000/v1/";



    public static void saveTestResults(String testResult, String testName, String testDescription) {
        JSONObject jsonResult = new JSONObject();
        sendTestResult(testDescription,testResult);

        jsonResult.put("test-name",testName);
        jsonResult.put("description",testDescription);
        jsonResult.put("result",testResult);
        testResultArrayJson.put(jsonResult);
        testResultsJson.put("tests", testResultArrayJson);
    }

    public static String getSessionID(String team_key, String platform, String version) {
        OkHttpClient client = new OkHttpClient();
        String url = TestNG_Report.url + "get_automation_session/"+team_key+"/"+platform+"/"+version;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String jsonResponse = responseBody.string();
                    return jsonResponse.split("\"Session_ID\"")[1].split(":")[1]
                            .replaceAll("[\", ]", "")
                            .replace("\n","")
                            .replace("}","");
                } else {
                    System.out.println("Response body is null.");
                }
            } else {
                System.out.println("HTTP GET request failed with response code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "None";
    }

    public static void sendTestResult(String testName, String result) {
        JSONObject object = new JSONObject();
        object.put("session_id",session_id);
        object.put("testname",testName);
        object.put("result",result);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(object));

        Request request = new Request.Builder()
                .url(url + "add-test-result/testng")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                int responseCode = response.code();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void print(Object text) {
        System.out.println(text);
    }

    public static void viewTestResult() {
        print("");
        print("=========================    TestOps Center    =========================");
        print("");

        for (int i = 0; i < testResultArrayJson.length(); i++) {
            JSONObject test = testResultArrayJson.getJSONObject(i);
            String testResult = test.getString("result");
            String testName = test.getString("test-name");
            String testDescription = test.getString("description");
            int count = i+1;

            if (Objects.equals(testResult, "Done")) {
                print(count+". "+ANSI_GREEN+testName+" - "+testDescription+ANSI_RESET);
            } else if (Objects.equals(testResult, "Fail")) {
                print(count+". "+ANSI_RED+testName+" - "+testDescription+ANSI_RESET);
            } else {
                print(count+". "+ANSI_YELLOW+testName+" - "+testDescription+ANSI_RESET);
            }

        }

        print("");
        print("=========================================================================");
        print("");
    }




}

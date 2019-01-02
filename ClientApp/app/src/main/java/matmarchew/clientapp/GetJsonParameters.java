package matmarchew.clientapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetJsonParameters {
    public static String getParameters(JSONObject json, String parameter) {
        String state = "";
        try {
            state = json.getString(parameter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }

    public static JSONObject getJsonFromString(String parameters) {
        try {
            JSONObject jsonObject = new JSONObject(parameters);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONObject getJsonFromList(List<?> elements, String key) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key, elements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getJsonArray(JSONObject json, String key) {
        try {
            return json.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONObject getJsonFromJsonArray(JSONArray jsonArray, int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}

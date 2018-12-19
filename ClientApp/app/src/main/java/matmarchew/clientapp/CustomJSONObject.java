package matmarchew.clientapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class CustomJSONObject extends JSONObject {

    public JSONObject put(String s, Collection collection) {
        try {
            return super.put(s, collection);
        } catch (JSONException e) {
            return this;
        }
    }

    @Override
    public JSONObject put(String s, Object o){
        try {
            return super.put(s, o);
        } catch (JSONException e) {
            return this;
        }
    }

    @Override
    public String getString(String s){
        try {
            return super.getString(s);
        } catch (JSONException e) {
            return "";
        }
    }

    public void getJSONObjectFromString(String s) {
        try {
            JSONObject json = new JSONObject(s);
            for (Iterator i = json.keys(); i.hasNext(); ) {
                String key = (String) i.next();
                this.put(key, json.get(key));
            }
        } catch (JSONException e) {
        }
    }

    @Override
    public JSONArray getJSONArray(String name) {
        try {
            return super.getJSONArray(name);
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    public void getJSONObjectFromJSONArray(JSONArray jsonArray, int i) {
        try {
            JSONObject json = jsonArray.getJSONObject(i);
            for (Iterator it = json.keys(); it.hasNext(); ) {
                String key = (String) it.next();
                this.put(key, json.get(key));
            }
        } catch (JSONException e) {
        }
    }
}

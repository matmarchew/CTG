package communication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class CustomJSONObject extends JSONObject {

    @Override
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
}

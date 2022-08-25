package demo.app.troika_game;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetInfoAsync extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject jsonObject = JSONParser.getDataFromWeb();

        try {
            if (jsonObject != null) {
                if (jsonObject.length() > 0) {
                    JSONArray array = jsonObject.getJSONArray("Attractions");


                    int lenArray = array.length();
                    if (lenArray > 0) {
                        for (int i = 0; i < lenArray; i++) {
                            Place_item model = new Place_item();

                            JSONObject innerObject = array.getJSONObject(i);
                            String name = innerObject.getString("name");
                            Log.d("LOHH", name);
                            String country = innerObject.getString("country");


                           /* JSONObject innerObject = array.getJSONObject(i);
                            String id = innerObject.getString("id");
                            String name = innerObject.getString("name");
                            String country = innerObject.getString("country");
                            String description = innerObject.getString("description");
                            String place = innerObject.getString("place");
                            String image = innerObject.getString("image");*/

                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

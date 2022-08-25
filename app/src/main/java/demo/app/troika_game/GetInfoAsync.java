package demo.app.troika_game;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetInfoAsync extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject jsonObject = JSONParser.getDataById(new Random().nextInt(5)+1);

        if (jsonObject != null) {


            try {
                String id = jsonObject.getString("id");
                Log.d("LOHH", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Place_item model = new Place_item();

                           /* JSONObject innerObject = array.getJSONObject(i);
                            String id = innerObject.getString("id");
                            String name = innerObject.getString("name");
                            String country = innerObject.getString("country");
                            String description = innerObject.getString("description");
                            String place = innerObject.getString("place");
                            String image = innerObject.getString("image");*/

        }
        return null;
    }
}

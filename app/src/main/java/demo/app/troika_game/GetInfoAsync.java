package demo.app.troika_game;


import static demo.app.troika_game.MainActivity.changeViews;
import static demo.app.troika_game.MainActivity.list;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetInfoAsync extends AsyncTask<Void, Void, Void> {

    Context context;
    ProgressDialog dialog;
    public GetInfoAsync(Context cont) {
        context=cont;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Подбираем для вас интересные места...");
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void unused) {
        dialog.cancel();
        changeViews(context);
        super.onPostExecute(unused);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject jsonObject = JSONParser.getDataFromWeb();

        try {
            if (jsonObject != null) {
                if (jsonObject.length() > 0) {
                    JSONArray array = jsonObject.getJSONArray("user");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        Place_item model = new Place_item();
                        model.name = obj.getString("name");
                        model.place = obj.getString("place");
                        model.wr1 = obj.getString("wra");
                        model.wr2 = obj.getString("wrb");
                        model.image = obj.getString("image");
                        list.add(model);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
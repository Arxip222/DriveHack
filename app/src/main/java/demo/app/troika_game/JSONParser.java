package demo.app.troika_game;

import android.util.Log;

import androidx.annotation.NonNull;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class JSONParser {

    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbxWvL870xJFANXSYLHM6ynSYgphij5F_2ZY0431r36u4vRzLA3d_Mlg1NNzpWS36dkJHw/exec?id=1aCDPWVDIfx9TDXLnSX4tOUAMfmsJNrThhOLMKfYX8qY&sheet=Attractions";

    private static final String KEY_USER_ID = "user_id";

    private static Response response;

    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e("LOGG", "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e("LOGG", "" + e.getLocalizedMessage());
        }
        return null;
    }
}
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

    private static final String MAIN_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=WINST7Ff4sA09Se61hfYgrqKzsiNLPVToNslbdytFzuKaU-qwTXy6LRWzPM-L5z83AQZLad2GF4Rspq8Ewof5Z9WQ8vaUHZWOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa07t3i54uQsNaxgB10F_ZRPiaeePqPIvJYWuU_EMsOAlB54hZnTg8si4Biu4BW2KrVdhIRUHuKwKbzyDYRkwIZHqsm9mXTayD92cJ7rcs-EYWdYkgsPE1CHSNkjYgkfhuliJYtC7WIpm6Y7eJIxH8aJaJVzTolGRk_2OCoIocAwwFitFXWSdQzGnVJ_Vdp1Vgw&lib=M-5mYj3IeHAsPrK1Zt1QRI-l5IHS07qn4";

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
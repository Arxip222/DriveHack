package demo.app.troika_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static TextView textView, schet;
    public static Button btn_next,btn0,btn1,btn2;
    ImageButton back;
    ConnectivityManager conMgr;
    public static Place_item item;
    public static ImageView image;
    public static int item_pos;
    public int count;
    public static ArrayList<Place_item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        count = 0;
        btn0 = findViewById(R.id.btn0);
        schet = findViewById(R.id.schet);
        back = findViewById(R.id.back);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.name);
        btn_next = findViewById(R.id.btn_skip);
        image = findViewById(R.id.image);
        image.setClipToOutline(true);
        loadData();
        registerMessage(0);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViews(MainActivity.this);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                startActivity(intent);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn0.getText().toString().equals(list.get(item_pos).place)){
                    registerMessage(++count);
                    Toast.makeText(MainActivity.this, "ПРАВИЛЬНО!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "НЕВЕРНО!", Toast.LENGTH_SHORT).show();
                }
                changeViews(MainActivity.this);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.getText().toString().equals(list.get(item_pos).place)){
                    registerMessage(++count);
                    Toast.makeText(MainActivity.this, "ПРАВИЛЬНО!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "НЕВЕРНО!", Toast.LENGTH_SHORT).show();
                }
                changeViews(MainActivity.this);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn2.getText().toString().equals(list.get(item_pos).place)){
                    registerMessage(++count);
                    Toast.makeText(MainActivity.this, "ПРАВИЛЬНО!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "НЕВЕРНО!", Toast.LENGTH_SHORT).show();
                }
                changeViews(MainActivity.this);
            }
        });
    }

    public void registerMessage(int count) {
        schet.setText("Ваш счет: "+count);
    }


    public static void changeViews(Context cont) {
        item_pos = new Random().nextInt(list.size());
        item = list.get(item_pos);
        loadButtons(item_pos, cont);
        Picasso.with(cont).load(item.image).resize(image.getWidth(), image.getHeight()).into(image);
        textView.setText(item.name);
    }
    public static void loadButtons(int right, Context context){
        int right_pos = new Random().nextInt(3);//Генерируем положение верного ответа из 3
        int pos1 = new Random().nextInt(3);//Генерируем положение 1 неверного ответа
        int pos2 = new Random().nextInt(3);//Генерируем положение 2 неверного ответа
        while ((pos2 == right_pos) | (pos2 == pos1)){
            pos2 = new Random().nextInt(3);
        }
        while ((pos1 == right_pos) | (pos1 == pos2)){
            pos1 = new Random().nextInt(3);
        }
        for (int j = 0; j < 3; j++) {
            if (pos1 == j){
                btn0.setText(list.get(right).getWr1());
            }else if (pos2 == j){
                btn1.setText(list.get(right).getWr2());
            }else if(right_pos == j){
                btn2.setText(list.get(right).getPlace());
            }
        }
    }
    private void loadData() {
        conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()) {
            new GetInfoAsync(MainActivity.this).execute();
        } else {
            Toast.makeText(this, "Подключение к интернету отсутствует", Toast.LENGTH_LONG).show();
        }
    }
}
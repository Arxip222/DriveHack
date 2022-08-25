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

    public static TextView textView;
    public static Button btn_next,btn0,btn1,btn2;
    ImageButton back;
    ConnectivityManager conMgr;
    public static Place_item item;
    public static ImageView image;
    public static int item_pos;
    public static ArrayList<Place_item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        btn0 = findViewById(R.id.btn0);
        back = findViewById(R.id.back);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.name);
        btn_next = findViewById(R.id.btn_skip);
        image = findViewById(R.id.image);
        image.setClipToOutline(true);
        loadData();
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
                if (btn0.getText().toString() == list.get(item_pos).place){
                    Toast.makeText(MainActivity.this, "ПРАВИЛЬНО!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "НЕВЕРНО!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.getText().toString() == list.get(item_pos).place){
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
                if (btn2.getText().toString() == list.get(item_pos).place){
                    Toast.makeText(MainActivity.this, "ПРАВИЛЬНО!", Toast.LENGTH_SHORT).show();
                    Log.d("LOHH", "TRUE");
                }
                else{
                    Toast.makeText(MainActivity.this, "НЕВЕРНО!", Toast.LENGTH_SHORT).show();
                    Log.d("LOHH", "FALSE");
                }
                changeViews(MainActivity.this);
            }
        });
    }


    public static void changeViews(Context cont) {
        item_pos = new Random().nextInt(list.size());
        item = list.get(item_pos);
        loadButtons(item_pos, cont);
        Picasso.with(cont).load(item.image).resize(image.getWidth(), image.getHeight()).into(image);
        textView.setText(item.name);
    }
    public static void loadButtons(int right, Context context){
        ArrayList<Place_item> listok = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).place != list.get(right).place) | (i == right)){
                listok.add(list.get(i));
            }
        }
        int right_pos = new Random().nextInt(3);//Генерируем положение верного ответа из 3
        int pos1 = new Random().nextInt(3);//Генерируем положение 1 неверного ответа
        int pos2 = new Random().nextInt(3);//Генерируем положение 2 неверного ответа
        int cont1 = new Random().nextInt(listok.size());//Генерируем контент для 1 неверного ответа
        int cont2 = new Random().nextInt(listok.size());//Генерируем контент 2 неверного ответа
        while ((cont1 == right) | (cont1 == cont2)){//Если неверыный контент совпадает с верным
            cont1 = new Random().nextInt(listok.size());//Обновляем значение для 1 неверного контента
        }
        while ((cont2 == cont1) | (cont2 == right)){//Если он совпадает с другими ответами
            cont2 = new Random().nextInt(listok.size());//Перегенирируем контент для 2 неверного ответа
        }
        while ((pos2 == right_pos) | (pos2 == pos1)){
            pos2 = new Random().nextInt(3);
        }
        while ((pos1 == right_pos) | (pos1 == pos2)){
            pos1 = new Random().nextInt(3);
        }
        for (int j = 0; j < 3; j++) {
            if (pos1 == j){
                btn0.setText(listok.get(cont1).place);
            }else if (pos2 == j){
                btn1.setText(listok.get(cont2).place);
            }else if(right_pos == j){
                btn2.setText(listok.get(right).place);
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
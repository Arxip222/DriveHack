package demo.app.troika_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HelloActivity extends AppCompatActivity {

    TextView quiz, goToReg, goToLog;
    LinearLayout login, registration;
    EditText email_log, pass_log, email_reg, pass_reg;
    Button log, reg;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        login = findViewById(R.id.login);
        registration = findViewById(R.id.registration);
        registration.setVisibility(View.GONE);
        quiz = findViewById(R.id.quiz);
        email_log = findViewById(R.id.email_log);
        email_reg = findViewById(R.id.email_reg);
        pass_log = findViewById(R.id.pass_log);
        pass_reg = findViewById(R.id.pass_reg);
        log = findViewById(R.id.log);
        reg = findViewById(R.id.reg);
        goToLog = findViewById(R.id.goToLog);
        goToReg = findViewById(R.id.goToReg);
        auth = FirebaseAuth.getInstance();

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.setVisibility(View.GONE);
                registration.setVisibility(View.VISIBLE);
            }
        });
        goToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmailValid(email_reg.getText())){
                    if (pass_reg.length() >= 6){
                        SignUpFirebase(email_reg.getText().toString(), pass_reg.getText().toString());
                    }
                    else{
                        Toast.makeText(HelloActivity.this, "Пароль должен содержать более 5 символов!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(HelloActivity.this, "Неправильный формат ввода почты!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmailValid(email_log.getText())){
                    if (pass_log.length() >= 6){
                        LoginFirebase(email_log.getText().toString(), pass_log.getText().toString());
                    }
                    else{
                        Toast.makeText(HelloActivity.this, "Минимальная длина пароля - 5 символов", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(HelloActivity.this, "Неправильный формат ввода почты!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SignUpFirebase(String email, String pass){
        dialog = new ProgressDialog(this);
        dialog.setTitle("Пожалуйста подождите");
        dialog.setMessage("Создание аккаунта...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                dialog.dismiss();
                FirebaseUser user = authResult.getUser();
                Log.d("LOGG", user.getEmail());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(HelloActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void LoginFirebase(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
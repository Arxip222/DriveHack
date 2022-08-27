package demo.app.troika_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Objects;

import demo.app.troika_game.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    Button scanQR, quiz_btn, logout;
    DatabaseReference dataRef, dataRef2;
    TextView balance_show, email_show;
    FirebaseAuth auth;
    public static String email,balance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scanQR = view.findViewById(R.id.scanQR);
        quiz_btn = view.findViewById(R.id.quiz_btn);
        balance_show = view.findViewById(R.id.balance_show);
        email_show = view.findViewById(R.id.email_show);
        logout = view.findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            Intent intent = new Intent(getContext(), HelloActivity.class);
            startActivity(intent);
        }
        dataRef = FirebaseDatabase.getInstance().getReference("users");
        dataRef2 = FirebaseDatabase.getInstance().getReference("QrCodes");
        getUserInfo();

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanOptions options = new ScanOptions();
                /*options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
                options.setPrompt("Отсканируйте код и получите приз");
                options.setCameraId(0);  // Use a specific camera of the device
                options.setBeepEnabled(false);
                options.setOrientationLocked(false);
                options.setBarcodeImageEnabled(true);*/
                barcodeLauncher.launch(options);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), HelloActivity.class);
                startActivity(intent);
            }
        });

        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(getContext(), "Код не отсканирован", Toast.LENGTH_LONG).show();
                } else {
                    dataRef2 = FirebaseDatabase.getInstance().getReference("QrCodes");
                    dataRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()){
                                QR_item model=ds.getValue(QR_item.class);
                                if (Objects.equals(model.code, result.getContents())){
                                    Toast.makeText(getContext(), "QR-код успешно активирован!", Toast.LENGTH_SHORT).show();
                                    getUserInfo();
                                    int summa_bal = model.balls + Integer.parseInt(balance);
                                    dataRef.child(auth.getCurrentUser().getUid()).child("balance").setValue(summa_bal);
                                    dataRef.push();
                                    getUserInfo();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

    public void getUserInfo(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ref.orderByChild(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    email = String.valueOf(ds.child("email").getValue());
                    balance = String.valueOf(ds.child("balance").getValue());
                    balance_show.setText("Баланс: "+balance+" бонусов");
                    email_show.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
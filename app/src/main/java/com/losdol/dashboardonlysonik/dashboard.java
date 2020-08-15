package com.losdol.dashboardonlysonik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class dashboard extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    void showSensorsData(long pH, long TDS, long LevelAir, long SuhuAir){
        TextView tdsText = (TextView) findViewById(R.id.tdsvalueTV);
        TextView phText = (TextView) findViewById(R.id.pHTV);
        TextView waterLevelText = (TextView) findViewById(R.id.watlevelTV);
        TextView suhuAirText = (TextView) findViewById(R.id.suhuTV);

        suhuAirText.setText(getString(R.string.suhuVal, SuhuAir));
        phText.setText(getString(R.string.pHval, pH));
        tdsText.setText(getString(R.string.tdsval, TDS));
        waterLevelText.setText(getString(R.string.watlevval, LevelAir));
    }

    void showUser(String username){
        TextView usernameTextView = (TextView) findViewById(R.id.welcomeUser);
        TextView dateTextView = (TextView) findViewById(R.id.tanggal);

        usernameTextView.setText(getString(R.string.stringUsername, username));
        dateTextView.setText(getString(R.string.stringTanggal, new SimpleDateFormat("EEEE, d LLLL", Locale.getDefault()).format(new Date())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("device-configs").document("sonik32");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        deviceData data = task.getResult().toObject(deviceData.class);

                        Log.d("Firestore", "TDS: " + data.getSensors().get("TDS"));
                        Log.d("Firestore", "pH: " + data.getSensors().get("pH"));
                        Log.d("Firestore", "Water Level: " + data.getSensors().get("Level Air"));
                        Log.d("Firestore", "Water Temp: " + data.getSensors().get("Suhu Air"));

                        showSensorsData(data.getSensors().get("pH"),
                                   data.getSensors().get("TDS"),
                                data.getSensors().get("Level Air"),
                                data.getSensors().get("Suhu Air"));
                        showUser(data.getUsername());
                    } else {
                        Log.d("Firestore", "No such document");
                    }
                } else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w("FirestoreFail", "Failed to listen from firestore", error);
                    return;
                }

                if(snapshot != null && snapshot.exists()){
                    //do something Penting nak iki
                    deviceData data = snapshot.toObject(deviceData.class);
                    showSensorsData(data.getSensors().get("pH"),
                            data.getSensors().get("TDS"),
                            data.getSensors().get("Level Air"),
                            data.getSensors().get("Suhu Air"));
                }
                else{
                    Log.d("Firebase", "Null");
                }

            }
        });
    }
}

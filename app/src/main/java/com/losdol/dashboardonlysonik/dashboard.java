package com.losdol.dashboardonlysonik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import android.graphics.Matrix;
import android.widget.ImageView;


public class dashboard extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Inisialisasi LineChart
    LineChart plant_growth_index_Graph;
    int i = 0;
    ArrayList<Entry> values = new ArrayList<>();

    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    void showUser(String username){
        TextView usernameTextView = (TextView) findViewById(R.id.welcomeUser);
        TextView dateTextView = (TextView) findViewById(R.id.tanggal);

        usernameTextView.setText(getString(R.string.stringUsername, username));
        dateTextView.setText(getString(R.string.stringTanggal, new SimpleDateFormat("EEEE, d LLLL", Locale.getDefault()).format(new Date())));
    }

    void showSensorsData(float pH, float TDS, float LevelAir, float SuhuAir, float kelembaban, float suhuUdara, float pgi){
        TextView tdsText = (TextView) findViewById(R.id.nilai_TDS);
        TextView phText = (TextView) findViewById(R.id.nilai_pH);
        TextView waterLevelText = (TextView) findViewById(R.id.nilai_LevelAir);
        TextView suhuAirText = (TextView) findViewById(R.id.nilai_Suhu_Air);
        TextView kelembabanText = (TextView) findViewById(R.id.nilai_kelembaban);
        TextView suhuUdaraText = (TextView) findViewById(R.id.nilai_Suhu_Udara);
        TextView pgiText = (TextView) findViewById(R.id.nilai_pgi);

        suhuAirText.setText(String.format("%.0f", SuhuAir));
        phText.setText(String.format("%.2f", pH));
        tdsText.setText(String.format("%.0f",TDS));
        waterLevelText.setText(String.format("%.0f", LevelAir));
        kelembabanText.setText(String.format("%.0f",kelembaban));
        suhuUdaraText.setText(String.format("%.0f",suhuUdara));
        pgiText.setText(String.format("%.0f", pgi));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView img = findViewById(R.id.gambar_tumbuhan);
        img.setImageResource(R.drawable.testimg);

        //Setup for line chart
        plant_growth_index_Graph = findViewById(R.id.PGI_Chart);
        plant_growth_index_Graph.getDescription().setEnabled(false);
        plant_growth_index_Graph.setDrawGridBackground(false);
        plant_growth_index_Graph.setBackgroundColor(Color.alpha(0));

//        for(int i = 0; i < 21; i++){
//            values.add(new Entry(i, new Random().nextInt((100 - 0) + 1) + 0));
//        }

        TextView pgiTextView = (TextView) findViewById(R.id.nilai_pgi);


        plant_growth_index_Graph.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        plant_growth_index_Graph.getAxisLeft().setEnabled(false);
        plant_growth_index_Graph.getAxisRight().setEnabled(false);
        plant_growth_index_Graph.getXAxis().setDrawAxisLine(false);
        plant_growth_index_Graph.getLegend().setEnabled(false);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("device-configs").document("sonik32");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        deviceData data = task.getResult().toObject(deviceData.class);
                        showUser(data.getUsername());
                        showSensorsData(data.getpH(),
                                data.getTDS(),
                                data.getLevel_Air(),
                                data.getSuhu_Air(),
                                data.getKelembaban(),
                                data.getSuhu_Udara(),
                                data.getLeaf_area());
                        Log.d("Firestore", "TDS: " + data.getTDS());
                        Log.d("Firestore", "pH: " + data.getpH());
                        Log.d("Firestore", "Water Level: " + data.getLevel_Air());
                        Log.d("Firestore", "Water Temp: " + data.getSuhu_Air());
                        double pgi_double = data.getPgi();
                        float pgi = (float) pgi_double;
                        values.add(new Entry(13, 11.62f));
                        values.add(new Entry(14, 14.34f));
                        values.add(new Entry(15, 16.22f));
                        values.add(new Entry(16, 17.77f));
                        values.add(new Entry(17, 20.11f));
                        values.add(new Entry(18, 22.14f));
                        values.add(new Entry(19, 24.33f));
                        values.add(new Entry(20, 25.97f));
                        values.add(new Entry(21, 27.35f));
                        values.add(new Entry(22, 28.38f));
                        values.add(new Entry(23, 29.25f));
                        LineDataSet phData;
                        if (plant_growth_index_Graph.getData() != null &&
                                plant_growth_index_Graph.getData().getDataSetCount() > 0) {
                            phData = (LineDataSet) plant_growth_index_Graph.getData().getDataSetByIndex(0);
                            phData.setValues(values);
                            plant_growth_index_Graph.getData().notifyDataChanged();
                            plant_growth_index_Graph.notifyDataSetChanged();
                            plant_growth_index_Graph.invalidate();
                        } else {
                            phData = new LineDataSet(values, "Sample Data");
                            phData.setDrawValues(false);
                            phData.setDrawIcons(false);
                            phData.setColor(Color.DKGRAY);;
                            phData.setLineWidth(1f);
                            phData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                            phData.setCircleRadius(3f);
                            phData.setDrawCircleHole(false);
                            phData.setDrawFilled(false);
                            phData.setFormLineWidth(1f);
                            phData.setFillColor(Color.DKGRAY);

                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(phData);
                            LineData data1 = new LineData(dataSets);
                            plant_growth_index_Graph.setData(data1);
                            plant_growth_index_Graph.invalidate();
                        }
                        i++;

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
                    showSensorsData(data.getpH(),
                            data.getTDS(),
                            data.getLevel_Air(),
                            data.getSuhu_Air(),
                            data.getKelembaban(),
                            data.getSuhu_Udara(),
                            data.getLeaf_area());

                    //double pgi_double = data.getPgi();
                    //float pgi = (float) pgi_double;
                    //values.add(new Entry(i, pgi));
                    LineDataSet phData;
                    if (plant_growth_index_Graph.getData() != null &&
                            plant_growth_index_Graph.getData().getDataSetCount() > 0) {
                        phData = (LineDataSet) plant_growth_index_Graph.getData().getDataSetByIndex(0);
                        phData.setValues(values);
                        plant_growth_index_Graph.getData().notifyDataChanged();
                        plant_growth_index_Graph.notifyDataSetChanged();
                        plant_growth_index_Graph.invalidate();
                    } else {
                        phData = new LineDataSet(values, "Sample Data");
                        phData.setDrawValues(false);
                        phData.setDrawIcons(false);
                        phData.setColor(Color.DKGRAY);;
                        phData.setLineWidth(1f);
                        phData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                        phData.setCircleRadius(3f);
                        phData.setDrawCircleHole(false);
                        phData.setDrawFilled(false);
                        phData.setFormLineWidth(1f);
                        phData.setFillColor(Color.DKGRAY);

                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(phData);
                        LineData data1 = new LineData(dataSets);
                        plant_growth_index_Graph.setData(data1);
                        plant_growth_index_Graph.invalidate();
                    }
                    i++;
                }
                else{
                    Log.d("Firebase", "Null");
                }
            }
        });
    }
}

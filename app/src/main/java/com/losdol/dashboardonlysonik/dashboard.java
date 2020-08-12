package com.losdol.dashboardonlysonik;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;


public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final TextView welcome = (TextView) findViewById(R.id.welcomeUser);
        final TextView tanggal = (TextView) findViewById(R.id.tanggal);

        final TextView tdsText = (TextView) findViewById(R.id.tdsvalueTV);
        final TextView phText = (TextView) findViewById(R.id.pHTV);
        final TextView waterLevelText = (TextView) findViewById(R.id.watlevelTV);
        final TextView suhuAirText = (TextView) findViewById(R.id.suhuTV);

        String username = "Virna";
        Integer suhu = 25;
        Integer tds = 23;
        Integer ph = 21;
        Integer levelAir = 12;

        String ambilTanggal = getString(R.string.stringTanggal,  new SimpleDateFormat("EEEE, d LLLL", Locale.getDefault()).format(new Date()));
        String textWelcome = getString(R.string.stringUsername, username);
        String textSuhu = getString(R.string.suhuVal, suhu);
        String textPH = getString(R.string.pHval, ph);
        String textWatLev = getString(R.string.watlevval, levelAir);
        String textTDS = getString(R.string.tdsval, tds);

        welcome.setText(textWelcome);
        tanggal.setText(ambilTanggal);
        tdsText.setText(textTDS);
        phText.setText(textPH);
        waterLevelText.setText(textWatLev);
        suhuAirText.setText(textSuhu);
    }
}

package com.losdol.dashboardonlysonik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class pick_plant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_plant);
        Spinner spinner = findViewById(R.id.pick_plant);
        List<String> list = new ArrayList<String>();
        list.add("Bayam");
        list.add("Kangkung");
        list.add("Sawi");
        list.add("Sawi Sendok");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button enterButton = findViewById(R.id.enter_pick_plant);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pick_plant.this, dashboard.class));
                finish();
            }
        });
    }
}

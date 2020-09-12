package com.losdol.dashboardonlysonik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ready_to_setup_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_setup_screen);
        Button enterButton = findViewById(R.id.enter_ready_setup);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ready_to_setup_screen.this, name_device_login.class));
                finish();
            }
        });
    }
}

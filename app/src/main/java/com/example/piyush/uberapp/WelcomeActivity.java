package com.example.piyush.uberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button driver,customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        driver=findViewById(R.id.welcome_driver);
        customer=findViewById(R.id.welcome_customer);

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WelcomeActivity.this,DriverActivity.class);
                startActivity(i);
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(WelcomeActivity.this,CustomerActivity.class);
                startActivity(i1);
            }
        });
    }
}

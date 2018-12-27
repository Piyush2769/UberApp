package com.example.piyush.uberapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverActivity extends AppCompatActivity {

    private Button d_login_button,d_register_button;
    private TextView driver_status,driver_reg_link;
    private EditText email_driver,pass_driver;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        d_login_button=findViewById(R.id.driver_login);
        d_register_button=findViewById(R.id.driver_register);
        driver_reg_link=findViewById(R.id.driver_register_link);
        driver_status=findViewById(R.id.driver_status);
        pass_driver=findViewById(R.id.password_driver);
        email_driver=findViewById(R.id.email_driver);
        loadingBar=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();

        d_register_button.setVisibility(View.INVISIBLE);
        d_register_button.setEnabled(false);

        driver_reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_login_button.setVisibility(View.INVISIBLE);
                driver_reg_link.setVisibility(View.INVISIBLE);
                driver_status.setText("Register Driver");

                d_register_button.setVisibility(View.VISIBLE);
                d_register_button.setEnabled(true);
            }
        });

        d_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_driver.getText().toString();
                String password=pass_driver.getText().toString();

                RegisterDriver(email,password);
            }
        });

        d_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_driver.getText().toString();
                String password=pass_driver.getText().toString();

                SignInDriver(email,password);
            }
        });

    }

    private void SignInDriver(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please Wait while we are signing...");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(DriverActivity.this, "Driver Login Successful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        Toast.makeText(DriverActivity.this, "Signing in Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }


    private void RegisterDriver(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Driver Registration");
            loadingBar.setMessage("Please Wait while we are creating...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(DriverActivity.this, "Driver registration Successful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        Toast.makeText(DriverActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }
}


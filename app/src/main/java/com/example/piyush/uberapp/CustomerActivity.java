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

public class CustomerActivity extends AppCompatActivity
{


    private Button login_button, register_button;
    private TextView customer_status, customer_reg_link;
    private EditText email_customer, pass_customer;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        login_button = findViewById(R.id.customer_login_btn);
        register_button = findViewById(R.id.customer_register_btn);
        customer_reg_link = findViewById(R.id.register_customer_link);
        customer_status = findViewById(R.id.customer_status);
        pass_customer = findViewById(R.id.password_customer);
        email_customer = findViewById(R.id.email_customer);
        loadingBar = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        register_button.setVisibility(View.INVISIBLE);
        register_button.setEnabled(false);

        customer_reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_button.setVisibility(View.INVISIBLE);
                customer_reg_link.setVisibility(View.INVISIBLE);
                customer_status.setText("Register Customer");

                register_button.setVisibility(View.VISIBLE);
                register_button.setEnabled(true);
            }
        });


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_customer.getText().toString();
                String password = pass_customer.getText().toString();

                RegisterCustomer(email, password);
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_customer.getText().toString();
                String password = pass_customer.getText().toString();

                SignInCustomer(email, password);
            }
        });

    }

    private void SignInCustomer(String email, String password)
    {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Customer SignIn");
            loadingBar.setMessage("Please Wait while we are signing...");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerActivity.this, "Customer login Successful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else {
                        Toast.makeText(CustomerActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }

    private void RegisterCustomer (String email, String password)
    {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Customer Registration");
            loadingBar.setMessage("Please Wait while we are creating...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerActivity.this, "Customer registration Successful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else {
                        Toast.makeText(CustomerActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });

        }
    }
}



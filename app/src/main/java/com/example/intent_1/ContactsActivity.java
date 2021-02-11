package com.example.intent_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactsActivity extends Activity {

    static final String KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void Call(View v)
    {
        EditText e;
        e = (EditText) findViewById(R.id.e);
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();
        Uri u = Uri.parse("tel:" + e.getText().toString());
        Intent i = new Intent(Intent.ACTION_DIAL, u);
        try
        {
            startActivity(i);
        }
        catch (SecurityException s)
        {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
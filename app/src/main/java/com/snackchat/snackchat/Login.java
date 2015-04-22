package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseObject;


public class Login extends Activity {


    private EditText username = null;
    private EditText password = null;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //initialize
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.buttonLogin);

    }

    @Override
    protected void onStart() {
        super.onStart();
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.buttonLogin);
    }

    public void loginAttempt(View view) {
        // if login attempt successful, send them to my groups screen
        // else, toast statement saying unsuccessful login
        final String givenName = username.getText().toString().trim();
        final String givenPassword = password.getText().toString().trim();

//        if (givenName.length() == 0)
//        {
//            Toast.makeText(getApplicationContext(),"Please enter a valid username", Toast.LENGTH_SHORT).show();
//        }
//        else if (givenPassword.length() == 0)
//        {
//            Toast.makeText(getApplicationContext(),"Please enter a valid password", Toast.LENGTH_SHORT).show();
//        }
//        else {
            Intent in = new Intent(getBaseContext(), GroupsPage.class);
            startActivity(in);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

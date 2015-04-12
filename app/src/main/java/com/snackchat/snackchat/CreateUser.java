package com.snackchat.snackchat;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;


public class CreateUser extends Activity {


    private static EditText fNameEditText = null;
    private static EditText lNameEditText = null;
    private static EditText emailEditText = null;
    private static EditText passwordEditText = null;
    private static EditText confirmEditText = null;
    private static String TAG = "tyler";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    public void onSubmit (View view){

        final String firstName = fNameEditText.getText().toString().trim();
        final String lastName = lNameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String confPass = confirmEditText.getText().toString().trim();

        //see if the info is being stored to the string above when the submit button is pressed
        Log.d(TAG, firstName);

        //Verify text entered is valid
        if (firstName.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please enter your first name", Toast.LENGTH_SHORT).show();
        }
        else if (lastName.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please enter your last name", Toast.LENGTH_SHORT).show();
        }
        else if (email.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please enter your email address", Toast.LENGTH_SHORT).show();
        }
        else if (password.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please enter a password", Toast.LENGTH_SHORT).show();
        }
        else if (confPass.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please confirm your password", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confPass))
        {
            confirmEditText.requestFocus();
            Toast.makeText(getApplicationContext(),"Your passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else if (!email.contains("@"))
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid email address", Toast.LENGTH_SHORT).show();
        }

        else{
            //code to write to the could should go here i think


        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create_user, container, false);


            //Get widgets by ID
            fNameEditText = (EditText) rootView.findViewById(R.id.firstName);
            lNameEditText = (EditText) rootView.findViewById(R.id.lastName);
            emailEditText = (EditText) rootView.findViewById(R.id.email);
            passwordEditText = (EditText) rootView.findViewById(R.id.password);
            confirmEditText = (EditText) rootView.findViewById(R.id.confPass);




            return rootView;
        }
    }
}

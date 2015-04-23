package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.security.acl.Group;
import java.util.List;
import java.util.ArrayList;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;


public class CreateNewGroupScreen extends Activity {

    private Button addMemberButton;
    private Button doneButton;
    private ListView lv;
    private ArrayList <String> stringArray;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> userAdapter;
    private EditText groupName;
    private Spinner memberName;
    private ArrayList<String> users = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group_screen);


        //Get all users to select from
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, ParseException e) {
                for(int i = 0;i<parseUsers.size();i++){
                    users.add(i,parseUsers.get(i).getUsername());
                }
                memberName = (Spinner) findViewById(R.id.memberNameTextBox);
                userAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item, users );
                userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                memberName.setAdapter(userAdapter);
            }
        });

        // initialize
        addMemberButton = (Button) findViewById(R.id.addMemberButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        lv = (ListView) findViewById(R.id.itemsListView);
        groupName = (EditText) findViewById(R.id.nameTextBox);

        stringArray = new ArrayList<String>();

        adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        lv.setAdapter(adapter);
    }


    // Adds the text from the "Item" text box to the stringArray
    public void onAddedItem(View view){

        if (memberName.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(), "Member name is a blank string. Try Again.", Toast.LENGTH_SHORT).show();
        }
        else {
            // Clear the Item text box after adding it to list
            stringArray.add(memberName.getSelectedItem().toString());
            adapter.notifyDataSetChanged();
            (findViewById(R.id.memberNameTextBox)).clearFocus();
        }
    }

    // When User Hits Cancel, brings them back to My Groups Page
    public void onCancel(View view){
        // Send them back to My Groups Page
        Intent in = new Intent(getBaseContext(), GroupsPage.class);
        startActivity(in);
    }

    /*
      Stores the given name (as submitName),
      the given Max Price (as submitMaxPrice),
      and the Array (as SubmitList)
    */
    public void onDone(View view){

        List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();
        for(String s : stringArray){
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("username", s);
            queries.add(query);
        }

        ParseQuery<ParseUser> mainQuery = ParseQuery.or(queries);

        mainQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, ParseException e) {
                ParseObject group = new ParseObject("Group");
                group.put("name", groupName.getText().toString());
                group.put("createdBy", ParseUser.getCurrentUser());

                ParseRelation<ParseObject> relation = group.getRelation("members");
                relation.add(ParseUser.getCurrentUser());
                for(ParseUser p : parseUsers){
                    relation.add(p);
                }

                group.saveInBackground();
            }
        });


        Intent in = new Intent(getBaseContext(), GroupsPage.class);
        startActivity(in);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_group_screen, menu);
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

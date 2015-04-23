package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ListsInGroup extends Activity {

    private List<String> listsInGroup = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private Button createNewListButton;
    private Button logoutButton;
    private String groupTitle;
    private TextView listsInGroupTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_in_group);

        populateLists();

        //Set Group title
        listsInGroupTitle = (TextView)findViewById(R.id.listsInGroupTitle);

        String title = getIntent().getExtras().getString("title");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Group");
        query.whereEqualTo("name", title);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                groupTitle= parseObject.get("name").toString();
                listsInGroupTitle.setText(groupTitle);
            }
        });




        // initialize
        createNewListButton = (Button) findViewById(R.id.createListButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        lv = (ListView) findViewById(R.id.allLists);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listsInGroup);
        lv.setAdapter(adapter);
    }

    private void populateLists(){

        //Query lists from Parse that you belong to
        ParseUser me = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lists");
        query.whereEqualTo("members", me);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseUsers, ParseException e) {
                for(ParseObject p : parseUsers){
                    listsInGroup.add(p.get("name").toString());
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listsInGroup);
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lists_in_group, menu);
        return true;
    }

    // When user hits Create A New List, brings them to Create New List Page
    public void onCreateNewList(View view){
        // Send them to Create New List Page
        Intent in = new Intent(getBaseContext(), CreateNewList.class);
        startActivity(in);
    }

    // When User Hits Logout, brings them back to Welcome Page
    public void onLogout(View view){
        // Send them back to Welcome Page
        Intent in = new Intent(getBaseContext(), Welcome.class);
        startActivity(in);
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

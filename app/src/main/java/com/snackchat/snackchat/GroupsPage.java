package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;




public class GroupsPage extends Activity {

    private List<String> myGroups = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private Button createNewGroupButton;
    private Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_page);

        populateGroupList();

        // initialize
        createNewGroupButton = (Button) findViewById(R.id.createGroupButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        lv = (ListView) findViewById(R.id.groupsListView);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,myGroups);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClick(position);
            }
        });


        ParseUser user = ParseUser.getCurrentUser();

    }

    private void itemClick(Integer titlePos) {
        Intent intent = new Intent(getBaseContext(),ListsInGroup.class);
        intent.putExtra("title", myGroups.get(titlePos));
        startActivity(intent);
    }

    private void populateGroupList(){

        ParseUser me = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Group");
        query.whereEqualTo("members", me);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseUsers, ParseException e) {
                for(ParseObject p : parseUsers){
                    myGroups.add(p.get("name").toString());
                }
            }
        });

    }

    // When user hits Create A New Group, brings them to Create New Group Page
    public void onCreateNewGroup(View view){
        // Send them to Create New Group Page
        Intent in = new Intent(getBaseContext(), CreateNewGroupScreen.class);
        startActivity(in);
    }


    // When User Hits Logout, brings them back to Welcome Page
    public void onLogout(View view){
        // Send them back to Welcome Page
        Intent in = new Intent(getBaseContext(), Welcome.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_groups_page, menu);
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

        Log.d("CLICK", "ITEM" + id);
        return super.onOptionsItemSelected(item);
    }
}

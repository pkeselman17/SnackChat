package com.snackchat.snackchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListsInGroup extends Activity {

    private List<String> listsInGroup = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private Button createNewListButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_in_group);

        populateLists();

        // initialize
        createNewListButton = (Button) findViewById(R.id.createListButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        lv = (ListView) findViewById(R.id.allLists);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listsInGroup);
        lv.setAdapter(adapter);
    }

    private void populateLists(){
        listsInGroup.add("Dan's List");
        listsInGroup.add("Tommy's List");
        listsInGroup.add("Bryan's List");
        listsInGroup.add("Phil's List");
        listsInGroup.add("TJ's List");
        listsInGroup.add("Michael's List");
        listsInGroup.add("Johnny's List");
        listsInGroup.add("Zach's List");
        listsInGroup.add("Brett's List");
        listsInGroup.add("Spencer's List");
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

    }

    // When User Hits Logout, brings them back to Welcome Page
    public void onLogout(View view){
        // Send them back to Welcome Page

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

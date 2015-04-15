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




public class GroupsPage extends Activity {

    private List<String> myGroups = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_page);

        populateGroupList();

        lv = (ListView) findViewById(R.id.groupsListView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,myGroups);
        lv.setAdapter(adapter);


    }

    private void populateGroupList(){
        myGroups.add("Roommates");
        myGroups.add("Soccer Team");
        myGroups.add("Fraternity Brothers");
        myGroups.add("Boys From Home");
        myGroups.add("4471 Group");
        myGroups.add("Basketball Team");
        myGroups.add("Neighborhood");
        myGroups.add("Family");
        myGroups.add("Work Friends");
        myGroups.add("Next Year Housemates");
    }
//
//    private void populateListView(){
//        ListView list = (ListView) findViewById(R.id.groupsListView);
//        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,myGroups);
//        lv.setAdapter(adapter);
//
//    }

    // What happens when you click Create A New Group
    // Somehow Link it to Create New Group Screen
    public void onAddedItem(View view){

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

        return super.onOptionsItemSelected(item);
    }
}

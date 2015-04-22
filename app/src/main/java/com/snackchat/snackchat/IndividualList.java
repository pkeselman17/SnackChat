package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class IndividualList extends Activity {

    private List<String> itemsInList = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private CheckBox checkBox;
    private EditText totalCost;
    private Button submitButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_list);

        populateLists();

        // Initialize
        checkBox = (CheckBox) findViewById(R.id.checkBoxField);
        totalCost = (EditText) findViewById(R.id.totalCostField);
        submitButton = (Button) findViewById(R.id.submitButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        lv = (ListView) findViewById(R.id.itemsInList);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,itemsInList);
        lv.setAdapter(adapter);
    }

    private void populateLists(){
        itemsInList.add("Dole Pineapple Cups");
        itemsInList.add("Golden Oreos");
        itemsInList.add("Freschetta Frozen Pizza");
        itemsInList.add("2% Milk");
        itemsInList.add("Tropicana OJ No Pulp");
        itemsInList.add("Honey Nut Cheerios");
        itemsInList.add("Quaker Oatmeal - Maple Brown Sugar");
        itemsInList.add("Popcorn");
        itemsInList.add("English Muffins");
        itemsInList.add("Eggs");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_individual_list, menu);
        return true;
    }

    // What happens when you click Submit
    public void onSubmit(View view){
        // if box is checked, and totalCost < maxPrice
        // Remove List from the Lists in the group
        // because the List's task is now completed
        // and then Send user to ListsInGroup Page

        // totalCost < maxPrice ($50)
        String entry;
        int tc = 100;
        entry = totalCost.getText().toString();
        tc = Integer.parseInt(entry);
        if(tc > 50)
        {
            Toast.makeText(getApplicationContext(), "Sorry, Total Cost is greater than the Max Price", Toast.LENGTH_SHORT).show();
        }
        // checkbox is checked?
        else if (checkBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please check the checkbox", Toast.LENGTH_SHORT).show();
        }
        else {
            // remove list from Lists in Group
            // send user to ListsInGroup page
            Intent in = new Intent(getBaseContext(), ListsInGroup.class);
            startActivity(in);
        }


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

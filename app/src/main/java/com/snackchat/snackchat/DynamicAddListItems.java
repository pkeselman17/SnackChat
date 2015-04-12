package com.snackchat.snackchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import java.util.ArrayList;


public class DynamicAddListItems extends Activity {

    private Button addButton;
    private Button doneButton;
    private ListView lv;
    private ArrayList <String> stringArray;
    private ArrayAdapter<String> adapter;
    private EditText name;
    private EditText maxPrice;
    private EditText itemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_list_items);

        // initialize
        addButton = (Button) findViewById(R.id.addItemButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        lv = (ListView) findViewById(R.id.itemsListView);
        name = (EditText) findViewById(R.id.NameTextBox);
        maxPrice = (EditText) findViewById(R.id.MaxPriceTextBox);
        itemText = (EditText) findViewById(R.id.ItemTextBox);

        stringArray = new ArrayList<String>();

        // This isn't really needed, just tests output with a line saying "Your List"
        stringArray.add("Your List");

        adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        lv.setAdapter(adapter);
    }

    // Adds the text from the "Item" text box to the stringArray
    public void onAddedItem(View view){
        stringArray.add(itemText.getText().toString());
        adapter.notifyDataSetChanged();
        // Clear the Item text box after adding it to list
        ((EditText) findViewById(R.id.ItemTextBox)).setText("");

    }

    /*
    Stores the given name (as submitName),
    the given Max Price (as submitMaxPrice),
    and the Array (as SubmitList)
     */
    public void onDone(View view){
        String submitName = name.getText().toString().trim();
        String submitMaxPrice = maxPrice.getText().toString().trim();
        ArrayList submitList = stringArray;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dynamic_add_list_items, menu);
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

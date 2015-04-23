package com.snackchat.snackchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class CreateNewList extends Activity {

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
        setContentView(R.layout.activity_create_new_list);

        // initialize
        addButton = (Button) findViewById(R.id.addItemButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        lv = (ListView) findViewById(R.id.itemsListView);
        name = (EditText) findViewById(R.id.NameTextBox);
        maxPrice = (EditText) findViewById(R.id.MaxPriceTextBox);
        itemText = (EditText) findViewById(R.id.ItemTextBox);

        stringArray = new ArrayList<String>();

        adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        lv.setAdapter(adapter);
    }

    // Adds the text from the "Item" text box to the stringArray
    public void onAddedItem(View view){
        //Verify text entered is valid
        if (itemText.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Item Name is an empty String, Try again.", Toast.LENGTH_SHORT).show();
        }
        else {
            stringArray.add(itemText.getText().toString());
            adapter.notifyDataSetChanged();
            // Clear the Item text box after adding it to list
            ((EditText) findViewById(R.id.ItemTextBox)).setText("");
        }
    }

    // When User Hits Cancel, brings them back to the Page of the Specific Group they were on
    public void onCancel(View view){
        // Send them back to Page of the Specific Group they were on
        Intent in = new Intent(getBaseContext(), ListsInGroup.class);
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
                final ParseObject list = new ParseObject("List");
                final List<ParseObject> items = new ArrayList<ParseObject>();

                for(String s : stringArray){
                    ParseObject item = new ParseObject("Item");
                    item.put("name",s);
                    item.saveInBackground();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            list.put("name", name.getText().toString());
                            list.put("maxprice",maxPrice.getText().toString());
                            list.put("created_by", ParseUser.getCurrentUser().getUsername());

                            ParseRelation<ParseObject> relation = list.getRelation("Items");
                            for(ParseObject p : items){
                                relation.add(p);
                            }
                        }
                    });

                }




                list.saveInBackground();




            }
        });


        Intent in = new Intent(getBaseContext(), GroupsPage.class);
        startActivity(in);

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

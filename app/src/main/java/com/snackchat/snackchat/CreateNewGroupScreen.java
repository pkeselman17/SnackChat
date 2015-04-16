package com.snackchat.snackchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;



public class CreateNewGroupScreen extends Activity {

    private Button addMemberButton;
    private Button doneButton;
    private ListView lv;
    private ArrayList <String> stringArray;
    private ArrayAdapter<String> adapter;
    private EditText groupName;
    private EditText memberName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group_screen);

        // initialize
        addMemberButton = (Button) findViewById(R.id.addMemberButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        lv = (ListView) findViewById(R.id.itemsListView);
        groupName = (EditText) findViewById(R.id.nameTextBox);
        memberName = (EditText) findViewById(R.id.memberNameTextBox);

        stringArray = new ArrayList<String>();

        // This isn't really needed, just tests output with a line saying "Your List"
        stringArray.add("Your Group");

        adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        lv.setAdapter(adapter);
    }

    // Adds the text from the "Item" text box to the stringArray
    public void onAddedItem(View view){

        if (memberName.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Member name is a blank string. Try Again.", Toast.LENGTH_SHORT).show();
        }
        else {
            // Clear the Item text box after adding it to list
            stringArray.add(memberName.getText().toString());
            adapter.notifyDataSetChanged();
            ((EditText) findViewById(R.id.memberNameTextBox)).setText("");
        }
    }

    // When User Hits Cancel, brings them back to My Groups Page
    public void onCancel(View view){
        // Send them back to My Groups Page

    }

    /*
      Stores the given name (as submitName),
      the given Max Price (as submitMaxPrice),
      and the Array (as SubmitList)
    */
    public void onDone(View view){
        String groupTitle = groupName.getText().toString().trim();
        // Start groupMemberList at position 1 because spot 0 has "Your Group"
        List groupMemberList = stringArray.subList(1, stringArray.size());
        //Verify text entered is valid
        if (groupTitle.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Please enter a Group name", Toast.LENGTH_SHORT).show();
        }
        else if (groupMemberList.size() == 0)
        {
            Toast.makeText(getApplicationContext(),"Please add members to the group", Toast.LENGTH_SHORT).show();
        }

        // ADD groupTitle and groupMemberList to My Groups
        // Send them back to the My Groups page they were on

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

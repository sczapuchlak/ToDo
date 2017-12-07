package com.sczapuchlak.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ListView listView = null;
    ArrayAdapter<String> adapter = null;
    DBOpenHelper dbHelper = null;
    EditText textbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbHelper = new DBOpenHelper(this, "ToDo", null, 1);
        items = dbHelper.getAll();

        listView = (ListView) findViewById(R.id.list_view);
        textbox = (EditText) findViewById(R.id.userInfo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = textbox.getText().toString();
                addItem(item);
                Snackbar.make(view, item+ " has been added!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new UndoListener()).show();
            }
        }
        );
    }
            private void addItem(String item){
                dbHelper.add(item);
                items.add(item);
                adapter.notifyDataSetChanged();

            }


    class UndoListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            items.remove(items.size()-1);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

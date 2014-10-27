package com.example.sqlitedatabase;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_create_contract = (Button) findViewById(R.id.btn_create_contract);
		final DBAdapter db = new DBAdapter(this);
		btn_create_contract.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.open();
				long id = db.insertContact("Hoang Van Quan1", "hoang.van.quan@framgia.com");
				id = db.insertContact("Hoang Van Quan2", "quandhcn@gmail.com");
				Toast.makeText(getBaseContext(), "contacts created!", Toast.LENGTH_SHORT).show();
				db.close();
			}
		});
		
		Button show_contracts = (Button) findViewById(R.id.show_contracts);
		show_contracts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.open();
		        Cursor c = db.getAllContacts();
		        if (c.moveToFirst())
		        {
		            do {
		                DisplayContact(c);
		            } while (c.moveToNext());
		        }
		        db.close();
			}
		});
		
		Button update_contract = (Button) findViewById(R.id.update_contract);
		update_contract.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.open();
		        if (db.updateContact(1, "Quan Hoang van", "edit@gmail.com")){
		            Toast.makeText(getBaseContext(), "Update successful.", Toast.LENGTH_LONG).show();
		        	Cursor c = db.getContact(1);
		        	DisplayContact(c);
		        }
		        else
		            {Toast.makeText(getBaseContext(), "Update failed.", Toast.LENGTH_LONG).show();}
		        db.close();

			}
		});
		
		Button delete_contract = (Button) findViewById(R.id.delete_contract);
		delete_contract.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.open();
		        if (db.deleteContact(1))
		            Toast.makeText(getBaseContext(), "Delete successful.", Toast.LENGTH_LONG).show();
		        else
		            Toast.makeText(getBaseContext(), "Delete failed.", Toast.LENGTH_LONG).show();
		        db.close();

			}
		});
	}
	public void DisplayContact(Cursor c)
	{
        Toast.makeText(getBaseContext(),
                "id: " + c.getString(0) + "\n" + 
                "Name: " + c.getString(1) + "\n" +
                "Email: " + c.getString(2),
                Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

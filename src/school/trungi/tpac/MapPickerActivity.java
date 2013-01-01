package school.trungi.tpac;

import java.util.ArrayList;

import school.trungi.tpac.game.MapAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MapPickerActivity extends Activity {

	String[] list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_picker);
		
		ListView MapList = (ListView) findViewById(R.id.map_list);
		list = fileList();
		ArrayAdapter<String> adapter = new MapAdapter(this, list);
		
		MapList.setAdapter(adapter);
		
		final Context c = this;
		
		MapList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startGame(arg2);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map_picker, menu);
		return true;
	}

	public void startGame(int map) {
		Intent intent = new Intent(this, GameActivity.class);
		
		intent.putExtra("map_name", list[map]);
		intent.putExtra("type", map);
		
		startActivity(intent);
		finish();
	}
}

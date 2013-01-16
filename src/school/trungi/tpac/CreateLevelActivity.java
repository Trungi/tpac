package school.trungi.tpac;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import school.trungi.tpac.common.Map;
import school.trungi.tpac.levelBuilder.EditorButton;
import school.trungi.tpac.levelBuilder.EditorView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class CreateLevelActivity extends Activity {

	public final static String TAG = "CreateLevelActivity";
	private int y, x;
	private String levelName;
	private EditorButton b;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.editor_layout);
		
		levelName = (String) getIntent().getStringExtra("level_name");
		x = getIntent().getIntExtra("sizeX", -1);
		y = getIntent().getIntExtra("sizeY", -1);
	}
	
	public void onStart() {
		super.onStart();
		b = (EditorButton) findViewById(R.id.editor_field);
		EditorView v = (EditorView) findViewById(R.id.editor);
		v.setButton(b);
		v.setMapSize(x, y);
	}
	
	public void saveMap(View v) {
		Map map = ((EditorView) findViewById(R.id.editor)).getMap();

		try {
			FileOutputStream output = openFileOutput(levelName, Context.MODE_PRIVATE);
			
			output.write(map.toString().getBytes());
			output.close();
			
		} catch (FileNotFoundException e) {
			Toast.makeText(this, R.string.can_not_save, Toast.LENGTH_SHORT);
			return;
		} catch (IOException e) {
			Toast.makeText(this, R.string.can_not_save, Toast.LENGTH_SHORT);
			return;
		}
		
		Toast.makeText(this, R.string.map_saved, Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void incPosition(View v) {
		b.setPosition(1);
		b.invalidate();
	}
	
	public void decPosition(View v) {
		b.setPosition(-1);
		b.invalidate();
	}
}

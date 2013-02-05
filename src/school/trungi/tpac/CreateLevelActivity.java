package school.trungi.tpac;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import school.trungi.tpac.common.Map;
import school.trungi.tpac.levelBuilder.EditorButton;
import school.trungi.tpac.levelBuilder.EditorView;
import school.trungi.tpac.levelBuilder.EditorWithButtonsView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class CreateLevelActivity extends Activity {

	public final static String TAG = "CreateLevelActivity";
	private int y, x;
	private boolean buttons;
	private String levelName;
	private EditorButton b;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		levelName = (String) getIntent().getStringExtra("level_name");
		x = getIntent().getIntExtra("sizeX", -1);
		y = getIntent().getIntExtra("sizeY", -1);
		buttons = getIntent().getBooleanExtra("buttons", true);
		
		if (buttons) {
			this.setContentView(R.layout.editor_with_buttons);
			ewb = (EditorWithButtonsView) findViewById(R.id.editor);
		} else {
			this.setContentView(R.layout.editor_no_buttons);
		}
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

		boolean startGhost = false, startPacman = false, food = false;
		
		for (int i=0; i<map.getM(); i++) {
			for (int j=0; j<map.getN(); j++) {
				if (map.get(i, j).isGhost()) {
					startGhost = true;
				}
				if (map.get(i, j).isPacman()) {
					startPacman = true;
				}
				if (map.get(i, j).isFood()) {
					food = true;
				}
			}
		}
		
		if (!startGhost) {
			Toast.makeText(this, "Please define starting point for ghosts", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!startPacman) {
			Toast.makeText(this, "Please define starting point for pacman", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!food) {
			Toast.makeText(this, "Please insert at least one food for pacman", Toast.LENGTH_SHORT).show();
			return;
		}
		
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
		if (buttons) ewb.invalidate();
	}
	
	public void decPosition(View v) {
		b.setPosition(-1);
		b.invalidate();
		if (buttons) ewb.invalidate();
	}
	
	/////////
	EditorWithButtonsView ewb;
	
	public void clickUp(View v) { 
		ewb.clickUp();
	}
	
	public void clickLeft(View v) {
		ewb.clickLeft();
	}
	
	public void clickRight(View v) {
		ewb.clickRight();
	}
	
	public void clickDown(View v) {
		ewb.clickDown();
	}
	
	public void clickConfirm(View v) {
		ewb.clickConfirm();
	}
}

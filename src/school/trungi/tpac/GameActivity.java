package school.trungi.tpac;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import school.trungi.tpac.game.GameView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	private GameView game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		game = (GameView) findViewById(R.id.game);
		String mapName = getIntent().getStringExtra("map_name");
		
		try {
			FileInputStream input = openFileInput(mapName);
		
			byte buffer[] = new byte[1024];
			StringBuffer content = new StringBuffer();
			int length;
		
			length = input.read(buffer);
			while (length != -1) {
				content.append(new String(buffer));
				length = input.read(buffer);
			}
		
			game.loadMap(content.toString());
		} catch (FileNotFoundException e) {
			Toast.makeText(this, R.string.not_found, Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(this, R.string.not_readable, Toast.LENGTH_SHORT).show();			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

}

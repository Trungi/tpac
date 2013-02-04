package school.trungi.tpac;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import school.trungi.tpac.game.FinishView;
import school.trungi.tpac.game.GameView;
import school.trungi.tpac.game.StatsView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	private GameView game;
	private Timer autoUpdate;
	private int ms = 200;

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

		FileInputStream in = null;
		try {
			in = openFileInput(mapName);
			game.loadMap(in);
			
			in.close();
			

			game.init((StatsView) findViewById(R.id.stats), this);
		} catch (FileNotFoundException e) {
			Toast.makeText(this, R.string.not_found, Toast.LENGTH_SHORT).show();
			finish();
		} catch (IOException e) {
			finish();
		}
	}
	
	@Override
	public void onResume() {
		 super.onResume();
		 autoUpdate = new Timer();
		 autoUpdate.schedule(new TimerTask() {
			 	@Override
			 	public void run() {
			 		runOnUiThread(new Runnable() {
			 			public void run() {
			 				redraw();
			 			}
			 		});
			 	}
		 }, 0, ms); 
	 }
	 
	@Override
	public void onPause() {
		autoUpdate.cancel();
		super.onPause();
	}

	 public void redraw() {
		 game.redraw();
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

	public void finishGame(long score, int lives) {
		Intent intent = new Intent(this, StatsActivity.class);
		
		intent.putExtra("score", score);
		intent.putExtra("lives", lives);
		
		startActivity(intent);
		finish();
	}
	
}

package school.trungi.tpac;

import java.util.Timer;
import java.util.TimerTask;

import school.trungi.tpac.game.FinishView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class StatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_finished);
		finishGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_stats, menu);
		return true;
	}


	public void finishGame() {
		long score = getIntent().getLongExtra("score", -1);
		int lives = getIntent().getIntExtra("lives", -1);
		
		final FinishView fv = (FinishView) findViewById(R.id.finish_stats);
	
		fv.init(lives, score);
		fv.invalidate();
	
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						fv.inc();
						fv.invalidate();
						if (fv.get() >= 5) {
							finishStats();
							cancel();
						}
					}
				});
			}
		}, 1000, 1000); 

}

	public void finishStats() {
		Intent intent = new Intent(this, MapPickerActivity.class);
		startActivity(intent);
		finish();
	}

}

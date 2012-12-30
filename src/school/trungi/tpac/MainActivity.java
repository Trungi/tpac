package school.trungi.tpac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void createNewLevel(View v) {
    	Intent intent = new Intent(this, CreateLevelActivity.class);
    	startActivity(intent);
    }
    
    public void notImplementedYet(View v) {
    	Toast.makeText(this, R.string.not_implemented_yet, Toast.LENGTH_SHORT).show();
    }
}

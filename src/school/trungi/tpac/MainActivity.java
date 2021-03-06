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
    	Intent intent = new Intent(this, EditorWelcomeActivity.class);
    	
    	// this is just a debug hack
    	/*
    	Intent intent = new Intent(this, CreateLevelActivity.class);
		intent.putExtra("level_name", "new name");
		intent.putExtra("sizeX", 25);
		intent.putExtra("sizeY", 25);
		intent.putExtra("buttons", false);
    	*/
		
    	startActivity(intent);
    }
    
    public void pickMap(View v) {
    	Intent intent = new Intent(this, MapPickerActivity.class);
    	startActivity(intent);
    }
    
    public void showAbout(View v) {
    	Intent intent = new Intent(this, AboutActivity.class);
    	startActivity(intent);
    }
    
    public void notImplementedYet(View v) {
    	Toast.makeText(this, R.string.not_implemented_yet, Toast.LENGTH_SHORT).show();
    }
    
}

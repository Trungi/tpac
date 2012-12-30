package school.trungi.tpac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditorWelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor_welcome);
	}
	
	public void createLevel(View v) {
		Intent intent = new Intent(this, CreateLevelActivity.class);
		EditText name = ((EditText)findViewById(R.id.level_name));
		EditText sizeX = ((EditText)findViewById(R.id.sizeX));
		EditText sizeY = ((EditText)findViewById(R.id.sizeY));
		
		intent.putExtra("level_name", name.getText().toString());
		intent.putExtra("sizeX", sizeX.getText().toString());
		intent.putExtra("sizeY", sizeY.getText().toString());
		
    	startActivity(intent);
    	finish();
	}
}

package school.trungi.tpac;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditorWelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor_welcome);
	}
	
	public void createLevel(View v) {
		Intent intent = new Intent(this, CreateLevelActivity.class);
		EditText name = ((EditText)findViewById(R.id.level_name));
		EditText x = ((EditText)findViewById(R.id.sizeX));
		EditText y = ((EditText)findViewById(R.id.sizeY));
		CheckBox button = ((CheckBox)findViewById(R.id.show_arrows));
		
		if (name.length() == 0) {
			Toast.makeText(this, R.string.name_too_short, Toast.LENGTH_SHORT).show();
			return;
		}
		if (name.length() > 20) {
			Toast.makeText(this, R.string.name_too_long, Toast.LENGTH_SHORT).show();
			return;
		}
		if (x.getText().toString().length() == 0 || y.getText().toString().length() == 0) {
			Toast.makeText(this, R.string.enter_dimension, Toast.LENGTH_SHORT).show();
			return;
		}
		if (x.getText().toString().length() > 50 || y.getText().toString().length() > 50) {
			Toast.makeText(this, R.string.map_too_big, Toast.LENGTH_SHORT).show();
			return;
		}
		if (getFileStreamPath(name.getText().toString()).exists()) {
			Toast.makeText(this, R.string.file_exists, Toast.LENGTH_SHORT).show();
			return;
		}
		
		int sizeX = Integer.parseInt(x.getText().toString());
		int sizeY = Integer.parseInt(y.getText().toString());
		
		intent.putExtra("level_name", name.getText().toString());
		intent.putExtra("sizeX", sizeX);
		intent.putExtra("sizeY", sizeY);
		intent.putExtra("buttons", button.isChecked());
		
    	startActivity(intent);
    	finish();
	}
}

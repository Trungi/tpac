package school.trungi.tpac;

import common.Map;

import school.trungi.tpac.levelBuilder.EditorButton;
import school.trungi.tpac.levelBuilder.EditorView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateLevelActivity extends Activity {

	public final static String TAG = "CreateLevelActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.editor_layout);
	}
	
	public void onStart() {
		super.onStart();
		EditorButton b = (EditorButton) findViewById(R.id.editor_field);
		EditorView v = (EditorView) findViewById(R.id.editor);
		v.setButton(b);
	}
	
	public void saveMap(View v) {
		Map map = ((EditorView) findViewById(R.id.editor)).getMap();

		Toast.makeText(this, R.string.map_saved, Toast.LENGTH_SHORT).show();
		finish();
	}
}

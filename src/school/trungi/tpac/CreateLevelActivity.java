package school.trungi.tpac;

import school.trungi.tpac.levelBuilder.EditorButton;
import school.trungi.tpac.levelBuilder.EditorView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreateLevelActivity extends Activity {

	public final static String TAG = "CreateLevelActivity";
	private EditorView editor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//editor = (EditorView)this.findViewById(R.id.editor);
		
		this.setContentView(R.layout.editor_layout);
	}
	
	public void incButton(View v) { 
		EditorButton b = (EditorButton) v;
		b.position++;
		b.invalidate();
	}
}

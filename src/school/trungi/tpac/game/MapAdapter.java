package school.trungi.tpac.game;

import java.util.ArrayList;

import school.trungi.tpac.R;
import android.content.Context;
import android.widget.ArrayAdapter;

public class MapAdapter extends ArrayAdapter<String> {

	public MapAdapter(Context context, String[] list) {
		super(context, R.layout.map_list_row, new ArrayList<String>());
		
		for (String str : list) {
			this.add(str);
		}
	}


}

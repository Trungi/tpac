package school.trungi.tpac.common;

import school.trungi.tpac.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class BoxTypes {
	public static final char list[] = {'E', 'b', 'B', 'F', 'P', 'G' };
	public static final int Rlist[] = {R.drawable.empty, R.drawable.block, 
		R.drawable.berry, R.drawable.food, R.drawable.pacman_spawn, R.drawable.ghost_spawn, };
	public Bitmap bitmaps[];
	
	public static final int EMPTY_BOX = 0;
	public static final int INVISIBLE_BOX = -1;
	public static final int FOOD = 3;
	public static final int BLOCK = 1;
	
	public BoxTypes(Resources r,int size, int bigSize) {
        bitmaps = new Bitmap[Rlist.length];
        
		for (int i=0; i<Rlist.length; i++) {
			Bitmap bitmap;
			
			if (list[i] > 'Z') {
				bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			} else {
				bitmap = Bitmap.createBitmap(bigSize, bigSize, Bitmap.Config.ARGB_8888);
			}
			
	        Canvas canvas = new Canvas(bitmap);
	        Drawable tile = r.getDrawable(Rlist[i]);

			if (list[i] > 'Z') {
				tile.setBounds(0, 0, size, size);
			} else {
				tile.setBounds(0, 0, bigSize, bigSize);
			}
			
	        tile.draw(canvas);
	        bitmaps[i] = bitmap;
		}
	}
}

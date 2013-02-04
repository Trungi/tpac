package school.trungi.tpac.game;

import school.trungi.tpac.common.Map;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StatsView extends View {

	protected Map map;
	protected int lives = 3;
	protected int food = 0;
	protected Paint paint = new Paint();
	
	public StatsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setMap(Map map) {
		this.map = map;
		
		for (int i=0; i<map.getM(); i++) {
			for (int j=0; j<map.getN(); j++) {
				if (map.get(i, j).isFood()) {
					food++;
				}
			}
		}
	}
	
	public void eat() {
		food--;
		invalidate();
	}
	
	public void kill() {
		lives--;
		invalidate();
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getFood() {
		return food;
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(0xff101010);
		
		canvas.drawText("Lives " + lives + ", food: " + food, 20, 20, paint);
		
	}
	
}

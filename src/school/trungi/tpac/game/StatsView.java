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
	protected long score = 0;
	protected long start;
	protected Paint paint = new Paint();
	
	public StatsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		start = System.currentTimeMillis();
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
		score += ((120000 - (System.currentTimeMillis() - start)) + 30000)/10;
				
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
	
	public long getScore() {
		return score;
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(0xff101010);
		
		canvas.drawText("Lives " + lives + ", food: " + food + " score: " +
				score, 20, 20, paint);
		
	}
	
}

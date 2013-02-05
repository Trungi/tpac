package school.trungi.tpac.game;

import school.trungi.tpac.common.Map;
import android.graphics.Canvas;
import android.graphics.Paint;

public class StatsView {

	protected Map map;
	protected int lives = 3;
	protected int food = 0;
	protected long score = 0;
	protected long start;
	protected Paint paint = new Paint();

	public StatsView(Map map) {
		this.map = map;
		paint.setColor(0xff101010);
		
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
	}
	

	public void eatFruit() {
		score += 30000;
	}
	
	public void kill() {
		lives--;
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
	
	public void draw(Canvas canvas,int x, int y) {
		
		canvas.drawText("Lives " + lives + ", food: " + food + " score: " +
				score, x, y, paint);
		
	}	
}

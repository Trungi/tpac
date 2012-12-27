package common;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BoxRenderer {

	Paint paint = new Paint();
	
	public void render(Canvas c, char param) {
		paint.setColor(0xFFFFFF);
		c.drawRect(0, 0, 1000, 1000, paint);
		c.drawText(param + "", 10, 10, paint);
		c.drawText(param + "sadfasdfasdfas fsad fasd fasdsdafasdfsadfasdfsafasdfsa" +
				"dfasdfasdfasdfasdfasdfasfasdf", 10, 10, paint);
	}
}

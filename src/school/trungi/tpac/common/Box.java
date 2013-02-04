package school.trungi.tpac.common;

public class Box {
	int item;
	
	public Box() {
		item = BoxTypes.EMPTY_BOX;
	}
	
	public Box(int _item) {
		item = _item;
	}
	
	@Override
	public String toString() {
		return Integer.toString(item);
	}

	public boolean isEmpty() {
		return item == BoxTypes.EMPTY_BOX;
	}
	
	public boolean isBig() {
		return BoxTypes.list[item] <= 'Z';
	}
	
	public boolean isFood() {
		return item == BoxTypes.FOOD;
	}

	public boolean isBlock() {
		return item == BoxTypes.BLOCK;
	}
}

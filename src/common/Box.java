package common;

public class Box {
	boolean right, top, left, bottom;
	char item;
	
	public Box() {
		item = 'e';
	}
	
	public Box(char _item) {
		item = _item;
	}
	
	@Override
	public String toString() {
		return new String(Character.toString(item));
	}
}

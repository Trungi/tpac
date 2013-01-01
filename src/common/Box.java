package common;

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
		if (item == BoxTypes.INVISIBLE_BOX) {
			return ".";
		} else {
			return new String(Character.toString(BoxTypes.list[item]));
		}
	}

	public boolean isEmpty() {
		return item == BoxTypes.EMPTY_BOX;
	}
}

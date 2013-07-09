package Events;

import java.awt.Point;

public class ScreenMetadata {
	
	
	private final Point cursorPointingAt;

	public ScreenMetadata(Point cursorAt) {
		this.cursorPointingAt = cursorAt;
	}

	public Point getCursorPointingAt() {
		return cursorPointingAt;
	}
}

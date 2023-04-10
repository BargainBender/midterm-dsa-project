package view.components;

import java.awt.Color;

/**
 * Provides color schemes for the current cells.
 * The first 10 values represent the category of percent:
 * 0         [10]
 * 1 - 10    [9]
 * 11 - 20   [8]
 * 21 - 30   [7]
 * 31 - 40   [6]
 * 41 - 50   [5]
 * 51 - 60   [4]
 * 61 - 70   [3]
 * 71 - 80   [2]
 * 81 - 90   [1]
 * 91 - 100  [0]
 * 
 * [11] Represents an area where values are unknown at the moment.
 * [12] Represents an area out of bounds or not part of our property.
 */
public final class CellColorSchemes {
	public static final int UNSET = 11;
	public static final int NON_PROPERTY = 12;
	
	public static final Color[] INFORMATIVE = {
			getHSV(107, 63, 54),
			getHSV(88, 64, 60),
			getHSV(74, 65, 67),
			getHSV(63, 66, 74),
			getHSV(54, 68, 86),
			getHSV(49, 71, 97),
			getHSV(40, 74, 96),
			getHSV(35, 73, 95),
			getHSV(30, 73, 93),
			getHSV(10, 68, 87),
			getHSV(5, 84, 85),
			
			getHSV(120, 0, 100),
			getHSV(120, 0, 83),
	};
	
	private static Color getHSV(float h, float s, float v) {
		return Color.getHSBColor(((h * 100f)/360f)/100f, s/100f, v/100f);
	}
}

package com.sun.awt;

import java.awt.*;

/**
 * Workaround for CottonMC/json-factory#7, part 2
 */
public class AWTUtilities {
	public static boolean isTranslucencySupported(Translucency translucency) {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT);
	}

	public enum Translucency {
		PERPIXEL_TRANSPARENT
	}
}

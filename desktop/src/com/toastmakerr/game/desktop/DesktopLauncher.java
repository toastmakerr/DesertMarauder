package com.toastmakerr.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.toastmakerr.game.DesertMarauderMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DesertMarauderMain.WIDTH;
		config.height = DesertMarauderMain.HEIGHT;
		config.title = DesertMarauderMain.TITLE;
		new LwjglApplication(new DesertMarauderMain(), config);
	}
}

package stortor.com.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import stortor.com.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 700;
		config.resizable = false;
//		config.foregroundFPS = 60;
		new LwjglApplication(new StarGame(), config);
	}
}

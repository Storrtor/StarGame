package stortor.com;

import com.badlogic.gdx.Game;

import stortor.com.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

}

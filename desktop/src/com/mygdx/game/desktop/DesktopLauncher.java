package com.mygdx.game.desktop;

import player.Demo;
import game.Animator;
import game.Drop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Demo";
		config.height = 800;
		config.width = 800;
		new LwjglApplication(new Drop(), config);
	}
}

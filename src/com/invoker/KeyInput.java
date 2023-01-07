package com.invoker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private Game game;
	private Invoker invoker;

	public KeyInput(Game game, Invoker invoker) {
		this.game = game;
		this.invoker = invoker;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (game.state) {
			case Invoker:
				if (key == KeyEvent.VK_Q)
					invoker.enqueue(Reagant.Quas);
				else if (key == KeyEvent.VK_W)
					invoker.enqueue(Reagant.Wex);
				else if (key == KeyEvent.VK_E)
					invoker.enqueue(Reagant.Exort);
				else if (key == KeyEvent.VK_R)
					invoker.changeSpell();
				else if (key == KeyEvent.VK_S)
					System.exit(0);
				else if (key == KeyEvent.VK_SPACE)
					game.state = State.Info;
				break;

			case Info:
				if (key == KeyEvent.VK_SPACE)
					game.state = State.Invoker;
				break;

			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}
	
}

package com.invoker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Invoker {

	public static final String QQQ = "Cold Snap";
	public static final String QQE = "Ice Wall";
	public static final String QQW = "Ghost Walk";
	public static final String WWW = "EMP";
	public static final String WWQ = "Tornado";
	public static final String WWE = "Alacrity";
	public static final String QWE = "Deafening Blast";
	public static final String EEE = "Sunstrike";
	public static final String EEQ = "Forged Spirit";
	public static final String EEW = "Chaos Meteor";

	public static final Color QUAS_COLOR = new Color(10, 10, 255);
	public static final Color WEX_COLOR = new Color(255, 10, 255);
	public static final Color EXORT_COLOR = new Color(255, 250, 10);

	private Reagant[] combo = { Reagant.None, Reagant.None, Reagant.None };
	
	private String currentSpell;

	public Invoker() {
		currentSpell = "None";
	}

	public void tick() {

	}

	public void render(Graphics g) {
		int x;
		int y;

		y = centerVertical(100);
		x = centerHorizontal(100);

		y -= 100;
		
		final int LEFT = x - 110;
		final int RIGHT = x + 110;

		renderElement(g, new Rectangle(LEFT, y, 100, 100), combo[0]);
		renderElement(g, new Rectangle(x, y, 100, 100), combo[1]);
		renderElement(g, new Rectangle(RIGHT, y, 100, 100), combo[2]);
		
		displaySpell(g);
	}

	public int centerHorizontal(int width) {
		return Game.WIDTH / 2 - width / 2;
	}

	public int centerVertical(int height) {
		return Game.HEIGHT / 2 - height / 2;
	}

	public void renderElement(Graphics g, Rectangle rect, Reagant reagant) {
		int x = rect.x;
		int y = rect.y;
		int width = rect.width;
		int height = rect.height;

		switch (reagant) {
			case Quas:
				g.setColor(QUAS_COLOR);
				break;
			
			case Wex:
				g.setColor(WEX_COLOR);
				break;

			case Exort:
				g.setColor(EXORT_COLOR);
				break;
		
			default:
				break;
		}
		
		g.fillRect(x, y, width, height);
	}

	public void enqueue(Reagant element) {
		combo[0] = combo[1];
		combo[1] = combo[2];
		combo[2] = element;
	}

	public void displaySpell(Graphics g) {
		g.setColor(new Color(150, 150, 150));
		g.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));
		g.drawString(currentSpell, Game.WIDTH / 2 - g.getFontMetrics().stringWidth(currentSpell) / 2, Game.HEIGHT / 2 - g.getFontMetrics().getHeight() / 2);
	}

	public void changeSpell() {
		int qCount = 0;
		int wCount = 0;
		int eCount = 0;

		for (int i = 0; i < 3; i++) {
			if (combo[i] == Reagant.Quas)
				qCount++;
			else if (combo[i] == Reagant.Wex)
				wCount++;
			else if (combo[i] == Reagant.Exort)
				eCount++;
		}

		if (qCount == 3) {
			currentSpell = QQQ;
		} else if (qCount == 2 && eCount == 1) {
			currentSpell = QQE;
		} else if (qCount == 2 && wCount == 1) {
			currentSpell = QQW;
		} else if (wCount == 3) {
			currentSpell = WWW;
		} else if (wCount == 2 && qCount == 1) {
			currentSpell = WWQ;
		} else if (wCount == 2 && eCount == 1) {
			currentSpell = WWE;
		} else if (qCount == 1 && wCount == 1 && eCount == 1) {
			currentSpell = QWE;
		} else if (eCount == 3) {
			currentSpell = EEE;
		} else if (eCount == 2 && qCount == 1) {
			currentSpell = EEQ;
		} else if (eCount == 2 && wCount == 1) {
			currentSpell = EEW;
		}
	}
	
}

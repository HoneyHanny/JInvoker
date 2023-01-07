package com.invoker;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	public static final String TITLE = "Runner";
	public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double DWIDTH = SCREEN.getWidth();
	public static final double DHEIGHT = SCREEN.getHeight();
	public static final int WIDTH = (int) DWIDTH;
	public static final int HEIGHT = (int) DHEIGHT;

	public static boolean running;

	public State state;
	
	private Thread thread;
	private Invoker invoker;

	public Game() {
		invoker = new Invoker();
		state = State.Invoker;

		new Window(this);
	
		addKeyListener(new KeyInput(this, invoker));
		
		start();
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.run();
	}

	private synchronized void stop() {
		if (!running)
			return;

		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
		}

		stop();
	}

	private void tick() {

	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		/* Start draw */

		/* Render background */
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		switch (state) {
			case Invoker:
				invoker.render(g);
				break;

			case Info:
				renderInfo(g);
				break;
		}

		/* End draw */
		g.dispose();
		bs.show();
	}

	public void renderInfo(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));

		g.drawString("QQQ = Quas", 500, 100);
		g.drawString("QQE = Ice Wall", 500, 130);
		g.drawString("QQW = Ghost Walk", 500, 160);
		g.drawString("WWW = EMP", 500, 190);
		g.drawString("WWQ = Tornado", 500, 220);
		g.drawString("WWE = Alacrity", 500, 250);
		g.drawString("QWE = Deafening Blast", 500, 280);
		g.drawString("EEE = Sunstrike", 500, 310);
		g.drawString("EEQ = Forged Spirit", 500, 340);
		g.drawString("EEW = Chaos Meteor", 500, 370);
	}

	public static void main(String[] args) {
		running = false;
		new Game();
	}
}

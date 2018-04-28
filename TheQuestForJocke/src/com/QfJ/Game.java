package com.QfJ;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

class Game extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	// Variabler och objekt som behövs
	private static int height = 300;
	private static int width = 16 * height / 9;
	private static int scale = 3;
	
	private static boolean running = false;
	private static String title = "Quest for Jocke";
	
	private static Canvas canvas = new Canvas();
	private static Thread thread;
	private static BufferStrategy bs;
	private static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private static int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// Konstruktor - Ställer in JFrame:en och canvas med rätt storlek och inställningar. Körs när ett Game-objekt skapas i main metoden.
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		setVisible(true);

		canvas.setSize(size);
		add(canvas);
	}
	
	// Main metod. Här "börjar" koden.
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	// Startar spel-thread:en
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	// Stoppar spel-thred:en
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Körs när spel thread:en startas
	public void run() {
		
		// Kör render och update funktionerna så länge running = true
		// Update begränsat till 60 fps, Render obegränsad
		while(running) {
			render();
			update();
		}
		stop();
	}
	
	// Renderar grafik
	public static void render() {
		bs = canvas.getBufferStrategy();
		
		// Skapar en bufferstrategy för canvas om sådan ej finns
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		// Sätter pixlarna i screen klassen lika med de i denna klassen, eftersom den faktiska renderingen har sker där.
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = Color.PINK.getRGB();
		}
		
		// Renderar och visar pixel[] arrayen som innehåller en färg för varje pixel i form av en hexadecimal, dvs ett nummer.
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	// Uppdaterar spelet (player movement, game logic)
	public static void update() {
		
	}
}
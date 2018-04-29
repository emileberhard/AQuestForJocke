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
	
	// Variabler och objekt som beh�vs
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
	
	// Konstruktor - St�ller in JFrame:en och canvas med r�tt storlek och inst�llningar. K�rs n�r ett Game-objekt skapas i main metoden.
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
	
	// Main metod. H�r "b�rjar" koden.
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
	
	// K�rs n�r spel thread:en startas
	public void run() {
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		long now;
		double delta = 0;
		double sextioDelsSekund = 1000000000.0 / 60.0;
		long lastTime = System.nanoTime();
		
		// K�r render och update funktionerna s� l�nge running = true
		// Update begr�nsat till 60 fps, Render obegr�nsad
		while(running) {
			now = System.nanoTime(); 
			delta += (now - lastTime) / sextioDelsSekund;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				System.out.println(updates + " ups " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	// Renderar grafik
	public static void render() {
		bs = canvas.getBufferStrategy();
		
		// Skapar en bufferstrategy f�r canvas om s�dan ej finns
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		// S�tter pixlarna i screen klassen lika med de i denna klassen, eftersom den faktiska renderingen har sker d�r.
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = Color.PINK.getRGB();
		}
		
		// Renderar och visar pixel[] arrayen som inneh�ller en f�rg f�r varje pixel i form av en hexadecimal, dvs ett nummer.
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	// Uppdaterar spelet (player movement, game logic)
	public static void update() {
		
	}
}
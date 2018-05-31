package com.QfJ;

import com.QfJ.characters.Jakob;
import com.QfJ.characters.Person;
import com.QfJ.characters.Xiange;
import com.QfJ.graphics.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	// Variabler och objekt som beh�vs
	public static final int HEIGHT = 500;
	public static final int WIDTH = 16 * HEIGHT / 9;
	private static int scale = 2;
	
	private boolean running = false;
	private String title = "Quest for Jocke";
	
	private Screen screen;
	private KeyListenerClass keyListener;
	private Physics physics = new Physics();
	private MusicPlayer musicPlayer = new MusicPlayer();
	private Person[] people = new Person[2];
	private Xiange xiangeObjekt = new Xiange(WIDTH/2, HEIGHT/2);
	private Jakob jakobObjekt = new Jakob(180, 70);
	
	private Canvas canvas = new Canvas();
	private Thread thread;
	private BufferStrategy bs;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// Konstruktor - Staller in JFrame:en och canvas med ratt storlek och installningar. Kors nar ett Game-objekt skapas i main metoden.
	public Game() {
		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		keyListener = new KeyListenerClass(xiangeObjekt);
		
		// st�ller in jframe
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		addKeyListener(keyListener);
		setVisible(true);
		setFocusable(true);
		
		// staller in canvas
		screen = new Screen(WIDTH, HEIGHT);
		canvas.setSize(size);
		add(canvas);
		canvas.addKeyListener(keyListener);
		canvas.setFocusable(true);
		pack();
		
		// spela musik
		try {
			musicPlayer.play("deantown.wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(Game.class.getClassLoader().getResourceAsStream(path));
			BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);
			return formattedImage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
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
	
	// Kors nar spel thread:en startas
	public void run() {
		people[0] = xiangeObjekt;
		people[1] = jakobObjekt;

		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		long now;
		double delta = 0;
		double sextioDelsSekund = 1000000000.0 / 60.0;
		long lastTime = System.nanoTime();
		
		// Kor render och update funktionerna sa lange running = true
		// Update begransat till 60 fps, Render obegansad
		while(running) {
			now = System.nanoTime(); 
			delta += (now - lastTime) / sextioDelsSekund;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				updates++;
				render();
				frames++;
				delta--;
				
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				setTitle(title + "  |  " + updates + " ups " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	// Renderar grafik
	private void render() {
		bs = canvas.getBufferStrategy();
		
		// Skapar en bufferstrategy for canvas om den ej redan finns (alltsa forsta gangen programmer kors)
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		// clearar sk�rmen f�r att stoppa trailing
		screen.clear();
		
		// rendererar annat som hp bar och text
		xiangeObjekt.renderHp(screen);
		
		// TA EJ BORT xiangeObjekt.speak("Where's my main man!?", screen);
		
		// renderar alla characters
		for(Person person : people) {
			person.render(screen);
			person.renderHp(screen);
		}
		
		// renderar allting som inte redan ar renderat till gratt
		screen.renderBackground();

		// Satter pixlarna i screen klassen lika med de i denna klassen, eftersom den faktiska renderingen har sker dar.
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		// Renderar och visar pixel[] arrayen som inneh�ller en f�rg f�r varje pixel i form av en hexadecimal, dvs ett nummer.
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	// Uppdaterar spelet (player movement, game logic)
	public void update() {
		//animation
		xiangeObjekt.move();
		xiangeObjekt.animate();
	
		// sorterar characters efter deras yPos så att de hamnar rätt in terms of foreground/background
		sortPlayers();
		
		// physics (under construction)
		for(int i = 1 ; i < people.length ; i++) {
			if(physics.isColliding(people[i].hitBox, people[i-1].hitBox)) {
				
			}
		}
	}
	
	public void sortPlayers() {
		for(int i = 0; i < people.length; i++) {
			for(int x = 0; x < people.length - 1; x++) {
				if(people[x].yPos + people[x].getPlayerImage().getHeight() < people[x+1].yPos + people[x+1].getPlayerImage().getHeight()) {
					Person temp = people[x];
					people[x] = people[x+1];
					people[x+1] = temp;
				}
			}		
		}
	}
}
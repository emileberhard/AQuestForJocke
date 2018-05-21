package com.QfJ;

import com.QfJ.characters.Jakob;
import com.QfJ.characters.Person;
import com.QfJ.characters.Xiange;
import com.QfJ.graphics.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	// Variabler och objekt som behï¿½vs
	public static final int HEIGHT = 500;
	public static final int WIDTH = 16 * HEIGHT / 9;
	private static int scale = 1;
	
	private boolean running = false;
	private String title = "Quest for Jocke";
	
	private Screen screen;
	private Person[] people = new Person[2];
	private Xiange xiangeObjekt = new Xiange(loadImage("xiange.png"), WIDTH/2, HEIGHT/2);
	private Jakob jakobObjekt = new Jakob("Jakob", loadImage("jakob.png"), 60, 70);
	
	private Canvas canvas = new Canvas();
	private Thread thread;
	private BufferStrategy bs;
	
	private BufferedImage boxBild;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// Konstruktor - Stï¿½ller in JFrame:en och canvas med rï¿½tt storlek och instï¿½llningar. Kï¿½rs nï¿½r ett Game-objekt skapas i main metoden.
	public Game() {
		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		addKeyListener(this);
		setVisible(true);
		setFocusable(true);
		
		screen = new Screen(WIDTH, HEIGHT);
		canvas.setSize(size);
		add(canvas);
		canvas.addKeyListener(this);
		canvas.setFocusable(true);
		pack();
		
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
	
	// Main metod. Hï¿½r "bï¿½rjar" koden.
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
	
	// Kï¿½rs nï¿½r spel thread:en startas
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
		
		// Kï¿½r render och update funktionerna sï¿½ lï¿½nge running = true
		// Update begrï¿½nsat till 60 fps, Render obegrï¿½nsad
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
		
		// clearar skärmen för att stoppa trailing
		screen.clear();
		
		// rendererar annat som hp bar och text
		xiangeObjekt.renderHp(screen);
		
		// xiangeObjekt.speak("Where's my main man!?", screen);
		
		// renderar alla characters
		for(Person person : people) {
			person.render(screen);
		}
		
		// renderar allting som inte redan ar renderat till gratt
		screen.renderBackground();

		// Satter pixlarna i screen klassen lika med de i denna klassen, eftersom den faktiska renderingen har sker dar.
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		// Renderar och visar pixel[] arrayen som innehï¿½ller en fï¿½rg fï¿½r varje pixel i form av en hexadecimal, dvs ett nummer.
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	// Uppdaterar spelet (player movement, game logic)
	public void update() {
		
	//animation
	xiangeObjekt.move();
	
	// sorterar characters efter deras yPos sÃ¥ att de hamnar rÃ¤tt in terms of foreground/background
	sortPlayers();

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

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			xiangeObjekt.up = true;
		}
		if(key == KeyEvent.VK_DOWN) {
			xiangeObjekt.down = true;
		}
		if(key == KeyEvent.VK_RIGHT) {
			xiangeObjekt.right = true;
		}
		if(key == KeyEvent.VK_LEFT) {
			xiangeObjekt.left = true;
		}
		if(key == KeyEvent.VK_S) {
			xiangeObjekt.smile = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			xiangeObjekt.up = false;
		}
		if(key == KeyEvent.VK_DOWN) {
			xiangeObjekt.down = false;
		}
		if(key == KeyEvent.VK_RIGHT) {
			xiangeObjekt.right = false;
		}
		if(key == KeyEvent.VK_LEFT) {
			xiangeObjekt.left = false;
		}
		if(key == KeyEvent.VK_S) {
			xiangeObjekt.smile = false;
		}
	}	
	
	public void keyTyped(KeyEvent e) {
		
	}
}
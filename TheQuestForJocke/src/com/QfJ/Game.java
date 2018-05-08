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
	
	// Variabler och objekt som beh�vs
	public static int height = 500;
	public static int width = 16 * height / 9;
	private static int scale = 1;
	
	private boolean running = false;
	private String title = "Quest for Jocke";
	
	private Screen screen;
	private Person[] people = new Person[2];
	private Xiange xiangeObjekt = new Xiange(loadImage("xiange.png"));
	private Jakob jakobObjekt = new Jakob("Jakob", loadImage("jakob.png"));
	
	private Canvas canvas = new Canvas();
	private Thread thread;
	private BufferStrategy bs;
	
	private BufferedImage boxBild;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// Konstruktor - St�ller in JFrame:en och canvas med r�tt storlek och inst�llningar. K�rs n�r ett Game-objekt skapas i main metoden.
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		setVisible(true);
		addKeyListener(this);
		
		screen = new Screen(width, height);
		canvas.setSize(size);
		add(canvas);
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
		people[0] = xiangeObjekt;
		people[1] = jakobObjekt;

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
		
		// Skapar en bufferstrategy f�r canvas om s�dan ej finns
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		
		screen.clear();

		// sorterar characters efter deras yPos så att de hamnar rätt in terms of foreground/background
		for(int i = 0; i < people.length; i++) {
			for(int x = 0; x < people.length - 1; x++) {
				if(people[x].yPos + people[x].getPlayerImage().getHeight() < people[x+1].yPos + people[x+1].getPlayerImage().getHeight()) {
					Person temp = people[x];
					people[x] = people[x+1];
					people[x+1] = temp;
				}
			}		
		}
		
		// renderar alla characters
		for(Person person : people) {
			screen.renderImage(person.getPlayerImage(), (int)person.xPos, (int)person.yPos, 1, 1);
		}
		
		// rendererar annat som hp bar och text
		screen.renderImage(boxBild, width - boxBild.getWidth() - ((width - boxBild.getWidth())/2), height - 40, 1, 1);
		screen.renderImage(xiangeObjekt.getHpImage(), 10, height - 30, 1, 1);
		screen.render();

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
		xiangeObjekt.smile();
		boxBild = xiangeObjekt.speak(1);

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
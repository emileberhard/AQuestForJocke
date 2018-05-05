package com.QfJ;

import com.QfJ.graphics.*;

import java.awt.Canvas;
import java.awt.Color;
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

class Game extends JFrame implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	// Variabler och objekt som beh�vs
	private static int height = 500;
	private static int width = 16 * height / 9;
	private static int scale = 1;
	
	private double xPos = 0;
	private double yPos = 0;
	private int time = 0;
	
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	
	private boolean running = false;
	private String title = "Quest for Jocke";
	
	private Screen screen;
	private TextBox textBox = new TextBox();
	private Xiange xiangeObjekt = new Xiange(loadImage("xiange.png"));
	
	private Canvas canvas = new Canvas();
	private Thread thread;
	private BufferStrategy bs;
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
	
	private BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(Game.class.getClassLoader().getResourceAsStream(path));
			BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);
			System.out.println(formattedImage.getWidth());
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
				setTitle(title + "  |  " + updates + " ups " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	// Renderar grafik
	public void render() {
		bs = canvas.getBufferStrategy();
		
		// Skapar en bufferstrategy f�r canvas om s�dan ej finns
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		BufferedImage boxBild = xiangeObjekt.speak(1);
		// renderar pixels[] i screen classen
		screen.renderImage(xiangeObjekt.getPlayerImage(), (int)xPos, (int)yPos);
		screen.renderImage(boxBild, width - boxBild.getWidth() - ((width - boxBild.getWidth())/2), height - 40);
		screen.renderImage(xiangeObjekt.getHpImage(), 10, height - 30);
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
		if(up) {
			if(right || left) {
				yPos -= 1.4142;
			}else {
				yPos -= 2;
			}
		}
		if(down) {
			if(right || left) {
				yPos +=1.4142;
			}else {
				yPos += 2;
			}
		}
		if(right) {
			if(up || down) {
				xPos +=1.4142;
			}else {
				xPos += 2;
			}
		}
		if(left) {
			if(up || down) {
				xPos-=1.4142;
			}else {
				xPos -= 2;
			}
		}
		if(yPos < 0) {
			yPos = 0;
		} 
		if(yPos > (height - xiangeObjekt.getPlayerImage().getHeight())){
			yPos = height - xiangeObjekt.getPlayerImage().getHeight();
		}
		if(xPos < 0) {
			xPos = 0;
		}
		if(xPos > (width - xiangeObjekt.getPlayerImage().getWidth())){
			xPos = width - xiangeObjekt.getPlayerImage().getWidth();
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			up = true;
		}
		if(key == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(key == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if(key == KeyEvent.VK_LEFT) {
			left = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			up = false;
		}
		if(key == KeyEvent.VK_DOWN) {
			down = false;
		}
		if(key == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if(key == KeyEvent.VK_LEFT) {
			left = false;
		}
	}
}
package termproject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.Color;

/**
 * Put your name here!!!
 * 
 * @author Tyren
 *
 */
public class SnakeGame {
	boolean gamestatus = true;
	// snake array
	ArrayList<Integer> snakex = new ArrayList<Integer>();
	ArrayList<Integer> snakey = new ArrayList<Integer>();
	// fruit array
	ArrayList<Integer> fruitx = new ArrayList<Integer>();
	ArrayList<Integer> fruity = new ArrayList<Integer>();
	// p is incremented when snake eats fruit and is used to to fill the rectangle
	// in a previous array element -(in for loop)
	int p = 1;
	// boolean are for movement... stops snake from moving backwards into itself
	boolean up = true;
	boolean down = true;
	boolean left = true;
	boolean right = true;

	private Timer timer;
	// x and y is the location of the head of my snake
	int x;
	int y;

	// speed is used to set delay; 180 decreases every time snake eats fruit
	int speed = 70;
//flocx= fruit location in x coordinate     flocy= fruit location in y coordinate		
	int flocx;
	int flocy;

	public SnakeGame(Timer timer) {
		// TODO Auto-generated constructor stub
		this.timer = timer;
		// initialed xy coordinates of snake and fruit x & y
		x = 50;
		y = 50;
		flocx = 180;
		flocy = 180;
	}

	public void updateGraphics(Graphics g, int width, int height, Direction dir) {
// game status boolean helps end the game if head of snake (x & y) coordinates hits a previous array element!
		if (gamestatus == true) {

			int z = timer.getDelay() - 100;
			if (z <= 0) {
				z = speed;
			}
			timer.setDelay(z);

			// updates previous x and y coordinates(head) to array list

			snakex.add(x);
			snakey.add(y);

			// adds the initialized values of the fruit to the array list since its 0
			if (fruitx.size() == 0) {
				fruitx.add(flocx);
				fruity.add(flocy);
			}

			// controls movement of game
			movement(g, width, height, dir);

			// my fruit is always a multiple of ten therefore x and y must be a multiple of
			// ten to hit it. This makes sure its never out of bounds!
			if (x % 10 != 0) {
				x++;

			}
			if (y % 10 != 0) {
				y++;

			}

			// game board- display a 10x10 rectangle in the x and y coordinates(head)

			g.clearRect(0, 0, width, height);
			g.setColor(Color.green);
			g.fillRect(x, y, 10, 10);

			// for loop keeps track of where to create a new 10 x 10 rectangle
			for (int i = 1; i <= p; i++) {

				// if statement runs when x & y (head of snake) hits the x & y location of the
				// fruit
				if (x == fruitx.get(fruitx.size() - 1) && y == fruity.get(fruity.size() - 1)) {
					// random number is generated and the new x and y location of the fruit is added
					// to the fruit array lists
					Random rand = new Random();

					flocx = rand.nextInt(width / 10) * 10;
					flocy = rand.nextInt(height / 10) * 10;

					fruitx.add(flocx);
					fruity.add(flocy);
					// p is incremented, since i=1 and i<p in the for loop i will also increment
					// during its next iteration
					p++;
					// controls the speed of the game
					if (speed > 0) {
						speed = speed - 5;

					}

				}

				// if statement says- if x & y(head of snake) hits a previous location, than
				// shut off game
				// snakex and snakey is the arraylist for the previous locations and the
				// variable i is keeping track of the elements
				// filled with a rectangle.

				if (x == snakex.get(snakex.size() - i) && y == snakey.get(snakey.size() - i)) {
					gamestatus = false;
				}

				// fills rectangles at the x & y locations in the array list
				g.setColor(Color.green);
				g.fillRect(snakex.get(snakex.size() - i), snakey.get(snakey.size() - i), 10, 10);

			}
			// fills a rectangle at the new generated fruit
			g.setColor(Color.RED);
			g.fillOval(fruitx.get(fruitx.size() - 1), fruity.get(fruity.size() - 1), 10, 10);

		}
	}

	public void movement(Graphics g, int width, int height, Direction dir) {

		if (dir == Direction.RIGHT && x < (width) && right == true) {

			x = x + 10;

			left = false;
			up = true;
			down = true;

		}

		if (dir == Direction.RIGHT && x >= width) {
			x = 0;

			left = false;
			up = true;
			down = true;

		}

		if (dir == Direction.DOWN && y < height && down == true) {
			y = y + 10;
			up = false;
			right = true;
			left = true;

		}

		if (dir == Direction.DOWN && y >= height) {
			y = 0;
			up = false;
			right = true;
			left = true;
		}

		if (dir == Direction.LEFT && x >= 0 && left == true) {
			x = x - 10;
			right = false;
			up = true;
			down = true;
		}
		if (dir == Direction.LEFT && x < 0) {
			x = width - 10;
			right = false;
			up = true;
			down = true;
		}

		if (dir == Direction.UP && y >= 0 && up == true) {
			y = y - 10;
			down = false;
			left = true;
			right = true;

		}
		if (dir == Direction.UP && y < 0) {
			y = height - 10;
			down = false;
			left = true;
			right = true;
		}

		// stop snake from moving back

		if (dir == Direction.LEFT && x < (width) && left == false) {

			x = x + 10;

			left = false;
			up = true;
			down = true;

		}

		if (dir == Direction.LEFT && x >= width && left == false) {
			x = 0;

			left = false;
			up = true;
			down = true;

		}

		if (dir == Direction.UP && y < height && up == false) {
			y = y + 10;
			up = false;
			right = true;
			left = true;

		}

		if (dir == Direction.UP && y >= height && up == false) {
			y = 0;
			up = false;
			right = true;
			left = true;
		}

		if (dir == Direction.RIGHT && x >= 0 && right == false) {
			x = x - 10;
			right = false;
			up = true;
			down = true;
		}
		if (dir == Direction.RIGHT && x < 0 && right == false) {
			x = width - 10;
			right = false;
			up = true;
			down = true;
		}

		if (dir == Direction.DOWN && y >= 0 && down == false) {
			y = y - 10;
			down = false;
			left = true;
			right = true;

		}
		if (dir == Direction.DOWN && y < 0 && down == false) {
			y = height - 10;
			down = false;
			left = true;
			right = true;
		}

	}
}

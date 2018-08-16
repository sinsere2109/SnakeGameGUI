package termproject;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/** @author P. Brack taken from example by John B. Matthews; distribution per GNU Public License */
public class SnakeGUI extends JApplet {

        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {

                        public void run() {
                                JFrame frame = new JFrame();
                                frame.setTitle("Snake");
                                initContainer(frame);
                                frame.pack();
                                frame.setVisible(true);
                        }
                });
        }

        // Common initialization for either JApplet or JFrame
        private static void initContainer(Container container) {
                // will need to hook up the clicking.
                GraphicsPanel subway = new GraphicsPanel();
                //container.add(Box.createVerticalStrut(8), BorderLayout.NORTH);
                container.add(subway, BorderLayout.CENTER);
                subway.beginOperation();
        }

        @Override
        public void init() {
                System.out.println("Initializing...");
                EventQueue.invokeLater(new Runnable() {

                        public void run() {
                                initContainer(SnakeGUI.this);
                        }
                });

        }

        @Override
        public void destroy() {
                System.out.println("That's all, folks...");
        }
}

class GraphicsPanel extends JPanel implements ActionListener,  KeyListener{
        
        Direction dir = Direction.RIGHT;
        SnakeGame gameBoard;
        
        public static final int MAX = 8; // Max stops
        private static final int DX = 4; // Initial velocity
        private static final int DOOR = 100; // Preferred width
        private int dx = DX;
        private int xx = 0;
        private int yy = 0;

        private Timer timer = new Timer(150, this);

        private boolean loading;

        public GraphicsPanel() {
                super(true);
                setPreferredSize(new Dimension(500,500));
                addKeyListener(this);
                setFocusable(true);
                gameBoard = new SnakeGame(timer);
                

        }

        public void beginOperation() {
                timer.setInitialDelay(200);
                timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                gameBoard.updateGraphics(g, getWidth(), getHeight(), dir);
        }

        // Handle Timer events
        public void actionPerformed(ActionEvent e) {

                // do some work.
                this.repaint();
        }

        @Override
      public void keyPressed(KeyEvent e) {int keyCode = e.getKeyCode();
    switch( keyCode ) { 
    case KeyEvent.VK_W:
        // handle up 
        dir = Direction.UP;
        break;
    case KeyEvent.VK_S:
        dir = Direction.DOWN;
        // handle down 
        break;
    case KeyEvent.VK_A:
        dir = Direction.LEFT;
        // handle left
        break;
    case KeyEvent.VK_D :
        dir = Direction.RIGHT;
        // handle right
        break;
 }
                
        }

        @Override
        public void keyReleased(KeyEvent e) {
                
        }

        @Override
        public void keyTyped(KeyEvent e) {
                
        }
}

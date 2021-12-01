import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements Runnable {
    Snake snake;
    static Food food;
    static Stone stone;
    static int score = 0;
    static int bg[][] = new int[30][20];
    static boolean game_run = false;
    static boolean game_over = false;

    Thread thread;
    public Gameplay() {
        Imagee.load();
        snake = new Snake();
        food = new Food();
        stone = new Stone();
        thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        while (true) {
            if(game_run) {
                snake.update();
                score = snake.length - 3;
            }
            if(game_over) {
                snake.reSnake();
            }
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }
    public void paint(Graphics g) {
        g.drawImage(Imagee.map, 0, 0, null);
        food.draw(g);
        snake.draw(g);
        stone.draw(g);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString("Score: " + (snake.length - 3), 5 , 15);
        if (!game_run) {
            g.drawString("PRESS SPACE TO PLAY", 200, 190);
        }
        if (game_over) {
            g.drawString("GAME OVER!!Score: " + score, 210, 170);
        }

//        // Hien thi Point
//		g.setColor(Color.black);
//        g.setFont(g.getFont().deriveFont(32.0f));
//		g.drawString("Point: "+diem, 590, 120);

        // Show high score
		g.drawString("HIGH SCORE:", 530, 200);
		for (int i=0; i<AppRunning.users.size() ;i++) {
			g.setColor(Color.black);
	        g.setFont(g.getFont().deriveFont(20.0f));
			g.drawString(AppRunning.users.get(i).toString(), 530, i * 20 + 240);
		}


    }


    
}

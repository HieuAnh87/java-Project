import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class AppRunning extends JFrame {
    Gameplay gl;
    public static ArrayList<Score> rank = new ArrayList<>();

    public AppRunning() {
        setSize(800, 440);
        setTitle("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getBxh();
        gl = new Gameplay();
        add(gl);
        this.addKeyListener(new keyDown());
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                updateBxh();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        AppRunning f = new AppRunning();
    }

    private class keyDown implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gl.snake.setDirection(Snake.left);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gl.snake.setDirection(Snake.right);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gl.snake.setDirection(Snake.up);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gl.snake.setDirection(Snake.down);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                Gameplay.game_run = !Gameplay.game_run;
                if (Gameplay.game_over) {
                    Gameplay.game_over = false;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private static void updateBxh() {
        try {
            FileWriter fw = new FileWriter("data/rank.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Score tmp : rank) {
                bw.write(tmp.getName() + " " + tmp.getScore());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
        }
    }

    private static void getBxh() {
        try {
            FileReader fr = new FileReader("data/rank.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null) {
                String s1[] = s.split(" ");
                rank.add(new Score(s1[0], Integer.parseInt(s1[1])));
            }
            br.close();
        } catch (IOException ex) {
        }
    }
}

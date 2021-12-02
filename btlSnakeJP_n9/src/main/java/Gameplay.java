import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import static java.lang.Thread.sleep;

import java.io.*;
import javax.sound.sampled.*;

import java.util.Collections;

public class Gameplay extends JPanel implements Runnable {
    String eat_sound = "sounds/sfx_point.wav";
    String die_sound = "sounds/sfx_hit.wav";
    Snake snake;
    Food food;
    StoneNBush sb;
    int score = 0;
    static boolean game_run = false;
    static boolean game_over = false;
    Thread thread;

    public Gameplay() {
        Imagee.load();
        snake = new Snake();
        food = new Food();
        sb = new StoneNBush();
        thread = new Thread(this);
        thread.start();
    }

    public boolean check_touch() {
        for (int i = 3; i < snake.length; i++) {
            if (snake.x[0] == snake.x[i] && snake.y[0] == snake.y[i]) {
                return false;
            }
        }
        for (int i = 0; i < StoneNBush.x.length; i++) {
            if (snake.x[0] == sb.x[i] && snake.y[0] == sb.y[i]) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        while (true) {
            if (!check_touch()) {
                try {
                    playSound(die_sound);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
                String name = JOptionPane.showInputDialog("Game Over","Enter your name: ");  
                if(name==null) name = "noname";
                AppRunning.rank.add(new Score(name, score));
                Collections.sort(AppRunning.rank);
                game_run = false;
                game_over = true;
            }
            if (food.x == snake.x[0] && food.y == snake.y[0]) {
                try {
                    playSound(eat_sound);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
                snake.length++;
                score += 1;
                food.random_pos();
                snake.count++;
            }
            if (game_run) {
                snake.update();
                score = snake.length - 3;
            }
            if (game_over) {
                snake = new Snake();
            }
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void paint(Graphics g) {
        Color SkyBlue = new Color(128, 222, 234);
        g.drawImage(Imagee.map, 0, 0, null);
        food.draw(g);
        snake.draw(g);
        sb.draw(g);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString("Score: " + (snake.length - 3), 5, 15);
        if (!game_run) {
            g.drawString("PRESS SPACE TO PLAY", 200, 190);
        }
        if (game_over) {
            g.drawString("GAME OVER!!Score: " + score, 210, 170);
        }
        g.setColor(SkyBlue);
        g.fillRect(600, 0, 182, 400);
        g.setColor(Color.white);
        g.drawString("HIGH SCORE", 640, 50);
        for (int i = 0; i < 10; i++) {
            g.drawString(AppRunning.rank.get(i).getName(), 640, 100 + i * 25);
            g.drawString(String.valueOf(AppRunning.rank.get(i).getScore()), 720, 100 + i * 25);
        }
    }
    public void playSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(filename);
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.start();
    }
}

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class AppRunning extends JFrame{
    Gameplay gl;

    public static ArrayList<User> users;


    public AppRunning() {
        setSize(850,450);

        // Doc du lieu tu file save
        users = new ArrayList<User>();
		ReadData();
        
        gl = new Gameplay();
        add(gl);
        
        this.addKeyListener(new keyDown());
        
		// Update du lieu khi tat chuong trinh
	this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateData();
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
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                gl.snake.setDirection(Snake.up);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                gl.snake.setDirection(Snake.down);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                gl.snake.setDirection(Snake.left);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gl.snake.setDirection(Snake.right);
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                Gameplay.game_run = !Gameplay.game_run;
                if(Gameplay.game_over) Gameplay.game_over = false;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public static void UpdateData(){
        
        BufferedWriter bw = null;
        try {
        	//mo file
            FileWriter fw = new FileWriter("data/data.txt");
            bw = new BufferedWriter(fw);
            // in ra file
            for(User u: users) {
            	bw.write(u.getName() + " " + u.getDiem());
                bw.newLine();
          	}
            
        } catch (IOException ex) {}
        finally{
            try {
                bw.close();
            } catch (IOException ex) {}
        }
    }

    	// Doc data tu file txt
	public static void ReadData(){
        
        try {
        	//mo file
            FileReader fr = new FileReader("data/data.txt");
            BufferedReader br = new BufferedReader(fr); // tien hanh doc file
            // Doc data
            String line = null;
            while((line = br.readLine())!=null){ // neu gia tri tra ve != null
                String[] str = line.split(" "); 
                users.add(new User(str[0], str[1])); // add diem va ten ng choi vao high score
            }
            
            br.close();
        } catch (IOException ex) {}
        
    }
    
}

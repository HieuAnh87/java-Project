import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;


public class Snake {
    int length = 3;
    int x[] = new int[30];
    int y[] = new int[20];
    public static int up = 1;   
    public static int down = -1;  
    public static int left = 2;  
    public static int right = -2;  
    int direction = Snake.right;
    
    long tmp = 0;
    public Snake() {
        x[0] = 15; y[0] = 10;
        x[1] = 14; y[1] = 10;
        x[2] = 13; y[2] = 10;
    }
    public void reSnake() {
        x[0] = 15; y[0] = 10;
        x[1] = 14; y[1] = 10;
        x[2] = 13; y[2] = 10;
        length = 3;
        direction = Snake.right;
    }
    public void setDirection(int direct) {
        if(direction * -1 != direct) direction = direct;
    }
    
    public void update() {
        for(int i = 3; i < length; i++) {
            if(x[0] == x[i] && y[0] == y[i]) {
                Gameplay.game_run = false;
                Gameplay.game_over = true;
            }
        }

        for(int i = 0; i < Stone.x.length; i++) {
            if(x[0] == Stone.x[i] && y[0] == Stone.y[i]){
                //Nhap du lieu de luu tru
	            String name = JOptionPane.showInputDialog("Awwww thua mat roi. Moi ban nhap ten (k co' dau' cach'): ");
	            System.out.println("ten la` :"+name);
	            if (name != null) {		// neu ngdung` k nhap ten
                     AppRunning.users.add(new User(name, String.valueOf(Gameplay.score)));
	            }
                Gameplay.game_run = false;
                Gameplay.game_over = true;
            }
        }
        if(System.currentTimeMillis() - tmp > 100) {
            if(Gameplay.food.x == x[0] && Gameplay.food.y == y[0]) {
                length++;
                Gameplay.score += 1;
                Gameplay.food.random_pos();
            }
            for(int i = length - 1; i > 0; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            if(direction == Snake.up) y[0]--;
            if(direction == Snake.down) y[0]++;
            if(direction == Snake.left) x[0]--;
            if(direction == Snake.right) x[0]++;
            if(x[0] >= 30) x[0] = 0;
            if(y[0] >=20) y[0] = 0;
            if(x[0] < 0) x[0] = 29;
            if(y[0] < 0) y[0] = 19;   
            tmp = System.currentTimeMillis();
        }   
    }
    public void draw(Graphics g) {
        g.setColor(Color.green);
        for(int i = 0; i < length; i++) {
            if(i == 0) {
                if(y[i]+1 ==  y[i+1]) g.drawImage(Imagee.head_up, x[i]*20, y[i]*20, null);
                else if(y[i]-1 ==  y[i+1]) g.drawImage(Imagee.head_down, x[i]*20, y[i]*20, null);
                if(x[i]+1 ==  x[i+1]) g.drawImage(Imagee.head_left, x[i]*20, y[i]*20, null);
                else if(x[i]-1 ==  x[i+1]) g.drawImage(Imagee.head_right, x[i]*20, y[i]*20, null);
            }
            else if(i == length-1) {
                if(y[i]+1 ==  y[i-1]) g.drawImage(Imagee.tail_up, x[i]*20, y[i]*20, null);
                else if(y[i]-1 ==  y[i-1]) g.drawImage(Imagee.tail_down, x[i]*20, y[i]*20, null);
                else if(x[i]+1 ==  x[i-1]) g.drawImage(Imagee.tail_left, x[i]*20, y[i]*20, null);
                else if(x[i]-1 ==  x[i-1]) g.drawImage(Imagee.tail_right, x[i]*20, y[i]*20, null);
            }
            else {
                int bfx = x[i+1] - x[i];
                int bfy = y[i+1] - y[i];
                int afx = x[i-1] - x[i];
                int afy = y[i-1] - y[i];
                if(bfx == afx) g.drawImage(Imagee.body_vertical, x[i]*20, y[i]*20, null);
                else if(bfy == afy) g.drawImage(Imagee.body_horizontal, x[i]*20, y[i]*20, null);
                else {
                    if((bfx == -1 && afy == -1) || (bfy == -1 && afx == -1)) 
                        g.drawImage(Imagee.body_tl, x[i]*20, y[i]*20, null);
                    else if((bfx == -1 && afy == 1) || (bfy == 1 && afx == -1)) 
                        g.drawImage(Imagee.body_bl, x[i]*20, y[i]*20, null);
                    else if((bfx == 1 && afy == -1) || (bfy == -1 && afx == 1)) 
                        g.drawImage(Imagee.body_tr, x[i]*20, y[i]*20, null);
                    else if((bfx == 1 && afy == 1) || (bfy == 1 && afx == 1)) 
                        g.drawImage(Imagee.body_br, x[i]*20, y[i]*20, null);
                }
            }
        }
    }
}

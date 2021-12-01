import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Food {
    int x, y;
    Image t;
    Image a[] = {Imagee.food1, Imagee.food2, Imagee.food3};
    Random r = new Random();

    public Food() {
        random_pos();
    }
    public boolean check_stone(int x, int y) {
        for (int i = 0; i < StoneNBush.x.length; i++) {
            if (x == StoneNBush.x[i] && y == StoneNBush.y[i]) {
                return false;
            }
        }
        return true;
    }
    public void random_pos() {
        t = a[r.nextInt(a.length)];
        x = r.nextInt(29);
        y = r.nextInt(19);
        while (!check_stone(x, y)) {
            random_pos();
        }
    }
    public void draw(Graphics g) {
        g.drawImage(t, x * 20, y * 20, null);
    }
}

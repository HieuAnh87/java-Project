import java.awt.Graphics;

public class StoneNBush {
    static int x[] = {2, 3, 10, 15, 20, 27, 25};
    static int y[] = {17, 9, 5, 13, 16, 19, 5};
    static int x1[] = {15, 19, 3, 4, 5, 6, 7, 23, 24, 23, 24};
    static int y1[] = {17, 9, 5, 15, 15, 15, 15, 2, 2, 3, 3};

    public void draw(Graphics g) {
        for (int i = 0; i < x.length; i++) {
            g.drawImage(Imagee.stone, x[i] * 20, y[i] * 20, null);
        }
        for (int i = 0; i < x1.length; i++) {
            g.drawImage(Imagee.bush, x1[i] * 20 - 5, y1[i] * 20 - 5, null);
        }
    }
}

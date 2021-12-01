import java.awt.Graphics;

public class Snake {

    int length = 3;
    int x[] = new int[600];
    int y[] = new int[600];
    public static int up = 1;
    public static int down = -1;
    public static int left = 2;
    public static int right = -2;
    int direction = Snake.right;
    int speed = 100;
    int count = 0;
    long tmp = 0;

    public Snake() {
        x[0] = 15;
        y[0] = 10;
        x[1] = 14;
        y[1] = 10;
        x[2] = 13;
        y[2] = 10;
    }

    //  Kiem tra lai huong nguoc lai
    public void setDirection(int direct) {
        if (direction * -1 != direct) {
            direction = direct;
        }
    }

    //  Cap nhap huong di chuyen, toan do than con ran sau khoang thoi gian ->> chinh toc do con ran
    public void update() {
        if (System.currentTimeMillis() - tmp > speed) {
            for (int i = length - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (count == 4 && speed >= 35) {
                speed -= 5;
                count = 0;
            }
            if (direction == Snake.up) {
                y[0]--;
            }
            if (direction == Snake.down) {
                y[0]++;
            }
            if (direction == Snake.left) {
                x[0]--;
            }
            if (direction == Snake.right) {
                x[0]++;
            }
            if (x[0] >= 30) {
                x[0] = 0;
            }
            if (y[0] >= 20) {
                y[0] = 0;
            }
            if (x[0] < 0) {
                x[0] = 29;
            }
            if (y[0] < 0) {
                y[0] = 19;
            }
            tmp = System.currentTimeMillis();
        }
    }

    //  Ve con ran
    public void draw(Graphics g) {
        for (int i = 0; i < length; i++) {
            //  Ve dau
            if (i == 0) {
                if (y[i] + 1 == y[i + 1]) {
                    g.drawImage(Imagee.head_up, x[i] * 20, y[i] * 20, null);
                } else if (y[i] - 1 == y[i + 1]) {
                    g.drawImage(Imagee.head_down, x[i] * 20, y[i] * 20, null);
                }
                if (x[i] + 1 == x[i + 1]) {
                    g.drawImage(Imagee.head_left, x[i] * 20, y[i] * 20, null);
                } else if (x[i] - 1 == x[i + 1]) {
                    g.drawImage(Imagee.head_right, x[i] * 20, y[i] * 20, null);
                }
            } //  Ve duoi
            else if (i == length - 1) {
                if (y[i] + 1 == y[i - 1]) {
                    g.drawImage(Imagee.tail_up, x[i] * 20, y[i] * 20, null);
                } else if (y[i] - 1 == y[i - 1]) {
                    g.drawImage(Imagee.tail_down, x[i] * 20, y[i] * 20, null);
                } else if (x[i] + 1 == x[i - 1]) {
                    g.drawImage(Imagee.tail_left, x[i] * 20, y[i] * 20, null);
                } else if (x[i] - 1 == x[i - 1]) {
                    g.drawImage(Imagee.tail_right, x[i] * 20, y[i] * 20, null);
                }
            } //  Ve than
            else {
                int bfx = x[i + 1] - x[i];
                int bfy = y[i + 1] - y[i];
                int afx = x[i - 1] - x[i];
                int afy = y[i - 1] - y[i];
                if (bfx == afx) {
                    g.drawImage(Imagee.body_vertical, x[i] * 20, y[i] * 20, null);
                } else if (bfy == afy) {
                    g.drawImage(Imagee.body_horizontal, x[i] * 20, y[i] * 20, null);
                } else {
                    if ((bfx == -1 && afy == -1) || (bfy == -1 && afx == -1)) {
                        g.drawImage(Imagee.body_tl, x[i] * 20, y[i] * 20, null);
                    } else if ((bfx == -1 && afy == 1) || (bfy == 1 && afx == -1)) {
                        g.drawImage(Imagee.body_bl, x[i] * 20, y[i] * 20, null);
                    } else if ((bfx == 1 && afy == -1) || (bfy == -1 && afx == 1)) {
                        g.drawImage(Imagee.body_tr, x[i] * 20, y[i] * 20, null);
                    } else if ((bfx == 1 && afy == 1) || (bfy == 1 && afx == 1)) {
                        g.drawImage(Imagee.body_br, x[i] * 20, y[i] * 20, null);
                    }
                }
            }
        }
    }
}

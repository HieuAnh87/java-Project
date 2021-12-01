
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Imagee {
    public static Image food1, food2, food3, map, stone, bush;
    public static Image head_up, head_left, head_down, head_right;
    public static Image tail_up, tail_left, tail_down, tail_right;
    public static Image body_horizontal, body_vertical, body_bl, body_br, body_tl, body_tr;
    public static void load() {
        try {
            map = ImageIO.read(new File("image/map.png"));
            food1 = ImageIO.read(new File("image/Mushroom_1.png"));
            food2 = ImageIO.read(new File("image/Mushroom_2.png"));
            food3 = ImageIO.read(new File("image/Mushroom_3.png"));
            stone = ImageIO.read(new File("image/stone.png"));
            bush = ImageIO.read(new File("image/tree_52.png"));
            head_up = ImageIO.read(new File("image/head_up.png"));
            head_left = ImageIO.read(new File("image/head_left.png"));
            head_down = ImageIO.read(new File("image/head_down.png"));
            head_right = ImageIO.read(new File("image/head_right.png"));
            tail_up = ImageIO.read(new File("image/tail_up.png"));
            tail_left = ImageIO.read(new File("image/tail_left.png"));
            tail_down = ImageIO.read(new File("image/tail_down.png"));
            tail_right = ImageIO.read(new File("image/tail_right.png"));
            body_horizontal = ImageIO.read(new File("image/body_horizontal.png"));
            body_vertical = ImageIO.read(new File("image/body_vertical.png"));
            body_bl = ImageIO.read(new File("image/body_bl.png"));
            body_tl = ImageIO.read(new File("image/body_tl.png"));
            body_br = ImageIO.read(new File("image/body_br.png"));
            body_tr = ImageIO.read(new File("image/body_tr.png"));
        } catch (Exception e) {
        }
    }
}

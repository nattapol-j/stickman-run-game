import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {
    private int x, y;
    private int width, height;
    private int speed;
    private int type; 

    public Obstacle(int x, int type) {
        this.x = x;
        this.type = type;
        this.speed = 7;

        if (type == 0) {
            this.width = 30;
            this.height = 30;
            this.y = 280;
        } else if (type == 1) {
            this.width = 25;
            this.height = 55;
            this.y = 255;
        } else if (type == 2) {
            this.width = 35;
            this.height = 20;
            this.y = 210; 
        }
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        if (type == 0) {
            g.setColor(Color.ORANGE);
            g.fillRoundRect(x, y, width, height, 10, 10); 
        } else if (type == 1) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height); 
        } else if (type == 2) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height); 
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    private int x, y;
    private int width, height;
    private int yVelocity;
    private int gravity;
    private boolean isJumping;

    private int currentFrame;
    private int frameCounter;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 60;
        this.yVelocity = 0;
        this.gravity = 1;
        this.isJumping = false;
        this.currentFrame = 0;
        this.frameCounter = 0;
    }

    public void update() {
        yVelocity += gravity;
        y += yVelocity;

        int groundLevel = 250;
        if (y >= groundLevel) {
            y = groundLevel;
            yVelocity = 0;
            isJumping = false;

            frameCounter++;
            if (frameCounter >= 6) {
                currentFrame = (currentFrame + 1) % 2;
                frameCounter = 0;
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            yVelocity = -15;
            isJumping = true;
        }
    }

    public void fastFall() {
        if (isJumping) {
            yVelocity = 35; 
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);

        // วาดส่วนหัว
        g.drawOval(x + 5, y, 20, 20);
        // วาดลำตัว
        g.drawLine(x + 15, y + 20, x + 15, y + 45);

        if (isJumping) {
            g.drawLine(x + 15, y + 30, x, y + 25);      
            g.drawLine(x + 15, y + 30, x + 30, y + 25);   
            g.drawLine(x + 15, y + 45, x + 5, y + 60);    
            g.drawLine(x + 15, y + 45, x + 25, y + 60);   
        } else if (currentFrame == 0) {
            g.drawLine(x + 15, y + 30, x, y + 35);      
            g.drawLine(x + 15, y + 30, x + 30, y + 25);   
            g.drawLine(x + 15, y + 45, x, y + 60);      
            g.drawLine(x + 15, y + 45, x + 25, y + 55);   
        } else {
            g.drawLine(x + 15, y + 30, x + 5, y + 25);    
            g.drawLine(x + 15, y + 30, x + 25, y + 35);   
            g.drawLine(x + 15, y + 45, x + 10, y + 55);   
            g.drawLine(x + 15, y + 45, x + 20, y + 60);   
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
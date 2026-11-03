import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private Random random;
    private int score;
    private boolean isGameOver;
    private int obstacleSpawnTimer = 0;
    private int minSpawnInterval = 30; 

    public GamePanel() {
        setPreferredSize(new Dimension(600, 350));
        setBackground(new Color(200, 235, 255));
        setFocusable(true);
        addKeyListener(this);

        player = new Player(50, 250);
        obstacles = new ArrayList<>();
        random = new Random();
        score = 0;
        isGameOver = false;

        timer = new Timer(100, this); 
        timer.start();
    }

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g); 

    g.setColor(new Color(150, 150, 150));
    g.fillRect(0, 310, 600, 40); 

    g.setColor(Color.DARK_GRAY);
    g.drawLine(0, 310, 600, 310);

    player.draw(g);
    for (Obstacle obs : obstacles) {
        obs.draw(g);
    }

    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.BOLD, 16));
    g.drawString("Score: " + score, 20, 30);

    if (isGameOver) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 200, 150);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Press ENTER to Restart", 210, 190);
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            player.update();

            obstacleSpawnTimer++;
            
            if (obstacleSpawnTimer >= minSpawnInterval) {
    if (random.nextInt(100) < 30) { 
        
        int randomType = random.nextInt(3); 
        
        obstacles.add(new Obstacle(600, randomType));
        
        obstacleSpawnTimer = 0;
    }
}

            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle obs = obstacles.get(i);
                obs.update();

                if (player.getBounds().intersects(obs.getBounds())) {
                    isGameOver = true;
                    timer.stop();
                }

                if (obs.getX() < -30) {
                    obstacles.remove(i);
                    score++;
                }
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.drawLine(0, 310, 600, 310);

        player.draw(g);
        for (Obstacle obs : obstacles) {
            obs.draw(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 20, 30);

        if (isGameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 200, 150);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press ENTER to Restart", 210, 190);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.jump();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.fastFall();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && isGameOver) {
            player = new Player(50, 250);
            obstacles.clear();
            score = 0;
            isGameOver = false;
            obstacleSpawnTimer = 0;
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
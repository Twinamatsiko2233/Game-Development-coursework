import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private Timer timer;

    // Game objects
    private int paddleX = 300;
    private int ballX = 350, ballY = 300;
    private int ballDX = 3, ballDY = -3;

    // Game stats
    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private int totalBricks = 21;
    private boolean play = true;
    private boolean isPaused = false;

    // Brick layout + strength
    private int[][] brickStrength = new int[3][7];
    private boolean[][] bricks = new boolean[3][7];
    private int brickWidth = 80;
    private int brickHeight = 30;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(8, this);
        timer.start();
        loadLevel(level);
    }

    // Level loader
    private void loadLevel(int lvl) {
        level = lvl;
        play = true;
        isPaused = false;

        // Set difficulty
        if (level == 1) {
            ballDX = 3; ballDY = -3;
        } else if (level == 2) {
            ballDX = 4; ballDY = -4;
            totalBricks = 24;
        } else if (level == 3) {
            ballDX = 5; ballDY = -5;
        } else if (level == 4) {
            ballDX = 6; ballDY = -6;
        }

        // Reset ball position
        ballX = 350;
        ballY = 300;

        // Create bricks and strengths
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                bricks[i][j] = true;

                // Assign strength based on level
                if (level == 1) brickStrength[i][j] = 1;
                if (level == 2) brickStrength[i][j] = (j % 2 == 0) ? 2 : 1;
                if (level == 3) brickStrength[i][j] = 2;
                if (level == 4) brickStrength[i][j] = (j % 3 == 0) ? 3 : 2;
            }
        }

        if (level == 2) totalBricks = countVisibleBricks();
        if (level != 2) totalBricks = countVisibleBricks();
    }

    private int countVisibleBricks() {
        int count = 0;
        for (int i = 0; i < bricks.length; i++)
            for (int j = 0; j < bricks[0].length; j++)
                if (bricks[i][j]) count++;
        return count;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 600);

        // Draw bricks
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j]) {
                    if (brickStrength[i][j] == 1) g.setColor(Color.CYAN);
                    if (brickStrength[i][j] == 2) g.setColor(Color.ORANGE);
                    if (brickStrength[i][j] == 3) g.setColor(Color.MAGENTA);

                    g.fillRect(j * brickWidth + 60, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 60, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }

        // Paddle
        g.setColor(Color.BLUE);
        g.fillRect(paddleX, 520, 100, 12);

        // Ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 16, 16);

        // HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Level: " + level, 20, 50);
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 600, 30);
        g.drawString("Bricks Left: " + totalBricks, 280, 30);

        // Pause overlay
        if (isPaused && play) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("PAUSED", 290, 300);
        }

        // Game Over
        if (!play && lives == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("GAME OVER!", 280, 300);
            g.drawString("Final Score: " + score, 275, 340);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press ENTER to Restart", 270, 380);
        }

        // Win overlay + level transition hint
        if (!play && lives > 0 && totalBricks == 0 && level < 4) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("LEVEL " + level + " CLEARED!", 230, 280);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Advancing to Level " + (level + 1) + "...", 270, 320);
        }

        if (!play && totalBricks == 0 && level == 4) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("YOU COMPLETED ALL LEVELS!", 190, 300);
            g.drawString("Final Score: " + score, 280, 340);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press ENTER to Play Again", 260, 380);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play && !isPaused) {
            ballX += ballDX;
            ballY += ballDY;

            // Wall collision
            if (ballX < 0 || ballX > 684) ballDX = -ballDX;
            if (ballY < 0) ballDY = -ballDY;

            // Paddle collision
            Rectangle ballRect = new Rectangle(ballX, ballY, 16, 16);
            if (ballRect.intersects(new Rectangle(paddleX, 520, 100, 12))) {
                ballDY = -ballDY;
            }

            // Brick collisions
            for (int i = 0; i < bricks.length; i++) {
                for (int j = 0; j < bricks[0].length; j++) {
                    if (bricks[i][j]) {
                        Rectangle brickRect = new Rectangle(j * brickWidth + 60, i * brickHeight + 50, brickWidth, brickHeight);
                        if (ballRect.intersects(brickRect)) {
                            brickStrength[i][j]--;

                            if (brickStrength[i][j] <= 0) {
                                bricks[i][j] = false;
                                totalBricks--;
                                score += 10;
                            }

                            ballDY = -ballDY;
                        }
                    }
                }
            }

            // Ball missed
            if (ballY > 570) {
                lives--;
                ballX = 350;
                ballY = 300;
                if (lives <= 0) play = false;
            }

            // Level cleared → advance
            if (totalBricks == 0 && lives > 0) {
                play = false;
                if (level < 4) {
                    // short delay before next level
                    Timer delay = new Timer(1200, evt -> loadLevel(level + 1));
                    delay.setRepeats(false);
                    delay.start();
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isPaused && play) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && paddleX > 0) paddleX -= 25;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddleX < 600) paddleX += 25;
        }

        // PAUSE / RESUME everything
        if (e.getKeyCode() == KeyEvent.VK_SPACE && play) {
            isPaused = !isPaused;
            if (isPaused) timer.stop();
            else timer.start();
        }

        // Restart
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !play) {
            loadLevel(1);
            score = 0;
            lives = 3;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}

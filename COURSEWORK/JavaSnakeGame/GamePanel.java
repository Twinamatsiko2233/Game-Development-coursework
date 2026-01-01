import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    static final int TILE_SIZE = 25;
    static final int GRID_WIDTH = 24;
    static final int GRID_HEIGHT = 24;
    static final int WIDTH = TILE_SIZE * GRID_WIDTH;
    static final int HEIGHT = TILE_SIZE * GRID_HEIGHT;

    Snake snake;
    Point food;
    ArrayList<Point> obstacles = new ArrayList<>();

    int score = 0;
    int level = 1;
    int highScore = 0;
    boolean running = false;
    boolean paused = false;
    String view = "MENU";

    Timer timer;
    int delay = 180; // Level 1 default slow

    Image snakeHeadImg;
    Image snakeTailImg;

    final String HS_FILE = "snake_highscore.dat";

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 40));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        loadHighScore();
        snake = new Snake(GRID_WIDTH/2, GRID_HEIGHT/2);
        spawnFood();

        try {
            snakeHeadImg = ImageIO.read(new File("snake_head.png")).getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
            snakeTailImg = ImageIO.read(new File("snake_tail.png")).getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        } catch(Exception e) {
            System.out.println("Error loading snake images. Make sure PNG files exist.");
        }

        timer = new Timer(delay, this);
        timer.start();
    }

    void loadHighScore() {
        try {
            File f = new File(HS_FILE);
            if (!f.exists()) return;
            DataInputStream d = new DataInputStream(new FileInputStream(f));
            highScore = d.readInt();
            d.close();
        } catch(Exception ignored) {}
    }

    void saveHighScore() {
        try {
            DataOutputStream o = new DataOutputStream(new FileOutputStream(HS_FILE));
            o.writeInt(highScore);
            o.close();
        } catch(Exception ignored) {}
    }

    void spawnFood() {
        Random r = new Random();
        while(true) {
            food = new Point(r.nextInt(GRID_WIDTH), r.nextInt(GRID_HEIGHT));
            if(!snake.body.contains(food) && !obstacles.contains(food)) break;
        }
    }

    void levelUp() {
        level++;
        if (level > 4) return;

        if(level == 2) delay = 120;
        if(level == 3) delay = 80;
        if(level == 4) delay = 60;

        createObstacles();
        snake.reset(GRID_WIDTH/2, GRID_HEIGHT/2);
        spawnFood();
        timer.setDelay(delay);
    }

    void createObstacles() {
        obstacles.clear();
        if(level == 1) return;
        Random r = new Random();
        int count = level * 2;
        for(int i=0;i<count;i++) {
            obstacles.add(new Point(r.nextInt(GRID_WIDTH), r.nextInt(GRID_HEIGHT)));
        }
    }

    void restart() {
        level = 1;
        score = 0;
        delay = 180;
        obstacles.clear();
        snake.reset(GRID_WIDTH/2, GRID_HEIGHT/2);
        spawnFood();
        running = true;
        paused = false;
        view = "GAME";
        timer.setDelay(delay);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running && !paused && view.equals("GAME")) {
            snake.move();

            if(snake.head.equals(food)) {
                score += 10;
                snake.grow();
                spawnFood();
            }

            if(snake.selfCollision() || snake.wallCollision() || obstacles.contains(snake.head)) {
                running = false;
                view = "OVER";
                if(score > highScore) {
                    highScore = score;
                    saveHighScore();
                }
            }

            if(score >= level * 80) {
                levelUp();
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(view.equals("MENU")) drawMenu(g);
        if(view.equals("GAME")) drawGame(g);
        if(view.equals("OVER")) {
            drawGame(g);
            drawGameOver(g);
        }
    }

    void drawMenu(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("SNAKE GAME", WIDTH/2 - 60, HEIGHT/2 - 20);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Press 1 — Play", WIDTH/2 - 50, HEIGHT/2 + 10);
        g.drawString("Press 2 — Instructions", WIDTH/2 - 80, HEIGHT/2 + 35);
        g.drawString("Press 3 — Quit", WIDTH/2 - 45, HEIGHT/2 + 60);
        g.drawString("High Score: " + highScore, WIDTH/2 - 65, HEIGHT - 5);
    }

    void drawGame(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        for(int i=0;i<snake.body.size();i++) {
            Point p = snake.body.get(i);
            if(i == 0 && snakeHeadImg != null) {
                g.drawImage(snakeHeadImg, p.x * TILE_SIZE, p.y * TILE_SIZE, null);
            } else if (i == snake.body.size()-1 && snakeTailImg != null) {
                g.drawImage(snakeTailImg, p.x * TILE_SIZE, p.y * TILE_SIZE, null);
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Score: " + score + "  Level: " + level + "  High Score: " + highScore, 5, HEIGHT + 30);

        if(paused) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("PAUSED", WIDTH/2 - 40, HEIGHT/2);
        }
    }

    void drawGameOver(Graphics g) {
        g.setColor(new Color(0,0,0,160));
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("GAME OVER", WIDTH/2 - 55, HEIGHT/2 - 10);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Final Score: " + score, WIDTH/2 - 50, HEIGHT/2 + 20);
        g.drawString("Press R to Restart", WIDTH/2 - 65, HEIGHT/2 + 50);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        if(view.equals("MENU")) {
            if(k == KeyEvent.VK_1) { running = true; view = "GAME"; }
            if(k == KeyEvent.VK_2) JOptionPane.showMessageDialog(this, "Use arrow keys to move.\nEat food to grow.\nAvoid walls and yourself.\nSPACE pauses.");
            if(k == KeyEvent.VK_3) System.exit(0);
        }

        if(view.equals("OVER") && k == KeyEvent.VK_R) restart();

        if(view.equals("GAME")) {
            if(k == KeyEvent.VK_LEFT && snake.dir != 'R') snake.dir = 'L';
            if(k == KeyEvent.VK_RIGHT && snake.dir != 'L') snake.dir = 'R';
            if(k == KeyEvent.VK_UP && snake.dir != 'D') snake.dir = 'U';
            if(k == KeyEvent.VK_DOWN && snake.dir != 'U') snake.dir = 'D';
            if(k == KeyEvent.VK_SPACE) paused = !paused;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}

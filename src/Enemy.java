import java.net.MalformedURLException;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {
    private static final int HEALTH_BAR_UPDATE_INTERVAL = 200; // Update interval in milliseconds
    private long lastHealthBarUpdate = System.currentTimeMillis();

    private int health;
    private int damage = 15;
    private int speed;
    private Picture enemyImage;
    private int x;
    private int y;
    private boolean won = false;
    private boolean dead = false;
    private Rectangle healthbar;
    private Rectangle OutLinehealthbar;

    Sound sound = new Sound();
    public void playSoundEffect(int i) {

        sound.setFile(i);
        sound.play();
    }

    public Enemy(int x, int y) throws MalformedURLException {
        enemyImage = new Picture(x, y, "resources/enemySkeletonLeft100x100.png");
        if (x < 40 * Window.cellSize) {
            enemyImage.load("resources/enemySkeletonRight100x100.png");
        }
        OutLinehealthbar = new Rectangle(x + 34, y + 14, 31, 6);
        healthbar = new Rectangle(x + 35, y + 15, 30, 5);

        healthbar.fill();
        healthbar.setColor(Color.RED);
        OutLinehealthbar.draw();
        enemyImage.draw();


        this.x = x;
        this.y = y;
        health = 100;
        damage = 10;
        speed = 1;


    }

    public void move(int playerX, int playerY) {
        
        int targetX = playerX;
        int targetY = playerY;

        if (x > targetX) {
            x -= speed;
            enemyImage.translate(-speed, 0);
            healthbar.translate(-speed, 0);
            OutLinehealthbar.translate(-speed, 0);
        } else if (x < targetX) {
            x += speed;
            enemyImage.translate(speed, 0);
            healthbar.translate(speed, 0);
            OutLinehealthbar.translate(speed, 0);
        }

        if (y > targetY) {
            y -= speed;
            enemyImage.translate(0, -speed);
            healthbar.translate(0, -speed);
            OutLinehealthbar.translate(0, -speed);
        } else if (y < targetY) {
            y += speed;
            enemyImage.translate(0, speed);
            healthbar.translate(0, speed);
            OutLinehealthbar.translate(0, speed);
        }

        if (x == targetX && y == targetY) {
            won = true;
            enemyImage.delete();
        }
    }


    public boolean hasWon() {
        return won;
    }

    /**
     * @param damage
     */
    public void enemyAttacked(int damage) {
        int initialHealth = health;
        health -= damage;

        if (System.currentTimeMillis() - lastHealthBarUpdate >= HEALTH_BAR_UPDATE_INTERVAL) {
            int healthbarWidth = 50;
            int targetWidth = (int) (health / 100.0 * healthbarWidth);

            if (healthbar.getWidth() > targetWidth) {
                healthbar.grow(targetWidth - healthbar.getWidth(), 0);
                healthbar.translate(-damage * 2, 0);
            }

            lastHealthBarUpdate = System.currentTimeMillis();
        }

        if (health <= 0) {
            healthbar.delete();
            OutLinehealthbar.delete();
            dead = true;
            enemyImage.delete();
            playSoundEffect(1);
        }
    }

    public void attackTower(Player player) {
        player.playerAttacked(damage);
        System.out.println("Tower attacked, life: " + player.getHealth());
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void superPowerHealth(int health) { //alteração joana
        this.health += health;
    }

    public boolean isDead() {
        return dead;
    }

}

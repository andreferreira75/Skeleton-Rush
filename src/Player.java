import java.util.Timer;
import java.util.TimerTask;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private int health;
    private final Picture playerImage;
    private int x;
    private int y;
    private Ellipse range;
    private int towerRange = 160;
    private int playerDamage = 1;
    private Rectangle healthbar;
    private Rectangle OutLinehealthbar;
    private int money = 0;
    private boolean showHealthbar;

    public Player() {
        x = 40 * Window.cellSize;
        y = 22 * Window.cellSize;
        range = new Ellipse(x - 7 * Window.cellSize - Window.PADDING, y - 7 * Window.cellSize, 20 * Window.cellSize, 20 * Window.cellSize);
        range.setColor(Color.WHITE);
        playerImage = new Picture(x, y, "resources/tower.png");
        health = 100;
        OutLinehealthbar = new Rectangle(x + 3, y - 16, 71, 8);
        healthbar = new Rectangle(x + 4, y - 15, 70, 7);
        money = 0;
        showHealthbar = true;

        startEarningMoney();
    }

    public void playerShow() {
        playerImage.draw();
        range.draw();

        if (showHealthbar) {
            OutLinehealthbar.draw();
            healthbar.fill();
            healthbar.setColor(Color.RED);
        }
    }

    public boolean isInRange(int enemyX, int enemyY) {
        int dx = (enemyX + 50) - 668;
        int dy = (enemyY + 50) - 390;
        int range = (int) (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));

        if (range < towerRange) {
            return true;
        }
        return false;
    }

    public void towerAttack(Enemy enemy) {
        enemy.enemyAttacked(playerDamage);
        System.out.println("Enemy attacked, life: " + enemy.getHealth());
    }

    public void playerAttacked(int damage) {
        health -= damage;

        if (showHealthbar) {
            healthbar.grow(-damage, 0);
        }

        if (health <= 0) {
            if (showHealthbar) {
                healthbar.delete();
            }
            // Handle player defeat here
            System.out.println("Player defeated");
        }
    }

    public void setShowHealthbar(boolean showHealthbar) {
        this.showHealthbar = showHealthbar;
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
    public Ellipse getrange(){
        return range;
    }

    public  void setRange(int z1, int x1,int z2,int x2){
      this.range = new Ellipse(x - z1 * Window.cellSize - Window.PADDING, y - x1 * Window.cellSize, z2 * Window.cellSize, x2 * Window.cellSize) ;  
    }
        public void setHealth(int health) {
        this.health = health;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public void setMoney(int moreMoney){
        money = money + moreMoney;
    }


    public int getPlayerDamage(){
        return playerDamage;
    }


// lucky based get money para os upgrades do jogador (PRECISA DE SER AFINADO)

    private void startEarningMoney() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int randomAmount = 5;
                money += randomAmount;
                System.out.println("Money up by: " + randomAmount + ". Current money: " + money);
            }
        }, 0, 20000);
    }

    public int getMoney() {
        return money;
    }



}
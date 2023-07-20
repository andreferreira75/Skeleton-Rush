import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements MouseListener, KeyboardHandler {

    private Window window;
    private Player player = new Player();
    private ArrayList<Enemy> enemies;
    private boolean menu = true;
    private boolean areSpawning = false;

    private int numberOfEnemies; //alteração Joana
    private int waveNumber = 1;

    Sound sound = new Sound(); // added sounds
    public Keyboard keyboard; // added keyboard

    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {

        sound.stop();
    }

    public Game() throws MalformedURLException {
        Canvas.getInstance().addMouseListener(this);
        showMenu();
        window = new Window(player);
        enemies = new ArrayList<>();
        numberOfEnemies = 0; // alteração Joana
        keyboard = new Keyboard(this);
        keyboardEvent();
    }

    public void showGame() {
        stopMusic();
        playMusic(0);
        window.init();
        player.playerShow();

    }

    public void showMenu() {
        playMusic(2);
        Picture startMenu = new Picture(10,10, "resources/Skeleton Rush Menu v4 Press Enter.png");
        startMenu.draw();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void addEnemy(int x, int y) throws MalformedURLException {
        Enemy enemy = new Enemy(x * Window.cellSize, y * Window.cellSize);
        enemy.superPowerHealth((waveNumber - 1) * 10);
        enemies.add(enemy);

        //  enemies.add(new Enemy(x * Window.cellSize, y * Window.cellSize));
    }

    public void startGame() {
        Thread enemyThread = new Thread(this::enemyThread);
        //Thread towerThread = new Thread(this::towerAttacks);
        //towerThread.start();
        enemyThread.start();
    }

public void enemyThread() {
    while (true) {
        synchronized (enemies) {
            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                enemy.move(player.getX(), player.getY());
                if (player.isInRange(enemy.getX(), enemy.getY()) && !enemy.isDead() && !enemy.hasWon()) {
                    player.towerAttack(enemy);
                }
                if (enemy.hasWon()) {
                    iterator.remove(); // Safely remove the enemy from the collection
                }
                 
        
            }
        }

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new IllegalThreadStateException();
        }
    }
}

    public void spawnEnemies() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {// alteração
                if (numberOfEnemies < 5) {
                    Random r = new Random();
                    int x = r.nextInt(2);
                    if (x == 1) {
                        x = 5;
                    } else {
                        x = 70;
                    }
                    int high = 40;
                    int low = 5;
                    int y = r.nextInt(high - low) + low;
                    try {
                        addEnemy(x, y);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    numberOfEnemies++; //altera
                    makeEnemiesStronger(); //alte
                }
                if (allEnemiesDead()) { // alterac
                    numberOfEnemies = 0;
                    waveNumber++;
                }
            }
        }, 0, 1000);
    }


    public boolean allEnemiesDead() { //alteração Joana
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isDead()) {
                return false;
            }
        }
        return true;
    }

    public void makeEnemiesStronger() {
        for (int i = 0; i < enemies.size(); i++) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!menu) {
            int mouseX = e.getX() - 11;
            int mouseY = e.getY() - 35;
            int mouseCol  = (mouseX / Window.cellSize);
            int mouseRow = (mouseY / Window.cellSize);

            if (!areSpawning) {
                areSpawning = true;
                spawnEnemies(); // Move spawnEnemies() call here
            }
        } else {
         
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void keyboardEvent() {
        KeyboardEvent keyboardEventEnter = new KeyboardEvent();
        keyboardEventEnter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventEnter.setKey(KeyboardEvent.KEY_ENTER);
        keyboard.addEventListener(keyboardEventEnter);


        KeyboardEvent keyboardEventOne = new KeyboardEvent();
        keyboardEventOne.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventOne.setKey(KeyboardEvent.KEY_1);
        keyboard.addEventListener(keyboardEventOne);


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_ENTER:

                if (!menu) {
                    if (!areSpawning) {
                        areSpawning = true;
                        spawnEnemies();
                    }
                } else {
                    menu = false;
                    showGame();
                    spawnEnemies(); // spawn automatico quando começa o jogo
                }
                break;


            case KeyboardEvent.KEY_1:
                window.updateMoneyText();
                System.out.print("Money: " + player.getMoney() + "\nDamage: " + player.getPlayerDamage() +  player.getrange() + "\nVida: "+ player.getHealth() + "\n\n");

                break;
        }
    }


}

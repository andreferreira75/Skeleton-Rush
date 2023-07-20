import java.util.ArrayList;
import java.util.List;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Upgrade implements KeyboardHandler {

    private Rectangle upgradeRectangle;
    private Rectangle opVida;
    private Rectangle opDamage;
    private Rectangle opRange;
    private List<Text> textLabels;
    private Player player;

    public Upgrade(Player player) {
        this.player = player;
        this.textLabels = new ArrayList<>();
    }

    Picture upgradesMenu = new Picture(10,10, "resources/Upgrades Menu 85%.png");
    public void showUpgradeRectangle() {


        upgradesMenu.draw();

        /*
        int rectangleWidth = 600;
        int rectangleHeight = 300;
        int rectangleX = (Window.width - rectangleWidth) / 2;
        int rectangleY = (Window.height - rectangleHeight) / 2;

        upgradeRectangle = new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        upgradeRectangle.setColor(Color.GRAY);
        upgradeRectangle.fill();

        opVida = new Rectangle(rectangleX, rectangleY + 20, rectangleWidth / 3, rectangleHeight / 5);
        opVida.setColor(Color.GREEN);
        opVida.fill();

        opDamage = new Rectangle(rectangleX, rectangleY + 120, rectangleWidth / 3, rectangleHeight / 5);
        opDamage.setColor(Color.GREEN);
        opDamage.fill();

        opRange = new Rectangle(rectangleX, rectangleY + 220, rectangleWidth / 3, rectangleHeight / 5);
        opRange.setColor(Color.GREEN);
        opRange.fill();

        // Add text labels
        Text vidaLabel = new Text(rectangleX + 10, rectangleY + 40, "Upgrade Health Press 5");
        vidaLabel.setColor(Color.BLACK);
        vidaLabel.draw();

        Text damageLabel = new Text(rectangleX + 10, rectangleY + 160, "Upgrade Damage Press 6");
        damageLabel.setColor(Color.BLACK);
        damageLabel.draw();

        Text rangeLabel = new Text(rectangleX + 10, rectangleY + 260, "Upgrade Range Press 7");
        rangeLabel.setColor(Color.BLACK);
        rangeLabel.draw();


        // Add labels to the list
        textLabels.add(vidaLabel);
        textLabels.add(damageLabel);
        textLabels.add(rangeLabel);
         */

        // Set up keyboard events
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent eventVida = new KeyboardEvent();
        eventVida.setKey(KeyboardEvent.KEY_5);
        eventVida.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventVida);

        KeyboardEvent eventDamage = new KeyboardEvent();
        eventDamage.setKey(KeyboardEvent.KEY_6);
        eventDamage.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventDamage);

        KeyboardEvent eventRange = new KeyboardEvent();
        eventRange.setKey(KeyboardEvent.KEY_7);
        eventRange.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventRange);
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()) {
            case KeyboardEvent.KEY_5:
                upgradeHealth();
                break;
            case KeyboardEvent.KEY_6:
                upgradeDamage();
                break;
            case KeyboardEvent.KEY_7:
                upgradeRange();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Do nothing
    }



    public void closeUpgradeRectangle() {
        upgradesMenu.delete();

        /*
        upgradeRectangle.delete();
        opVida.delete();
        opDamage.delete();
        opRange.delete();
         */

        // Delete text labels
        for (Text label : new ArrayList<>(textLabels)) {
            label.delete();
        }
    }

    public void upgradeHealth() {
        if (player.getMoney() >= 10) {
            player.setHealth(player.getHealth() + 10);
            player.setMoney(player.getMoney() - 10);
            System.out.println("Player health upgraded. Current health: " + player.getHealth());
        } else {
            System.out.println("Insufficient money to upgrade health.");
        }
    }

    public void upgradeDamage() {
        if (player.getMoney() >= 10) {
            player.setPlayerDamage(player.getPlayerDamage() + 1);
            player.setMoney(player.getMoney() - 10);
            System.out.println("Player damage upgraded. Current damage: " + player.getPlayerDamage());
        } else {
            System.out.println("Insufficient money to upgrade damage.");
        }
    }

    public void upgradeRange() {
        if (player.getMoney() >= 10) {
            player.setRange(9, 6, 21, 21);
            System.out.println("Player range upgraded. Current range: " + player.getrange());
        } else {
            System.out.println("Insufficient money to upgrade range.");
        }
    }
}

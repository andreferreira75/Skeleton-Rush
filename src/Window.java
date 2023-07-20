import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Window implements KeyboardHandler {

  public static final int width = 1280;
    public static final int height = 720;
    public static final int col = 80;
    public static final int row = 45;
    public static final int cellSize = 16;
    public static Object keyboard;
    private int imageX = (width - 37) / 2;
    private int imageY = (height - 45) / 2;
    public static final int PADDING = 10;
    private Picture background;
    private Rectangle upgradeButton;
    private Upgrade upgrade;
    private Player player;
    private Picture moneyImage;
    private Text moneyText;


    public Window(Player player) {
        this.player = player;
        background = new Picture(PADDING, PADDING, "resources/Background V2.png");

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent upgradeEvent = new KeyboardEvent();
        upgradeEvent.setKey(KeyboardEvent.KEY_U);
        upgradeEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(upgradeEvent);

        KeyboardEvent closeEvent = new KeyboardEvent();
        closeEvent.setKey(KeyboardEvent.KEY_K);
        closeEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(closeEvent);

        upgrade = new Upgrade(player);

        moneyText = new Text(35, height - 150, "X " + player.getMoney());
        moneyText.setColor(Color.WHITE);
        moneyText.grow(Window.cellSize, PADDING);
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static int rowToY(int row) {
        return PADDING + row * cellSize;
    }

    public static int columnToX(int column) {
        return PADDING + column * cellSize;
    }

    public void init() {
        background.draw();
        showUpgradeButton();
        showMoneyImage();
        showMoneyText();
    }
      public void showMoneyImage() {
        int moneyX = 15;
        int moneyY = height-120;
        moneyImage = new Picture(moneyX, moneyY, "resources/Coin.png" );
        moneyImage.draw();
        moneyImage.translate(0, 60);
        updateMoneyText();
    }

 

    public void showMoneyText() {
        String labelText = "X ";

        moneyText = new Text(35, height - 150, labelText + player.getMoney());
        moneyText.setColor(Color.WHITE);
        moneyText.grow(cellSize, PADDING);
        moneyText.draw();
        moneyText.translate(65, 120);
    }

    public void updateMoneyText() {
        moneyText.setText("X " + player.getMoney());
    }

    public void showUpgradeButton() {

        Picture upgradeButton = new Picture(10,10, "resources/Press U for Upgrades Button.png");
        upgradeButton.draw();

        /*
        int buttonWidth = 80;
        int buttonHeight = 35;

        upgradeButton = new Rectangle(width - 70, height - 25, buttonWidth, buttonHeight);
        upgradeButton.setColor(Color.BLUE);
        upgradeButton.fill();

        String buttonText = "Press 'U'";
        Text text = new Text(0, 0, buttonText);
        text.setColor(Color.WHITE);
        double textX = upgradeButton.getX() + buttonWidth / 2 - text.getWidth() / 2;
        double textY = upgradeButton.getY() + buttonHeight / 2 + text.getHeight() / 4;
        text.translate(textX, textY);
        text.draw();

         */
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        if (event.getKey() == KeyboardEvent.KEY_U) {
            upgrade.showUpgradeRectangle();
          
        } else if (event.getKey() == KeyboardEvent.KEY_K) {
            upgrade.closeUpgradeRectangle();
            
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Not used in this example
    }

    public Object getKeyboard() {
        return null;
    }
}

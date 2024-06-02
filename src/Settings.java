
import javax.swing.ImageIcon;
import java.awt.Color;

abstract interface Settings {

    // Window Title
    public String       APP_NAME            = "Test";

    // Window Resizable
    public boolean      RESIZABLE           = false;

    //  Window size (width x height)
    public int          WINDOW_WIDTH        = 1920;
    public int          WINDOW_HEIGHT       = 1080;

    public int          X_CENTER            = WINDOW_WIDTH / 2;
    public int          Y_CENTER            = WINDOW_HEIGHT / 2;

    // Window styles
    public ImageIcon    LOGO_FILE           = new ImageIcon("assets/logo.png");
    public String       APP_FONT            = "Impact";

    public Color        MAINMENU_BG_COLOR   = new Color(200, 200, 200);
    public Color        GAME_BG_COLOR       = new Color(30, 30, 30);

    // Grid settings
    public int          GRID_DIMENSION      = 10;
    public int          GRID_SIZE           = 50;
    public int          GRID_MARGIN         = 5;

    public Color        GRID_EMPTY_COLOR    = new Color(255, 255, 255); 
    public Color        GRID_OCCUPY_COLOR   = new Color(0, 255, 0); 
    public Color        GRID_HIT_COLOR      = new Color(255, 0, 0); 
    public Color        GRID_MISS_COLOR     = new Color(0, 0, 0); 

    public int          GRID_PANEL_SIZE     = GRID_DIMENSION * (GRID_SIZE + GRID_MARGIN) - GRID_MARGIN;

    // Game Settings and Variable
    public boolean      HIDE_ENEMY_SHIPS    = true;
    public int[]        SHIP_SIZES          = {5, 4, 3, 3, 2};
    public String[]     SHIP_NAMES          = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};

    public int EMPTY     = 0;
    public int OCCUPY    = 1;
    public int MISS      = 2;
    public int HIT       = 3;

    // Game Audio
    public float APP_VOL = 75;
    public AudioPlayer MENU_BGM = new AudioPlayer("assets/menu_bgm.wav");
    public AudioPlayer GAME_BGM = new AudioPlayer("assets/game_bgm.wav");
    public AudioPlayer BUTTON_PRESS = new AudioPlayer("assets/button_press.wav");
    public AudioPlayer ATTACK_PHASE = new AudioPlayer("assets/alarm.wav");
    
}

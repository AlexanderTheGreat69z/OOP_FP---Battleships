import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JLabel;

public class MainMenu implements Settings{

    /* -----------------------------------------| GAME TITLE |----------------------------------------- */
    private int gameTitle_width     = Settings.WINDOW_WIDTH;
    private int gameTitle_height    = 200;
    private int gameTitle_x         = 0;
    private int gameTitle_y         = Settings.Y_CENTER - gameTitle_height;

    /* -----------------------------------------| BUTTON PANEL |----------------------------------------- */
    private int buttonPanel_width   = 200;
    private int buttonPanel_height  = 150;
    private int buttonPanel_x       = Settings.X_CENTER - buttonPanel_width / 2;
    private int buttonPanel_y       = (gameTitle_y + gameTitle_height) + 50;

    /* -----------------------------------------| WINDOW COMPONENTS |----------------------------------------- */
    private Frame frame             = new Frame();
    private Label gameTitle         = new Label("|[ BATTLESHITS ]|", 100);
    private Panel buttonPanel       = new Panel(buttonPanel_x, buttonPanel_y, buttonPanel_width, buttonPanel_height);
    private Button play_button      = new Button("Play", 30);
    private Button exit_button      = new Button("Exit", 30);

    // Displays the title of the game
    private void displayGameTitle(){
        this.gameTitle.setOpaque(true);
        this.gameTitle.setBounds(gameTitle_x, gameTitle_y, gameTitle_width, gameTitle_height);
        this.gameTitle.setForeground(Color.black);
        this.gameTitle.setBackground(new Color(150, 150, 150));
        this.gameTitle.setVerticalAlignment(JLabel.CENTER);
        this.gameTitle.setHorizontalAlignment(JLabel.CENTER);
        this.frame.add(gameTitle);
    }

    // Displays the menu buttons
    private void displayMenuButtons(){ 
        this.buttonPanel.setLayout(new BorderLayout(0, 50));
        this.buttonPanel.setOpaque(false);

        this.play_button.setFocusable(false);
        this.play_button.addActionListener(e -> {
            Settings.MENU_BGM.stop();
            Settings.GAME_BGM.play(Settings.APP_VOL, true);
            GameWindow gameWindow  = new GameWindow();
            gameWindow.run();
            this.frame.setVisible(false);
        });

        this.exit_button.setFocusable(false);
        this.exit_button.addActionListener(e -> {
            System.exit(0);
        });

        this.buttonPanel.add(exit_button, BorderLayout.SOUTH);
        this.buttonPanel.add(play_button, BorderLayout.NORTH);
        this.frame.add(buttonPanel);
    }

    public void run(){
        Settings.MENU_BGM.play(Settings.APP_VOL, true);
        frame.setLayout(null);
        this.frame.getContentPane().setBackground(Settings.MAINMENU_BG_COLOR);
        this.displayGameTitle();
        this.displayMenuButtons();
        this.frame.setVisible(true);
    }
}

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;

public class GameWindow implements Settings{

    // Window Frame
    private Frame frame             = new Frame();

    /* ------------------------------| GRID SETUPS |------------------------------ */
    private int grid_y              = 150;

    // Player Grid Set
    private int playerGrid_x        = (X_CENTER - Settings.GRID_PANEL_SIZE) / 2;
    private Grid playerGrid         = new Grid(playerGrid_x, grid_y, false);

    // Enemy Grid Set
    private int enemyGrid_x         = (3*Settings.X_CENTER - Settings.GRID_PANEL_SIZE) / 2;
    private Grid enemyGrid          = new Grid(enemyGrid_x, grid_y, Settings.HIDE_ENEMY_SHIPS);

    /* ------------------------------| BUTTON PANELS |------------------------------ */
    private int button_margin       = 5;
    private int button_textSize     = 30;

    private int buttons_y           = grid_y + Settings.GRID_PANEL_SIZE + 175;
    private int buttons_width       = (Settings.WINDOW_WIDTH / 2) - 200;
    private int buttons_height      = 100;

    // Row command buttons
    private int rowButtons_x        = (Settings.X_CENTER - buttons_width) / 2;
    private Panel rowButtons        = new Panel(rowButtons_x, buttons_y, buttons_width, buttons_height);

    // Col command buttons
    private int colButtons_x        = (3*Settings.X_CENTER - buttons_width) / 2;
    private Panel colButtons        = new Panel(colButtons_x, buttons_y, buttons_width, buttons_height);

    /* ------------------------------| PHASE DISPLAY |------------------------------ */
    private int phaseDisplay_size   = 50;

    private int phaseDisplay_width  = 500;
    private int phaseDisplay_height = 75;
    private int phaseDisplay_x      = Settings.X_CENTER - (phaseDisplay_width / 2);
    private int phaseDisplay_y      = 40;

    private Label phaseDisplay      = new Label("", phaseDisplay_size);

    /* ------------------------------| COORDINATE DISPLAY |------------------------------ */

    private int coordDisplay_size   = 100;
    private int label_size          = 20;
    private int coordDisplay_width  = 300;
    private int coordDisplay_height = 175;
    private int coordDisplay_x      = Settings.X_CENTER - (coordDisplay_width / 2);
    private int coordDisplay_y      = (Settings.Y_CENTER - coordDisplay_height) / 2;

    private Panel coordDisplayPanel = new Panel(coordDisplay_x, coordDisplay_y, coordDisplay_width, coordDisplay_height);
    private Label coordDisplayLabel = new Label("", label_size);
    private Label coordDisplay      = new Label("_ _ _", coordDisplay_size);

    /* ------------------------------| COMMAND DISPLAY |------------------------------ */
    private int comsDisplay_size   = 30;
    private int comsDisplay_width  = 600;
    private int comsDisplay_height = 100;
    private int comsDisplay_x      = Settings.X_CENTER - (comsDisplay_width / 2);
    private int comsDisplay_y      = Settings.Y_CENTER + 190;

    private Label comsDisplay      = new Label(".....", comsDisplay_size);

    /* ------------------------------| DEPLOY BUTTON |------------------------------ */
    private int deployButton_width  = 200;
    private int deployButton_height = 100;
    private int deployButton_x      = Settings.X_CENTER - (deployButton_width/ 2);
    private int deployButton_y      = (coordDisplay_y + coordDisplay_height) + 50;

    private Button deployButton     = new Button("", 20);

    /* ------------------------------| ORIENTATION BUTTONS |------------------------------ */
    private int orientButton_size   = 100;
    private int orientButton_y      = deployButton_y + deployButton_height;
    private int vButton_x           = deployButton_x;
    private int hButton_x           = deployButton_x + orientButton_size;

    private Button vButton = new Button("V", 50);
    private Button hButton = new Button("H", 50);

    /* ------------------------------| ATTACK BUTTON |------------------------------ */
    private int attackButton_size   = 200;
    private int attackButton_x      = Settings.X_CENTER - (attackButton_size / 2);
    private int attackButton_y      = deployButton_y;
    
    private Button attackButton     = new Button("LAUNCH MISSILE", 20);

    /* ------------------------------| GAME DATA DETECTION |------------------------------ */
    private boolean playerDies = false;
    private boolean enemyDies  = false;

    private boolean isVertical = false;
    private boolean isHorizontal = true;

    private String orientation      = "H";

    private String rowPos           = "";
    private int rowIndex            = 0;

    private String colPos           = "";
    private int colIndex            = 0;

    private boolean rowPositionGiven = false;
    private boolean colPositionGiven = false;

    private boolean playerWins       = false;
    private GameOver gameOver        = new GameOver();

    /* ------------------------------| GUI FUNCTIONS |------------------------------ */

    // Load phase title
    private void displayPhaseDisplay(){
        this.phaseDisplay.setBounds(phaseDisplay_x, phaseDisplay_y, phaseDisplay_width, phaseDisplay_height);
        this.phaseDisplay.setOpaque(true);
        this.phaseDisplay.setVerticalAlignment(JLabel.CENTER);
        this.phaseDisplay.setHorizontalAlignment(JLabel.CENTER);
        this.phaseDisplay.setBackground(Color.black);
        this.phaseDisplay.setForeground(Color.white);
        this.frame.add(phaseDisplay);
    }

    // Load player and enemy grids
    private void displayGrids(){
        this.frame.add(playerGrid.getPanel());
        this.frame.add(enemyGrid.getPanel());
    }

    // Load coordinate display
    private void displayCoordinatesDisplay(){
        
        // Coordinates Display Setup
        this.coordDisplayPanel.setLayout(new BorderLayout());
        this.coordDisplayPanel.setOpaque(true);
        this.coordDisplayPanel.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 10));

        // Coordinates Title
        this.coordDisplayLabel.setOpaque(false);
        this.coordDisplayLabel.setVerticalAlignment(JLabel.CENTER);
        this.coordDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        this.coordDisplay.setForeground(Color.black);
        this.coordDisplayPanel.add(coordDisplayLabel, BorderLayout.NORTH);

        // Display
        this.coordDisplay.setOpaque(true);
        this.coordDisplay.setVerticalAlignment(JLabel.CENTER);
        this.coordDisplay.setHorizontalAlignment(JLabel.CENTER);
        this.coordDisplay.setForeground(Color.green);
        this.coordDisplay.setBackground(Color.black);
        this.coordDisplayPanel.add(coordDisplay, BorderLayout.SOUTH);

        this.frame.add(coordDisplayPanel);
    }

    // Load command display
    private void displayCommandsDisplay(){
        // Display
        this.comsDisplay.setBounds(comsDisplay_x, comsDisplay_y, comsDisplay_width, comsDisplay_height);
        this.comsDisplay.setOpaque(true);
        this.comsDisplay.setVerticalAlignment(JLabel.CENTER);
        this.comsDisplay.setHorizontalAlignment(JLabel.CENTER);
        this.comsDisplay.setForeground(Color.red);
        this.comsDisplay.setBackground(Color.black);

        this.frame.add(comsDisplay);
    }

    // Load Row-Column Button Interface
    private void displayButtonsInterface(){
        // Row button setup
        this.rowButtons.setLayout(new GridLayout(2, 5, button_margin, button_margin));
        this.rowButtons.setOpaque(false);

        // Column button setup
        this.colButtons.setLayout(new GridLayout(2, 5, button_margin, button_margin));
        this.colButtons.setOpaque(false);

        this.frame.add(rowButtons);
        this.frame.add(colButtons);

        this.loadButtonFunctions();
    }

    // Load Ship Deployment Orientation Buttons
    private void displayOrientationButtons(){

        // Vertical orientation button
        this.vButton.setBounds(vButton_x, orientButton_y, orientButton_size, orientButton_size);
        this.vButton.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 3));
        this.vButton.setFocusable(false);
        this.vButton.addActionListener(e -> {
            if(isHorizontal) {
                isHorizontal = false;
                this.hButton.setBackground(new Color(125, 125, 125));
            }
            this.vButton.setBackground(Color.green);
            this.isVertical = true;
            this.orientation = "V";
        });

        // Horizontal Orientation button
        this.hButton.setBounds(hButton_x, orientButton_y, orientButton_size, orientButton_size);
        this.hButton.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 3));
        this.hButton.setFocusable(false);
        this.hButton.setBackground(Color.green);
        this.hButton.addActionListener(e -> {
            if(isVertical) {
                isVertical = false;
                this.vButton.setBackground(new Color(125, 125, 125));
            }
            this.hButton.setBackground(Color.green);
            this.isHorizontal = true;
            this.orientation = "H";
        });

        this.frame.add(vButton);
        this.frame.add(hButton);
    }

    // Load Buttons in Row-Column Interface
    private void loadButtonFunctions(){
        // Load Buttons
        char row_letter = 65;
        int col_number = 1;
        for (int i = 0; i < Settings.GRID_DIMENSION; i++){

            // Row Button Setup
            String rowButton_text = "" + row_letter;
            Button rowButton = new Button(rowButton_text, button_textSize);
            rowButton.setFocusable(false);
            rowButton.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 3));

            // Column Button Setup
            String colButton_text = "" + col_number;
            Button colButton = new Button(colButton_text, button_textSize);
            colButton.setFocusable(false);
            colButton.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 3));

            // Row Button Functionality
            int index = i;
            rowButton.addActionListener(e -> {
                Settings.BUTTON_PRESS.stop();
                Settings.BUTTON_PRESS.play(100, false);
                this.rowPos = rowButton_text;
                this.rowIndex = index;

                if (!colPositionGiven){
                    this.coordDisplay.setText(rowPos + "_ _");
                }
                else{
                    this.coordDisplay.setText(rowPos + colPos);
                    this.enableAttackButton();
                    this.enableDeployButton();
                }

                this.rowPositionGiven = true;
            });

            // Column Button Functionality
            colButton.addActionListener(e -> {
                Settings.BUTTON_PRESS.stop();
                Settings.BUTTON_PRESS.play(100, false);
                this.colPos = colButton_text;
                this.colIndex = index;

                if (!rowPositionGiven){
                    this.coordDisplay.setText("_ " + colPos);
                }
                else{
                    this.coordDisplay.setText(rowPos + colPos);
                    this.enableAttackButton();
                    this.enableDeployButton();
                }

                this.colPositionGiven = true;
            });

            rowButtons.add(rowButton);
            colButtons.add(colButton);

            row_letter++;
            col_number++;
        }
    }
    
    /* ------------------------------| ATTACK BUTTON |------------------------------ */

    // Load Attack Button
    private void displayAttackButton(){

        // Attack button setup
        this.attackButton.setBounds(attackButton_x, attackButton_y, attackButton_size, attackButton_size);
        this.attackButton.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 20));
        this.attackButton.setFocusable(false);
        this.attackButton.setVisible(true);
        this.disableAttackButton();

        // Attack button on-press function
        this.attackButton.addActionListener(e -> {
            if (enemyGrid.getData(rowIndex, colIndex) != Settings.HIT || enemyGrid.getData(rowIndex, colIndex) != Settings.MISS){
                this.playerAttack();
                this.enemyAttack();
                this.coordDisplay.setText("_ _ _");
                this.comsDisplay.setText("MISSION: Attack Enemy Grid!");
                this.disableAttackButton();
                this.resetData();
            }
            else {
                this.comsDisplay.setText("We have attacked that point");
            }
            this.checkGameEnd();
        });

        this.frame.add(attackButton);
    }

    // Enable attack button
    private void enableAttackButton(){
        attackButton.setBackground(Color.red);
        attackButton.setForeground(Color.white);
        attackButton.setEnabled(true);
    }

    // Disable attack button
    private void disableAttackButton(){
        attackButton.setBackground(Color.gray);
        attackButton.setForeground(Color.black);
        attackButton.setEnabled(false);
    }

    // Player attack function
    private void playerAttack(){
        playerGrid.attack(enemyGrid, rowIndex, colIndex);
    }
    
   /* ------------------------------| DEPLOY BUTTON |------------------------------ */

    // Load Ship Deployment Button
    private void displayDeployButton(){
        this.deployButton.setBounds(deployButton_x, deployButton_y, deployButton_width, deployButton_height);
        this.deployButton.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 20));
        this.deployButton.setFocusable(false);
        disableDeployButton();
        deployShips(0);

    }

    // Function to deploy ships on button press
    private void deployShips(int i){
        if (i < Settings.SHIP_SIZES.length){
            int shipSize     = Settings.SHIP_SIZES[i];
            String shipName  = Settings.SHIP_NAMES[i];

            this.deployButton.setText("Deploy " + shipName);
            this.comsDisplay.setText("Deploy " + shipName + " of length " + shipSize);
            this.deployButton.addActionListener(e -> {
                boolean player_spaceAvail = playerGrid.checkSpace(orientation, rowIndex, colIndex, shipSize);
                if(player_spaceAvail){
                    this.playerDeployShip(orientation, shipSize);
                    this.enemyDeployShip(this.generateOrientation(), shipSize);

                    this.coordDisplay.setText("_ _ _");
                    this.disableDeployButton();
                    this.resetData();
                    this.deployShips(i + 1);
                }
            });
            this.frame.add(deployButton);
        }
        else{
            this.attackPhase();
        }
    }

    // Enable deployment button
    private void enableDeployButton(){
        deployButton.setBackground(Color.black);
        deployButton.setForeground(Color.green);
        deployButton.setEnabled(true);
    }

    // Disable deployment button
    private void disableDeployButton(){
        deployButton.setBackground(Color.gray);
        deployButton.setForeground(Color.black);
        deployButton.setEnabled(false);
    }

    // Player ship deployment function
    private void playerDeployShip(String ori, int size){
        playerGrid.spawnShip(ori, rowIndex, colIndex, size);
    }
    
    /* ------------------------------| GAME PHASES |------------------------------ */
    
    // DEPLOYMENT PHASE: Player and enemy strategicly position all their ships in the grid
    private void deploymentPhase(){
        this.attackButton.setVisible(false);

        this.displayPhaseDisplay();
        this.phaseDisplay.setText("DEPLOYMENT PHASE");
        this.coordDisplayLabel.setText("Ship Deployment Position");

        this.displayDeployButton();
        this.displayOrientationButtons();
    }

    // ATTACK PHASE: Player and enemy guess and attack opposing ship positions
    private void attackPhase(){
        Settings.ATTACK_PHASE.play(Settings.APP_VOL, false);
        this.comsDisplay.setText("MISSION: Attack Enemy Grid!");
        this.deployButton.setVisible(false);
        this.vButton.setVisible(false);
        this.hButton.setVisible(false);

        this.frame.remove(vButton);
        this.frame.remove(hButton);
        this.frame.remove(deployButton);

        this.displayAttackButton();
        this.phaseDisplay.setText("ATTACK PHASE");
        this.coordDisplayLabel.setText("Missile Lock-On Position");
    }
    
    /* ------------------------------| ENEMY AI |------------------------------ */

    // Randomly generate orientation for enemy ship deployment
    private String generateOrientation(){
        String[] oris = {"V", "H"};
        String r_orient = oris[(int)(Math.random() * 2)].toUpperCase();
        return r_orient;
    }

    // Randomly generate positions for enemy ship deployment
    private void enemyDeployShip(String r_orient, int ship_size){

        // Generate enemy deploy position
        int rand_r = 0;
        int rand_c = 0;

        if (r_orient == "V"){
            rand_r = (int)(Math.random() * 5);
            rand_c = (int)(Math.random() * 10);
        }
        else if (r_orient == "H"){
            rand_r = (int)(Math.random() * 10);
            rand_c = (int)(Math.random() * 5);
        }

        // Re-check if ship deployment collides with other ships
        boolean spaceAvail = enemyGrid.checkSpace(r_orient, rand_r, rand_c, ship_size);
        if (spaceAvail){
            enemyGrid.spawnShip(r_orient, rand_r, rand_c, ship_size);
        }
        else{
            enemyDeployShip(r_orient, ship_size);
        }
    }

    // Enemy AI for attack
    private void enemyAttack(){
        int rand_r = (int)(Math.random() * 10);
        int rand_c = (int)(Math.random() * 10);

        if (playerGrid.getData(rand_r, rand_c) != Settings.HIT || playerGrid.getData(rand_r, rand_c) != Settings.MISS){
            enemyGrid.attack(playerGrid, rand_r, rand_c);
        }
        else{
            enemyAttack();
        }
    }
    
    /* ------------------------------| OTHER FUNCTIONS |------------------------------ */

    // End  the game if one grid has all ships dead
    public void checkGameEnd(){
        this.playerDies = playerGrid.allDead();
        this.enemyDies  = enemyGrid.allDead();

        if (playerDies || enemyDies){
            if(enemyDies){this.playerWins = true;}
            Settings.GAME_BGM.stop();
            this.gameOver.setWin(this.playerWins);
            this.gameOver.run();
            this.frame.setVisible(false);
        }
    }
    
    // Reset all input data
    public void resetData(){
        rowPositionGiven = false;
        colPositionGiven = false;
        isVertical = false;
        isHorizontal = false;

        rowIndex = 0;
        colIndex = 0;

        rowPos      = "";
        colPos      = "";
    }

    // Return the last living player
    public boolean getWinner(){
        return this.playerWins;
    }
    
    public void run(){
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(Settings.GAME_BG_COLOR);

        this.displayGrids();
        this.displayCoordinatesDisplay();
        this.displayCommandsDisplay();
        this.displayButtonsInterface();

        this.deploymentPhase();
        this.frame.setVisible(true);
    }
}
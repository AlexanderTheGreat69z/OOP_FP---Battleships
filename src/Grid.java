import java.awt.Color;
import java.awt.GridLayout;
//import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Grid implements Settings{

    /* -----------------------------------------| GRID OPTIONS |----------------------------------------- */
    private int border_padding = 10;
    private boolean shipPositionsHidden;

    /* -----------------------------------------| GRID ESSENTIALS |----------------------------------------- */
    private int[][] dataMatrix = new int[Settings.GRID_DIMENSION][Settings.GRID_DIMENSION];
    private Panel[][] gridMatrix = new Panel[Settings.GRID_DIMENSION][Settings.GRID_DIMENSION];
    private Panel gridPanel;


    Grid(int x, int y, boolean hideShips){
        this.shipPositionsHidden = hideShips;
        gridPanel = new Panel(x, y, Settings.GRID_PANEL_SIZE, Settings.GRID_PANEL_SIZE);
        gridPanel.setOpaque(false);

        // Create Border
        gridPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(75, 75, 75), 30), 
            new EmptyBorder(border_padding, border_padding, border_padding, border_padding)
        ));

        // Set Grids with GridLayout
        gridPanel.setLayout(new GridLayout(Settings.GRID_DIMENSION, Settings.GRID_DIMENSION, Settings.GRID_MARGIN, Settings.GRID_MARGIN));
        for (int i = 0; i < Settings.GRID_DIMENSION; i++) {
            for (int j = 0; j < Settings.GRID_DIMENSION; j++){
                Panel grid = new Panel(0, 0, Settings.GRID_SIZE, Settings.GRID_SIZE);
                grid.setBackground(Settings.GRID_EMPTY_COLOR);

                gridPanel.add(grid);
                this.gridMatrix[i][j] = grid;
            }
        }

    }

    /* -----------------------------------------| SETTERS AND GETTERS |----------------------------------------- */

    // Get Grid Panel Component
    public Panel getPanel(){
        return gridPanel;
    }

    // Get Grid Element
    public Panel getGrid(int row, int col){
        return this.gridMatrix[row][col];
    }

    // Set grid Data
    public void setData(int row, int col, int data){
        this.dataMatrix[row][col] = data;
    }

    // Get grid Data
    public int getData(int row, int col){
        return this.dataMatrix[row][col];
    }

    /* -----------------------------------------| GAME FUNCTIONS |----------------------------------------- */

    // Check for available space for ship deployment to prevent collision
    public boolean checkSpace(String orientation, int r, int c, int size){
        boolean vertical_withinGrid   = r + size < Settings.GRID_DIMENSION + 1;
        boolean horizontal_withinGrid = c + size < Settings.GRID_DIMENSION + 1;
        boolean space_occupied = true;

        for (int i = 0; i < size; i++){
            if (orientation.equals("V") && vertical_withinGrid){
                space_occupied = (this.getData(r+i, c) == Settings.OCCUPY);
            }
            if (orientation.equals("H") && horizontal_withinGrid){
                space_occupied = (this.getData(r, c+i) == Settings.OCCUPY);
            }

            if (space_occupied){return false;}
        }
        return true;
    }

    // Spawn ship of a certain size and orientation within the grid
    public void spawnShip(String orientation, int row, int col, int size){
        for (int i = 0; i < size; i++){
            if(orientation.equals("V")){
                dataMatrix[row+i][col] = Settings.OCCUPY;
                if(!this.shipPositionsHidden){gridMatrix[row+i][col].setBackground(Settings.GRID_OCCUPY_COLOR);}
            }

            if(orientation.equals("H")){
                dataMatrix[row][col+i] = Settings.OCCUPY;
                if(!this.shipPositionsHidden){gridMatrix[row][col+i].setBackground(Settings.GRID_OCCUPY_COLOR);}
            }
        }
    }

    // Attack a grid at a certain position
    public void attack(Grid grid, int row, int col){
        if(grid.getData(row, col) == Settings.EMPTY){
            grid.setData(row, col, Settings.MISS);
            grid.getGrid(row, col).setBackground(Settings.GRID_MISS_COLOR);
        }
        else if(grid.getData(row, col) == Settings.OCCUPY){
            grid.setData(row, col, Settings.HIT);
            grid.getGrid(row, col).setBackground(Settings.GRID_HIT_COLOR);
        }
    }

    // Check if all ships are eliminated
    public boolean allDead(){
        for(int i = 0; i < Settings.GRID_DIMENSION; i++){
            for(int j = 0; j < Settings.GRID_DIMENSION; j++){
                if(this.getData(i, j) == Settings.OCCUPY){
                    return false;
                }
            }
        }
        return true;
    }
}

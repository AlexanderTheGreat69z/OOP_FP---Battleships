
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;


/* -------------------------------------- | Frame | -------------------------------------- */

class Frame extends JFrame{
    Frame(){
        this.setTitle(Settings.APP_NAME);
        this.setIconImage(Settings.LOGO_FILE.getImage());
        this.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Settings.RESIZABLE);
    }
}

/* -------------------------------------- | Label | -------------------------------------- */

class Label extends JLabel implements Settings{
    Label(String text, int size){
        this.setText(text);
        this.setFont(new Font(APP_FONT, Font.PLAIN, size));
    }
}

/* -------------------------------------- | Panel | -------------------------------------- */

class Panel extends JPanel{
    int x, y, w, h;
    Panel(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.setBounds(this.x, this.y, this.w, this.h);
    }

    Panel(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.setBounds(this.x, this.y, this.w, this.h);
        this.setBackground(color);
    }

    public void setPosition(int x, int y){

    }
}

/* -------------------------------------- | Buttons | -------------------------------------- */

class Button extends JButton{
    private Color button_col = new Color(125, 125, 125);
    private Color font_col = new Color(255, 255, 255);

    Button(String text, int textSize){
        this.setBounds(0, 0, 0, 0);
        this.setBackground(button_col);
        this.setForeground(font_col);
        this.setFont(new Font(Settings.APP_FONT, Font.PLAIN, textSize));
        this.setText(text);
    }
}



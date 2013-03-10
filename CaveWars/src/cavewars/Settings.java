package cavewars;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

class Settings extends JFrame{
    public void Fill(int y, String up, String down, String left, String right, String jump) throws IOException {
        PrintWriter settings = new PrintWriter(new BufferedWriter(new FileWriter("Settings.txt"))); //Skapar filen el. öppnar filen. Kommer tömma och sedan fylla igen.
        switch(y){
            case 0:settings.print("Resolution: ");
                    settings.print("400 200");
                    settings.println();
                    break;
            case 1:settings.print("Resolution: ");
                    settings.print("600 300");
                    settings.println();
                    break;
            case 2:settings.print("Resolution: ");
                    settings.print("800 400");
                    settings.println();
                    break;
            case 3:settings.print("Resolution: ");
                    settings.print("1000 500");
                    settings.println();
                    break;
            case 4:settings.print("Resolution: ");
                    settings.print("1500 750");
                    settings.println();
                    break;
        }
        
        settings.print("Player_movement_jump: ");
        settings.print("KEY_" + jump);
        settings.println();
        
        settings.print("Player_movement_up: ");
        settings.print("KEY_" + up);
        settings.println();
        
        settings.print("Player_movement_down: ");
        settings.print("KEY_" + down);
        settings.println();
        
        settings.print("Player_movement_left: ");
        settings.print("KEY_" + left);
        settings.println();
        
        settings.print("Player_movement_right: ");
        settings.print("KEY_" + right);
        settings.println();
        settings.close();
    }
}

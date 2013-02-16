package cavewars;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

class Settings extends JFrame{
    private JComboBox resolutionMenu;
    private JButton ok;
    String [] resolChoise = {"400x200","600x300","800x400","1000x500","1500x750"};
    int x;
    
    public void Menu(){
        setLayout(new FlowLayout());
        
        resolutionMenu = new JComboBox(resolChoise);
        ok = new JButton("Ok");
        
        resolutionMenu.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent event) {
                        if(event.getStateChange()==ItemEvent.SELECTED){
                            x = resolutionMenu.getSelectedIndex();
                        }
                    }
                });
        add(resolutionMenu);
        
        ok.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if(x == 0){
                            
                        }else if(x == 1){
                            
                        }else if(x == 2){
                            
                        }else if(x ==3){
                            
                        }else if(x == 4){
                            
                        }
                    }
                });
        add(ok);
    }

    public void Fill() throws IOException {
        PrintWriter settings = new PrintWriter(new BufferedWriter(new FileWriter("Settings.txt", true))); //Skapar filen el. öppnar filen
        settings.print("Resolution: ");
        settings.print("1000 500");
        settings.println();
        
        settings.print("Player_movement_jump: ");
        settings.print("space");
        settings.println();
        
        settings.print("Player_movement_left: ");
        settings.print("left_key");
        settings.println();
        
        settings.print("Player_movement_right: ");
        settings.print("right_key");
        settings.println();
        
        settings.print("Player_movement_up: ");
        settings.print("up_key");
        settings.println();
        
        settings.print("Player_movement_down: ");
        settings.print("down_key");
        settings.println();
        settings.close();
    }
}

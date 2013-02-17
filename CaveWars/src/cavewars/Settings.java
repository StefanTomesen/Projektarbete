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
                        switch(x){
                            case 0:try {
                                    Fill(x);
                                } catch (IOException ex) {
                                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            break;
                                
                            case 1:try {
                                    Fill(x);
                                } catch (IOException ex) {
                                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            break;
                                
                            case 2:try {
                                    Fill(x);
                                } catch (IOException ex) {
                                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                                
                            case 3:try {
                                    Fill(x);
                                } catch (IOException ex) {
                                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                                
                            case 4:try {
                                    Fill(x);
                                } catch (IOException ex) {
                                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                        }     
                    }
                });
        add(ok);
    }

    public void Fill(int y) throws IOException {
        PrintWriter settings = new PrintWriter(new BufferedWriter(new FileWriter("Settings.txt"))); //Skapar filen el. öppnar filen. Kommer tömma och sedan fylla igen.
        System.out.println(y);
        switch(y){
            case -1:settings.print("Resolution: ");
                    settings.print("1000 500");
                    settings.println();
                    break;
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

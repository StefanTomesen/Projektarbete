/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cavewars;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

/**
 *
 * Mattias Stenqvist, 3B
 */
class IPMenu extends Menu{
    private int width = CaveWars.windowWidth;
    private int height = CaveWars.windowHeight;
	public static String ipAdress = "localhost";
    public static int ipPort = 36745;
    
    public IPMenu() throws FileNotFoundException 
    {   
        MenuButton print_ip = new MenuButton("print_IP", "Insert IP", width/3, height/2);
        //MenuButton print_port = new MenuButton("print_port", "Insert port", width/2, height/2);
        MenuButton localhost = new MenuButton("localhost","Play local", width*2/3, height/2);
        
        buttonList.add(print_ip);
        //buttonList.add(print_port);
        buttonList.add(localhost);

    }
    
    @Override
    public void buttonPressed(String id) {
        switch(id){
            case "print_IP": ipAdress = JOptionPane.showInputDialog("Insert IP adresse"); CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE); break;
            //case "print_port": ipPort = Integer.parseInt(JOptionPane.showInputDialog("Insert port")); break;
            case "localhost": ipAdress = "localhost"; CaveWars.caveWars.enterState(CaveWars.GAME_PLAY_STATE); break;
        }
    }

}

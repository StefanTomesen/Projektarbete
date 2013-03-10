package cavewars;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
 */
class SettingsMenu extends Menu {

    private int width = CaveWars.windowWidth;
    private int height = CaveWars.windowHeight;
    Settings settings = new Settings();
    private String upM = "W";
    private String downM = "S";
    private String leftM = "A";
    private String rightM = "D";
    private String jumpM = "SPACE";
    private int y = 3;

    public SettingsMenu() {
        MenuButton resol1 = new MenuButton("resol1", "Fullscreen"/*"500 x 250"*/, width/4, height/6);
        MenuButton resol2 = new MenuButton("resol2", "600 x 300", (width/4) * 2, height/6);
        MenuButton resol3 = new MenuButton("resol3", "800 x 400", (width/4) * 3, height/6);
        MenuButton resol4 = new MenuButton("resol4", "1000 x 500", (width/3) , (height/6)*2);
        MenuButton resol5 = new MenuButton("resol5", "1500 x 750", (width/3) * 2, (height/6)*2);
        
        MenuButton moveUP = new MenuButton("moveUP", "Key up", width/4, (height/6)*3);
        MenuButton moveDOWN = new MenuButton("moveDOWN", "Key down", (width/4) * 2, (height/6)*3);
        MenuButton moveJUMP = new MenuButton("moveJUMP", "Key jump", (width/4) * 3, (height/6)*3);
        MenuButton moveLEFT = new MenuButton("moveLEFT", "Key left", (width/3), (height/6)*4);
        MenuButton moveRIGHT = new MenuButton("moveRIGHT", "Key right", (width/3) * 2, (height/6)*4);
        
        MenuButton sendInfo = new MenuButton("send", "Done", width/2, (height/6)*5);
        
        buttonList.add(resol1);
        buttonList.add(resol2);
        buttonList.add(resol3);
        buttonList.add(resol4);
        buttonList.add(resol5);
        
        buttonList.add(moveUP);
        buttonList.add(moveDOWN);
        buttonList.add(moveJUMP);
        buttonList.add(moveLEFT);
        buttonList.add(moveRIGHT);
        
        buttonList.add(sendInfo);
    }

    @Override
    public void buttonPressed(String id) {
        switch(id){
            case("resol1"): y = 0; CaveWars.setFullscreenMode(); saveAndClose(); break;//CaveWars.setScreenSize(500, 250); 
            case("resol2"): y = 1; CaveWars.setScreenSize(600, 300); saveAndClose(); break;
            case("resol3"): y = 2; CaveWars.setScreenSize(800, 400); saveAndClose(); break;
            case("resol4"): y = 3; CaveWars.setScreenSize(1000, 500); saveAndClose(); break;
            case("resol5"): y = 4; CaveWars.setScreenSize(1500, 750); saveAndClose(); break;
            case("moveUP"): upM = JOptionPane.showInputDialog(null, "Insert movement key: UP", "Up key", JOptionPane.INFORMATION_MESSAGE); break;
            case("moveDOWN"): downM = JOptionPane.showInputDialog(null, "Insert movement key: DOWN", "Down key", JOptionPane.INFORMATION_MESSAGE); break;
            case("moveJUMP"): jumpM = JOptionPane.showInputDialog(null, "Insert movement key: JUMP", "Jump key", JOptionPane.INFORMATION_MESSAGE); break;
            case("moveLEFT"): leftM = JOptionPane.showInputDialog(null, "Insert movement key: LEFT", "Left key", JOptionPane.INFORMATION_MESSAGE); break;
            case("moveRIGHT"): rightM = JOptionPane.showInputDialog(null, "Insert movement key: RIGHT", "Right key", JOptionPane.INFORMATION_MESSAGE); break;
            case("send"): saveAndClose(); break;
        }
    }
	
	public void saveAndClose()
	{
		try {
			settings.Fill(y, upM, downM, leftM, rightM, jumpM);
		} catch (IOException ex) {
			Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
		CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
	}

}

package cavewars.util;

import javax.swing.JOptionPane;

public class Shutdown
{
	public static void shutDown(String s)
	{
		Dialogs.showMessage(s);
		System.exit(0);
	}
}

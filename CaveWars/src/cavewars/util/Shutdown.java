package cavewars.util;

import javax.swing.JOptionPane;

public class Shutdown
{
	public static void shutDown(String s)
	{
		JOptionPane.showMessageDialog(null, s);
		System.exit(0);
	}
}

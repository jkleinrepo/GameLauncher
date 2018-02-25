import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.awt.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GameLauncher extends JPanel{

	private static JFrame window;
	private static JPanel gameset1, gameset2, gameset3;
	private static String[] directories;
	
	public GameLauncher() {
		
		super(new GridLayout(3,4));
		gameset1 = new JPanel(new GridLayout(0,4));
		gameset2 = new JPanel(new GridLayout(0,4));
		gameset3 = new JPanel(new GridLayout(0,4));
		
		add(gameset1);
		add(gameset2);
		add(gameset3);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException{
		createWindow();
		folderList();
		buttonAssignments();
		window.setVisible(true);
	}
	
	private static void createWindow() {
		window = new JFrame("Game Launcher");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new GameLauncher();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setSize(440, 440);
		window.setResizable(true
				);
		//window.setVisible(true);
	}
	
	private static void buttonAssignments() {
		for (int i = 0; i < directories.length; i++) {
			try {
			ButtonCreator bc = new ButtonCreator(directories[i]);
			
			if (i >= 0 && i <= 3) {
				gameset1.add(bc.launch);
			}
			
			if (i >= 4 && i <= 7) {
				gameset2.add(bc.launch);
			}
			
			if (i >= 8 && i <= 11) {
				gameset3.add(bc.launch);
			}
			
			/*if (i >= 9 && i <= 11) {
				gameset4.add(bc.launch);
			}*/
			}
			
			catch (LineUnavailableException lue) {
				JOptionPane.showMessageDialog(null, lue.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(null, fnfe.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (UnsupportedAudioFileException uafe) {
				JOptionPane.showMessageDialog(null, uafe.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	private static String[] folderList() {
		File file = new File("C:\\Users\\Josh Klein\\Saved Games\\Games");
		directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		return directories;
	}
	
	private static void buildWindow() {
		window.add(gameset1);
		window.add(gameset2);
		window.add(gameset3);
	}
}
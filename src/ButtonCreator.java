import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.*;

public class ButtonCreator {

	public JButton launch;
	private MyListener gameLaunch = new MyListener();
	final String actionTitle;
	final String userName = System.getProperty("user.name");
	private Image image;
	private Image newImg;
	private ImageIcon icon;
	private ImageIcon icon2;
	private ImageIcon notFound;
	
	public ButtonCreator (String gameTitle) throws FileNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException{
		icon = new ImageIcon("C:\\Users\\" + userName +"\\Saved Games\\Games\\"+gameTitle+"\\"+gameTitle+".jpg");
		//image = icon.getImage();
		//newImg = image.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
		//icon = new ImageIcon(newImg);
		icon2 = new ImageIcon("C:\\Users\\"+ userName +"\\Saved Games\\Games\\"+gameTitle+"\\"+gameTitle+"2.jpg");
		notFound = new ImageIcon("C:\\Users\\"+ userName +"\\Saved Games\\Games\\not_found.png");
		
		launch = new JButton(icon);
		launch.setSize(110,110);
		launch.setVisible(true);
		launch.addActionListener(gameLaunch);
		actionTitle = gameTitle;
		File music = new File("C:\\Users\\"+ userName +"\\Saved Games\\Games\\"+actionTitle+"\\"+actionTitle+".wav");
        AudioInputStream song = AudioSystem.getAudioInputStream(music);
        Clip clip = AudioSystem.getClip();
        clip.open(song);
        
        launch.addMouseListener(new java.awt.event.MouseAdapter(){
        	
        	public void mouseEntered (java.awt.event.MouseEvent evt){
        		launch.setIcon(icon2);
        		clip.start();
        	}
        	
        	public void mouseExited(java.awt.event.MouseEvent evt){
        		launch.setIcon(icon);
        		clip.stop();
        		clip.setMicrosecondPosition(clip.getMicrosecondPosition() - clip.getMicrosecondPosition());
        	}
        	

        });
        

	}
	
	private JButton launchDisplay(String exists) {
		JButton iconCheck;
		File file = new File(exists.toString());
		if (file.isFile()) {
			iconCheck = new JButton(icon);
		}
		else {
			iconCheck = new JButton(notFound);
		}
		
		return iconCheck;
	}
	
	private JButton launchSwitch(String exists, JButton iconSwitch) {
		JButton iconCheck = iconSwitch;
		File file = new File(exists.toString());
		if (file.isFile()) {
			if (iconCheck.getIcon().equals(icon)) {
				iconCheck.setIcon(icon2);
			}
			else if (iconCheck.getIcon().equals(icon2)){
				iconCheck.setIcon(icon);
			}
		}
		else {
			iconCheck = new JButton(notFound);
		}
		
		return iconCheck;
	}	
	
	class MyListener implements ActionListener{
		
	public void actionPerformed(ActionEvent e) {
		String run = new String ("C:\\Users\\"+ userName +"\\Saved Games\\Games\\"+actionTitle+"\\"+actionTitle+".lnk");
		try {
			ProcessBuilder pb = new ProcessBuilder ("cmd", "/c", run, "-n", "100");
			pb.start();
			System.exit(0);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(GameLauncher.class.getName()).log(Level.SEVERE, null, ex);
		}
		// TODO Auto-generated method stub
		
	}
	}

}
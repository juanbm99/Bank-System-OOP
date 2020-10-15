package view;






import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import controllers.PrepaidSystemController;
import model.CreditCard;
import model.PrepaidSystem;


public class FrameWelcome extends JFrame implements Serializable {
	private PrepaidSystemController controller;
	private PrepaidSystem ps;



	public FrameWelcome(PrepaidSystemController controller)  {
		this.controller = controller;
		ps=new PrepaidSystem();

		setTitle("PrepaidSystem");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


		JButton button=new JButton("ENTER");

		button.setBackground(Color.gray);
		button.setFont(new Font("Arial",Font.PLAIN,30));


		button.addActionListener(event -> {
			try {
				showDialog();
			} catch (ClassNotFoundException | IOException e) {
				Logger.getLogger(getName());
			} 
		});
		JPanel jp=new JPanel();


		ImageIcon icon=new ImageIcon(getClass().getResource("/resources/credit-card.png"));
		JLabel profile=new JLabel();
		profile.setBounds(400,130,200,200);
		Image img=icon.getImage();
		Image newImg=img.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);

		profile.setIcon(image);
		jp.add(profile);
		jp.setBackground(Color.gray);

		this.getContentPane().add(button);
		this.getContentPane().add(jp,BorderLayout.SOUTH);
		this.setVisible(true);


	}


	private void showDialog() throws ClassNotFoundException, IOException {
		List<CreditCard> lista=new ArrayList();
		lista=ps.getCardHistory().read();
		FramePrepaidSystem view=new FramePrepaidSystem(controller, lista);
		controller.setView(view);
		this.dispose();

	}

}
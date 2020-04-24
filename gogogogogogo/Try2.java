package gogogogogogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Try2 extends JFrame implements ActionListener{

	
	private JPanel contentPane;
	private JToggleButton playBtn;
	private JButton preBtn,nextBtn;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem manu1;
	private JMenuItem manu2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Try2 frame = new Try2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Try2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 260);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("FIle");
		menuBar.add(mnNewMenu);
		
		manu1 = new JMenuItem("Add tracks");
		mnNewMenu.add(manu1);
		
		manu2 = new JMenuItem("Exit");
		mnNewMenu.add(manu2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		preBtn = new JButton("◀◀");
		panel.add(preBtn);
		
		playBtn = new JToggleButton("||▶");
		playBtn.setIcon(null);
		panel.add(playBtn);
		
		nextBtn = new JButton("▶▶");
		panel.add(nextBtn);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

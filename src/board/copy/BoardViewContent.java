package board.copy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class BoardViewContent extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btn_cancel;
	private JTextField txt_main;
	private JTextField txt_writer;
	private JTextArea txt_content;
	private JButton btn_rewrite;
	private JLabel la_boardno;
	private JPanel panel_1;
	private JButton btn_endwrite = new JButton("수정 완료");
	private JButton btn_delete;
	private JLabel la_id = new JLabel("");
	private JLabel lblNewLabel_5;
	private JLabel la_id2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardViewContent frame = new BoardViewContent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardViewContent() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("게시판");
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("글제목 : ");
		panel.add(lblNewLabel);
		
		txt_main = new JTextField();
		panel.add(txt_main);
		txt_main.setColumns(32);
		
		JLabel lblNewLabel_4 = new JLabel("                               ");
		panel.add(lblNewLabel_4);
		
		 la_boardno = new JLabel("New label");
		panel.add(la_boardno);
		
		 panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		 btn_cancel = new JButton("뒤로가기");
		 btn_cancel.addActionListener(this);
		
		JLabel lblNewLabel_2 = new JLabel("작성자 : ");
		panel_1.add(lblNewLabel_2);
		
		txt_writer = new JTextField();
		txt_writer.setColumns(23);
		panel_1.add(txt_writer);
		panel_1.add(btn_cancel);
		
		JLabel lblNewLabel_3 = new JLabel("                         ");
		panel_1.add(lblNewLabel_3);
		
		 btn_rewrite = new JButton("글 수정");
		panel_1.add(btn_rewrite);
		
		btn_delete = new JButton("글 삭제");
		panel_1.add(btn_delete);
		btn_rewrite.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("글내용");
		contentPane.add(lblNewLabel_1, BorderLayout.WEST);
		
		 txt_content = new JTextArea();
		contentPane.add(txt_content, BorderLayout.CENTER);
		txt_content.setEditable(false);
		txt_main.setEditable(false);
		
		lblNewLabel_5 = new JLabel("작성자 : ");
		panel.add(lblNewLabel_5);
		
		la_id2 = new JLabel("아이디");
		panel.add(la_id2);
		btn_endwrite.addActionListener(this);
		btn_delete.addActionListener(this);
		

		
//		if(la_id.getText().equals(la_id2.getText())==true) {
//			btn_delete.setVisible(true);
//			btn_rewrite.setVisible(true);
//		}
//		
//		if(la_id.getText().equals(la_id2.getText())==false) {
//			btn_delete.setVisible(false);
//			btn_rewrite.setVisible(false);
//		}
		setVisible(true);
		
	}
	
	public void textfill(int boardno) {
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAO();
		vo=dao.view(boardno);
		txt_main.setText(vo.getContentname());
		txt_content.setText(vo.getContent());
		la_boardno.setText(vo.getBoardno()+"");
//		txt_writer.setText(vo2.get());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		
		if(btn == btn_cancel) { // 취소버튼
		Board board = new Board();
		board.refresh();
		dispose();
		}
		
		
		if(btn==btn_rewrite) {
			if(la_id.getText().equals(la_id2.getText())==true) {
				
				// 수정버튼
				txt_content.setEditable(true);
				txt_main.setEditable(true);
				btn_rewrite.setVisible(false);
				panel_1.add(btn_endwrite);
				
			}else if(la_id.getText().equals(la_id2.getText())==false) {
				
				JOptionPane.showMessageDialog(this, "작성자만 글을 수정할 수 있습니다.", "게시글 수정취소",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
		if(btn==btn_endwrite) { // 수정완료
			BoardVO vo = new BoardVO();
			BoardDAO dao = new BoardDAO();
		vo.setContent(txt_content.getText());
		vo.setContentname(txt_main.getText());
		vo.setBoardno(Integer.parseInt(la_boardno.getText()));
		int result = dao.modify(vo);
		if(result !=0) {
			JOptionPane.showMessageDialog(this, "글이 수정되었습니다.", "게시글 수정",JOptionPane.INFORMATION_MESSAGE);
			Board board = new Board();
			board.refresh();
			dispose();
			
		}else {
			JOptionPane.showMessageDialog(this, "수정이 실패하였습니다.", "게시글 수정",JOptionPane.WARNING_MESSAGE);
		}
		txt_content.setEditable(false);
		txt_main.setEditable(false);
		btn_rewrite.setVisible(true);
		btn_endwrite.setVisible(false);
		}
		
		
		if(btn==btn_delete) { // 삭제버튼
			if(la_id.getText().equals(la_id2.getText())==true) {
				
				BoardVO vo = new BoardVO();
				BoardDAO dao = new BoardDAO();
				int no = Integer.parseInt(la_boardno.getText());
				
				String option[]= {"예","아니오",};
				int a = JOptionPane.showOptionDialog(this, "정말로 삭제하시겠습니까?", "게시글 삭제", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
				System.out.println(a);
				if(a==0) {
					int result = dao.delete(no);
					
					if(result !=0) {
						JOptionPane.showMessageDialog(this, "글이 삭제되었습니다.", "게시글 삭제",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}else {
					JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.", "게시글 삭제취소",JOptionPane.WARNING_MESSAGE);
				}
			}else if(la_id.getText().equals(la_id2.getText())==false) {
				JOptionPane.showMessageDialog(this, "작성자만 글을 삭제할 수 있습니다.", "게시글 삭제취소",JOptionPane.WARNING_MESSAGE);
				
			}
			
		}
		
	}
	
	public void getid(String a) {
		la_id.setText(a);
	}
	
	public void checkid(String b) {
		la_id2.setText(b);
		
	}
	


}

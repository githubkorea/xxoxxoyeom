package board.copy;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class BoardSub extends Frame implements ActionListener, WindowListener {
	private Label list_lb = new Label("�۸��");
	private Label read_lb = new Label("�ۺ���");
	private List list_li = new List(10, false);
	private TextArea read_ta = new TextArea();
	private Button write_bt = new Button("�۾���");
	private Button refresh_bt = new Button("���ΰ�ħ");
	private Button edit_bt = new Button("����");
	private Button delete_bt = new Button("����");
	private Button end_bt = new Button("����");

	private Dialog write_dlg = new Dialog(this, "�۾���", true);
	private Label dlg_toptitle_lb = new Label("�۾���", Label.CENTER);
	private Label dlg_title_lb = new Label("������ : ", Label.RIGHT);
	private Label dlg_author_lb = new Label("�ۼ��� : ", Label.RIGHT);
	private Label dlg_attach_lb = new Label("÷������ : ", Label.RIGHT);
	private TextField dlg_title_tf = new TextField();
	private TextField dlg_author_tf = new TextField();
	private TextField dlg_attach_tf = new TextField();
	private TextArea dlg_write_ta = new TextArea();
	private Button dlg_attach_bt = new Button("ã�ƺ���");
	private Button dlg_register_bt = new Button("���");
	private Button dlg_cancel_bt = new Button("���");

	public BoardSub() {
		super("�Խ���");
		this.init();
		this.start();
		this.setSize(500, 500);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
	}
	public void init_dlg() {
		write_dlg.setLayout(new BorderLayout(10, 10));
		write_dlg.add("North", dlg_toptitle_lb);
		Panel p = new Panel(new BorderLayout(5, 5));
		Panel p2 = new Panel(new BorderLayout(3, 3));
		Panel p3 = new Panel(new GridLayout(3, 1));
		p3.add(dlg_title_lb);
		p3.add(dlg_author_lb);
		p3.add(dlg_attach_lb);
		p2.add("West", p3);
		Panel p4 = new Panel(new GridLayout(3, 1));
		p4.add(dlg_title_tf);
		p4.add(dlg_author_tf);
		Panel p5 = new Panel(new BorderLayout());
		dlg_attach_tf.setEnabled(false);
		p5.add("Center", dlg_attach_tf);
		p5.add("East", dlg_attach_bt);
		p4.add(p5);
		p2.add("Center", p4);
		p.add("North", p2);
		p.add("Center", dlg_write_ta);
		write_dlg.add("Center", p);
		Panel p1 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(dlg_register_bt);
		p1.add(dlg_cancel_bt);
		write_dlg.add("South", p1);
	}
	public void init() {
		this.init_dlg();
		this.setLayout(new GridLayout(1, 2, 10, 10));
		Panel p = new Panel(new BorderLayout(5, 5));
		p.add("North", list_lb);
		p.add("Center", list_li);
		Panel p2 = new Panel(new GridLayout(1, 2, 5, 5));
		p2.add(write_bt);
		p2.add(refresh_bt);
		p.add("South", p2);
		this.add(p);
		Panel p1 = new Panel(new BorderLayout(5, 5));
		p1.add("North", read_lb);
		read_ta.setEditable(false);
		p1.add("Center", read_ta);
		Panel p3 = new Panel(new GridLayout(1, 3, 5, 5));
		p3.add(edit_bt);
		p3.add(delete_bt);
		p3.add(end_bt);
		p1.add("South", p3);
		this.add(p1);
	}
	public void start() {
		write_bt.addActionListener(this);
		dlg_attach_bt.addActionListener(this);
		dlg_cancel_bt.addActionListener(this);
		dlg_register_bt.addActionListener(this);
		this.addWindowListener(this);
		refresh_bt.addActionListener(this);
		end_bt.addActionListener(this);
		list_li.addActionListener(this); // ���б�.
		edit_bt.addActionListener(this);
		delete_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == write_bt) {
			write_dlg.setSize(300, 400);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension dlg = write_dlg.getSize();
			int xpos = (int) (screen.getWidth() / 2 - dlg.getWidth() / 2);
			int ypos = (int) (screen.getHeight() / 2 - dlg.getHeight() / 2);
			write_dlg.setLocation(xpos, ypos);
			write_dlg.setVisible(true);
		} else if (e.getSource() == dlg_attach_bt) {
			FileDialog fdlg = new FileDialog(this, "ã�ƺ���", FileDialog.LOAD);
			fdlg.setVisible(true);
			String dir = fdlg.getDirectory();
			String file = fdlg.getFile();
			if (dir == null || file == null)
				return;
			dlg_attach_tf.setText(dir.trim() + file.trim());
		} else if (e.getSource() == dlg_cancel_bt) {
			dlg_title_tf.setText("");
			dlg_author_tf.setText("");
			dlg_attach_tf.setText("");
			dlg_write_ta.setText("");
			write_dlg.setVisible(false);
		} else if (e.getSource() == dlg_register_bt) {
			String title = dlg_title_tf.getText();
			String author = dlg_author_tf.getText();
			if (title == null
				|| author == null
				|| title.trim().length() == 0
				|| author.trim().length() == 0) {
				return;
			}
			title = title.trim();
			author = author.trim();
			File dir = new File("boardData");
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file = new File(dir, title + " (" + author + ")");
			try {
				PrintWriter out =
					new PrintWriter(new BufferedWriter(new FileWriter(file)));
				String attach = dlg_attach_tf.getText();
				if (attach == null || attach.trim().length() == 0) {
					out.println("null");
				} else {
					out.println(attach);
				}
				out.println(dlg_write_ta.getText().trim());
				out.close();
			} catch (IOException ee) {
			}
			dlg_title_tf.setText("");
			dlg_author_tf.setText("");
			dlg_attach_tf.setText("");
			dlg_write_ta.setText("");
			write_dlg.setVisible(false);
		} else if (e.getSource() == refresh_bt) {
			File dir = new File("boardData");
			String[] list = dir.list();
			list_li.clear();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					list_li.add(i + 1 + " : " + list[i]);
				}
			}
		} else if (e.getSource() == end_bt) {
			System.exit(0);
		} else if (e.getSource() == list_li) {
			String data = list_li.getSelectedItem();
			data = data.substring(data.indexOf(":") + 1).trim();
			File dir = new File("boardData");
			File file = new File(dir, data);
			String attach = "";
			String contents = "";
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				attach = in.readLine();
				while (true) {
					String s = in.readLine();
					if (s == null)
						break;
					contents += "\n" + s;
				}
				in.close();
			} catch (IOException ee) {
			}
			read_ta.setText("");
			read_ta.append(
				"������ : " + data.substring(0, data.indexOf("(")) + "\n\n");
			read_ta.append(
				"�ۼ��� : "
					+ data.substring(
						data.indexOf("(") + 1,
						data.lastIndexOf(")"))
					+ "\n\n");
			read_ta.append("÷������ : " + attach + "\n\n");
			read_ta.append("���� : " + contents);
		} else if (e.getSource() == edit_bt) {
			if (edit_bt.getLabel().equals("����")) {
				edit_bt.setLabel("������");
				read_ta.setEditable(true);
			} else {
				//�����ϰ�...
				String data = read_ta.getText().trim();
				String title =
					data.substring(
						data.indexOf("������ : ") + 5,
						data.indexOf("�ۼ��� : "));
				String author =
					data.substring(
						data.indexOf("�ۼ��� : ") + 5,
						data.indexOf("÷������ : "));
				String attach =
					data.substring(
						data.indexOf("÷������ : ") + 6,
						data.indexOf("���� : "));
				String contents = data.substring(data.indexOf("���� : ") + 4);
				File dir = new File("boardData");
				File file =
					new File(dir, title.trim() + " (" + author.trim() + ")");
				try {
					PrintWriter out =
						new PrintWriter(
							new BufferedWriter(new FileWriter(file)));
					out.println(attach);
					out.println(contents);
					out.close();
				} catch (IOException ee) {
				}
				edit_bt.setLabel("����");
				read_ta.setEditable(false);
			}
		} else if (e.getSource() == delete_bt) {
			String data = read_ta.getText().trim();
			String title =
				data.substring(
					data.indexOf("������ : ") + 5,
					data.indexOf("�ۼ��� : "));
			String author =
				data.substring(
					data.indexOf("�ۼ��� : ") + 5,
					data.indexOf("÷������ : "));
			File dir = new File("boardData");
			File file =
				new File(dir, title.trim() + " (" + author.trim() + ")");
			file.delete();
			read_ta.setText("");
			String[] list = dir.list();
			list_li.clear();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					list_li.add(i + 1 + " : " + list[i]);
				}
			}
		}
	}
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			System.exit(0);
		}
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowActivated(WindowEvent e) {
		if (e.getSource() == this) {
			File dir = new File("boardData");
			String[] list = dir.list();
			list_li.clear();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					list_li.add(i + 1 + " : " + list[i]);
				}
			}
		}
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
}
public class BoardTest {
	public static void main(String[] args) {
		BoardSub bs = new BoardSub();
	}
}

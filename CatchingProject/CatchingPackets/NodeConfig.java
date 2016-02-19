package CatchingPackets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NodeConfig {

	JFrame frame;
	JLabel label_Title,label_Node,labImg;
	JButton button_Next;
	JPanel p;
	JComboBox cboSelectNodeNo;
	String cboSelect="**Select**";
	int cboSelectIndex;
	@SuppressWarnings("static-access")
	public NodeConfig() {
		frame=new JFrame();
		frame.setResizable(false);
		frame.setTitle("Configuration");
		frame.setSize(500,400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		screenSize.height = screenSize.height/2;
		screenSize.width = screenSize.width/2;
		size.height = size.height/2;
		size.width = size.width/2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label_Title=new JLabel("Network Configuration");
		Font ft=new Font("ariel",Font.BOLD+Font.ITALIC,30);
		label_Title.setFont(ft);
		label_Title.setForeground(Color.DARK_GRAY);
		label_Title.setBounds(90,20,350,40);

		labImg=new JLabel();
		ImageIcon ic=new ImageIcon("nodeconfig.jpg");
		labImg.setIcon(ic);
		labImg.setBounds(0, 40, 500, 400);

		label_Node=new JLabel("Select Number of Nodes");
		Font font=new Font("Calibri",Font.BOLD+Font.ITALIC,19);
		label_Node.setForeground(Color.blue);
		label_Node.setFont(font);
		label_Node.setBounds(100, 150, 200, 30);

		String[] add={"**Select**","One","Two","Three","Four","Five","Six","Seven"};
		cboSelectNodeNo=new JComboBox(add);
		cboSelectNodeNo.setForeground(Color.RED);
		cboSelectNodeNo.setBounds(300, 150, 100, 30);
		cboSelectNodeNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cboSelect=cboSelectNodeNo.getSelectedItem().toString();
					cboSelectIndex=cboSelectNodeNo.getSelectedIndex();
			}
		});

		button_Next=new JButton("OK");
		ImageIcon but_icon=new ImageIcon("nodeConfigBtn.jpg");
		button_Next.setIcon(but_icon);
		button_Next.setBounds(200,250,135,37);

		button_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(cboSelect.equals("**Select**")) {
					JOptionPane.showMessageDialog(null, "Select Number of Nodes", "Select Field", 1);
				}
				else{
					for(int i=1;i<=cboSelectIndex;i++){
						new LinkCreate();
						frame.dispose();
					}
				}
			}
		});

		p=new JPanel(null);
		p.setBackground(Color.white);
		p.add(label_Title);
		p.add(label_Node);
		p.add(cboSelectNodeNo);
		p.add(button_Next);
		p.add(labImg);
		frame.add(p);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new NodeConfig();
	}
}

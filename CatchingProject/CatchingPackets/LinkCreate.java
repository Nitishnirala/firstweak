package CatchingPackets;
import reuse.DbConn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class LinkCreate {
	JFrame frame;
	JLabel label_Title,label_Img,labNode,labConNode,labIp,labLevel,labImg;
	JComboBox cboNode,cboConNode,cboLevel;
	JButton but_Assign;
	JPanel p;
	JTextField txtNodeIP;
	static ResultSet rs;
	String strCboNode="Select",strCboConNode="Select",strCboLevel="Select";

	public LinkCreate() {
		frame=new JFrame();
		frame.setTitle("Create Link");
		frame.setResizable(false);
		frame.setSize(650,400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		screenSize.height = screenSize.height/2;
		screenSize.width = screenSize.width/2;
		size.height = size.height/2;
		size.width = size.width/2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		labImg=new JLabel();
 		ImageIcon icon1=new ImageIcon("dgm2.jpg");
 		labImg.setIcon(icon1);
 		labImg.setBounds(325, 70, 650, 500);

		label_Title=new JLabel("Node Configuration");
		Font font=new Font("Harlow Solid Italic",Font.ITALIC,30);
		label_Title.setForeground(Color.red);
		label_Title.setFont(font);
		label_Title.setBounds(190,20,300,50);

		labNode=new JLabel("Select Node");
		Font ft=new Font("Calibri",Font.BOLD +Font.ITALIC,17);
		labNode.setForeground(Color.BLUE);
		labNode.setFont(ft);
		labNode.setBounds(50, 100, 200, 30);

		String[] select={"Select"};
		cboNode=new JComboBox(select);
		cboNode.setForeground(Color.RED);
		cboNode.setBounds(225, 100, 140, 25);
		cboNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strCboNode= cboNode.getSelectedItem().toString();
				if(strCboNode.equals("Sink")){
					cboConNode.setEnabled(false);
					strCboConNode="No";
				}
				else{
					cboConNode.setEnabled(true);
					strCboConNode="Select";
				}
			}
		});

		labIp=new JLabel("Enter IP-Address");
		labIp.setForeground(Color.BLUE);
		labIp.setFont(ft);
		labIp.setBounds(50, 150, 200, 30);

		txtNodeIP=new JTextField("localhost");
		txtNodeIP.setBounds(225, 150, 140, 25);
		txtNodeIP.setFont(ft);
		txtNodeIP.setForeground(Color.red);

		labConNode=new JLabel("Connected To");
		labConNode.setFont(ft);
		labConNode.setForeground(Color.BLUE);
		labConNode.setBounds(50, 200, 200, 30);

		cboConNode=new JComboBox(select);
		cboConNode.setForeground(Color.RED);
		cboConNode.setBounds(225, 200, 140, 25);
		cboConNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strCboConNode= cboConNode.getSelectedItem().toString();
			}
		});

		try{
			rs=DbConn.selectRs();
			while(rs.next()){
				cboConNode.addItem(rs.getString(1));
				cboNode.addItem(rs.getString(1));
			}
		}
		catch(Exception rs){}

		labLevel=new JLabel("Select Level");
		labLevel.setForeground(Color.BLUE);
		labLevel.setFont(ft);
		labLevel.setBounds(50, 250, 200, 30);

		String[] level={"Select","Level-0","Level-1","Level-2"};
		cboLevel=new JComboBox(level);
		cboLevel.setForeground(Color.RED);
		cboLevel.setBounds(225, 250, 140, 25);
		cboLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strCboLevel= cboLevel.getSelectedItem().toString();
			}
		});

		but_Assign=new JButton("CONFIGURE");
		but_Assign.setForeground(Color.DARK_GRAY);
		but_Assign.setBounds(150,325,150,25);
		but_Assign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(strCboNode.equals("Select")||strCboConNode.equals("Select")||strCboLevel.equals("Select")||txtNodeIP.getText().equals("")) {
					JOptionPane.showMessageDialog(frame,"Configuration Not Completed","Incomplete",0);
				}
				else {
					if(strCboNode.equals("Sink")){
						if(strCboLevel.equals("Level-0")){
							JOptionPane.showMessageDialog(frame,"Sink Successfully Configured","Configured",1);
							register();
						}
						else{
							JOptionPane.showMessageDialog(frame,"Sink Configuration should be Level-0" ,"Error",0);
						}
					}
					else {
						  if(strCboNode.equals(strCboConNode)){
							JOptionPane.showMessageDialog(frame,"You have Connected Same Node" ,"Error",0);
						  }
						  else {
							 if(strCboLevel.equals("Level-0")){
								JOptionPane.showMessageDialog(frame,"Level-0 is not suit for selected Node" ,"Error",0);
							 }
							 else{
								JOptionPane.showMessageDialog(frame,"Node Successfully Configured","Configured",1);
								register();
							 }
						  }
					}
				}
			}
		});

		label_Img=new JLabel();
		ImageIcon ic=new ImageIcon("Correct-Link.gif");
		label_Img.setIcon(ic);
		label_Img.setBounds(90, 220, 200, 120);

		p=new JPanel(null);
		p.setBackground(Color.white);
		p.add(label_Title);
		p.add(labNode);
		p.add(cboNode);
		p.add(labIp);
		p.add(txtNodeIP);
		p.add(labConNode);
		p.add(cboConNode);
		p.add(labLevel);
		p.add(cboLevel);
		p.add(but_Assign);
		p.add(label_Img);
		p.add(labImg);
		frame.add(p);
		frame.setVisible(true);
	}

	void register(){
		DbConn.updatedetail("update catchtab set IPaddress='"+(txtNodeIP.getText())+"',NextNode='"+strCboConNode+"',Level='"+strCboLevel+"' where NodeName='"+strCboNode+"'");
		frame.dispose();
	}
	//public static void main(String[] args) {
		//new LinkCreate();
	//}
}

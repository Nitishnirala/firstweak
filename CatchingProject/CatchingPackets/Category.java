package CatchingPackets;
import reuse.DbConn;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Category {
	JFrame f;
	JPanel p;
	JLabel labTitle,labPlus,labPlusNo,labMinus,labMinusNo,labImg;
	Font a,c;
	int DbPlus,DbMinus;

	Category(String Nodename){
		DbPlus=DbConn.selectint(Nodename, 6);
		DbMinus=DbConn.selectint(Nodename, 7);

		JFrame.setDefaultLookAndFeelDecorated(true);
		f=new JFrame("Category");
		f.setSize(400,350);
		f.setLocation(250,250);
		f.setResizable(false);

		p=new JPanel();
		c=new Font("TimesRoman",Font.BOLD + Font.ITALIC,30);
		a=new Font("TimesRoman",Font.BOLD + Font.ITALIC,20);

		labTitle=new JLabel(Nodename+" Category Status");
		labTitle.setBounds(50,25,400,50);
		labTitle.setForeground(Color.DARK_GRAY);
		labTitle.setFont(c);

		labPlus=new JLabel("Status of Plus  (+)  ----");
		labPlus.setBounds(75,125,300,50);
		labPlus.setForeground(Color.BLUE);
		labPlus.setFont(a);

		labPlusNo=new JLabel();
		labPlusNo.setBounds(300,125,300,50);
		labPlusNo.setForeground(Color.GREEN);
		labPlusNo.setFont(a);

		labMinus=new JLabel("Status of Minus (-) ----");
		labMinus.setBounds(75,225,300,50);
		labMinus.setForeground(Color.BLUE);
		labMinus.setFont(a);

		labMinusNo=new JLabel(new Integer(DbMinus).toString());
		labMinusNo.setBounds(300,225,300,50);
		labMinusNo.setForeground(Color.RED);
		labMinusNo.setFont(a);

		if((DbConn.selectdetail(Nodename, 9)).equals("No"))	{
			labPlusNo.setText("No");
			labMinusNo.setText("No");
		}
		else{
			labPlusNo.setText(new Integer(DbPlus).toString());
			labMinusNo.setText(new Integer(DbMinus).toString());
		}

		labImg=new JLabel();
 		ImageIcon icon=new ImageIcon("category.jpg");
 		labImg.setIcon(icon);
 		labImg.setBounds(-50, 0, 787, 588);

		p.setLayout(null);
		p.add(labTitle);
		p.add(labPlus);
		p.add(labPlusNo);
		p.add(labMinus);
		p.add(labMinusNo);
		p.add(labImg);
		f.add(p);
		f.setVisible(true);
	}
	public static void main(String arg[]){
		new Category("Node-A");
	}
}

package CatchingPackets;
import reuse.AESencrp;
import reuse.DbConn;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.net.*;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Sink implements Runnable
{
	String CurrentDirectory;
	String SinkPath;
	static int Sink_Port;
	JLabel labImg,labTitle,labSnder,labSnderName,labInter,labInterName,labInfo,labBorder,labJoption;
	JPanel p;
	JButton btnMerge,btnCategory,btnRank,btnReset,btnDecry,btnRemoveBit,btnView,btnVerify,btnIFrame;
    Border bdr1;
    String s1;
    static ServerSocket servSock;
    static Socket cliSock;
    JFrame f;
    DefaultListModel model;
    JList list;
    Object listObj;
    static int k=0;
    String fSplitname,fileContent,JoptionNode = "";
    File file;
    Font c;
    static String BitAction="action1";
    static String DecryAction="action1";
    String[] nodes = new String[] {"Node-A", "Node-B", "Node-C", "Node-D","Node-E","Node-F" };
    JInternalFrame IFrame;

	@SuppressWarnings("static-access")
	public Sink()
    {
		/////initial setup in database
		DbConn.updatedetail("update catchtab set CategoryPlus=0,CategoryMinus=0,Rank='No Status',Action='No'");

		///getting current directory of workspace
		CurrentDirectory=System.getProperty("user.dir");
		/////Create SINK folder
		new File(CurrentDirectory+"/CATCHING PACKETS/SINK PACKETS").mkdirs();
		SinkPath=CurrentDirectory+"/CATCHING PACKETS/SINK PACKETS/";

		//////	getting sink port from database
		Sink_Port=DbConn.selectint("Sink", 3);
		try {
  			servSock=new ServerSocket(Sink_Port);
        }
        catch(Exception e) {
        	JOptionPane.showMessageDialog(f,"Sink is Already in Active","Already in Active",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
        }

		f=new JFrame("SINK");
		f.setSize(500,450);
		p=new JPanel();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setResizable(false);
		c=new Font("TimesRoman",Font.BOLD + Font.ITALIC,25);

		labImg=new JLabel();
 		ImageIcon icon=new ImageIcon("content11.jpg");
 		labImg.setIcon(icon);
 		labImg.setBounds(-50, 0, 787, 588);

		labTitle=new JLabel("SINK");
		labTitle.setBounds(220,25,100,50);
		labTitle.setForeground(Color.BLUE);
		labTitle.setFont(c);

		labSnder=new JLabel("Sender  :");
		labSnder.setBounds(50,40,400,80);
		labSnderName=new JLabel();
		labSnderName.setBounds(150,40,400,80);
		labSnderName.setVisible(false);
		labInter=new JLabel("Intermediate  :");
		labInter.setBounds(50,80,400,70);
		labInterName=new JLabel();
		labInterName.setBounds(150,80,400,70);
		labInterName.setVisible(false);

		labInfo=new JLabel();
		labInfo.setBounds(50,135,400,70);

		model = new DefaultListModel();
		list = new JList(model);
		list.setBounds(50,200, 150, 100);
		//list.setVisible(false);
		list.addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent e) {
				 listObj = list.getSelectedValue();
			 }
			});
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	try {
				    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+SinkPath+listObj.toString()+"\"");
				    	 }
				    catch(Exception exception) { exception.printStackTrace(); }
		    }
		});


		btnMerge=new JButton("MERGE");
		btnMerge.setBounds(190,350,100,20);
		btnMerge.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				merge();
  				JOptionPane.showMessageDialog(f,"Packets have been Merged","Merge",1);
  			}
        });

        btnReset=new JButton("RESET");
  		btnReset.setBounds(325,350,100,20);
  		btnReset.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				deleteFolder(SinkPath);
  				addListElements();
  				labInfo.setText("");
  				labSnderName.setText("");
  				labInterName.setText("");
  				deleteFolder(SinkPath);
  				BitAction="action1";
  			    DecryAction="action1";
  			    btnVerify.setEnabled(false);
  			    IFrame.setVisible(false);
  			    btnRemoveBit.setVisible(false);
  			    btnDecry.setVisible(false);
  			}
        });

  		btnDecry=new JButton("Decryption");
  		btnDecry.setBounds(300,150,125,20);
  		btnDecry.setEnabled(false);
  		btnDecry.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				if(DecryAction.equals("action2")){
  					listOfFiles(SinkPath,labSnderName.getText());
  					DecryAction="action3";
  					btnRemoveBit.setEnabled(false);
  				}
  				if(DecryAction.equals("action1")){
  					listOfFiles(SinkPath,labInterName.getText());
  					btnRemoveBit.setEnabled(true);
  					DecryAction="action2";
  				}
  				else{
  					DecryAction="action1";
  				}
  				addListElements();
  				btnDecry.setEnabled(false);
  				labInfo.setText("Decrypted Packets");
  			}
        });

  		btnVerify=new JButton("Verify");
  		btnVerify.setBounds(50,350,100,20);
  		btnVerify.setEnabled(false);
  		btnVerify.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				btnRemoveBit.setEnabled(true);
  				btnVerify.setEnabled(false);
  				File folder = new File(SinkPath);
  		        File[] listOfFiles = folder.listFiles();
  		        int numFiles=listOfFiles.length;
  		        if(numFiles==4){
  		        	JOptionPane.showMessageDialog(f,"All Packets Received. No Dropped Packets","No Drop",1);
  		        }
  		        if(numFiles<4){
  		        	JOptionPane.showMessageDialog(f,(4-numFiles)+" Packets Dropped","Packet Dropped",0);
  		        	System.out.println(4-numFiles);
  		        	DbConn.updatedetail("UPDATE catchtab SET CategoryMinus = (CategoryMinus+(4-numFiles)) where NodeName='"+(labInterName.getText())+"'");
  		        }
  			}
        });

  		btnRemoveBit=new JButton("Bit Identity");
  		btnRemoveBit.setBounds(300,100,125,20);
  		btnRemoveBit.setEnabled(false);
  		btnRemoveBit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				btnRemoveBit();
  				labInfo.setText("Bit identified Packets");
  			}
        });

  		btnCategory=new JButton("Categorization");
  		btnCategory.setBounds(300,200,125,20);
  		btnCategory.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				updateRankDb();
  				JoptionNode = (String) JOptionPane.showInputDialog(f,"Select the Node !","Category", JOptionPane.INFORMATION_MESSAGE,null, nodes,"Node-A");
  	  		     new Category(JoptionNode);
  			}
        });

        btnRank=new JButton("Rank");
        btnRank.setBounds(300,250,125,20);
        btnRank.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				updateRankDb();
  				JoptionNode = (String) JOptionPane.showInputDialog(f,"Select the Node !","Category", JOptionPane.INFORMATION_MESSAGE,null, nodes,"Node-A");
  				btnIFrame.setText(JoptionNode+" Rank Status --- "+DbConn.selectdetail(JoptionNode, 8));
  				IFrame.setVisible(true);
  			}
        });

        btnView=new JButton("View");
        btnView.setBounds(300,300,125,20);
        btnView.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				 try {
  			    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+SinkPath+"\"");
  			    	}
  			    	catch(Exception exception) { exception.printStackTrace(); }
  			}
        });

        btnIFrame=new JButton();
        btnIFrame.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  					IFrame.setVisible(false);
  			}
        });

        IFrame=new JInternalFrame("Rank Status of Node",true,false,false,false);
		IFrame.setSize(270, 100);
		IFrame.setLocation(25, 200);
		IFrame.setContentPane(btnIFrame);

		labBorder=new JLabel("");
		labBorder.setBounds(400,500,250,250);
        Border blackline = BorderFactory.createLineBorder(Color.blue);
        bdr1 = BorderFactory.createTitledBorder(blackline, "Catching Packets");
        labBorder.setBounds(17,20,460,380);
        labBorder.setBorder(bdr1);

		p.setLayout(null);
		p.add(labTitle);
		p.add(labSnder);
		p.add(labSnderName);
		p.add(labInter);
		p.add(labInterName);
		p.add(labInfo);
		p.add(btnMerge);
		p.add(btnCategory);
		p.add(btnRank);
		p.add(btnView);
		p.add(btnVerify);
		p.add(btnReset);
		p.add(btnDecry);
		p.add(btnRemoveBit);
		p.add(labBorder);
		p.add(list);
		p.add(labImg);
		f.add(IFrame);
		f.add(p);
		f.setLocation(150,150);
		f.setResizable(false);
		f.setVisible(true);
	}

	public void run() {
			try {
				DataInputStream dis=new DataInputStream(cliSock.getInputStream());
				String getmsg=dis.readUTF();
				StringTokenizer st=new StringTokenizer(getmsg,"#$#");
				String getStr1=st.nextToken();
				String getStr2=st.nextToken();

				if(getmsg.startsWith("Node-")) {
					labSnderName.setText(getStr1);
					labInterName.setText(getStr2);
					labInfo.setText("Received Packets");
					btnVerify.setEnabled(true);
					try {
				    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+SinkPath+"\"");
				    	}
				    	catch(Exception exception) { exception.printStackTrace(); }
				}
				else{
				FileWriter fs2=new FileWriter(SinkPath+getStr1,true);
				BufferedWriter out2=new BufferedWriter(fs2);
				out2.write(getStr2);
				out2.close();
				addListElements();
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
	}


	void btnRemoveBit(){
		File folder = new File(SinkPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                File f = new File(SinkPath+listOfFiles[i].getName());
                int index = f.getName().lastIndexOf('.');
                if (index>0&& index <= f.getName().length() - 2 ) {
                 String fnameNoExt=f.getName().substring(0, index);
                 ///remove last character in fnameNoExt
                f.renameTo(new File(SinkPath+(fnameNoExt.substring(0, fnameNoExt.length() - 1))+".txt"));
                }
            }
        }
        if(BitAction.equals("action2")){
        	JOptionPane.showMessageDialog(f,"Bit is Identified. Bit added by Sender - "+(labSnderName.getText())+". Decrypt the Packets!","Sender - "+(labSnderName.getText()),1);
        	labSnderName.setVisible(true);
        	BitAction="action3";
        }
        if(BitAction.equals("action1")){
        	JOptionPane.showMessageDialog(f,"Bit is Identified. Bit added by Intermediate - "+(labInterName.getText())+". Decrypt the Packets!","Intermediate - "+(labInterName.getText()),1);
        	labInterName.setVisible(true);
        	BitAction="action2";
        }
        else{
        	BitAction="action1";
        }
        addListElements();
        btnRemoveBit.setEnabled(false);
        btnDecry.setEnabled(true);
	}

	void addListElements() {
		model.removeAllElements();
			File folder = new File(SinkPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
            	model.addElement(listOfFiles[i].getName());
            }
        }
	}

	void merge() {
		///////getiing file name from sink folder
		String fnameNoExt="";
		File folder = new File(SinkPath);
        File[] listOfFiles = folder.listFiles();
        int num =listOfFiles.length;
        System.out.println(SinkPath+listOfFiles[num-1].getName());
		File f = new File(SinkPath+listOfFiles[num-1].getName());
	    int index = f.getName().lastIndexOf('.');
	    if (index>0&& index <= f.getName().length() - 2 ) {
	    fnameNoExt=f.getName().substring(0, index); }
	    /////remove first 2 char from string
	    String mergeFname=fnameNoExt.substring(2);

		try {
	         PrintWriter pw = new PrintWriter(new FileOutputStream(SinkPath+mergeFname+".txt"));
	         File file = new File(SinkPath);
	         File[] files = file.listFiles();
	         for (int i = 0; i < files.length; i++) {
	                 BufferedReader br = new BufferedReader(new FileReader(files[i].getPath()));
	                 String line = br.readLine();
	                 while (line != null) {
	                         pw.println(line);
	                         line = br.readLine();
	                 }
	                 br.close();
	                 k++;
	         }
	         deleteFolder(SinkPath);
	         addListElements();
	         pw.close();
		 }
		 catch(Exception esadaf){ }
	 }

	void deleteFolder(String deletePath) {
		///////////////to delete all files in folder
		File directory = new File(deletePath);
		//  Get all files in directory
		File[] files1 = directory.listFiles();
		for (File file : files1) {
		//   Delete each file
			if (!file.delete()) {
			//   Failed to delete file
			//System.out.println("Failed to delete "+file);
			}
		}
	}

	void listOfFiles(String countFpath,String action){
		try{
			Thread.sleep(4000);
			File folder = new File(countFpath);
			File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {
				    if (listOfFiles[i].isFile()) {
				    	decryptpkts(countFpath,listOfFiles[i].getName(),action);
				    }
				 }
		}
		catch(Exception ds){}
	}

	void decryptpkts(String path,String fname,String modifier){
		try{
			readFile(path,fname);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();

			FileWriter fs2=new FileWriter(path+fSplitname,true);
			BufferedWriter out2=new BufferedWriter(fs2);
			out2.write(AESencrp.decrypt(fileContent));
			out2.close();
			DbConn.updatedetail("UPDATE catchtab SET CategoryPlus = (CategoryPlus+1) where NodeName='"+modifier+"'");
		}
		catch(Exception r){
			JOptionPane.showMessageDialog(f,"Cannot Decrypt "+fname+". Packet was Modified By "+modifier,"Error",JOptionPane.ERROR_MESSAGE);
			DbConn.updatedetail("UPDATE catchtab SET CategoryMinus = (CategoryMinus+1) where NodeName='"+modifier+"'");
		}
	}

	void readFile(String path,String RdFileName) {
		try{
				file=new File(path+RdFileName);
				BufferedReader d = new BufferedReader(new FileReader(file));
				StringBuffer sb=new StringBuffer();
				fSplitname = file.getName().toString();
				//file_size = file.length();
				String line;
				while((line=d.readLine())!=null)
				sb.append(line + "\n");
				//////to remove last \n
				sb.setLength(sb.length()-1);
				sb.substring(1);
				fileContent=sb.toString();
				d.close();
		}
		catch(Exception RdEx){}
	}

	void updateRankDb(){
		for(int i=0;i<6;i++){
			if((DbConn.selectdetail(nodes[i], 9)).equals("Yes")) {
				if(DbConn.selectint(nodes[i], 7)==0){
					DbConn.updatedetail("update catchtab set Rank='Good' where NodeName='"+nodes[i]+"'");
				}
				else if(DbConn.selectint(nodes[i], 7)==1){
					DbConn.updatedetail("update catchtab set Rank='Temporarily Good' where NodeName='"+nodes[i]+"'");
				}
				else if(DbConn.selectint(nodes[i], 7)==2){
					DbConn.updatedetail("update catchtab set Rank='Suspiciously Bad' where NodeName='"+nodes[i]+"'");
				}
				else if(DbConn.selectint(nodes[i], 7)>=3){
					DbConn.updatedetail("update catchtab set Rank='Bad' where NodeName='"+nodes[i]+"'");
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			Sink s=new Sink();
			while(true) {
				cliSock=servSock.accept();
				Thread thread=new Thread(s);
				thread.start();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}



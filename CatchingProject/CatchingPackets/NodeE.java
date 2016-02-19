package CatchingPackets;
import reuse.AESencrp;
import reuse.DbConn;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NodeE implements Runnable{
	//////////////Current Node Name//////////
	static String CurrentNodeName="Node-E";
	////////////////////////////////////////
	static String CurrentDirectory,SndPath,RcvPath,SndDbIP,Sink_IP;
	static int CurrentNode_Port,SndDbPort,Sink_Port;

	static ServerSocket servSock;
	static Socket cliSock;

	String filename,fileContent,fSplitname,fnameNoExt;
	String[] splitCont;
	int list2Index;
	Object list1Obj,list2Obj;

	File file;
	JFrame frame;
	JPanel p;
	JLabel label_Title1,labFname,labRcvfrm,labImg,lab_SendTo,labTb1,labTb2,labinfo1,labinfo2,labRcvfrmNode,labAlert,labAlert1;
	JButton btnBrowse,btnSplit,btnEncrypt,btnSnd,btnRef,btnSndAddBit,btnRcvEncrypt,btnRcvAddBit,btnView,btnReset;
	JTextArea ta_SendContent;
	JScrollPane sp_SendContent;
	Border border1,border2;
	JList list1,list2 ;
	DefaultListModel model1,model2;
	Font a,c;

	@SuppressWarnings("static-access")
	public NodeE() {
		///getting current directory of workspace
		CurrentDirectory=System.getProperty("user.dir");
		/////Create SEND folder
		new File(CurrentDirectory+"/CATCHING PACKETS/SEND PACKETS").mkdirs();
		SndPath=CurrentDirectory+"/CATCHING PACKETS/SEND PACKETS/";
		/////Create RECEIVE folder
		new File(CurrentDirectory+"/CATCHING PACKETS/RECEIVE PACKETS").mkdirs();
		RcvPath=CurrentDirectory+"/CATCHING PACKETS/RECEIVE PACKETS/";

		////get current node port from database
		CurrentNode_Port=DbConn.selectint(CurrentNodeName, 3);
		try	{
 			servSock=new ServerSocket(CurrentNode_Port);
 		}
 		catch(Exception l) {
 			JOptionPane.showMessageDialog(frame,CurrentNodeName+" is Already in Active","Already in Active",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
 			l.printStackTrace();
 		}
		frame=new JFrame(CurrentNodeName);
		frame.setSize(700,500);
		frame.setLocation(150,150);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		a=new Font("Arial Narrow",Font.BOLD + Font.ITALIC,16);
		c=new Font("TimesRoman",Font.BOLD + Font.ITALIC,25);

		model1 = new DefaultListModel();
		list1 = new JList(model1);
		list1.setBounds(50,330, 150, 100);
		//list1.setVisible(false);
		list1.addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent e) {
				 list1Obj = list1.getSelectedValue();
			 }
			});
		list1.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		       // JList list = (JList)evt.getSource();
		        //if (evt.getClickCount() == 2) {   // for double click
		        	try {
				    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+SndPath+list1Obj.toString()+"\"");
				    	 }
				    catch(Exception exception) { exception.printStackTrace(); }
		       // } else if (evt.getClickCount() == 3) {   // Triple-click
		          //  int index = list.locationToIndex(evt.getPoint());
		       // }
		    }
		});

		model2 = new DefaultListModel();
		list2 = new JList(model2);
		list2.setBounds(375,240, 150, 100);
		//list2.setVisible(false);
		list2.addListSelectionListener(new ListSelectionListener() {
		 public void valueChanged(ListSelectionEvent e) {
			list2Obj= list2.getSelectedValue();
			list2Index=list2.getSelectedIndex();
		 }
		});
		list2.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	Object[] options = {"Modify","Drop","Cancel"};
		        int n = JOptionPane.showOptionDialog(frame,"Do you want to Modify/Drop "+list2Obj.toString(),"Modify/Drop",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
			    if(n==0) {
			    	 try {
			    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+RcvPath+list2Obj.toString()+"\"");
			    	 }
			    	 catch(Exception exception) { exception.printStackTrace(); }
			    }
			    if(n==1) {
					 try{
						File fdel=new File(RcvPath+list2Obj.toString());
						fdel.delete();	model2.remove(list2Index);
						}
						catch(Exception ef) {}
			    }
		    }
		});

		label_Title1=new JLabel(CurrentNodeName);
		label_Title1.setBounds(300,2,100,50);
		label_Title1.setForeground(Color.BLUE);
		label_Title1.setFont(c);
		ImageIcon icon1=new ImageIcon("alert.gif");
		ImageIcon icon2=new ImageIcon("loading.gif");

		labAlert=new JLabel();
		labAlert.setBounds(540,2,250,50);
		labAlert.setFont(a);
		labAlert.setForeground(Color.DARK_GRAY);
		labAlert.setIcon(icon1);
		labAlert.setVisible(false);

		labAlert1=new JLabel("Sending To Sink");
		labAlert1.setBounds(360,130,450,50);
		//labAlert1.setFont(a);
		labAlert1.setForeground(Color.DARK_GRAY);
		labAlert1.setIcon(icon2);
		labAlert1.setVisible(false);

        labFname=new JLabel("File : ");
        labFname.setBounds(40,90,400,30);

        btnBrowse=new JButton("BROWSE");
        btnBrowse.setBounds(230,150,90,20);
        btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browse(e);
				//String strLong = Long.toString(file_size);
				labFname.setText("File: "+filename);
				ta_SendContent.setText(fileContent);
			}
        });

        btnSplit=new JButton("SPLIT");
        btnSplit.setEnabled(false);
        btnSplit.setBounds(230,200,90,20);
        btnSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+SndPath+"\"");
		    	}
		    	catch(Exception exception) { exception.printStackTrace(); }
				splitpkts();
				addList1Elements();
				 btnSplit.setEnabled(false);
				 btnEncrypt.setEnabled(true);
				JOptionPane.showMessageDialog(frame,"File has been splitted into 4 packets","File Splitted",1);
			}
        });

        btnEncrypt=new JButton("ENCRYPT");
        btnEncrypt.setEnabled(false);
        btnEncrypt.setBounds(230,250,90,20);
        btnEncrypt.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				listOfFiles(SndPath);
  				labinfo1.setText("Encrypted Packets");
  				JOptionPane.showMessageDialog(frame,"Packets have been encrypted ","Packets Encrypted",1);
    			addList1Elements();
    			 btnEncrypt.setEnabled(false);
    			 btnSndAddBit.setEnabled(true);
 			}
         });

        btnSndAddBit=new JButton("ADD BITS");
  		btnSndAddBit.setEnabled(false);
        btnSndAddBit.setBounds(230,300,90,20);
        btnSndAddBit.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				addbits();
 				JOptionPane.showMessageDialog(frame,"Bits have been added to packets ","Bits added",1);
 				addList1Elements();
 				labinfo1.setText("Bit Added Packets");
 				btnSndAddBit.setEnabled(false);
 				btnSnd.setEnabled(true);
 			}
         });

  		btnSnd=new JButton("SEND");
        btnSnd.setEnabled(false);
  		btnSnd.setBounds(230,350,90,20);
  		btnSnd.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				////////////////initiate the action in DB
 				DbConn.updatedetail("update catchtab set Action='Yes' where NodeName='"+CurrentNodeName+"'");
 				////getting ip and port of next node from database to send
 				String DbNextNode=DbConn.selectdetail(CurrentNodeName, 4);
 				SndDbIP=DbConn.selectdetail(DbNextNode, 2);
 				SndDbPort=DbConn.selectint(DbNextNode, 3);
 				try{
 					cliSock=new Socket(SndDbIP,SndDbPort);
	  				DataOutputStream dos=new DataOutputStream(cliSock.getOutputStream());
					dos.writeUTF(CurrentNodeName);
 				}
 				catch(Exception sndName){}
 				sendAllFiles(SndPath,SndDbIP,SndDbPort);
  				deleteFolder(SndPath);
	  			JOptionPane.showMessageDialog(frame,"All Packets have been sent to Receiver","Packets Sent",1);
	  			addList1Elements();
	  			labinfo1.setText("Packets Sent to Intermediate");
	  			ta_SendContent.setText("");
	  			btnSnd.setEnabled(false);
 			}
         });

  		btnRef=new JButton("REFRESH");
  		btnRef.setBounds(230,400,90,20);
  		btnRef.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				deleteFolder(SndPath);
  				addList1Elements();
  				labinfo1.setText("");
  				labFname.setText("File: ");
  				ta_SendContent.setText("");
  				labAlert.setVisible(false);
  				btnSplit.setEnabled(false);
  				btnEncrypt.setEnabled(false);
  				btnSndAddBit.setEnabled(false);
 			}
         });

        //////////////////////////////////Receiving section///////////////////////
  		btnRcvEncrypt=new JButton("ENCRYPT");
        btnRcvEncrypt.setEnabled(false);
  		btnRcvEncrypt.setBounds(565,220,90,20);
  		btnRcvEncrypt.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				listOfFiles(RcvPath);
  				JOptionPane.showMessageDialog(frame,"Packets have been encrypted ","Packets Encrypted",1);
  				addlist2Elements();
  				labinfo2.setText("Received Packets Encrypted");
  				btnRcvEncrypt.setEnabled(false);
  				btnRcvAddBit.setEnabled(true);
  			}
        });

  		btnRcvAddBit=new JButton("ADD BITS");
  		btnRcvAddBit.setBounds(565,280,90,20);
  		btnRcvAddBit.setEnabled(false);
  		btnRcvAddBit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////////////////////Rename all files in the directory///////////////
				File folder = new File(RcvPath);
		        File[] listOfFiles = folder.listFiles();
		        for (int i = 0; i < listOfFiles.length; i++) {
		            if (listOfFiles[i].isFile()) {
		                File f = new File(RcvPath+listOfFiles[i].getName());
		                int index = f.getName().lastIndexOf('.');
		                if (index>0&& index <= f.getName().length() - 2 ) {
		                 String fnameNoExt=f.getName().substring(0, index);
		                f.renameTo(new File(RcvPath+fnameNoExt+(i+1)+".txt"));
		                }
		            }
		        }
		        addlist2Elements();
		        labinfo2.setText("Bits Added to Received Packets");
		        btnRcvAddBit.setEnabled(false);
			}
        });

  		btnView=new JButton("VIEW");
  		btnView.setBounds(565,340,90,20);
  		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			    	  Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \""+RcvPath+"\"");
			    	}
			    	catch(Exception exception) { exception.printStackTrace(); }
			}
        });

  		btnReset=new JButton("RESET");
  		btnReset.setBounds(475,400,90,20);
  		btnReset.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				deleteFolder(RcvPath);
  				addlist2Elements();
  				labinfo2.setText("");
  				labRcvfrmNode.setText("");
  				lab_SendTo.setText("Send To :");
  				labAlert.setVisible(false);
  				btnRcvEncrypt.setEnabled(false);
  				btnRcvAddBit.setEnabled(false);
  			}
        });

        ta_SendContent=new JTextArea();
        Font font=new Font("Calibri",Font.ITALIC,20);
        ta_SendContent.setForeground(Color.BLUE);
        ta_SendContent.setFont(font);
        sp_SendContent=new JScrollPane(ta_SendContent);
        sp_SendContent.setBounds(40,130,170,150);
        ta_SendContent.setEditable(false);

        labinfo1=new JLabel();
        labinfo1.setBounds(40,255,200,100);
        labinfo1.setForeground(Color.RED);
        labinfo2=new JLabel();
        labinfo2.setBounds(375,160,400,100);
        labinfo2.setForeground(Color.RED);

        labRcvfrm=new JLabel("File Received From :");
        labRcvfrm.setBounds(375,85,120,55);
        labRcvfrmNode=new JLabel();
        labRcvfrmNode.setBounds(500,85,120,55);
        lab_SendTo=new JLabel("Send To :");
        lab_SendTo.setBounds(375,130,100,50);

        labTb1=new JLabel();
        Border line = BorderFactory.createLineBorder(Color.blue);
        border1=BorderFactory.createTitledBorder(line,"Sending Process");
        labTb1.setBounds(20,65,320,390);
        labTb1.setBorder(border1);
        labTb2=new JLabel();
        border2 = BorderFactory.createTitledBorder(line,"Receiving Process");
        labTb2.setBounds(350,65,330,390);
        labTb2.setBorder(border2);

        labImg=new JLabel();
 		ImageIcon icon=new ImageIcon(CurrentNodeName+".jpg");
 		labImg.setIcon(icon);
 		labImg.setBounds(-50, 0, 787, 588);

 		p=new JPanel(null);
 		p.add(label_Title1);
 		p.add(labAlert);
 		p.add(labAlert1);
 		p.add(labFname);
 		p.add(btnBrowse);
 		p.add(sp_SendContent);
 		p.add(list1);
 		p.add(list2);
 		p.add(labinfo1);
 		p.add(labinfo2);
 		p.add(btnEncrypt);
 		p.add(btnSplit);
 		p.add(btnRef);
 		p.add(btnSnd);
 		p.add(btnRcvEncrypt);
 		p.add(labRcvfrm);
 		p.add(labRcvfrmNode);
 		p.add(lab_SendTo);
 		p.add(btnReset);
 		p.add(labTb1);
 		p.add(labTb2);
 		p.add(btnSndAddBit);
 		p.add(btnRcvAddBit);
 		p.add(btnView);
 		p.add(labImg);
 		frame.add(p);
 		frame.setVisible(true);
	}

	public void run() {
		try	{
			labAlert.setText("Intermediate Node");
			labAlert.setVisible(true);
			//////getting sink ip and port from database
			Sink_IP=DbConn.selectdetail("Sink", 2);
			Sink_Port=DbConn.selectint("Sink", 3);
			DataInputStream dis=new DataInputStream(cliSock.getInputStream());
			String getmsg=dis.readUTF();
			if(getmsg.startsWith("Node-")) {
				////////////////initiate the action in DB
 				DbConn.updatedetail("update catchtab set Action='Yes' where NodeName='"+CurrentNodeName+"'");

 				btnRcvEncrypt.setEnabled(true);
				labRcvfrmNode.setText(getmsg);
				Thread.sleep(60000);
				labAlert1.setVisible(true);
				Thread.sleep(10000);
				try{
 					cliSock=new Socket(Sink_IP,Sink_Port);
	  				DataOutputStream dos=new DataOutputStream(cliSock.getOutputStream());
					dos.writeUTF(getmsg+"#$#"+CurrentNodeName);
 				}
 				catch(Exception sndToSink){}
				sendAllFiles(RcvPath,Sink_IP,Sink_Port);
				deleteFolder(RcvPath);
	  			addlist2Elements();
	  			labinfo2.setText("Packets Sent to Sink");
	  			lab_SendTo.setText("Send To : Sink");
	  			labAlert1.setVisible(false);
	  			JOptionPane.showMessageDialog(frame,"All Packets have been sent to Sink","Packets Sent",1);
			}
			else{
				StringTokenizer st=new StringTokenizer(getmsg,"#$#");
				String PktFname=st.nextToken();
				String PktContent=st.nextToken();

				FileWriter fs2=new FileWriter(RcvPath+PktFname,true);
				BufferedWriter out2=new BufferedWriter(fs2);
				out2.write(PktContent);
				out2.close();
				addlist2Elements();
				labinfo2.setText("Received Packets");
			}
		}
		catch(Exception k) { k.printStackTrace(); }
	}

	@SuppressWarnings("deprecation")
	public void browse(ActionEvent e) {
		FileDialog fd=new FileDialog(frame,"Files",FileDialog.LOAD);
		fd.show();
		String filedir = fd.getDirectory() + fd.getFile();
		BufferedReader d;
 		StringBuffer sb = new StringBuffer();
 		try {
 			File file=new File(filedir);
			d = new BufferedReader(new FileReader(file));
			filename = file.getName().toString();
			//long file_size = file.length();
			String line;
			while((line=d.readLine())!=null)
			sb.append(line + "\n");
			fileContent=sb.toString();
			d.close();
			labAlert.setText("Sender Node");
			labAlert.setVisible(true);
			btnSplit.setEnabled(true);
 		}
 		catch(FileNotFoundException fe) {
 			JOptionPane.showMessageDialog(frame,"File Not Choosed","Not Choose",JOptionPane.ERROR_MESSAGE);
 		}
 		catch(IOException x){
			x.printStackTrace();
		}
	}

	void splitpkts() {
		try {
			/////Store the fileContent in the splitCont Array based on space
			splitCont=fileContent.split(" ");
				Thread.sleep(3000);
					for(int i=0;i<splitCont.length/4;i++) {
					FileWriter fs=new FileWriter(SndPath+"1."+filename,true);
					BufferedWriter out=new BufferedWriter(fs);
					out.write(splitCont[i]+" ");
					out.close();
				}

				Thread.sleep(3000);
					for(int j=splitCont.length/4;j<splitCont.length/2;j++) {
					FileWriter fs1=new FileWriter(SndPath+"2."+filename,true);
					BufferedWriter out1=new BufferedWriter(fs1);
					out1.write(splitCont[j]+" ");
					out1.close();
				}

				Thread.sleep(3000);
					for(int k=splitCont.length/2;k<(splitCont.length*3/4);k++) {
					FileWriter fs2=new FileWriter(SndPath+"3."+filename,true);
					BufferedWriter out2=new BufferedWriter(fs2);
					out2.write(splitCont[k]+" ");
					out2.close();
				}

				Thread.sleep(3000);
				for(int l=(splitCont.length*3/4);l<splitCont.length;l++) {
					FileWriter fs3=new FileWriter(SndPath+"4."+filename,true);
					BufferedWriter out3=new BufferedWriter(fs3);
					out3.write(splitCont[l]+" ");
					out3.close();
				}
				labinfo1.setText("Splitted Packets");
		}
		catch(Exception r){}
	}

	void encryptpkts(String path,String fname) {
		try{
			readFile(path,fname);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
			FileWriter fs2=new FileWriter(path+fSplitname,true);
			BufferedWriter out2=new BufferedWriter(fs2);
			out2.write(AESencrp.encrypt(fileContent));
			out2.close();
		}
		catch(Exception r){}
	}

	void addbits() {
		 	File file = new File(filename);
		 	int index = file.getName().lastIndexOf('.');
		    if (index>0&& index <= file.getName().length() - 2 ) {
		       fnameNoExt=file.getName().substring(0, index);
		    }
		    String bit1= JOptionPane.showInputDialog(null, "Enter Bit-0 for Packet1","Add Bit for Packet1",1);
		    File f1=new File(SndPath+"1."+fnameNoExt+".txt");
		    File fRe1=new File(SndPath+"1."+fnameNoExt+bit1+".txt");
		    f1.renameTo(fRe1);

		    String bit2= JOptionPane.showInputDialog(null, "Enter Bit-1 for Packet2","Add Bit for Packet2",1);
		    File f2=new File(SndPath+"2."+fnameNoExt+".txt");
		    File fRe2=new File(SndPath+"2."+fnameNoExt+bit2+".txt");
		    f2.renameTo(fRe2);

		    String bit3= JOptionPane.showInputDialog(null, "Enter Bit-2 for Packet3","Add Bit for Packet3",1);
		    File f3=new File(SndPath+"3."+fnameNoExt+".txt");
		    File fRe3=new File(SndPath+"3."+fnameNoExt+bit3+".txt");
		    f3.renameTo(fRe3);

		    String bit4= JOptionPane.showInputDialog(null, "Enter Bit-3 for Packet4","Add Bit for Packet4",1);
		    File f4=new File(SndPath+"4."+fnameNoExt+".txt");
		    File fRe4=new File(SndPath+"4."+fnameNoExt+bit4+".txt");
		    f4.renameTo(fRe4);
	}


	void readFile(String path,String RdFileName) {
		try{
				file=new File(path+RdFileName);
				BufferedReader d = new BufferedReader(new FileReader(file));
				StringBuffer sb=new StringBuffer();
				fSplitname = file.getName().toString();
				//long file_size = file.length();
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

	void addList1Elements() {
		model1.removeAllElements();
			File folder = new File(SndPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
            	model1.addElement(listOfFiles[i].getName());
            }
        }
	}

	void addlist2Elements() {
		model2.removeAllElements();
		File folder = new File(RcvPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
            	model2.addElement(listOfFiles[i].getName());
            }
        }
        list2.setVisible(true);
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

	void listOfFiles(String countFpath){
			try{
				Thread.sleep(4000);
				File folder = new File(countFpath);
				File[] listOfFiles = folder.listFiles();
					for (int i = 0; i < listOfFiles.length; i++) {
					    if (listOfFiles[i].isFile()) {
					     encryptpkts(countFpath,listOfFiles[i].getName());
					    }
					    // else if (listOfFiles[i].isDirectory()) {
					    // System.out.println("Directory " + listOfFiles[i].getName());
					    // }
					 }
			}
			catch(Exception ds){}
	}

	void sendAllFiles(String path,String SndIP,int SndPort) {
		File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	try{
	  				Thread.sleep(6000);
	  				readFile(path,listOfFiles[i].getName());
	  				cliSock=new Socket(SndIP,SndPort);
	  				DataOutputStream dos=new DataOutputStream(cliSock.getOutputStream());
					dos.writeUTF(listOfFiles[i].getName()+"#$#"+fileContent);
			    	}
	  				catch(Exception dfd){}
			      }
			    }
	}

	public static void main(String args[]) {
		try {
			NodeE node=new NodeE();
			while(true) {
				cliSock=servSock.accept();
				Thread thr=new Thread(node);
				thr.start();
			}
		}
		catch(Exception k) {
			k.printStackTrace();
		}
	}
}

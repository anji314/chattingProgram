package onetoone;

import java.awt.*;
import java.net.*;

import onetoone.OneToOneS.WinListener;

import java.io.*;
import java.awt.event.*;


public class OneToOneC extends Frame implements ActionListener {
	TextArea display;
	TextField text;
	Label lword;
	Socket client;
	BufferedWriter output;
	BufferedReader input;
	String clientdata="";
	String serverdata="";
	
	public OneToOneC() {
		super("Ŭ���̾�Ʈ");
		display=new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		display.setEditable(false);
		add(display,BorderLayout.CENTER);
		
		Panel pword=new Panel(new BorderLayout());
		lword =new Label("��ȭ��");
		text= new TextField(30);
		text.addActionListener(this);
		pword.add(lword,BorderLayout.WEST);
		pword.add(text,BorderLayout.EAST);
		add(pword,BorderLayout.SOUTH);
		
		addWindowListener(new WinListener());
		setSize(300,200);
		setVisible(true);
		
	}
	
	
	public void runClient() {
		try {
			String serverIP="10.2.0.134";
			client= new Socket(serverIP,7777);
			input=new BufferedReader(new InputStreamReader(client.getInputStream()));
			output= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			while(true) {
				String serverdata=input.readLine();
				if(serverdata.equals("quit")) {
					display.append("\n�������� ������ �ߴܵǾ����ϴ�.");
					output.flush();
					break;
				}else {
					display.append("\n���� �޼��� : "+serverdata);
					output.flush();
				}
			}
			client.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		clientdata= text.getText();
		try {
			display.append("\nŬ���̾�Ʈ : "+clientdata);
			output.write(clientdata+"\r\n");
			output.flush();
			text.setText("");
			if(clientdata.equals("quit")) {
				client.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) {
		OneToOneC c= new OneToOneC();
		c.runClient();
		}

	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	
	
	
	

}

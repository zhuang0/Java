import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class WC8 extends Frame implements ActionListener,Runnable
{
	DatagramSocket D;
	byte dataBuf[] = new byte[512];
	InetAddress A;
	DatagramPacket Pac = new DatagramPacket(dataBuf, dataBuf.length);
	TextArea T1 = new TextArea();
	TextField T2 = new TextField(23);
	TextField T3 = new TextField(20);
	public WC8()
	{
		Panel P1 = new Panel();
		P1.add(new Label("sent messages here \n (input \"exit\" to exit)"));
		P1.add(T2);
		Panel P2 = new Panel();
		P2.add(new Label("input IP address here"));
		P2.add(T3);
		T2.addActionListener(this);
		add(P1, "South");
		add(P2, "North");
		add(T1, "Center");
		setTitle("WC8");
		setSize(500, 200);
		setVisible(true);
		(new Thread(this)).start();
	}
	public void actionPerformed(ActionEvent e1)
	{
		try{
				String str1 = T2.getText();
				byte[] buf = str1.getBytes();
				String str = T3.getText();
				DatagramSocket D = new DatagramSocket();
				InetAddress A = InetAddress.getByName(str);
				DatagramPacket P1 = new DatagramPacket(buf, buf.length, A, 8001);
				D.send(P1);
				if(str1.equals("exit") == true)
				{
					System.exit(0);
				}
				T1.append(P1.getAddress() + " I say: " + str1 + "\n");
				T2.setText("");
		}
		catch(Exception e2){}
	}
	public void run()
	{
		try{
				DatagramSocket D = new DatagramSocket(8002);
				while(true)
				{
					D.receive(Pac);
					String str2 = new String(Pac.getData(), 0, Pac.getLength());
					T1.append(Pac.getAddress() + " WS8 says: " + str2 + "\n");
				}
		}
		catch(Exception e3){}
	}
	public static void main(String args[])
	{
		new WC8();
	}
	
	
}

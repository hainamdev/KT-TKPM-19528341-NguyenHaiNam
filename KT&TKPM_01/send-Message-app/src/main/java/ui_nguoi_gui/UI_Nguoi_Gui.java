package ui_nguoi_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.BasicConfigurator;

public class UI_Nguoi_Gui extends JFrame implements ActionListener {

	Color clrMain = new Color(223, 249, 251);
	private JTextField txtTinNhan;
	private JButton btnGuiTinNhan;
	static ConnectionFactory factory;
	static Destination destination;
	static Connection con;

	public UI_Nguoi_Gui() throws Exception {
		setTitle("Zola");
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(clrMain);
		setLayout(null);

		txtTinNhan = new JTextField();
		txtTinNhan.setBounds(20, 10, 440, 70);
		add(txtTinNhan);

		btnGuiTinNhan = new JButton("Gửi");
		btnGuiTinNhan.setBounds(140, 100, 200, 30);
		add(btnGuiTinNhan);

		btnGuiTinNhan.addActionListener(this);

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// config environment for JMS
		BasicConfigurator.configure();
		// config environment for JNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		// create context
		Context ctx = new InitialContext(settings);
		// lookup JMS connection factory
		factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		// lookup destination. (If not exist-->ActiveMQ create once)
		destination = (Destination) ctx.lookup("dynamicQueues/NguyenHaiNam");
		// get connection using credential
		con = factory.createConnection("admin", "admin");

		new UI_Nguoi_Gui().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object sou = e.getSource();
		if (sou.equals(btnGuiTinNhan)) {

			// connect to MOM
			try {
				con.start();
				// create session
				Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
				// create producer
				MessageProducer producer = session.createProducer(destination);
				String sms = txtTinNhan.getText().toString();
				Message msg = session.createTextMessage(sms);
				producer.send(msg);
				System.out.println("Đã gửi:  " + sms);
				session.close();
				con.close();
				txtTinNhan.setText("");
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}

package sender;

import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.BasicConfigurator;
import data.Person;
import helper.XMLConvert;

public class QueueSender {
	public static void main(String[] args) throws Exception {
//config environment for JMS
		BasicConfigurator.configure();
//config environment for JNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
//create context
		Context ctx = new InitialContext(settings);
//lookup JMS connection factory
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//lookup destination. (If not exist-->ActiveMQ create once)
		Destination destination = (Destination) ctx.lookup("dynamicQueues/NguyenHaiNam");
//get connection using credential
		Connection con = factory.createConnection("admin", "admin");
//connect to MOM
		con.start();
//create session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
//create producer
		MessageProducer producer = session.createProducer(destination);
//create text message
		for(int i = 1; i <=10 ; i++) {
			Message msg = session.createTextMessage("không yêu em lần " + String.valueOf(i));
			producer.send(msg);
//			Person p = new Person(1001, "Nguyễn Hải Nam", new Date());
//			String xml = new XMLConvert<Person>(p).object2XML(p);
//			msg = session.createTextMessage(xml);
//			producer.send(msg);
			System.out.println("yêu em lần " + String.valueOf(i));
		}
//shutdown connection
		session.close();
		con.close();
//		System.out.println("Finished...");
	}
}

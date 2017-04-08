package activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 * Created by sunny-chen on 2017/4/8.
 */
public class Publisher {

    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
        ConnectionFactory  connectionFactory;

        Connection connection;

        Session session;

        Destination destination;

        MessageProducer producer;

        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("chen");

            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessage(session, producer);

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        for (int i = 0; i < SEND_NUMBER; i++) {
            TextMessage message = session.createTextMessage("ActiveMq发送消息" + i);
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}

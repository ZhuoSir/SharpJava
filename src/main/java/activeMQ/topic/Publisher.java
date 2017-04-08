package activeMQ.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 * Created by sunny-chen on 2017/4/8.
 */
public class Publisher {

    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("zhuo");
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for (int i = 0; i < SEND_NUMBER; i++) {
                TextMessage message = session.createTextMessage();

                message.setText("message_" + System.currentTimeMillis());
                producer.send(message);
                System.out.println("Sent message: " + message.getText());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

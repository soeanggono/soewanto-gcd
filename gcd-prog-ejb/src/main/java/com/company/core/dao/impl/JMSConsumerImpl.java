package com.company.core.dao.impl;

import com.company.core.dao.JMSConnection;
import com.company.core.dao.JMSConsumer;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.naming.NamingException;

public class JMSConsumerImpl extends JMSConnection implements JMSConsumer{
    
    private static final Logger LOGGER = Logger.getLogger(JMSConsumerImpl.class.getName());
    
    private MessageConsumer messageConsumer;
    private TextMessage textMessage;

    public JMSConsumerImpl(String jmsQueue) throws NamingException, JMSException {
        super(jmsQueue);
        messageConsumer = queueSession.createConsumer(queue);
    }
    
    @Override
    public TextMessage consumeMessage() throws JMSException {
        return consumeMessage(5000);
    }

    @Override
    public TextMessage consumeMessage(long timeOut) throws JMSException {
        queueConnection.start();
        textMessage = (TextMessage) messageConsumer.receive(timeOut);
        
        queueSession.close();
        if (queueConnection != null) {
            queueConnection.close();
        }
        
//        LOGGER.info("JMS produced: " + textMessage.getText());
        return textMessage;
    }
}

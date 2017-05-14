package com.company.core.dao.impl;

import com.company.core.dao.JMSConnection;
import com.company.core.dao.JMSProducer;
import java.util.List;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.naming.NamingException;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class JMSProducerImpl extends JMSConnection implements JMSProducer{
    
    private static final Logger LOGGER = Logger.getLogger(JMSProducerImpl.class.getName());
    
    private MessageProducer messageProducer;
    private Message message;

    public JMSProducerImpl(String jmsQueue) throws NamingException, JMSException {
        super(jmsQueue);
        messageProducer = queueSession.createProducer(queue);
    }

    @Override
    public void sendMessages(List<String> messages) throws JMSException {
        queueConnection.start();
        
        messages.forEach(text -> {
            try {
                message = queueSession.createTextMessage(text);
                messageProducer.send(message);
                LOGGER.info("JMS produced: " + text);
            } catch (JMSException ex) {
                LOGGER.severe(ExceptionUtils.getStackTrace(ex));
            }
        });
        
        queueSession.close();
        if (queueConnection != null) {
            queueConnection.close();
        }
    }
}

package com.company.core.dao.impl;

import com.company.core.dao.JMSBrowser;
import com.company.core.dao.JMSConnection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;
import javax.naming.NamingException;

public class JMSBrowserImpl extends JMSConnection implements JMSBrowser {
    
    private static final Logger LOGGER = Logger.getLogger(JMSBrowserImpl.class.getName());
    
    private QueueBrowser queueBrowser;

    public JMSBrowserImpl(String jmsQueue) throws NamingException, JMSException {
        super(jmsQueue);
        queueBrowser = queueSession.createBrowser(queue);
    }

    @Override
    public List<TextMessage> browseMessages() throws JMSException {
        queueConnection.start();
        List<TextMessage> result = Collections.list(queueBrowser.getEnumeration());
        
        queueSession.close();
        if (queueConnection != null) {
            queueConnection.close();
        }
        
        LOGGER.info("List record count: " + result.size());
        return result;
    }
}

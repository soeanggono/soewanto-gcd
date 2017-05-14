package com.company.core.dao;

import com.company.core.common.CommonConstant;
import com.company.core.common.ContextUtil;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

public class JMSConnection {
    
    private static final Logger LOGGER = Logger.getLogger(JMSConnection.class.getName());
    
    protected Context context;
    protected QueueConnectionFactory queueConnectionFactory;
    protected QueueConnection queueConnection;
    protected Queue queue;
    protected QueueSession queueSession;
    
    public JMSConnection(String jmsQueue) throws NamingException, JMSException {
        context = ContextUtil.getInitialContext();
        queueConnectionFactory = (QueueConnectionFactory) context.lookup(CommonConstant.JMS_CONNECTION_FACTORY);
        queueConnection = queueConnectionFactory.createQueueConnection(CommonConstant.JMS_USERNAME, CommonConstant.JMS_PASSWORD);
        queue = (Queue) context.lookup(jmsQueue);
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}

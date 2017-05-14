package com.company;

import com.company.core.common.CommonConstant;
import com.company.core.dao.JMSBrowser;
import com.company.core.dao.JMSConsumer;
import com.company.core.dao.JMSProducer;
import com.company.core.dao.impl.JMSBrowserImpl;
import com.company.core.dao.impl.JMSConsumerImpl;
import com.company.core.dao.impl.JMSProducerImpl;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJMS {
    private JMSProducer jmsProducer;
    private JMSConsumer jmsConsumer;
    private JMSBrowser jmsBrowser;
    
    @Before
    public void setup() throws NamingException, JMSException {
        
    }
    
    @Test
    public void testSendMessage() throws JMSException, NamingException {
        jmsProducer = new JMSProducerImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
        List<String> messages = new ArrayList<>();
        messages.add("1");
        messages.add("2");
        jmsProducer.sendMessages(messages);
        Assert.assertEquals("success", "success");
    }
    
    @Test
    public void testBrowseMessages() throws JMSException, NamingException {
        jmsBrowser = new JMSBrowserImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
        List<TextMessage> list = jmsBrowser.browseMessages();
        Assert.assertNotNull(list);
    }
    
    @Test
    public void testGetMessage() throws JMSException, NamingException {
        TextMessage textMessage = null;
        jmsConsumer = new JMSConsumerImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
        textMessage = jmsConsumer.consumeMessage();
        jmsConsumer = new JMSConsumerImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
        textMessage = jmsConsumer.consumeMessage();
        Assert.assertNotNull(textMessage);
    }
}

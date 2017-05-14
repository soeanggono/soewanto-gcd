package com.company.core.service.impl;

import com.company.core.common.CommonConstant;
import com.company.core.dao.JMSBrowser;
import com.company.core.dao.JMSConsumer;
import com.company.core.dao.JMSProducer;
import com.company.core.dao.impl.JMSBrowserImpl;
import com.company.core.dao.impl.JMSConsumerImpl;
import com.company.core.dao.impl.JMSProducerImpl;
import com.company.core.service.GCDService;
import com.company.core.util.GcdCalc;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.TextMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Named("gcdServiceImpl")
public class GCDServiceImpl implements GCDService {
    
    private static final Logger LOGGER = Logger.getLogger(GCDServiceImpl.class.getName());
    
    @Inject
    @Named("gcdCalc")
    private GcdCalc gcdCalc;

    @Override
    public String push(int i1, int i2) {
        String result = "failed";
        try {
            List<String> messages = new ArrayList<>();
            messages.add(Integer.toString(i1));
            messages.add(Integer.toString(i2));
            JMSProducer jmsProducer = new JMSProducerImpl(CommonConstant.JMS_QUEUE_LIST_INT);
            jmsProducer.sendMessages(messages);
            
            result = "Values are stored";
        } catch (Exception ex) {
            LOGGER.severe(ExceptionUtils.getStackTrace(ex));
        }
        
        return result;
    }

    @Override
    public List<Integer> list() {
        List<Integer> result = new ArrayList<>();
        try {
            JMSBrowser jmsBrowser = new JMSBrowserImpl(CommonConstant.JMS_QUEUE_LIST_INT);
            List<TextMessage> textMessages = jmsBrowser.browseMessages();
            for (TextMessage textMessage : textMessages) {
                result.add(new Integer(textMessage.getText()));
            }
        } catch (Exception ex) {
            LOGGER.severe(ExceptionUtils.getStackTrace(ex));
        }
        
        return result;
    }
    
    @Override
    public int gcd() {
        int result = 0;
        try {
            JMSConsumer jmsConsumer = new JMSConsumerImpl(CommonConstant.JMS_QUEUE_LIST_INT);
            int i1 = Integer.parseInt(jmsConsumer.consumeMessage().getText());
            jmsConsumer = new JMSConsumerImpl(CommonConstant.JMS_QUEUE_LIST_INT);
            int i2 = Integer.parseInt(jmsConsumer.consumeMessage().getText());
            
            result = gcdCalc.gcd(i1, i2);
            
            List<String> messages = new ArrayList<>();
            messages.add(Integer.toString(result));
            JMSProducer jmsProducer = new JMSProducerImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
            jmsProducer.sendMessages(messages);
        } catch (Exception ex) {
            LOGGER.severe(ExceptionUtils.getStackTrace(ex));
        }
        
        return result;
    }

    @Override
    public List<Integer> gcdList() {
        List<Integer> result = new ArrayList<>();
        try {
            JMSBrowser jmsBrowser = new JMSBrowserImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
            List<TextMessage> textMessages = jmsBrowser.browseMessages();
            for (TextMessage textMessage : textMessages) {
                result.add(new Integer(textMessage.getText()));
            }
        } catch (Exception ex) {
            LOGGER.severe(ExceptionUtils.getStackTrace(ex));
        }
        
        return result;
    }

    @Override
    public int gcdSum() {
        int result = 0;
        try {
            JMSBrowser jmsBrowser = new JMSBrowserImpl(CommonConstant.JMS_QUEUE_LIST_GCD);
            List<TextMessage> textMessages = jmsBrowser.browseMessages();
            for (TextMessage textMessage : textMessages) {
                result += Integer.parseInt(textMessage.getText());
            }
        } catch (Exception ex) {
            LOGGER.severe(ExceptionUtils.getStackTrace(ex));
        }
        
        return result;
    }
}

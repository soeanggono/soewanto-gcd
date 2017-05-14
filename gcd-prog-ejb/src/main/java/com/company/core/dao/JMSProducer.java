package com.company.core.dao;

import java.util.List;
import javax.jms.JMSException;

public interface JMSProducer {
    
    public void sendMessages(List<String> messages) throws JMSException;
    
}

package com.company.core.dao;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public interface JMSConsumer {
    public TextMessage consumeMessage() throws JMSException;
    public TextMessage consumeMessage(long timeOut) throws JMSException;
}

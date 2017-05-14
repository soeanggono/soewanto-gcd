package com.company.core.dao;

import java.util.List;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public interface JMSBrowser {
    public List<TextMessage> browseMessages() throws JMSException;
}

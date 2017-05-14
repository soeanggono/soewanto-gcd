package com.company.core.common;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {
    public static Context getInitialContext() throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
        props.setProperty(Context.PROVIDER_URL, CommonConstant.JMS_PROVIDER_URL);
        Context context = new InitialContext(props);
        return context;
    }
}

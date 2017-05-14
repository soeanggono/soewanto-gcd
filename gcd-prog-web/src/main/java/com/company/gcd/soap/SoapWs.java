package com.company.gcd.soap;

import com.company.core.service.GCDService;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="GcdService", name="Gcd", targetNamespace="http://www.jboss.org")
public class SoapWs {
    
    private static final Logger LOGGER = Logger.getLogger(SoapWs.class.getName());
    
    @Inject
    @Named("gcdServiceImpl")
    private GCDService gcdService;
    
    @WebMethod
    public int gcd() {
        return gcdService.gcd();
    }
    
    @WebMethod
    public List<Integer> gcdList() {
        return gcdService.gcdList();
    }
    
    @WebMethod
    public int gcdSum() {
        return gcdService.gcdSum();
    }
}

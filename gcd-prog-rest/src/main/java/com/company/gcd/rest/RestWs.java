package com.company.gcd.rest;

import com.company.service.GCDService;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/")
@Consumes({"application/json"})
@Produces({"application/json"})
public class RestWs {
    
    private static final Logger LOGGER = Logger.getLogger(RestWs.class.getName());
    
    @Inject
    @Named("gcdServiceImpl")
    private GCDService gcdService;
    
    @GET
    @Path("/push")
    public String push(@QueryParam("i1") int i1, @QueryParam("i2") int i2) {
        return gcdService.push(i1, i2);
    }
    
    @GET
    @Path("list")
    public List<Integer> list() {
        return gcdService.list();
    }
}

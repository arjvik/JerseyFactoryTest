package com.arjvik.arjmart;

import static com.arjvik.arjmart.Log.getLog;
import static com.arjvik.arjmart.Log.log;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.arjvik.arjmart.service.Service_DefaultFactory_DefaultObject;
import com.arjvik.arjmart.service.Service_DefaultFactory_SingletonObject;
import com.arjvik.arjmart.service.Service_DefaultObject;
import com.arjvik.arjmart.service.Service_SingletonFactory_DefaultObject;
import com.arjvik.arjmart.service.Service_SingletonFactory_SingletonObject;
import com.arjvik.arjmart.service.Service_SingletonObject;

@Path("/")
public class MyResource {

	@Inject Service_DefaultFactory_DefaultObject s1;
	@Inject Service_DefaultFactory_SingletonObject s2;
	@Inject Service_SingletonFactory_DefaultObject s3;
	@Inject Service_SingletonFactory_SingletonObject s4;
	@Inject Service_DefaultObject s5;
	@Inject Service_SingletonObject s6;
	
	@Inject
	public MyResource() {
		log(getClass().getName());
	}
	
    @GET
    public String root() {
    	log("<--------------------------------------->");
        return getLog()+getServiceStuff();
    }

	private String getServiceStuff() {
		return String.format("Service calls: %n"+"%s%n"+"%s%n"+"%s%n"+"%s%n"+"%s%n"+"%s", s1.get(), s2.get(), s3.get(), s4.get(), s5.get(), s6.get());
	}
}

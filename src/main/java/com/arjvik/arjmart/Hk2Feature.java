package com.arjvik.arjmart;

import javax.inject.Singleton;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import com.arjvik.arjmart.service.*;

import org.glassfish.jersey.internal.inject.AbstractBinder;

@Provider
public class Hk2Feature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		context.register(new AbstractBinder(){

			@Override
			protected void configure() {
				bindFactory(Factory_DefaultFactory_DefaultObject.class).to(Service_DefaultFactory_DefaultObject.class);
				bindFactory(Factory_DefaultFactory_SingletonObject.class).to(Service_DefaultFactory_SingletonObject.class).in(Singleton.class);
				bindFactory(Factory_SingletonFactory_DefaultObject.class, Singleton.class).to(Service_SingletonFactory_DefaultObject.class);
				bindFactory(Factory_SingletonFactory_SingletonObject.class, Singleton.class).to(Service_SingletonFactory_SingletonObject.class).in(Singleton.class);
				bind(Service_DefaultObject.class).to(Service_DefaultObject.class);
				bind(Service_SingletonObject.class).to(Service_SingletonObject.class).in(Singleton.class);
			}
			
		});
		return true;
	}

}

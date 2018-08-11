package com.arjvik.arjmart.service;

import static com.arjvik.arjmart.Log.log;

import java.util.function.Supplier;

import javax.inject.Inject;

public class Factory_DefaultFactory_DefaultObject implements Supplier<Service_DefaultFactory_DefaultObject> {

	public static int initcounter = 1;
	public final int counter;

	@Inject
	public Factory_DefaultFactory_DefaultObject() {
		counter = initcounter++;
		log(getCount());
	}
	
	private String getCount() {
		return getClass().getName() + " - " + counter;
	}

	@Override
	public Service_DefaultFactory_DefaultObject get() {
		return new Service_DefaultFactory_DefaultObject();
	}

}

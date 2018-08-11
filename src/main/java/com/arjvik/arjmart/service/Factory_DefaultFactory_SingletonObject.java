package com.arjvik.arjmart.service;

import static com.arjvik.arjmart.Log.log;

import java.util.function.Supplier;

import javax.inject.Inject;

public class Factory_DefaultFactory_SingletonObject implements Supplier<Service_DefaultFactory_SingletonObject> {

	public static int initcounter = 1;
	public final int counter;

	@Inject
	public Factory_DefaultFactory_SingletonObject() {
		counter = initcounter++;
		log(getCount());
	}

	private String getCount() {
		return getClass().getName() + " - " + counter;
	}

	@Override
	public Service_DefaultFactory_SingletonObject get() {
		return new Service_DefaultFactory_SingletonObject();
	}
}

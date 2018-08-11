package com.arjvik.arjmart.service;

import static com.arjvik.arjmart.Log.log;

import java.util.function.Supplier;

import javax.inject.Inject;

public class Factory_SingletonFactory_DefaultObject implements Supplier<Service_SingletonFactory_DefaultObject> {

	public static int initcounter = 1;
	public final int counter;

	@Inject
	public Factory_SingletonFactory_DefaultObject() {
		counter = initcounter++;
		log(getCount());
	}

	private String getCount() {
		return getClass().getName() + " - " + counter;
	}

	@Override
	public Service_SingletonFactory_DefaultObject get() {
		return new Service_SingletonFactory_DefaultObject();
	}
}

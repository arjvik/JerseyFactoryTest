package com.arjvik.arjmart.service;

import static com.arjvik.arjmart.Log.log;

import java.util.function.Supplier;

import javax.inject.Inject;

public class Factory_SingletonFactory_SingletonObject implements Supplier<Service_SingletonFactory_SingletonObject> {

	public static int initcounter = 1;
	public final int counter;

	@Inject
	public Factory_SingletonFactory_SingletonObject() {
		counter = initcounter++;
		log(getCount());
	}

	private String getCount() {
		return getClass().getName() + " - " + counter;
	}

	@Override
	public Service_SingletonFactory_SingletonObject get() {
		return new Service_SingletonFactory_SingletonObject();
	}
}

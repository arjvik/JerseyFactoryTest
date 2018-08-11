package com.arjvik.arjmart.service;

import javax.inject.Inject;

import static com.arjvik.arjmart.Log.*;

public class Service_DefaultFactory_DefaultObject {

	public static int initcounter = 1;
	public final int counter;

	@Inject
	public Service_DefaultFactory_DefaultObject() {
		counter = initcounter++;
		log(get());
	}

	public String get() {
		return getClass().getName() + " - " + counter;
	}

}

package org.dstadler.commoncrawl.datalayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataAccessFactory {
    public static final String DB_TEST = "commoncrawltest";

	public static EntityManager getInstance(String db) {
		System.out.println("Creating DataAccessLayer");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory(db, System.getProperties());

		if(factory == null) {
			throw new IllegalStateException("Could not initialize EntityManagerFactory.");
		}

		return factory.createEntityManager();
	}
}

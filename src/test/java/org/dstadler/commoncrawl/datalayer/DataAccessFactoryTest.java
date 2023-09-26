package org.dstadler.commoncrawl.datalayer;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataAccessFactoryTest {
	public static DatabaseStarter starter = new DatabaseStarter();

	@BeforeClass
	public static void startDB() {
		starter.start();
	}

	@AfterClass
	public static void stopDB() {
		assertTrue(starter.stop());
	}

	@Test
	public void testGetInstance() {
		EntityManager em = DataAccessFactory.getInstance(DataAccessFactory.DB_TEST);
		em.close();
	}
}

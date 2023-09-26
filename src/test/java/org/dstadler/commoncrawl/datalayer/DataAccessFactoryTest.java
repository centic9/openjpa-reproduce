package org.dstadler.commoncrawl.datalayer;

import javax.persistence.EntityManager;

import org.junit.Test;

public class DataAccessFactoryTest extends DatabaseBase {

	@Test
	public void testGetInstance() {
		EntityManager em = DataAccessFactory.getInstance(DataAccessFactory.DB_TEST);
		em.close();
	}
}

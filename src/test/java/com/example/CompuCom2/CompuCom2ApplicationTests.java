package com.example.CompuCom2;

import com.example.CompuCom2.repository.procedures.Procedures;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompuCom2ApplicationTests {

	@PersistenceContext
	private EntityManager entityManager;
	private Log LOG = LogFactory.getLog(CompuCom2ApplicationTests.class);

	@Autowired
	private Procedures procedures;

	@Test
	public void contextLoads() {
		StoredProcedureQuery storedProcedureQuery = entityManager
				.createStoredProcedureQuery("getUserEmails");
		storedProcedureQuery.getResultList().forEach(u -> LOG.info((String) u));
	}

	@Test
	public void testUsersEmail(){
		procedures.userEmails().forEach(emails -> LOG.info(emails));
	}

	@Test
	public void testUsers(){
		procedures.getUsers().forEach(user -> LOG.info(user));
	}

	@Test
	public void testSingleUser(){
		LOG.info(procedures.getUser(3));
	}

}

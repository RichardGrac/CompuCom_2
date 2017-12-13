package com.example.CompuCom2;

import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;
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
		LOG.info(procedures.getUser(34));
	}

	@Test
	public void addSingleUser(){
		UserModel userModel = UserModel.builder()
				.email("qq@qq.com")
				.password("qq")
				.username("qq")
				.build();
		UserAddressModel userAddressModel = UserAddressModel.builder()
				.city("qq")
				.colony("qq")
				.country("qq")
				.number("qq")
				.reference("qq")
				.state("qq")
				.street("qq")
				.zip("qq")
				.build();
		procedures.addUser(userModel, userAddressModel);
	}

}

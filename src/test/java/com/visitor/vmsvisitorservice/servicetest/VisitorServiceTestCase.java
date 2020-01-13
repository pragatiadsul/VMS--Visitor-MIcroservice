
package com.visitor.vmsvisitorservice.servicetest;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.visitor.vmsvisitorservice.dao.IVisitorDao;
import com.visitor.vmsvisitorservice.dto.VisitorDto;
import com.visitor.vmsvisitorservice.model.Visitor;
import com.visitor.vmsvisitorservice.service.VisitorServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class VisitorServiceTestCase {

	@Autowired
	private VisitorServiceImpl visitorService;

	@Autowired
	private IVisitorDao visitorDao;

	/**
	 * Method used to get VisitorsList
	 */

	@Test
	void testVisitorsList() {

		Visitor visitor = new Visitor();
		visitor.setName("reshma");
		List<Visitor> member = visitorService.visitorsList();
		Visitor newMember = member.get(0);
		Assert.assertEquals(visitor.getName(),"reshma");

	}

	/**
	 * Method used to get Visitor by using VisitorById
	 */

	@Test
	void testGetByVisitorId() {
		Visitor visitor = visitorService.getByVisitorId(02);
		Assert.assertEquals("sameer", visitor.getName());
		Assert.assertEquals("p@gmail.com", visitor.getEmail());
	}


	/**
	 * Method used to add Visitor
	 */
	@Test
	public void testSaveVisitor() {
		VisitorDto v1 = new VisitorDto();
		v1.setName("sangam");
		// v1.setAddress("wakad,pune");

		v1.setContactPersonEmail("Hr@gmail.com");
		v1.setContactPersonName("kishori");
		v1.setMobileNo("9658253641");

		v1.setEmail("sangam@gmail.com");
		v1.setIdProof("98TYNBYUTR");
		v1.setReasonForVisit("joining");
		v1.setContactPersonMobileNo("8798898974");
		v1.setVisitorType("client");
		visitorService.addVisitor(v1);
	}


	/**
 * Method used get VisitorByName
	 */
	@Test
	public void testGetVisitorByName() {
		Visitor cat = visitorService.getVisitorByName("sameer");
		Assert.assertEquals("sameer", cat.getName());
	}

	@Disabled
	@Test
	public void testGetVisitorById() {
		Visitor cat = visitorService.getByVisitorId(0);
		Assert.assertEquals("reshma", cat.getName());
		// Assert.assertEquals("color", cat.getCategoryDesc());
	}

	@Disabled
	@Test
	public void testGetUpdateVisitor() {
		Visitor visitor = new Visitor();
		((List<Visitor>) visitorDao).get(3);
		visitor.setName("reshma");
		Assert.assertEquals("name is", visitor.getName());

	}

}

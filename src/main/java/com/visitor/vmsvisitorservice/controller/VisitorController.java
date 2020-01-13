package com.visitor.vmsvisitorservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.visitor.vmsvisitorservice.dao.IVisitorDao;
import com.visitor.vmsvisitorservice.dto.VisitorDto;
import com.visitor.vmsvisitorservice.exception.VisitorNotFoundException;
import com.visitor.vmsvisitorservice.model.Visitor;
import com.visitor.vmsvisitorservice.service.IVisitorService;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

	private static final Logger logger = LogManager.getLogger(VisitorController.class);

	@Autowired
	private IVisitorService visitorService;


	/**
	 * Method used for Visitor registration
	 * 
	 * @param Visitor Object
	 * @return status string
	 */
	@PostMapping("/addVisitor")
	public ResponseEntity<String> addVisitor(@RequestBody VisitorDto visitorDto) {
		return new ResponseEntity<String>(visitorService.addVisitor(visitorDto), HttpStatus.OK);
	}

	/**
	 * Method used to get registered Visitors list
	 * 
	 * @param No any parameter provided
	 * @return List of registered Visitors
	 */
	@GetMapping("/visitorsList")
	public List<Visitor> visitorsList() {
		return visitorService.visitorsList();
	}

	/**
	 * Method used to fetch specific Visitor by Id
	 * 
	 * @param Id
	 * @return Visitor object
	 * @throws Visitor Not Found Exception
	 */

	@GetMapping("/visitorsList/{id}")
	public Visitor getByVisitorId(@PathVariable long id) {

		logger.info("get visitors list by id ==" + id);

		try {
			Visitor userData = visitorService.getByVisitorId(id);
			return userData;
		} catch (Exception e) {
			throw new VisitorNotFoundException("");
		}

	}

	/**
	 * Method used to fetch specific Visitor by Name
	 * 
	 * @param Name
	 * @return Visitor object
	 */

	@PostMapping("/visitorByName")
	public Visitor getVisitorByName(@RequestBody Visitor visitor) {
		String name = visitor.getName();

		return visitorService.getVisitorByName(name);
	}

	/**
	 * Method used to update the visitor
	 * 
	 * @param visitor object
	 * @return Visitor object
	 */
	@GetMapping("/updateVisitorById/{id}")
	public void updateVisitorById(@RequestBody Visitor visitor, @PathVariable long id) {
		visitorService.updateVisitorById(visitor, id);
	}

	
	/**
	 * Method used to delete Visitor by Id
	 * 
	 * @param visitor ID
	 */
	@GetMapping("/deleteVisitorById/{id}")
	public void deleteVisitorById(@PathVariable long id)
	{
		visitorService.deleteVisitorById(id);
		
	}
	
	/**
	 * Method used to search the Record by using any Attribute
	 * 
	 * @param Visitor Object
	 * @return resultList
	 */

	@PostMapping("/search")
	public List<Visitor> searchVisitor(@RequestBody Visitor visitor) {

		return visitorService.searchVisitor(visitor);
	}
	
	
	/**
	 * Method used for Visitor Registration
	 * 
	 * @param Visitor Object
	 * @return status Visitor Object
	 */
	@PostMapping("/add")
	public Visitor add(@RequestBody VisitorDto visitorDto) {
		return visitorService.add(visitorDto);
	}




}
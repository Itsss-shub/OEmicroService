package com.bank.homeloan.restapi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.homeloan.restapi.app.model.AllPersonalDocsStatus;
import com.bank.homeloan.restapi.app.model.CustomerDetails;
import com.bank.homeloan.restapi.app.servicei.CustomerServiceI;

import lombok.extern.slf4j.Slf4j;




@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/bank/homeloan/restapi/customer")
public class CustomerController {
	
	@Autowired
	CustomerServiceI csi;
	
	
	@GetMapping("/getAllApplWithFTOE")
	public ResponseEntity<List<CustomerDetails>> getAllApplications(){
		
	List<CustomerDetails>appList=	csi.getAllApplications();
		log.info("All loan application fetch from database...!!!");
		
		return new ResponseEntity<List<CustomerDetails>>(appList,HttpStatus.OK);
	}
	
	@GetMapping("/getById/{customerDetailsId}")
	public ResponseEntity<CustomerDetails> getCustomerApp(@PathVariable Integer customerDetailsId){
		CustomerDetails app= csi.getById(customerDetailsId);
		log.info("Application data fetched using Id");
		return new ResponseEntity<CustomerDetails>(app,HttpStatus.OK);
	}
	
	@PutMapping("/rejection/{customerDetailsId}")
	public ResponseEntity<CustomerDetails> upStatusRejected(@PathVariable Integer customerDetailsId,@RequestBody AllPersonalDocsStatus c){
		CustomerDetails app= csi.getByIdUpdate(customerDetailsId,c);
		log.info("Application status changed status :"+app.getStatus());
		return new ResponseEntity<CustomerDetails>(app,HttpStatus.OK);
	}
	
	@PutMapping("/docVerfy/{customerDetailsId}")
	public ResponseEntity<CustomerDetails> upStatusDocVerfy(@PathVariable Integer customerDetailsId,@RequestBody AllPersonalDocsStatus c){
		CustomerDetails app= csi.upStatus(customerDetailsId,c);
		log.info("Application status changed status :"+app.getStatus());
		return new ResponseEntity<CustomerDetails>(app,HttpStatus.OK);
	}

}

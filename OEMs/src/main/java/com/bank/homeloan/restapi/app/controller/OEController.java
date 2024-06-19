package com.bank.homeloan.restapi.app.controller;

import java.util.List;

import org.slf4j.Logger;
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

import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.servicei.OEServiceI;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/bank/homeloan/restapi/enquiry")
public class OEController {

	@Autowired
	OEServiceI si;
	
	@GetMapping("/getFTOE")
	public ResponseEntity<List<Enquiry>> getData() {
		List<Enquiry> en=si.getByStatus();
		log.info("fetching all enquiry with status FTOE");
		return  new ResponseEntity<List<Enquiry>>(en,HttpStatus.OK);
		
	}
	
	@PutMapping("/editEnquiry/{enquiryId}")
	public ResponseEntity<Enquiry> editEnquiry(@RequestBody Enquiry en, @PathVariable Integer enquiryId)
	
	{
		Enquiry e = si.editEnquiry(enquiryId, en);
		log.info("cibil score :  "+e.getCibilScore());
		
		return new ResponseEntity<Enquiry>(e,HttpStatus.OK);
		
	}
	
	
	
}

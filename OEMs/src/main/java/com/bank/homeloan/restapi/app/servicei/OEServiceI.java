package com.bank.homeloan.restapi.app.servicei;

import java.util.List;

import com.bank.homeloan.restapi.app.model.Enquiry;

public interface OEServiceI {

	Enquiry editEnquiry(Integer enquiryId, Enquiry en);

	List<Enquiry> getByStatus();

}

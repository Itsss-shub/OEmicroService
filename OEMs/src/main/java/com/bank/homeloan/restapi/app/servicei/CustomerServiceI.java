package com.bank.homeloan.restapi.app.servicei;

import java.util.List;

import com.bank.homeloan.restapi.app.model.AllPersonalDocsStatus;
import com.bank.homeloan.restapi.app.model.CustomerDetails;

public interface CustomerServiceI {

	List<CustomerDetails> getAllApplications();

	CustomerDetails getById(Integer customerDetailsId);

	CustomerDetails getByIdUpdate(Integer customerDetailsId, AllPersonalDocsStatus c);

	CustomerDetails upStatus(Integer customerDetailsId, AllPersonalDocsStatus c);

}

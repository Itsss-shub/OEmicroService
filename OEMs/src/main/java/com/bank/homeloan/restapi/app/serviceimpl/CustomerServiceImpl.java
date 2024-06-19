package com.bank.homeloan.restapi.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.homeloan.restapi.app.exception.CustomerApplicationsNotFoundException;
import com.bank.homeloan.restapi.app.model.AllPersonalDocsStatus;
import com.bank.homeloan.restapi.app.model.CustomerDetails;
import com.bank.homeloan.restapi.app.repository.CustomerRepo;
import com.bank.homeloan.restapi.app.servicei.CustomerServiceI;


@Service
public class CustomerServiceImpl implements CustomerServiceI{

	@Autowired
	CustomerRepo cri;

	@Override
	public List<CustomerDetails> getAllApplications() {
		List<CustomerDetails> allApp = cri.findByStatus("FTOE");
		if(!allApp.isEmpty()) {
			return allApp;
		}
		else {
		throw new CustomerApplicationsNotFoundException("No appliction found on database...!!");
		}
	}

	@Override
	public CustomerDetails getById(Integer customerDetailsId) {
		Optional<CustomerDetails> ap = cri.findById(customerDetailsId);
		if(ap!=null) {
			CustomerDetails app = ap.get();
		return app;
		}
		else {
			throw new CustomerApplicationsNotFoundException("No Application found on this id..!!!");
		}
	}

	@Override
	public CustomerDetails getByIdUpdate(Integer customerDetailsId, AllPersonalDocsStatus c) {
		Optional<CustomerDetails> app = cri.findById(customerDetailsId);
		if(app!=null) {
			CustomerDetails appl = app.get();
			//setting status to document updated
			
//			appl.getAllPersonalDocsStatus().setAadharCardStatus(c.getAadharCardStatus());
//			appl.getAllPersonalDocsStatus().setAddressProofStatus(c.getAddressProofStatus());
//			appl.getAllPersonalDocsStatus().setBankChequeStatus(c.getBankChequeStatus());
//			appl.getAllPersonalDocsStatus().setBankStatementStatus(c.getBankStatementStatus());
//			appl.getAllPersonalDocsStatus().setIncomeProofStatus(c.getIncomeProofStatus());
//			appl.getAllPersonalDocsStatus().setIncomeTaxReturnStatus(c.getIncomeTaxReturnStatus());
//			appl.getAllPersonalDocsStatus().setPanCardStatus(c.getPanCardStatus());
//			appl.getAllPersonalDocsStatus().setPhotoStatus(c.getPhotoStatus());
//			appl.getAllPersonalDocsStatus().setProfessionalSalarySlipStatus(c.getProfessionalSalarySlipStatus());
//			appl.getAllPersonalDocsStatus().setPropertyInsuranceStatus(c.getPropertyInsuranceStatus());
//			appl.getAllPersonalDocsStatus().setPropertyProofStatus(c.getPropertyProofStatus());
//			appl.getAllPersonalDocsStatus().setSignatureStatus(c.getSignatureStatus());
//			appl.getAllPersonalDocsStatus().setThumbStatus(c.getThumbStatus());
			
			//setting application status
			appl.setStatus("DocRejceted");
		return cri.save(appl);
		}
		else {
			throw new CustomerApplicationsNotFoundException("No data present in database for this id ..!!!");
		}
	}

	@Override
	public CustomerDetails upStatus(Integer customerDetailsId, AllPersonalDocsStatus c) {
		
		Optional<CustomerDetails> app = cri.findById(customerDetailsId);
		if(app!=null) {
			CustomerDetails appl = app.get();
			//setting status to document updated
//			
//			appl.getAllPersonalDocsStatus().setAadharCardStatus(c.getAadharCardStatus());
//			appl.getAllPersonalDocsStatus().setAddressProofStatus(c.getAddressProofStatus());
//			appl.getAllPersonalDocsStatus().setBankChequeStatus(c.getBankChequeStatus());
//			appl.getAllPersonalDocsStatus().setBankStatementStatus(c.getBankStatementStatus());
//			appl.getAllPersonalDocsStatus().setIncomeProofStatus(c.getIncomeProofStatus());
//			appl.getAllPersonalDocsStatus().setIncomeTaxReturnStatus(c.getIncomeTaxReturnStatus());
//			appl.getAllPersonalDocsStatus().setPanCardStatus(c.getPanCardStatus());
//			appl.getAllPersonalDocsStatus().setPhotoStatus(c.getPhotoStatus());
//			appl.getAllPersonalDocsStatus().setProfessionalSalarySlipStatus(c.getProfessionalSalarySlipStatus());
//			appl.getAllPersonalDocsStatus().setPropertyInsuranceStatus(c.getPropertyInsuranceStatus());
//			appl.getAllPersonalDocsStatus().setPropertyProofStatus(c.getPropertyProofStatus());
//			appl.getAllPersonalDocsStatus().setSignatureStatus(c.getSignatureStatus());
//			appl.getAllPersonalDocsStatus().setThumbStatus(c.getThumbStatus());
			
			//setting application status
			appl.setStatus("DocVerify");
		return cri.save(appl);
		}
		else {
			throw new CustomerApplicationsNotFoundException("No data present in database for this id ..!!!");
		}
		
	}
	
   
}

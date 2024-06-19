package com.bank.homeloan.restapi.app.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.homeloan.restapi.app.exception.EnquiryNotFoundException;
import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.repository.OERepositoryI;
import com.bank.homeloan.restapi.app.servicei.OEServiceI;

@Service
public class OEServiceImpl implements OEServiceI {

	@Autowired
	private JavaMailSender sender;
	
	
	@Autowired@Value("$spring.mail.username")String mymail;
	
	
	@Autowired OERepositoryI ri;

	@Override
	public Enquiry editEnquiry(Integer enquiryId, Enquiry en) {
		Optional<Enquiry> om = ri.findById(enquiryId);
		Enquiry e = om.get();
		Random rand = new Random();
		int min = 600;
		int max = 900;
		int c = rand.nextInt((max - min) + 1) + min;
		System.out.println("Randam value : " + c);
		if (en.getCibilScore() == null) {
			en.setCibilScore(c);
			//System.out.println(en.getCibilScore());
			int cibil = en.getCibilScore();
			if (cibil > 650) {

				en.setStatus("approved");
				// System.out.println(e.getStatus());
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom(mymail);
				message.setTo(e.getEmail());
				String sub = "HOME LOAN application approved";

				message.setSubject(sub);
				
				String msg = "Dear " + e.getName() + ",\n\n"
						+ "We are pleased to inform you that your loan application Enquiry has been approved by Apna Finance Corp Limited!\n\n"
						+ "Loan Details:\n" + "- Cibil Score: $" + cibil + "\n" + "- Interest Rate: "
						+ "8.75 " + "%\n" +

						"Next Steps:\n" + "1. Please review and fill the loan application form carefully.\n"
						+ "2. Fill the form to us within the specified time frame.\n"
						+ "3. Once we receive the form, we will proceed your loan application to further process.\n\n"
						+ "If you have any questions or need further assistance, feel free to reply to this email or contact our customer service team at +91 8088080880.\n\n"
						+ "Congratulations once again, and thank you for choosing Apna Finance Corp Limited!\n\n"
						+ "Best regards,\n" + "Pritesh Badgujar\n" + "Operational Executive\n"
						+ "Apna Finance Corp Limited";
				message.setText(msg);

				sender.send(message);

			} else {
				en.setStatus("rejected");
		
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom(mymail);
				message.setTo(e.getEmail());
				String sub = "HOME LOAN application rejcted";

				message.setSubject(sub);
				String msg = "Dear " + e.getName() + ",\n\n"
						+ "We regret to inform you that your loan application with Apna Finance Corp Limited has been rejected. We appreciate your interest in our services and understand that this decision may be disappointing.\n\n"
						+ "Reason for Rejection:\n" + "- " + "Due to your less credit score/ CIBIL : " +cibil+ "\n\n" + "Next Steps:\n"
						+ "1. If you have any questions regarding the rejection, please feel free to reach out to our customer service team at "
						+  " +91 8088080880"+ ".\n"
						+ "2. Consider reviewing your financial situation and addressing any issues that may have led to the rejection.\n"
						+ "3. You are welcome to reapply in the future once the necessary improvements have been made.\n\n"
						+ "Thank you for considering Apna Finance Corp Limited for your financial needs. We wish you the best in your financial endeavors.\n\n"
						+ "Sincerely,\n" + "Pritesh Badgujar\n" + "Operational Executive\n" + "Apna Finance Corp Limited";
				message.setText(msg);

				sender.send(message);
			}

		}

		return ri.save(en);

	}

	@Override
	public List<Enquiry> getByStatus() {
    
		 List<Enquiry> enq = ri.findEnqByStatus("FTOE");
		 
		 if(!enq.isEmpty()) {
			 return enq;
		 }
		 else {
			 throw new EnquiryNotFoundException("no enquiry found on the status FTOE");
		 }
	}

}

package email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import emailService.impl.EmailService;

@Controller
@RequestMapping("/Sys")
public class SysController {
	@Autowired
    private EmailService emailService;
	
	@RequestMapping("/StartSMTP")
	public String startSMTP() {
		emailService.startSMTP();
		return "SMTP";
	}
	
	@RequestMapping("/StopSMTP")
	public String stopSMTP() {
		emailService.stopSMTP();
		return "SMTP";
	}
	
}

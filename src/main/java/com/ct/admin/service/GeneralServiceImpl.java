package com.ct.admin.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Override
	public String sendMail(String mailId) {
		return "Hey I will Send mail with this otp: "+getOtp()+" to this mailId: "+mailId+" don't worry";
	}

	@Override
	public int getOtp() {
		Random random = new Random();
		return random.nextInt(999999 - 100000 + 1) + 100000;
	}

}

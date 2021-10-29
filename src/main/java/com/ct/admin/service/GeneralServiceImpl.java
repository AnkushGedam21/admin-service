package com.ct.admin.service;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Override
	public String sendMail(String mailId) {
		return "Hey I will Send mail with this otp: "+getOtp()+" to this mailId: "+mailId+" don't worry";
	}

	@Override
	public String getOtp() {
//		Random random = new Random();
		//return random.nextInt(999999 - 100000 + 1) + 100000;
//		String password = new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char)i)).collect(Collectors.joining());
//	
		String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
		        StringBuilder::appendCodePoint, StringBuilder::append)
		        .toString();
		return password;
	}

}

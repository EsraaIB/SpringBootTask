package com.okhtub.user;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange.Map;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;

	public MyUser getUserById(int id) {

		Optional<MyUser> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			System.out.println("User not found!");
			return null;
		}

	}

	public String getLanguageByUserId(int userId, String language) throws JsonProcessingException {

		Optional<MyUser> user = userRepo.findById(userId);
		String lang = language.toLowerCase();
		if (!language.equals("arabic") || !language.equals("english"))
			return "Language must be english or arabic";

		if (user.isPresent()) {
			ObjectMapper objectMapper = new ObjectMapper();
			HashMap<String, Object> object = new HashMap<String, Object>();

			// set id to map that will be converted to json
			object.put("id", user.get().getUserId());

			// convert language to lower case as standard input

			// set language that user choose
			if (lang.equals("english"))
				object.put("value", user.get().getEducationLevel().getEnglishLevel());
			else if (lang.equals("arabic"))
				object.put("value", user.get().getEducationLevel().getArabicLevel());

			// convert map to json
			String arrayToJson = objectMapper.writeValueAsString(object);

			return arrayToJson;

		} else {
			return "User isn't exist ";
		}
	}

}

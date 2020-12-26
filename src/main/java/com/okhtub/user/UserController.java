package com.okhtub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	// get user to specific id
	@RequestMapping("/getUser/{userId}")
	public ResponseEntity<MyUser> getUserById(@PathVariable int userId) throws JsonProcessingException {

		try {
			MyUser l = userService.getUserById(userId);
			System.out.println(l);
			if (l == null) {
				
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	// get user by id and language
	@RequestMapping("/getLanguageLevel/{userId}/{language}")
	public String getLanguageByUserId(@PathVariable int userId, @PathVariable String language)
			throws JsonProcessingException {
		try {
			return userService.getLanguageByUserId(userId, language);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
		//return new ResponseEntity<>("{\"result\" :\" user not found\" }" , HttpStatus.OK);


	}

}

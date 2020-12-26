package com.okhtub.education.level;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class EducationController {

	@Autowired
	EducationService educationService;

	// Manually format json
	@RequestMapping("/getAllEductionLevels")
	public String getAllEducationLevels() throws JsonProcessingException {

		return educationService.getAllEducationLevels();
	}

	// Automatically format json
	@RequestMapping("/getAllEductionLevels2")
	public ResponseEntity<ArrayList<EducationLevel>> getAllEducationLevels2() throws JsonProcessingException {

		ArrayList<EducationLevel> l = educationService.getAllEducationLevels2();
		if (l == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(l);
		}
	}

	// get all available education levels
	@RequestMapping("/getAvailableEducationLevels")
	public ResponseEntity<List<EducationLevel>> getAvailableEducationLevels() throws JsonProcessingException {

		List<EducationLevel> l = educationService.getAllAvailableLevels();
		if (l == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(l);
		}
	}//end 

	// Update Education level
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{levelId}")
	public ResponseEntity<EducationLevel> updateEducationLevel(@RequestBody EducationLevel educationLevel,
			@PathVariable int levelId) {
		if (educationLevel != null) {

			EducationLevel x = educationService.updateEducationLevel(educationLevel, levelId);
			if (x == null) {
				System.out.println("user isn't exist to be updated!");
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(x);
			}

		}
		return ResponseEntity.notFound().build();

	}

}

package com.okhtub.education.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EducationService {

	@Autowired
	EducationRepo educationRepo;

	// return all education levels as json
	public String getAllEducationLevels() throws JsonProcessingException {
	    ArrayList<EducationLevel> educationList = new ArrayList<>();
		educationRepo.findAll().forEach(educationList::add);

		// Sort result by sorting attribute
		Collections.sort(educationList, (p1, p2) -> p1.getSorting() - p2.getSorting());

		// convert to json
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = objectMapper.writeValueAsString(educationList);

		return arrayToJson;
	}

	// return all education levels as json v2
	public ArrayList<EducationLevel> getAllEducationLevels2() {
		ArrayList<EducationLevel> educationList = new ArrayList<>();
		educationRepo.findAll().forEach(educationList::add);
		// Sort result by sorting attribute
		Collections.sort(educationList, (p1, p2) -> p1.getSorting() - p2.getSorting());

		return educationList;
	}

	// get all available Education level
	public List<EducationLevel> getAllAvailableLevels() {
		List<EducationLevel> educationList = new ArrayList<>();
		educationList = educationRepo.findAllByAvailable(1);
		// Sort result by sorting attribute
		Collections.sort(educationList, (p1, p2) -> p1.getSorting() - p2.getSorting());

		return educationList;

	}

	// update education level
	public EducationLevel updateEducationLevel(EducationLevel educationLevel, int levelId) {

		Optional<EducationLevel> obj=educationRepo.findById(levelId);
		if(obj.isPresent())
		{
			System.out.println(obj.get().getId());
			obj.get().setEnglishLevel(educationLevel.getEnglishLevel());
			obj.get().setArabicLevel(educationLevel.getArabicLevel());
			obj.get().setAvailable(educationLevel.getAvailable());
			obj.get().setSorting(educationLevel.getSorting());

			return educationRepo.save(obj.get());
		}
		else
			return null;

	}
	
	public EducationLevel getEducationLevelById(int id) {
		
		Optional<EducationLevel> level=educationRepo.findById(id);
		if(level.isPresent())
			return level.get();
		else 
			return null;
		
	}

	public List<String> getAllEnglishLevels() {
		List<String> allEnglishLevels=new ArrayList<>();
		allEnglishLevels.add("Fluent");
		allEnglishLevels.add("VeryGood");
		allEnglishLevels.add("Good");
		allEnglishLevels.add("Fair");
		allEnglishLevels.add("Weak");
		return allEnglishLevels;
	}
	public List<String> getAllArabicLevels() {
		List<String> allArabicLevels=new ArrayList<>();
		allArabicLevels.add("ممتاز");
		allArabicLevels.add("جيد جدا");
		allArabicLevels.add("جيد");
		allArabicLevels.add("مقبول");
		allArabicLevels.add("ضعيف");
		return allArabicLevels;
	}

	public void deleteEducationLevel(int idint) {
		educationRepo.deleteById(idint);
	}

}

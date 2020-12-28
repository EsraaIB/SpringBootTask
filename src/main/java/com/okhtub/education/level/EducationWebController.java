package com.okhtub.education.level;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EducationWebController {

	@Autowired
	EducationService educationService;

	@RequestMapping("/home")
	public String getEducationLevelHome(Model model) {

		ArrayList<EducationLevel> allLevels = educationService.getAllEducationLevels2();
		model.addAttribute("allLevels", allLevels);
		return "home";
	}

	// get page of update amd the data of user
	@RequestMapping(method = RequestMethod.GET, value = { "/updateLevel" })
	public String update(HttpServletRequest request, Model model) {
		String levelId = request.getParameter("id");
		int idint = Integer.parseInt(levelId);
		EducationLevel selectedLevel = educationService.getEducationLevelById(idint);

		if (selectedLevel != null) {
			model.addAttribute("selectedLevel", selectedLevel);
			model.addAttribute("allEnglishLevels", educationService.getAllEnglishLevels());
			model.addAttribute("allArabicLevels", educationService.getAllArabicLevels());

			return "updateLevel";
		} else {
			System.out.println("User is not exist");
			return "home";

		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateLevel")
	public void updateBus(Model model, @ModelAttribute("selectedLevel") EducationLevel selectedLevel)

	{ //fill drop downlist
		model.addAttribute("allEnglishLevels", educationService.getAllEnglishLevels());
		model.addAttribute("allArabicLevels", educationService.getAllArabicLevels());

		
		// check if object is exists
		if (selectedLevel != null) {
			// check english level format
			String eng = selectedLevel.getEnglishLevel();
			if (eng.equals("Fluent") || eng.equals("VeryGood") || eng.equals("Good") || eng.equals("Fair")
					|| eng.equals("Weak")) {
				// check arabic format
				String ar = selectedLevel.getArabicLevel();
				if (ar.equals("ممتاز") || ar.equals("جيد جدا") || ar.equals("جيد") || ar.equals("مقبول")
						|| ar.equals("ضعيف")) {
					// check available is 0 or 1
					int available = selectedLevel.getAvailable();
					if (available == 0 || available == 1) {

						// check sorting
						if (selectedLevel.getSorting() != 0) {
							EducationLevel result = educationService.updateEducationLevel(selectedLevel,
									selectedLevel.getId());
							// check if updated successfully
							if (result != null) {
								System.out.println("updated successfully!");
								model.addAttribute("Updatemessage", "updated successfully!");
							} else// failed update
							{
								System.out.println("failed to update");
								model.addAttribute("exception", "failed to update!");
							}
						} else {
							System.out.println("sorting can't be 0");
							model.addAttribute("exception", "Sorting can't be 0!");
						}
					} else // wrong available format
					{
						System.out.println("wrong available value");
						model.addAttribute("exception", "Choose appropriate available value!");

					}

				} else // wrong arabic format
				{
					System.out.println("wrong arabic level format");
					model.addAttribute("exception", "Choose appropriate Arabic level !");

				}

			} else // wrong English format
			{
				System.out.println("wrong enlish level format");
				model.addAttribute("exception", "Choose appropriate English level !");

			}

		} else // object not exist
		{
			System.out.println("  Education Level is not Specified ");
			model.addAttribute("exception", "Error , please try later!");

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/deleteLevel" })
	public String delete(HttpServletRequest request, Model model) {
		String levelId = request.getParameter("id");
		int idint = Integer.parseInt(levelId);

		EducationLevel level = educationService.getEducationLevelById(idint);

		if (level != null) {
			educationService.deleteEducationLevel(idint);
			
		}

		return "redirect:/home";
	}

}

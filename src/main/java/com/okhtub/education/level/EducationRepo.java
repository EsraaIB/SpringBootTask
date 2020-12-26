package com.okhtub.education.level;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends CrudRepository<EducationLevel, Integer> {
  
	List<EducationLevel> findAllByAvailable(int available);
	
	
}

package com.stream.video.component;

import org.slf4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import lombok.extern.java.Log;
//import ch.qos.logback.classic.Logger;
//import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> notFoundException(NotFoundException ex)
	{
//		log.error(ex.getMessage());
	
		System.out.println(ex.getMessage());
		return ResponseEntity.notFound().build();
		
	}

}

package com.salecart.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

public class MessageCustom {
	private Map<String, Object> response = new HashMap<>();

	public ResponseEntity<?> error(List<FieldError> fielsError) {
		List<String> error = fielsError.stream()
				.map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());

		response.put("message", error);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> errorCatch(String messageError){
		response.put("message", messageError);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> error(String error) {
		response.put("message", error);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> success(String success) {
		response.put("message", success);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

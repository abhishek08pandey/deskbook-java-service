package com.onerivet.deskbook.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse<T>{

	private T data;
	private String error;
	
	public GenericResponse(T data, String error) {
		this.data = data;
		this.error = error;
	}
	
	
}

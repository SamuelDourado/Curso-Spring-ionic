package com.cursomc.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void setErros(List<FieldMessage> erros) {
		this.erros = erros;
	}
	
	public void addError(FieldMessage error) {
		this.erros.add(error);
	}
	
	public void addError(String field, String message) {
		this.erros.add(new FieldMessage(field,message));
	}
}

package com.gastos.exes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class SegPersonaNewDto {
	
	@Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "The E-mail address is not valid. Please verify E-mail introduced")
	private String email;
	
	private String nombrePersona;
	private String apellidosPersona;
    
	private String password;

}

package com.gastos.exes.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SegPersonaDto {
	
	private int codPersona;
	
	@Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "The E-mail address is not valid. Please verify E-mail introduced")
	private String email;
	
	private String nombrePersona;
	private String apellidosPersona;
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ultimoAcceso;

}

/**
 * 
 */
package com.gastos.exes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.gastos.exes.dto.AutenticateUserRequest;
import com.gastos.exes.dto.SegPersonaDto;
import com.gastos.exes.service.SegPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gastos.exes.util.Cosntants;
import com.gastos.exes.util.NotHtml;
import com.gastos.exes.security.SecurityValidation;


/**
 * @author tiago
 *
 */
@RestController
@RequestMapping("/personas")
@Slf4j
public class PersonasController {

	@Autowired
	@Qualifier("segPersonaService")
	SegPersonaService segPersonaService;

	 @Autowired
	 SecurityValidation validarHeaders;
	
	 @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/getByEmail")
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseEntity<?> getNamePersonByEmail(@NotNull @NotHtml @RequestParam(name="email") String email,
	                                                 HttpServletRequest header) {
	       if (validarHeaders.realizarSeguridad(header)) {
			   SegPersonaDto result = segPersonaService.getUserData(email);
	           if(result != null) {
	           	   return new ResponseEntity<>(result, HttpStatus.OK);
	           } else{
	                   return new ResponseEntity<>(Cosntants.NO_DATA, HttpStatus.NOT_FOUND);
	               }
	       }else {
	           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //401
	       }
	 }
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "/autenticate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAutentication(@NotNull @NotHtml @RequestBody AutenticateUserRequest autenticate,
											  HttpServletRequest header) {
		if (validarHeaders.realizarSeguridad(header) && segPersonaService.getAutenticationUser(autenticate)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //401
		}
	}
}

/**
 * 
 */
package com.gastos.exes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.gastos.exes.dto.AutenticateUserRequest;
import com.gastos.exes.dto.SegPersonaDto;
import com.gastos.exes.dto.SegPersonaNewDto;
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
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "false")
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

	@RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, value = "/newUser")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> newUser(@NotNull @NotHtml @RequestBody SegPersonaNewDto nuevoUsuario,
											  HttpServletRequest header) {
		if (validarHeaders.realizarSeguridad(header)) {
			boolean generateNewUser = segPersonaService.newUsuer(nuevoUsuario);
			if (generateNewUser) {
				return new ResponseEntity<>(true, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(false, HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //401
		}
	}
}

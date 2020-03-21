/**
 * 
 */
package com.gastos.exes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gastos.exes.util.Cosntants;
import com.gastos.exes.util.NotHtml;
import com.gastos.exes.util.SecurityValidation;


/**
 * @author tiago
 *
 */
@RestController
@RequestMapping("/exes-back")
public class ExesController {
	
	  @Autowired
	  SecurityValidation validarHeaders;
	
	  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/")
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<?> getNamePersonByEmail(@NotNull @NotHtml @RequestParam(name="email") String email,
	                                                  HttpServletRequest header) {
	        if (validarHeaders.realizarSeguridad(header)) {
	            if(email != null) {
	            	   return new ResponseEntity<>(email, HttpStatus.OK);
	            } else{
	                    return new ResponseEntity<>(Cosntants.EMAIL_NO_EXIST, HttpStatus.NOT_FOUND);
	                }
	        }else {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //401
	        }
	    }

}

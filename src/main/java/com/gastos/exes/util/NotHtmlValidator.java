package com.gastos.exes.util;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validate that the string does not contain HTML.
 */
public final class NotHtmlValidator implements ConstraintValidator<NotHtml, String> {

    @Override
    public void initialize(NotHtml parameters) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    	boolean isValid=true;
        if (value != null) {
        	// Avoid anything between script tags
        	  Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        	// Avoid anything in a src='...' type of expression
              Pattern srcPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

              Pattern srcCommaPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

              // Remove any lonesome </script> tag
              Pattern endScriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
              
              // Remove any lonesome <script ...> tag
              Pattern startScriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


              // Avoid eval(...) expressions
              Pattern evalPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


              // Avoid expression(...) expressions
              Pattern expPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

              // Avoid javascript:... expressions
              Pattern javaScriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

              // Avoid vbscript:... expressions
              Pattern vbscriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
              
              Pattern extraPattern= Pattern.compile(".*\\<[^>]+>.*");

              // Avoid onload= expressions
              Pattern onloadPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        	  if(extraPattern.matcher(value).find()||scriptPattern.matcher(value).find()||srcPattern.matcher(value).find()||srcCommaPattern.matcher(value).find()||endScriptPattern.matcher(value).find()||startScriptPattern.matcher(value).find()||evalPattern.matcher(value).find()||expPattern.matcher(value).find()||javaScriptPattern.matcher(value).find()||vbscriptPattern.matcher(value).find()||onloadPattern.matcher(value).find()){
        		isValid=false;
        	  }
        }
        return isValid;
    }
}

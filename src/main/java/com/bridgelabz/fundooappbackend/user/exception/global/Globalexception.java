package com.bridgelabz.fundooappbackend.user.exception.global;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.fundooappbackend.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundooappbackend.user.exception.custom.LoginException;
import com.bridgelabz.fundooappbackend.user.exception.custom.RegistrationExcepton;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.ValidateUserException;
import com.bridgelabz.fundooappbackend.user.exception.custom.ValidationException;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.response.Response;

@ControllerAdvice
public class Globalexception 
{
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.OK, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RegistrationExcepton.class)
	public ResponseEntity<Response> registrationExcepton(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Response> tokenException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> validationException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidateUserException.class)
	public ResponseEntity<Response> ValidateUserException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<Response> ForgotPasswordException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> methodArgumentNotValidException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.ENTER_EMAIL, e.getMessage(), "Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.USER_NOT_EXISTING, e.getMessage(), "Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> missingServletRequestParameterException(Exception e) 
	{
		return new ResponseEntity<Response>(new Response(Messages.ENTER_EMAIL, e.getMessage(), "Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

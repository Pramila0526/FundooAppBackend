package com.bridgelabz.fundooappbackend.user.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooappbackend.user.configuration.PasswordConfiguration;
import com.bridgelabz.fundooappbackend.user.dto.ForgotPasswordDto;
import com.bridgelabz.fundooappbackend.user.dto.LoginDto;
import com.bridgelabz.fundooappbackend.user.dto.RegistrationDto;
import com.bridgelabz.fundooappbackend.user.dto.ResetPasswordDto;
import com.bridgelabz.fundooappbackend.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundooappbackend.user.exception.custom.RegistrationExcepton;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.ValidateUserException;
import com.bridgelabz.fundooappbackend.user.message.MessageUtility;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;
import com.bridgelabz.fundooappbackend.user.response.Response;
import com.bridgelabz.fundooappbackend.user.utility.TokenUtility;

/**********************************************************************************************************
 * @author :Pramila Mangesh Tawari 
 * Purpose :Service Implementation Class for
 *          implementing actual Flow/Logic
 *
 *********************************************************************************************************/

@Service
public class UserServiceImplementation implements UserService {
	
	static Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	@Autowired
	private UserRepository repository; // create object user repo

	// JavaMailSender class
	@Autowired
	private JavaMailSender javaMailSender;

	// PasswordEncoder Object
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Token utility Object
	@Autowired
	private TokenUtility tokenutility;

	// ModelMapper for mapping from object to dto or vice versa
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordConfiguration passConfig;

//	static Logger logger = Logger.getLogger(UserServiceImplementation.class);

/**
 * @return User Registration Mathod :- After the User Registration, OTP/ Token
 *         in been sent to Users Mail For Verification Using JMS(Java Mail
 *         Sender).
 *
 **************************************************************************************************/
	public Response Register(RegistrationDto regdto) 
	{
		User user = mapper.map(regdto, User.class); // Mapping new User data into the user Class
		
		System.out.println(user.getFirstname());
		
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail()))) // check user already
		{
			logger.info("Registration Completed");
			return new Response(Messages.BAD_REQUEST, "User Registration Completed ", Messages.EMAIL_ALREADY_REGISTERED);
		}  
			user.setPassword(passwordEncoder.encode(regdto.getPassword()));
			
			System.out.println(user);
			user = repository.save(user); // Storing Users Data in Database
      			
			if (user == null) 
			{
				logger.info("Null Content");
				throw new RegistrationExcepton(Messages.ENTER_EMAIL);
			}
			String token = tokenutility.createToken(user.getEmail());
			System.out.println(token);
		
			javaMailSender
					.send(MessageUtility.verifyUserMail(regdto.getEmail(), token, Messages.REGISTRATION_MAIL_TEXT));
				
			logger.info("Token Sent");
			return new Response(Messages.OK, null, Messages.USER_ADDED_SUCCESSFULLY);
		}
	
/**
 * @return Validating the User :- It Checks if the Token is from valid mail Id
 *         or Not. If token is valid, it sets the validation as 1.
 *
 ***********************************************************************************************/
	public Response validateUser(String token) 
	{
		String email = tokenutility.getUserToken(token); // get user id from user token.
		if (email.isEmpty()) 
		{
			throw new TokenException(Messages.INVALID_EMAIL);
		}

		User user = repository.findByEmail(email);
		if (user != null)
		{ // if userid is found validate should be true
			user.setValidate(true);
			repository.save(user);
			return new Response(Messages.OK, "email  ", Messages.EMAIL_VERIFIED);
		}
		else 
		{
			return new Response(Messages.BAD_REQUEST, "Email Not Verified", Messages.NOT_VERFIY_EMAIL);
		}
	}

/**
 * @return User Login Method :- Login the Authenticated User
 *
 ***********************************************************************************************/
	public Response loginUser(LoginDto logindto) 
	{
		User user = repository.findByEmail(logindto.getEmail()); // find email present or not
		System.out.println(user);
		if (user == null)
		{
			logger.info("Null Content");
			return new Response(Messages.BAD_REQUEST, "User Registrtion ", Messages.USER_NOT_EXISTING);
		}
		String token = tokenutility.createToken(user.getEmail());

		if (!user.isValidate()) 
		{

			new ValidateUserException(Messages.LINK_NOT_ACTIVE);
		} 
		else
		{
			if (user.getEmail().equals(logindto.getEmail())
					&& passConfig.encoder().matches(logindto.getPassword(), user.getPassword())) 
			{ // encode the user
				logger.info("username password Matched");
				return new Response(Messages.OK, Messages.LOGIN_SUCCESSFUL, token); // password
			}
		}
		return new Response(Messages.BAD_REQUEST, "User Registrtion ", Messages.LOGIN_UNSUCCESSFUL);
	}
	
/**
 * @return Forgot Passwrod Method :- In Case if Password is not remebering then
 *         we can recover it by sending token to the email id.
 *
 **********************************************************************************************/
	public Response forgotPassword(ForgotPasswordDto forgetPasswordDto) 
	{
		User user = repository.findByEmail(forgetPasswordDto.getEmail()); // find by user email id

		System.out.println(user);
		if (user == null)
		{ 
			logger.info("Null Content");
			throw new ForgotPasswordException(Messages.USER_NOT_EXISTING);
		} 
		else
		{
			String token = tokenutility.createToken(user.getEmail());
			System.out.println(token);
			logger.info("Token Generated");
			javaMailSender
					.send(MessageUtility.verifyUserMail(forgetPasswordDto.getEmail(), token, Messages.VERIFY_MAIL)); // send
			logger.info("Token Sent");																									// email
		}
		return new Response(Messages.OK, null, Messages.EMAIL_VERIFIED);
	}

/**
 * @return Update User Method :- Updating the user account by new Information
 *
 **********************************************************************************************/
	public String updateUserByEmail(User user, String email) 
	{
		User updateUser = repository.findByEmail(email);
		updateUser = user;
		repository.save(updateUser);
		logger.info("Upadated User");
		return Messages.USER_UPDATE_SUCCESSFULLY;
	}

/**
 * @return Set Password Method :- Changing the Password
 *
 *********************************************************************************************/
	public Response setPassword(ResetPasswordDto setPasswordDto, String token) 
	{
		String useremail = tokenutility.getUserToken(token);

		User updateUser = repository.findByEmail(useremail);
		
		if (setPasswordDto.getPassword().equals(setPasswordDto.getConfirmpassword())) 
		{ // check password or cfmpassword
			logger.info("Username Password Matched");
			updateUser.setPassword(passwordEncoder.encode(setPasswordDto.getPassword())); // new password encode it

			updateUserByEmail(updateUser, useremail);
			
			return new Response(Messages.OK, "Changed password", Messages.PASSWORD_CHANGED_SUCCESSFULLY);
		} 
		else
		{
			return new Response(Messages.BAD_REQUEST, null, Messages.PASSWORD_NOT_MATCHING);
		}
	}

	/**
	 * @return Find User :- Particular user's data by the token
	 *
	 */
	public Response findUser(String token) 
	{
		String email = tokenutility.getUserToken(token);
		if (email.isEmpty())
		{
			logger.info("Email Doesn't Exists");
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		return new Response(Messages.OK, "User Registration ", repository.findByEmail(email));
	}

	/**
	 * @return Show All Users Method :- Showing the users's List
	 *
	 */
	public List<User> showAllUsers(String token) 
	{
		System.out.println("check");
		logger.info("All USers");
		return repository.findAll(); // show all user details in JPA
	}
}
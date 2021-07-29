package com.time.allocation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.time.allocation.project.dto.ErrorDTO;
import com.time.allocation.project.exception.UnauthorizedException;
import com.time.allocation.project.dto.AuthenticationRequestDTO;
import com.time.allocation.project.dto.AuthenticationResponseDTO;
import com.time.allocation.project.service.UserService;
import com.time.allocation.project.utility.AuthenticationUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"/authenticate"})
@RestController
@RequestMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final AuthenticationUtility authenticationUtility;

    private final AuthenticationManager authenticationManager;
    
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    
    
    public AuthenticationController(AuthenticationUtility authenticationUtility, 
    		AuthenticationManager authenticationManager,
    		UserService userService, PasswordEncoder passwordEncoder)
    {
    	this.authenticationUtility = authenticationUtility;
    	this.authenticationManager = authenticationManager;
    	this.userService = userService;
    	this.passwordEncoder = passwordEncoder;
    }

   
    @ApiOperation(value = "Authentication")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Authenticated"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDTO authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception{
    	 try {
	    	UserDetails userDetails = userService.loadUserByUsername(authenticationRequestDTO.getLogin());
	
        	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(
                    authenticationRequestDTO.getLogin(),
                    authenticationRequestDTO.getPassword()
            );
        	
        	authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        	

            final String token = authenticationUtility.generateToken(userDetails);

            return  new AuthenticationResponseDTO(token);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
			throw new UnauthorizedException();
        }

    }
}

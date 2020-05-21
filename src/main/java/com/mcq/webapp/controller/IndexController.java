package com.mcq.webapp.controller;

 
import com.mcq.webapp.config.JwtUtilConfig;
import com.mcq.webapp.model.Authentication;
import com.mcq.webapp.model.AuthenticationResponse; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
 

@RestController
@RequestMapping("/")
public class IndexController {
 //Logger logger = LoggerFactory.getLogger(LoggingController.class);
 

    @Autowired
    private AuthenticationManager authenticationManaget;

    // @Autowired
    // private MainUserDetailService mainUserDetailService;


    @Autowired
    private UserController mainUserDetailService;

    @Autowired
    private JwtUtilConfig jj;

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the EasyNotes application. You can create a new Note by making a POST request to /api/notes endpoint.";
    }

    @RequestMapping(value="/authenticate",method=RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Authentication authenticationRequest) throws Exception{
       

            // UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            // token.setDetails(new WebAuthenticationDetails(request));
        
        System.out.println("test kjkjk"+authenticationRequest.getUsername()+" "+authenticationRequest.getPassword());
        authenticationManaget.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
       

        final UserDetails userDetails=mainUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jj.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

package com.DDN.login.controller;


import com.DDN.login.common.ApiResponse;
import com.DDN.login.dto.auth.AccountCreationRequest;
import com.DDN.login.dto.auth.AccountCreationResponse;
import com.DDN.login.dto.auth.AuthenticationRequest;
import com.DDN.login.dto.auth.AuthenticationResponse;
import com.DDN.login.model.ERole;
import com.DDN.login.model.Role;
import com.DDN.login.model.User;

import com.DDN.login.repository.RoleRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.security.jwt.JwtUtils;
import com.DDN.login.security.jwt.Md5Util;

import com.DDN.login.security.service.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserReposity userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AccountCreationRequest accountCreationRequest) throws Exception{
        if (userRepository.existsByUsername(accountCreationRequest.getUsername())) {
            return ResponseEntity.ok(new AccountCreationResponse("failure", "Username already exists"));
        }

        if (userRepository.existsByEmail(accountCreationRequest.getEmail())) {
            return ResponseEntity.ok(new AccountCreationResponse("failure", "Email already exists"));
        }

        // Create new user's account
        User user = new User(accountCreationRequest.getFirstname(),accountCreationRequest.getLastname(),
                accountCreationRequest.getUsername(), Md5Util.getInstance().getMd5Hash(accountCreationRequest.getPassword()),
                accountCreationRequest.getEmail());

        Set<String> strRoles = accountCreationRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new AccountCreationResponse("success", null));
    }

    @GetMapping("/allUser")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam int page, @RequestParam int pageSize){
        List<User> users = new ArrayList<>();
        Pageable pagingSort = PageRequest.of(page,pageSize);
        Page<User> pageUsers;

        pageUsers = userRepository.findAll(pagingSort);

        users = pageUsers.getContent();
        Map<String,Object> response = new HashMap<>();
        response.put("users", users);
        response.put("currentPage", pageUsers.getNumber());
        response.put("totalItems", pageUsers.getTotalElements());
        response.put("totalPages", pageUsers.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<User> findByUserId(@PathVariable Long id) {
        Optional<User> userInfo = userRepository.findById(id);

        return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") long userId) {
            userRepository.deleteById(userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "User has been removed"), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestHeader(value="Authorization") String headerData) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        String[] data = headerData.split(" ");
        byte[] decoded = Base64.getDecoder().decode(data[1]);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        data = decodedStr.split(":");

        authenticationRequest.setUsername(data[0]);
        authenticationRequest.setPassword(data[1]);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            Md5Util.getInstance().getMd5Hash(authenticationRequest.getPassword()))
            );
        } catch(BadCredentialsException e) {
            return ResponseEntity.ok(new AuthenticationResponse(null, "Incorrect username or password", null));
        } catch(Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse(null, "User name does not exist", null));
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        Optional<User> user = userRepository.findByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, null, user.get().getId()));
    }
}

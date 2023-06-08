package com.example.projectFiler.controller;

import com.example.projectFiler.entity.ERole;
import com.example.projectFiler.entity.RoleEntity;
import com.example.projectFiler.entity.UserEntity;
import com.example.projectFiler.payload.request.LoginRequest;
import com.example.projectFiler.payload.request.SignupRequest;
import com.example.projectFiler.payload.response.JwtResponse;
import com.example.projectFiler.payload.response.MessageResponse;
import com.example.projectFiler.repository.RoleRepository;
import com.example.projectFiler.repository.UserRepository;
import com.example.projectFiler.security.jwt.JwtUtils;
import com.example.projectFiler.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private AuthenticationManager authenticationManager;

  @Autowired
  public void AuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  private UserRepository userRepository;

  @Autowired
  public void UserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private RoleRepository roleRepository;

  @Autowired
  public void RoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  private PasswordEncoder encoder;

  @Autowired
  public void PasswordEncoder(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  private JwtUtils jwtUtils;

  @Autowired
  public void JwtUtils(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(
    @Valid @RequestBody LoginRequest loginRequest
  ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(),
        loginRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails
      .getAuthorities()
      .stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(
      new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles)
    );
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    UserEntity user = new UserEntity(
      signUpRequest.getUsername(),
      encoder.encode(signUpRequest.getPassword())
    );

    Set<String> strRoles = signUpRequest.getRole();
    Set<RoleEntity> roles = new HashSet<>();

    if (strRoles == null) {
      RoleEntity userRole = roleRepository
        .findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(
        role -> {
          switch (role) {
            case "admin":
              RoleEntity adminRole = roleRepository
                .findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);

              break;
            default:
              RoleEntity userRole = roleRepository
                .findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(userRole);
          }
        }
      );
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}

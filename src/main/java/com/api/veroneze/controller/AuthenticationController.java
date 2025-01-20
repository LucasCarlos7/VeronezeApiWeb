//package com.api.veroneze.controller;
//
//import com.api.veroneze.application.security.TokenService;
//import com.api.veroneze.data.entity.FuncionarioEntity;
//import com.api.veroneze.data.entity.dto.AuthenticationDTO;
//import com.api.veroneze.data.entity.dto.LoginResponseDTO;
//import com.api.veroneze.data.inteface.FuncionarioRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private FuncionarioRepository funcionarioRepository;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
//
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//
//        var token = tokenService.generateToken((FuncionarioEntity) auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }
//}

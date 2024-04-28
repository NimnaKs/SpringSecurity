package com.codevent.demoSpringSecurity.controller;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello")
public class HelloRestController {

    @GetMapping("/user")
    /*@PreAuthorize("hasRole('ROLE_USER')")*/
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/admin")
    @RolesAllowed("ROLE_ADMIN")
    /*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }
}

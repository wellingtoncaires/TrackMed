package com.trackmed.tmbase.application.resources;

import com.trackmed.tmbase.application.services.EmailService;
import com.trackmed.tmbase.domain.entities.Email;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/email")
@RequiredArgsConstructor
public class EmailResource {

    private final EmailService service;

    @PostMapping(params = "enviar")
    public ResponseEntity<Email> sendEmail(@RequestParam("enviar") @RequestBody @Valid Email email) {
        service.sendEmail(email);
        return ResponseEntity.ok(email);
    }
}

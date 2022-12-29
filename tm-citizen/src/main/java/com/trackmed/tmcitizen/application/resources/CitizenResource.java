package com.trackmed.tmcitizen.application.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cidadao")
public class CitizenResource {

    @GetMapping(value = "teste")
    public String teste() {
        return "OK";
    }
}

package com.trackmed.tmcitizen.application.resources;

import com.trackmed.tmcitizen.application.services.AddressService;
import com.trackmed.tmcitizen.domains.entities.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/cidadao/enderecos")
@RequiredArgsConstructor
public class AddressResource {

    private final AddressService service;

    @GetMapping(params = "cep")
    public ResponseEntity<Address> findByCep(@RequestParam("cep") String cep) {
        Address address = service.findByCep(cep);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping(params = "pesquisa-cep")
    public ResponseEntity<Address> searchByCep(@RequestParam("pesquisa-cep") String cep) {
        Address address = service.searchExternalCep(cep);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address) {
        return ResponseEntity.ok().body(service.save(address));
    }

    @PutMapping(params = "id")
    public ResponseEntity<Address> update(@RequestParam("id") UUID id) {
        return ResponseEntity.ok().body(service.update(id));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

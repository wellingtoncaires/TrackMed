package com.trackmed.tmcitizen.application.services;

import com.google.gson.Gson;
import com.trackmed.tmcitizen.domains.entities.Address;
import com.trackmed.tmcitizen.exceptions.CitizenException;
import com.trackmed.tmcitizen.infra.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.UUID;

import static com.trackmed.tmcitizen.utils.ValidationUtils.*;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public Address findByCep(String cep) {
        if(!isCepValid(cep)){
            throw new CitizenException("O formato do CEP digitado está incorreto!");
        }
        Optional<Address> address = repository.findByCep(cep);
        if(address.isEmpty()) {
            throw new CitizenException("CEP não encontrado!");
        }
        return address.get();
    }

    public Address findExternalCep(String cep) {
        if(!isCepValid(cep)) {
            throw new CitizenException("O formato do CEP digitado está incorreto!");
        }
        Address address = searchExternalCep(cep);
        return address;
    }

    public Address searchExternalCep(String cep) {
        String path = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            URL url = new URL(path);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String response = "";
            StringBuilder jsonResponse = new StringBuilder();
            while ((response = bufferedReader.readLine()) != null) {
                jsonResponse.append(response);
            }
            Address address = new Gson().fromJson(jsonResponse.toString(), Address.class);
            return address;
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Address save(Address address) {
        address.setCep(address.getCep().replaceAll("[.-]", ""));
        address = repository.save(address);
        return address;
    }

    public Address findById(UUID id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new CitizenException("Endereço não cadastrado!"));
        return address;
    }

    public Address update(UUID id) {
        Address address = findById(id);
        address.setCep(address.getCep().replaceAll("[.-]", ""));
        return repository.save(address);
    }

    public void delete(UUID id) {
        Address address = findById(id);
        repository.delete(address);
    }
}

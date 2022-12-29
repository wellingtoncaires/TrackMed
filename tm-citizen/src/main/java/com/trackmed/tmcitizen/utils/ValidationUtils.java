package com.trackmed.tmcitizen.utils;

import com.trackmed.tmcitizen.exceptions.CitizenException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isCepValid(String cep) {
        if(cep.trim() == null) {
            return false;
        }
        cep = cep.replaceAll("[.-]", "");
        if(cep.length() != 8) {
            return false;
        }
        int cepInt;
        try {
            cepInt = Integer.parseInt(cep);
        }catch (NumberFormatException e) {
            throw new CitizenException("Formato de CEP inválido!");
        }
        return true;
    }

    public static  boolean isCpfValid(String cpf) {
        String regex = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)";
        Pattern pattern = Pattern.compile(cpf);
        Matcher matcher = pattern.matcher(regex);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        }
        return false;
    }

    public static boolean isCnpjValid(String cnpj) {
        String regex = "\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}";
        Pattern pattern = Pattern.compile(cnpj);
        Matcher matcher = pattern.matcher(regex);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        }
        return false;
    }
}

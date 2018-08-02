package com.rizkimuhammad.challenge.java.monolithic.bankbackend.controller;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.service.RegistrationService;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.RegistrationRequestVO;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO registerUser(@RequestBody RegistrationRequestVO registrationRequestVO) {
        return registrationService.register(registrationRequestVO);
    }
}

package com.rizkirm.challenge.bank.service;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.persistence.repository.UserAccountRepository;
import com.rizkirm.challenge.bank.validator.RegistrationValidator;
import com.rizkirm.challenge.bank.vo.RegistrationRequestVO;
import com.rizkirm.challenge.bank.vo.RegistrationResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Service
public class RegistrationService extends RegistrationValidator {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public RegistrationResponseVO doRegistration(RegistrationRequestVO registrationRequestVO) {
        checkRequest(registrationRequestVO);

        if (userAccountRepository.existsByEmail(registrationRequestVO.getEmail())) {
            throw new CustomBadRequestException("Email already used");
        }

        if (userAccountRepository.existsByUsername(registrationRequestVO.getUsername())) {
            throw new CustomBadRequestException("Username already used");
        }

        UserAccount userAccount = new UserAccount(registrationRequestVO.getUsername(),
                registrationRequestVO.getFullName(), passwordEncoder.encode(registrationRequestVO.getPassword()),
                registrationRequestVO.getEmail(), registrationRequestVO.getBalance());

        userAccountRepository.save(userAccount);

        return new RegistrationResponseVO(userAccount.getAccountNumber(), userAccount.getBalance());
    }
}
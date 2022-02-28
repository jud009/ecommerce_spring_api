package com.udj.course.services.validation;

import com.udj.course.domain.Client;
import com.udj.course.domain.enums.ClientType;
import com.udj.course.dto.ClientNewDTO;
import com.udj.course.repositories.ClientRepository;
import com.udj.course.resources.Exceptions.FieldMessage;
import com.udj.course.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    private ClientRepository repository;

    private final static String ERROR_FIELD_NAME = "CpfOrCnpj";
    private final static String ERROR_FIELD_EMAIL = "Email";
    private final static String INVALID_CPF = "CPF inválido.";
    private final static String INVALID_CNPJ = "CNPJ inválido.";
    private final static String INVALID_EMAIL = "Email inválido.";

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        if (isCNPJInvalid(objDto)) {
            errors.add(new FieldMessage(ERROR_FIELD_NAME, INVALID_CNPJ));
        }

        if (isCPFInvalid(objDto)) {
            errors.add(new FieldMessage(ERROR_FIELD_NAME, INVALID_CPF));
        }

        if (!isEmailValid(objDto)){
            errors.add(new FieldMessage(ERROR_FIELD_EMAIL, INVALID_CPF));
        }

        for (FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode
                    (e.getFieldName()).addConstraintViolation();
        }
        return errors.isEmpty();
    }

    private boolean isCPFInvalid(ClientNewDTO objDTO) {
        return objDTO.getType().equals(ClientType.PERSONAL.getId()) && !BR.isValidCPF(objDTO.getCpfOrCnpj());
    }

    private boolean isCNPJInvalid(ClientNewDTO objDTO) {
        return objDTO.getType().equals(ClientType.ENTERPRISE.getId()) && !BR.isValidCNPJ(objDTO.getCpfOrCnpj());
    }

    private boolean isEmailValid(ClientNewDTO objDTO) {
        Client aux = repository.findByEmail(objDTO.getEmail());
        return aux == null;
    }
}

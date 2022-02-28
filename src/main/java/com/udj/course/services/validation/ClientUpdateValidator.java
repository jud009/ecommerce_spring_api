package com.udj.course.services.validation;

import com.udj.course.domain.Client;
import com.udj.course.domain.enums.ClientType;
import com.udj.course.dto.ClientDTO;
import com.udj.course.dto.ClientNewDTO;
import com.udj.course.repositories.ClientRepository;
import com.udj.course.resources.Exceptions.FieldMessage;
import com.udj.course.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repository;

    private final static String ERROR_FIELD_EMAIL = "Email";
    private final static String INVALID_EMAIL = "Email inválido.";

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        if (!isEmailValid(objDto)) {
            errors.add(new FieldMessage(ERROR_FIELD_EMAIL, INVALID_EMAIL));
        }

        for (FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode
                    (e.getFieldName()).addConstraintViolation();
        }
        return errors.isEmpty();
    }

    private boolean isEmailValid(ClientDTO objDTO) {
        Map<String, String> map = (Map<String, String>)
                request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = Integer.parseInt(map.get("id"));

        Client aux = repository.findByEmail(objDTO.getEmail());
        return aux != null && uriId.equals(aux.getId());
    }
}

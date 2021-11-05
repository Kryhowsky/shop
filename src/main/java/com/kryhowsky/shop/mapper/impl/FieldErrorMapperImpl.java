package com.kryhowsky.shop.mapper.impl;

import com.kryhowsky.shop.mapper.FieldErrorMapper;
import com.kryhowsky.shop.model.dto.FieldErrorDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class FieldErrorMapperImpl implements FieldErrorMapper {

    @Override
    public FieldErrorDto toDto(ObjectError objectError) {

        var fieldError = (FieldError) objectError;

        return new FieldErrorDto(fieldError.getDefaultMessage(), fieldError.getField());
    }
}

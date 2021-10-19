package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dto.FieldErrorDto;
import org.springframework.validation.ObjectError;

public interface FieldErrorMapper {

    FieldErrorDto toDto(ObjectError objectError);
}

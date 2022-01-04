package com.kryhowsky.shop.mapper.impl

import com.kryhowsky.shop.model.dto.FieldErrorDto
import org.springframework.validation.FieldError
import spock.lang.Specification

class FieldErrorMapperImplSpec extends Specification {

    def fieldErrorMapper = new FieldErrorMapperImpl();

    def 'should test ObjectError to Dto'() {
        given:
        def objectError = Mock(FieldError)

        when:
        def result = fieldErrorMapper.toDto(objectError)

        then:
        result instanceof FieldErrorDto
        1 * objectError.getDefaultMessage()
        1 * objectError.getField()
        0 * _

    }

}

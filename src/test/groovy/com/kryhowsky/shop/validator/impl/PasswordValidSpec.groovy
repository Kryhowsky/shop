package com.kryhowsky.shop.validator.impl

import com.kryhowsky.shop.model.dto.UserDto
import spock.lang.Specification

class PasswordValidSpec extends Specification {

    def passwordValidator = new PasswordValidator()

    def 'should test password validator'() {

        given: 'deklarujemy to co jest potrzebne do testu'
        def userDto = new UserDto(password: password, confirmPassword: confirmPassword)

        when: 'wywołanie testowanej metody'
        def result = passwordValidator.isValid(userDto, null)

        then: 'sprawdzenie wyniku lub czy wystąpił exception lub czy konkretne metody z konkretnymi metodami się wywołały'
        result == expected

        where:
        password | confirmPassword || expected
        "123"    | "123"           || true
        "123"    | "321"           || false

    }
}

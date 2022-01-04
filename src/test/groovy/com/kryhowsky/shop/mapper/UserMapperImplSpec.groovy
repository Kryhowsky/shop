package com.kryhowsky.shop.mapper

import com.kryhowsky.shop.model.dao.User
import com.kryhowsky.shop.model.dto.UserDto
import spock.lang.Specification

class UserMapperImplSpec extends Specification {

    def userMapper = new UserMapperImpl()

    def 'should test Dao to Dto'() {
        given:
        def userDao = new User(id: 1, firstName: "Jan", lastName: "Kowalski", email: "email@email.com", password: "Password")

        when:
        def result = userMapper.toDto(userDao)

        then:
        result.id == userDao.id
        result.firstName == userDao.firstName
        result.lastName == userDao.lastName
        result.email == userDao.email
        result.password == null

    }

    def 'should test Dto to Dao'() {
        given:
        def userDto = new UserDto(id: 1, email: "email@email.com", password: "Password", firstName: "Jan", lastName: "Kowalski")

        when:
        def result = userMapper.toDao(userDto)

        then:
        result.id == userDto.id
        result.email == userDto.email
        result.password == userDto.password
        result.firstName == userDto.firstName
        result.lastName == userDto.lastName

    }

    def 'should return null in toDto'() {
        given:
        def nullUser = null

        when:
        def result = userMapper.toDto(nullUser)

        then:
        result == null

    }

    def 'should return null in toDao'() {
        given:
        def nullUser = null

        when:
        def result = userMapper.toDao(nullUser)

        then:
        result == null;

    }

}

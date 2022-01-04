package com.kryhowsky.shop.service.impl

import com.kryhowsky.shop.model.dao.Role
import com.kryhowsky.shop.model.dao.User
import com.kryhowsky.shop.repository.RoleRepository
import com.kryhowsky.shop.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UserServiceImplSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def roleRepository = Mock(RoleRepository)

    def userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository)

    def 'should save user'() {
        given:
        def user = Mock(User)
        def role = Mock(Role)

        when:
        userService.save(user)

        then:
        1 * user.getPassword() >> '1234'
        1 * passwordEncoder.encode('1234') >> '4321'
        1 * user.setPassword('4321')
        1 * roleRepository.findByName('ROLE_USER') >> Optional.of(role)
        1 * user.setRoles(Collections.singleton(role))
        1 * userRepository.save(user)
        0 * _ // nothing more performed

    }

    def 'should save user without the role'() {
        given:
        def user = Mock(User)

        when:
        userService.save(user)

        then:
        1 * user.getPassword() >> '1234'
        1 * passwordEncoder.encode('1234') >> '4321'
        1 * user.setPassword('4321')
        1 * roleRepository.findByName('ROLE_USER') >> Optional.empty()
        1 * userRepository.save(user)
        0 * _

    }

    def 'should update user'() {
        given:
        def userInput = new User(email: "email@email", firstName: "Jan", lastName: "Kowalski")
        def userDb = new User(id: 1, email: "test@test.pl", firstName: "Adam", lastName: "Nowak")
        userRepository.getById(1) >> userDb

        when:
        def result = userService.update(userInput, 1)

        then:
        result.id == 1
        result.email == userInput.email
        result.firstName == userInput.firstName
        result.lastName == userInput.lastName

    }

    def 'should delete user'() {
        given:
        def idInput = 1

        when:
        userService.delete(idInput)

        then:
        1 * userRepository.deleteById(1)
        0 * _

    }

    def 'should return page of users'() {
        given:
        def pageableInput = Mock(Pageable)

        when:
        userService.getPage(pageableInput)

        then:
        1 * userRepository.findAll(pageableInput)
        0 * _

    }

    def 'should return user by id'() {
        given:
        def idInput = 1

        when:
        userService.getUserById(idInput)

        then:
        1 * userRepository.getById(idInput)
        0 * _

    }

}

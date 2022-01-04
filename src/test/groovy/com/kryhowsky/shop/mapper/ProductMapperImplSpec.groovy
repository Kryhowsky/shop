package com.kryhowsky.shop.mapper

import com.kryhowsky.shop.model.dao.Product
import com.kryhowsky.shop.model.dto.ProductDto
import spock.lang.Specification

class ProductMapperImplSpec extends Specification {

    def productMapper = new ProductMapperImpl()

    def 'should test Dao to Dto'() {
        given:
        def productDao = new Product(id: 1, imagePath: "image/path", brand: "Nivea", name: "Żel", description: "Uniwersalny żel", price: 2.5, quantity: 10);

        when:
        def result = productMapper.toDto(productDao)

        then:
        result.id == productDao.id
        result.imagePath == productDao.imagePath
        result.brand == productDao.brand
        result.name == productDao.name
        result.description == productDao.description
        result.price == productDao.price
        result.quantity == productDao.quantity

    }

    def 'should test Dto to Dao'() {
        given:
        def productDto = new ProductDto(id: 1, imagePath: "image/path", brand: "Nivea", name: "Żel", description: "Uniwersalny żel", price: 2.5, quantity: 10);

        when:
        def result = productMapper.toDao(productDto)

        then:
        result.id == productDto.id
        result.imagePath == productDto.imagePath
        result.brand == productDto.brand
        result.name == productDto.name
        result.description == productDto.description
        result.price == productDto.price
        result.quantity == productDto.quantity

    }

    def 'should return null toDto'() {
        given:
        def nullProduct = null

        when:
        def result = productMapper.toDto(nullProduct)

        then:
        result == null

    }

    def 'should return null toDao'() {
        given:
        def nullProduct = null

        when:
        def result = productMapper.toDao(nullProduct)

        then:
        result == null

    }

}

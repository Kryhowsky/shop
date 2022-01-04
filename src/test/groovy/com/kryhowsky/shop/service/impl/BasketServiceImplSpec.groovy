package com.kryhowsky.shop.service.impl

import com.kryhowsky.shop.model.dao.Basket
import com.kryhowsky.shop.model.dao.Product
import com.kryhowsky.shop.model.dao.User
import com.kryhowsky.shop.repository.BasketRepository
import com.kryhowsky.shop.service.ProductService
import com.kryhowsky.shop.service.UserService
import spock.lang.Specification

class BasketServiceImplSpec extends Specification {

    def basketRepository = Mock(BasketRepository)
    def userService = Mock(UserService)
    def productService = Mock(ProductService)

    def basketService = Spy(BasketServiceImpl, constructorArgs: [basketRepository, userService, productService])

    def 'should throw exception when quantity is not enough'() {
        given:
        def productId = 1
        def productQuantity = 10
        def product = new Product(id: 1, quantity: 5)
        productService.getProductById(productId) >> product

        when:
        basketService.addProduct(productId, productQuantity)

        then:
        thrown IllegalArgumentException

    }

    def 'should add product to basket'() {
        given:
        def productId = 1
        def productQuantity = 10
        def user = new User()
        def product = new Product(quantity: 20)
        def basket = new Basket(user: user, product: product, quantity: 10)

        when:
        basketService.addProduct(productId, productQuantity)

        then:
        1 * basketService.addProduct(productId, productQuantity)
        1 * userService.getCurrentUser() >> user
        1 * productService.getProductById(productId) >> product
        1 * basketRepository.save(basket)
        0 * _

    }

    def 'should delete product'() {
        given:
        def productId = 1
        def userId = 1
        def user = Mock(User)

        when:
        basketService.deleteProduct(productId)

        then:
        1 * basketService.deleteProduct(productId)
        1 * userService.getCurrentUser() >> user
        1 * user.getId() >> userId
        1 * basketRepository.deleteByUserIdAndProductId(userId, productId)
        0 * _

    }

    def 'should update product quantity'() { // TODO: Pozmieniać na Mocki
        given:
        def user = Mock(User)
        def productId = 1
        def basketToUpdate = Mock(Basket)
        def product = Mock(Product)

        when:
        basketService.updateProductQuantity(productId, 5)

        then:
        1 * basketService.updateProductQuantity(productId, 5)
        1 * userService.getCurrentUser() >> user
        1 * user.getId() >> 1
        1 * basketRepository.findByUserIdAndProductId(1, productId) >> Optional.of(basketToUpdate)
        1 * productService.getProductById(productId) >> product
        1 * product.getQuantity() >> 15
        2 * basketToUpdate.getQuantity() >> 3
        1 * basketToUpdate.setQuantity(8)
        0 * _

    }

    def 'should add product when update'() {
        given:
        def productId = 1
        def productQuantity = 10
        def user = Mock(User)
        def product = Mock(Product)
        def basket = Mock(Basket)

        when:
        basketService.updateProductQuantity(productId, productQuantity)

        then:
        1 * basketService.updateProductQuantity(productId, productQuantity)
        1 * userService.getCurrentUser() >> user
        1 * user.getId() >> 1
        1 * basketRepository.findByUserIdAndProductId(1, productId) >> Optional.empty()
        1 * basketService.addProduct(productId, productQuantity) >> basket
        1 * productService.getProductById(productId) >> product
        1 * product.getQuantity() >> 15
        1 * basket.getQuantity() >> 10
        0 * _

    }

//    def 'should return list of products'() { // TODO: Sprawdzić wynik a nie wywołanie metod
//        given:
//        def user = new User();
//
//        when:
//        basketService.getProducts()
//
//        then:
//        1 * userService.getCurrentUser() >> user
//
//    }

    def 'should delete products'() {
        given:
        def user = Mock(User)

        when:
        basketService.deleteProducts()

        then:
        1 * basketService.deleteProducts()
        1 * userService.getCurrentUser() >> user
        1 * user.getId() >> 1
        1 * basketRepository.deleteByUserId(1)
        0 * _

    }

}

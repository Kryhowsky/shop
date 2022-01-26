package com.kryhowsky.shop.service.impl


import spock.lang.Specification

class ProductServiceImplSpec extends Specification  {

//    def productRepository = Mock(ProductRepository)
//    def filePropertiesConfig = Mock(FilePropertiesConfig)
//    def fileHelper = Mock(FileHelper)
//
//    def productService = new ProductServiceImpl(productRepository, filePropertiesConfig, fileHelper)
//
//    def 'should save product'() {
//        given:
//        def product = Mock(Product)
//        def image = Mock(MultipartFile)
//        def path = Mock(Path)
//        def inputStream = Mock(InputStream)
//        def biConsumer = Mock(BiConsumerThrowable)
//
//        when:
//        productService.save(product, image)
//
//        then:
//        1 * productRepository.save(product)
//        1 * image.getOriginalFilename() >> 'image.png'
////        1 * FilenameUtils.getExtension('image.png') >> 'png'
//        1 * product.getId() >> 1
//        1 * filePropertiesConfig.getProduct() >> 'C:/products/image/'
////        1 * Path.get('C:/products/image/product_1.png') >> 'C:/products/image/product_1.png'
//        1 * image.getInputStream() >> inputStream
//        1 * fileHelper.copyInputStream() >> biConsumer
//        1 * biConsumer.accept(inputStream, _ as Path)
//        1 * product.setImagePath('C:\\products\\image\\product_1.png')
//        1 * productRepository.save(product)
//        0 * _
//
//    }
//
//    def 'should throw exception on product save'() {
//        given:
//        def path = Paths.get('C:/products/image/product_1.png')
//        def product = Mock(Product)
//        def image = Mock(MultipartFile)
//        image.getOriginalFilename() >> 'C:/products/image/product_1.png'
//        def inputStream = Mock(InputStream)
//
//        when:
//        productService.save(product, image)
//
//        then:
//        1 * productRepository.save(product)
//        1 * image.getOriginalFilename() >> 'image.png'
//        1 * FilenameUtils.getExtension('image.png') >> 'png'
//        1 * product.getId() >> 1
//        1 * filePropertiesConfig.getProduct() >> 'C:/products/image/'
//        1 * Paths.get('C:/products/image/','product_1.png') >> 'C:/products/image/product_1.png'
//        1 * image.getInputStream() >> inputStream
//        1 * Files.copy(inputStream, 'C:/products/image/product_1.png')
//
//    }
//
//    def 'should delete product'() {
//        given:
//        def inInput = 1
//
//        when:
//        productService.delete(inInput)
//
//        then:
//        1 * productRepository.deleteById(inInput)
//        0 * _
//
//    }
//
//    def 'should return page of products'() {
//        given:
//        def pageable = Mock(Pageable)
//
//        when:
//        productService.getPage(pageable)
//
//        then:
//        1 * productRepository.findAll(pageable)
//        0 * _
//
//    }
//
//    def 'should return product by id'() {
//        given:
//        def idInput = 1
//
//        when:
//        productService.getProductById(idInput)
//
//        then:
//        1 * productRepository.getById(idInput)
//        0 * _
//
//    }

}

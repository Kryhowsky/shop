package com.kryhowsky.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void shouldSaveProduct() throws Exception {
//        MockMultipartFile image = new MockMultipartFile("image", "image_1.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[0]);
//        MockMultipartFile product = new MockMultipartFile("product", "", MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsBytes(ProductDto.builder()
//                .brand("Coca-Cola")
//                .name("Coca-Cola")
//                .description("Napój gazowany")
//                .price(7.00)
//                .quantity(10)
//                .build()));
//
//        mockMvc.perform(multipart("/api/products")
//                        .file(image)
//                        .file(product)
//                        .with(processor -> {
//                            processor.setMethod(HttpMethod.POST.name());
//                            return processor;
//                        }))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.imagePath").value("target\\product_1.jpg"))
//                .andExpect(jsonPath("$.brand").value("Coca-Cola"))
//                .andExpect(jsonPath("$.name").value("Coca-Cola"))
//                .andExpect(jsonPath("$.description").value("Napój gazowany"))
//                .andExpect(jsonPath("$.price").value(7.00))
//                .andExpect(jsonPath("$.quantity").value(10));
//
//    }

}

package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.repository.ProductRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvFileGenerator implements FileGeneratorStrategy {

        private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generating CSV file using Generic Strategy...");
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        String[] headers = {"Id", "Brand", "Description", "Name", "Price", "Quantity", "Created By", "Created Date", "Last Modified By", "Last Modified Date"};
        csvWriter.writeNext(headers);

        productRepository.findAll().forEach(product -> {
            String[] content = {product.getId().toString(), product.getBrand(), product.getDescription(), product.getName(), product.getPrice() == null ? "" : product.getPrice().toString(), product.getQuantity() == null ? "" : product.getQuantity().toString(), product.getCreatedBy() == null ? "" : product.getCreatedBy(), product.getCreatedDate() == null ? "" : product.getCreatedDate().toString(), product.getLastModifiedBy() == null ? "" : product.getLastModifiedBy(), product.getLastModifiedDate() == null ? "" : product.getLastModifiedDate().toString()};
            csvWriter.writeNext(content);
        });

        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }
}

package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfFileGenerator implements FileGeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.PDF;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generating PDF file using Generic Strategy...");
        Table table = new Table(10);
        table.addHeaderCell("Id");
        table.addHeaderCell("Brand");
        table.addHeaderCell("Description");
        table.addHeaderCell("Name");
        table.addHeaderCell("Price");
        table.addHeaderCell("Quantity");
        table.addHeaderCell("Created By");
        table.addHeaderCell("Created Date");
        table.addHeaderCell("Last Modified By");
        table.addHeaderCell("Last Modified Date");

        productRepository.findAll().forEach(product -> {
            table.addCell(product.getId().toString());
            table.addCell(product.getBrand());
            table.addCell(product.getDescription());
            table.addCell(product.getName());
            table.addCell(product.getPrice() == null ? "" : product.getPrice().toString());
            table.addCell(product.getQuantity() == null ? "" : product.getQuantity().toString());
            table.addCell(product.getCreatedBy() == null ? "" : product.getCreatedBy());
            table.addCell(product.getCreatedDate() == null ? "" : product.getCreatedDate().toString());
            table.addCell(product.getLastModifiedBy() == null ? "" : product.getLastModifiedBy());
            table.addCell(product.getLastModifiedDate() == null ? "" : product.getLastModifiedDate().toString());
        });

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(new PdfDocument(new PdfWriter(byteArrayOutputStream)));
        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

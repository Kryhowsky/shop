package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class XlsFileGenerator implements FileGeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.XLS;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generating XLS file using Generic Strategy..");

        try (Workbook workbook = WorkbookFactory.create(false)) {
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Id");
            row.createCell(1).setCellValue("Brand");
            row.createCell(2).setCellValue("Description");
            row.createCell(3).setCellValue("Name");
//            row.createCell(4).setCellValue("Price");
//            row.createCell(5).setCellValue("Quantity");
//            row.createCell(6).setCellValue("Created By");
//            row.createCell(7).setCellValue("Created Date");
//            row.createCell(8).setCellValue("Last Modified By");
//            row.createCell(9).setCellValue("Last Modified Date");

            List<Product> productList = productRepository.findAll();

            int index = 1;
            for (Product product : productList) {
                var productRow = sheet.createRow(index++);
                productRow.createCell(0).setCellValue(product.getId());
                productRow.createCell(1).setCellValue(product.getBrand());
                productRow.createCell(2).setCellValue(product.getDescription());
                productRow.createCell(3).setCellValue(product.getName());
//                productRow.createCell(4).setCellValue(product.getPrice());
//                productRow.createCell(5).setCellValue(product.getQuantity());
//                productRow.createCell(6).setCellValue(product.getCreatedBy());
//                productRow.createCell(7).setCellValue(product.getCreatedDate());
//                productRow.createCell(8).setCellValue(product.getLastModifiedBy());
//                productRow.createCell(9).setCellValue(product.getLastModifiedDate());
            }

            sheet.setAutoFilter(new CellRangeAddress(0, index, 0, 3));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error("Cannot generate Excel file!", e);
        }

        return new byte[0];
    }
}

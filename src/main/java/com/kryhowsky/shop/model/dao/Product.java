package com.kryhowsky.shop.model.dao;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable implements IdentifiedDataSerializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imagePath;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
        objectDataOutput.writeLong(id);
    }

    @Override
    public void readData(ObjectDataInput objectDataInput) throws IOException {
        id = objectDataInput.readLong();
    }
}

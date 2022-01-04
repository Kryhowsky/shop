package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Auditable;
import com.kryhowsky.shop.model.dto.AuditableDto;
import com.kryhowsky.shop.security.SecurityUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface AuditableMapper<T extends Auditable, U extends AuditableDto> {

    @AfterMapping
    default void mapAuditToDto(T auditable, @MappingTarget U auditableDto) {
        if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            auditableDto.setCreatedBy(null);
            auditableDto.setCreatedDate(null);
            auditableDto.setLastModifiedBy(null);
            auditableDto.setLastModifiedDate(null);
        }
    }
}

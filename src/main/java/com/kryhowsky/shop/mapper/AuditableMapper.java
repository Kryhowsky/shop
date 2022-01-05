package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Auditable;
import com.kryhowsky.shop.model.dto.AuditableDto;
import com.kryhowsky.shop.security.SecurityUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface AuditableMapper<T extends Auditable, U extends AuditableDto> {

    @AfterMapping
    default void mapAuditToDto(T auditable, @MappingTarget U.AuditableDtoBuilder<?, ?> auditableDto) {
        if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            auditableDto.createdBy(null);
            auditableDto.createdDate(null);
            auditableDto.lastModifiedBy(null);
            auditableDto.lastModifiedDate(null);
        }
    }
}

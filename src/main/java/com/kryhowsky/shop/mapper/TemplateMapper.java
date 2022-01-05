package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Template;
import com.kryhowsky.shop.model.dto.TemplateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    TemplateDto toDto(Template template);
    Template toDao(TemplateDto templateDto);

}

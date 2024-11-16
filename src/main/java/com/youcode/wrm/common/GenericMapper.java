package com.youcode.wrm.common;

import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Component;

@MapperConfig(componentModel = "spring")
@Component
public interface GenericMapper<Entity, Request, Response> {
    Response toDto(Entity entity);

    Entity toEntity(Request requestDto);
}
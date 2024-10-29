package com.example.Eventy.Mapper;

import com.example.Eventy.Dto.Event.EventDTO;
import com.example.Eventy.Model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO(Event event);
    Event toEntity(EventDTO eventDTO);
}



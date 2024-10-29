package com.example.Eventy.Dto.Event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private Long userId; // ID de l'utilisateur qui a créé l'événement
}

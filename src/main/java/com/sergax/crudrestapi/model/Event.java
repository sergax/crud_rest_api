package com.sergax.crudrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    private Long id;

    @Column(name = "event_name")
    private String eventName;
}

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
@Table(name = "file")
public class File {

    @Id
    private Long id;

    @Column (name = "file_name")
    private String name;
}

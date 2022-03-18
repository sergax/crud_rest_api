package com.sergax.crudrestapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long file_id;

    @Column(name = "file_name")
    private String fileName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private List<Event> eventList;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "event", joinColumns = @JoinColumn(name = "file_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private  List<User> usersList;
}

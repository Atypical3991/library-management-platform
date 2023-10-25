package com.example.library_management_platform.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="publisher")
public class Publisher  extends BaseEntity{

    @Column(name="name")
    private String name;

    @Column(name="books")
    private List<Long> book_ids;

}

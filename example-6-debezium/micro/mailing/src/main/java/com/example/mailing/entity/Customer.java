package com.example.mailing.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private int id;

    private String name;

    private String email;

}

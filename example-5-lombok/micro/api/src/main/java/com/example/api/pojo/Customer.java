package com.example.api.pojo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private int id;

    private String name;

    private String email;

}

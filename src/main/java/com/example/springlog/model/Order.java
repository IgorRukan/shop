package com.example.springlog.model;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "number_of_order")
    private String numberOfOrder;

}

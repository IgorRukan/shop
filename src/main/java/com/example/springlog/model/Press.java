package com.example.springlog.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="press")
public class Press {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private LocalDateTime localDateTime;
    @Column(name = "status")
    private String status;

    public Press(Long id, LocalDateTime localDateTime, String status) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.status = status;
    }

    public Press() {

    }
    @PrePersist
    private void init(){
        localDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

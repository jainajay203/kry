package se.kry.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "ENDPOINT")
public class Endpoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private String status;
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    @Column(nullable = false, updatable = true)
    private Timestamp updatedAt;
}

package se.kry.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EndpointDTO {

    private String name;
    private String url;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

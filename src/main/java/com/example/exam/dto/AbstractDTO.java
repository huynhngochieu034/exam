package com.example.exam.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO<T> implements Serializable {

    private static final long serialVersionUID = 7213600440729202783L;

    private Long id;
    private Date createdDate;
    private Date modifiedDate;
}
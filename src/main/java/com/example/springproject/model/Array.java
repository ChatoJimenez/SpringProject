package com.example.springproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;

import java.util.List;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Array {

    @Id
    @PrimaryKey
    private int id;
    @Column
    private String name;
    @Column
    private List<Integer> data;

}

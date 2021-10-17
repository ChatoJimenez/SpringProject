package com.example.springproject.repo;

import com.example.springproject.model.Array;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ArrayRepository extends CassandraRepository<Array, Integer> {
}

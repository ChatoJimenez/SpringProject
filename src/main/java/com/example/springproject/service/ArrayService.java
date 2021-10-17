package com.example.springproject.service;

import com.example.springproject.exception.ResourceNotFoundException;
import com.example.springproject.model.Array;
import com.example.springproject.repo.ArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArrayService {

    @Autowired
    ArrayRepository arrayRepository;

    public Optional<Integer> getFirstElement(int id) {
        return getArrayById(id).getData().stream()
                .findFirst();
    }

    public List<Integer> getDuplicates(int id){
        return getArrayById(id).getData().stream().
                filter(num -> Collections.frequency(getArrayById(id).getData(), num) > 1)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    public Optional<Integer> getLargestElement(int id){
        return getArrayById(id).getData().stream()
                .sorted()
                .reduce((n1, n2) -> n2);
    }

    public List<Integer> getAll(int id){
        return getArrayById(id).getData().stream().
                collect(Collectors.toList());
    }

    //CRUD
    public Array createArray(Array array){
        return arrayRepository.save(array);
    }

    public List<Array> getAllArrays(){
        return (List<Array>) arrayRepository.findAll();
    }

    public Array getArrayById(int id){
        Optional<Array> arrOp = arrayRepository.findById(id);
        if(arrOp.isPresent()){
            return arrOp.get();
        } else {
            throw new ResourceNotFoundException("Array not found with provided id: " + id);
        }
    }

    public void deleteArray(int id) {
        arrayRepository.delete(getArrayById(id));
    }

    public Array updateArray(int id, Array array) {
        Array updatedArray = getArrayById(id);
        updatedArray.setData(array.getData());
        arrayRepository.save(updatedArray);
        return updatedArray;
    }

}


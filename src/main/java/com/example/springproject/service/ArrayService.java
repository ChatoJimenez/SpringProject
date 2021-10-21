package com.example.springproject.service;

import com.example.springproject.exception.InternalServerError;
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
        if(arrayRepository.findById(array.getId()).isPresent()){
            throw new InternalServerError("Array with id: "+ array.getId() + " already exists");
        } else if (array.getData().isEmpty()) {
            throw new InternalServerError("Array with id: "+ array.getId() + " cannot be saved with null data");
        } else if (array.getId() <= 0) {
            throw new InternalServerError("The id needs to be greater than 0");
        } else{
            return arrayRepository.save(array);
        }
    }

    public List<Array> getAllArrays(){
        return arrayRepository.findAll();
    }

    public Array getArrayById(int id){
        if(id <= 0) {
            throw new InternalServerError("The id needs to be greater than 0");
        }
        Optional<Array> arrOp = arrayRepository.findById(id);
        if(arrOp.isPresent()){
            return arrOp.get();
        } else {
            throw new InternalServerError("Array not found with provided id: " + id);
        }
    }

    public void deleteArray(int id) {
        arrayRepository.delete(getArrayById(id));
    }

    public Array updateArray(int id, Array array) {
        if (array.getData().isEmpty()){
            throw new InternalServerError("Array with id: "+ id + " cannot be updated with null data");
        } else if (array.getId() <= 0) {
            throw new InternalServerError("The id needs to be greater than 0");
        } else {
            Array updatedArray = getArrayById(id);
            updatedArray.setData(array.getData());
            arrayRepository.save(updatedArray);
            return updatedArray;
        }

    }

}


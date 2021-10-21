package com.example.springproject.web;

import com.example.springproject.exception.ApiRequestException;
import com.example.springproject.model.Array;
import com.example.springproject.service.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping(path="/array")
public class ArrayController {

    @Autowired
    private final ArrayService arrayService;

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @GetMapping("/duplicates/{id}")
    public ResponseEntity<?> getDuplicates(@PathVariable int id){
        try{
            if(id <= 0) {
                return new ResponseEntity<>("The ID needs to be greater than 0", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (!arrayService.getArrayById(id).isPresent()){
                return new ResponseEntity<>("Array not found with provided id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return  ResponseEntity.ok("Id: "+ arrayService.getArrayById(id).get().getId() +"\nName: "+ arrayService.getArrayById(id).get().getName()
                        +"\nData: "+arrayService.getAll(id) + "\nDuplicates: " + arrayService.getDuplicates(id));
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/largest/{id}")
    public ResponseEntity<?> getLargest(@PathVariable int id){
        try{
            if(id <= 0) {
                return new ResponseEntity<>("The ID needs to be greater than 0", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (!arrayService.getArrayById(id).isPresent()){
                return new ResponseEntity<>("Array not found with provided id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return  ResponseEntity.ok("Id: "+ arrayService.getArrayById(id).get().getId() +"\nName: "+ arrayService.getArrayById(id).get().getName()
                        +"\nData: "+arrayService.getAll(id)+ "\nLargest element: " + arrayService.getLargestElement(id));
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/first/{id}")
    public ResponseEntity<?> getFirst(@PathVariable int id){
        try {
            if(id <= 0) {
                return new ResponseEntity<>("The ID needs to be greater than 0", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (!arrayService.getArrayById(id).isPresent()){
                return new ResponseEntity<>("Array not found with provided id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return  ResponseEntity.ok("Id: "+ arrayService.getArrayById(id).get().getId() +"\nName: "+ arrayService.getArrayById(id).get().getName()
                        +"\nData: "+arrayService.getAll(id)+ "\nFirst element: " + arrayService.getFirstElement(id));
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

    //CRUD
    @GetMapping("/arrays")
    public ResponseEntity<List<Array>> getArrays (){
        return new ResponseEntity<>(arrayService.getAllArrays(), HttpStatus.OK);
    }

    @GetMapping("/arrays/{id}")
    public ResponseEntity<?> getArray(@PathVariable int id){
        try{
            if(id <= 0) {
                return new ResponseEntity<>("The ID needs to be greater than 0", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (!arrayService.getArrayById(id).isPresent()){
                return new ResponseEntity<>("Array not found with provided id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(arrayService.getArrayById(id), HttpStatus.OK);
            }
        } catch (MethodArgumentTypeMismatchException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/arrays")
    public ResponseEntity<?> createArray(@RequestBody Array arr) {
        arrayService.createArray(arr);
        return new ResponseEntity<>("The array with id: " + arr.getId() +" was created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/arrays/{id}")
    public ResponseEntity<?> updateArray(@PathVariable int id, @RequestBody Array arr){
        try {
            arrayService.updateArray(id, arr);
            return new ResponseEntity<>("The array with id: " + id + " was updated successfully", HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/arrays/{id}")
    public ResponseEntity<?> deleteArray(@PathVariable int id) {
        try{
            arrayService.deleteArray(id);
            return new ResponseEntity<>("The array with id: " + id + " was deleted successfully", HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The ID is not an integer", HttpStatus.BAD_REQUEST);
        }

    }

}

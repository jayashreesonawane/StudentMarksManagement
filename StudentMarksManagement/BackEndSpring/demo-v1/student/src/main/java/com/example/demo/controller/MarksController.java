package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.MarksEntity;
import com.example.demo.service.MarksService;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class MarksController {

     @Autowired
     private MarksService marksService;
     
     @Autowired
     private UserService userService;

     @GetMapping("/marks")
     public List<MarksEntity> getAllMarks() {
         return marksService.getAllMarks();
     }
	 
     @GetMapping("/marks/{id}")
     public ResponseEntity<MarksEntity> getMarksById(@PathVariable Long id) {
         Optional<MarksEntity> marks = marksService.getMarksById(id);
         return marks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
     }

     @PostMapping("/marks")
     public ResponseEntity<MarksEntity> createMarks(@RequestBody MarksEntity marks) {
    	 System.out.println(marks.getSem1()+" "+marks.getSem2()+" "+marks.getSem3()+" "+marks.getTotal()+" "+marks.getId()+" "+marks.getUser());
         MarksEntity savedMarks = marksService.saveMarks(marks);
         return new ResponseEntity<>(savedMarks, HttpStatus.OK);
     }

     @PutMapping("/marks/{id}")
     public ResponseEntity<MarksEntity> updateMarks(@PathVariable Long id, @RequestBody MarksEntity marks) {
         System.out.println(marksService.getMarksById(id));
    	 if (!marksService.getMarksById(id).isPresent()) {
             return ResponseEntity.notFound().build();
         }
         marks.setId(id);
         MarksEntity updatedMarks = marksService.updateMarks(marks);
         return ResponseEntity.ok(updatedMarks);
     }

     //only delete the data in marks table
     @DeleteMapping("/marks/{id}")
     public ResponseEntity<Void> deleteMarks(@PathVariable Long id) {
         if (!marksService.getMarksById(id).isPresent()) {
             return ResponseEntity.notFound().build();
         }
         marksService.deleteMarks(id);
         return ResponseEntity.noContent().build();
     }
     
     
     //delete user in user table , his/her marks details from marks table
     @DeleteMapping("/user/delete/{id}")
     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
         if (!userService.getUserById(id).isPresent()) {
             return ResponseEntity.notFound().build();
         }
         userService.deleteUser(id);
         marksService.deleteMarks(id);
         return ResponseEntity.noContent().build();
     }
     
     // get user by id all marks
     @GetMapping("/user/{id}")
     public ResponseEntity<List<MarksEntity>> getAllMarksByUserId(@PathVariable Long id){
    	 return ResponseEntity.ok(marksService.fetchMarksByUserId(id));
     }
    
}
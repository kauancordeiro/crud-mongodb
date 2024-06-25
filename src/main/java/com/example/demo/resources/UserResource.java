package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;



    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById (@PathVariable String id){
        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(obj));

    }
    @PostMapping
    public ResponseEntity<Void> insert (@RequestBody UserDTO objDto){
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id){
        service.delete(id);

        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> insert (@PathVariable String id,@RequestBody UserDTO objDto){
        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);

        return ResponseEntity.noContent().build();

    }



}

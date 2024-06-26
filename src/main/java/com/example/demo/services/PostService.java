package com.example.demo.services;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dtos.UserDTO;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

    }

    public List<Post> findByTitle(String title){
        return repository.findByTitle(title);
    }

    public List<Post> fullSearch(String text, Date minDate,Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24* 60 * 60 * 1000);
        return repository.fullSearch(text,minDate,maxDate);

    }

}

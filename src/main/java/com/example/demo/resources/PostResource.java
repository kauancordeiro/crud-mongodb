package com.example.demo.resources;

import com.example.demo.domain.Post;
import com.example.demo.resources.util.URL;
import com.example.demo.services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);

    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text){

        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);

        return ResponseEntity.ok().body(posts);
    }
    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text",defaultValue = "") String text,
                                                 @RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
       Date min = URL.convertDate(minDate, new Date(0L));
       Date max = URL.convertDate(maxDate, new Date());
       List<Post> posts = service.fullSearch(text,min,max);

       return ResponseEntity.ok().body(posts);
    }
}

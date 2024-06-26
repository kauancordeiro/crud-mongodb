package com.example.demo.config;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown","maria@gmail.com" );
        User alex = new User(null, "Alex Green","alex@gmail.com" );
        User bob = new User(null, "Bob Grey","bob@gmail.com" );

        userRepository.save(maria);
        userRepository.save(alex);
        userRepository.save(bob);

        Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu Viagem", "Vou viajar para SP. Abraços.", maria);
        Post post2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia", "Acordei feliz hoje!",alex);
        Post post3 = new Post(null,sdf.parse("25/03/2018"),"Partiu mandela", "Hoje é aonde?",bob);

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);


    }
}

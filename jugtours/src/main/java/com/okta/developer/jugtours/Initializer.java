package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

/*    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }*/

    private final GroupRepository repository;
    private final PostRepository repository2;
    private final MemberRepository repository3;

    public Initializer(GroupRepository repository,PostRepository repository2,MemberRepository repository3) {
        this.repository = repository;
        this.repository2 = repository2;
        this.repository3 = repository3;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG","Yongsan JUG").forEach(name ->
                repository.save(new Group(name))
        );




        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);


        Stream.of("Denver post", "Utah post", "Seattle post",
                "Richmond post","Yongsan post").forEach(name ->
                repository2.save(new Post(name)));




        repository3.save(new Member("11134","wooju","1234","1Email@email"));
        repository3.save(new Member("22134","cosmos","1234","2Email@email"));
        repository3.save(new Member("33134","universe","1234","3Email@email"));

//        Stream.of("Denver member","Utah member").forEach(name ->
//                repository3.save(new Member(name)));
//


    }



}
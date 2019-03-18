package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
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

        Member wooju = repository3.findByName("wooju");
        MemberRole r1 = MemberRole.builder()
                .rno((long)1)
                .roleName("ADMIN")
                .build();
        wooju.setRoles(Collections.singletonList(r1));
        repository3.save(wooju);

        Member cosmos = repository3.findByName("cosmos");
        MemberRole r2 = MemberRole.builder()
                .rno((long) 2)
                .roleName("MASTER")
                .build();
        cosmos.setRoles(Collections.singletonList(r2));
        repository3.save(cosmos);

        Member universe = repository3.findByName("universe");
        MemberRole r3 = MemberRole.builder()
                .rno((long)3)
                .roleName("SUPERVISER")
                .build();
        universe.setRoles(Collections.singletonList(r3));
        repository3.save(universe);


//        Stream.of("Denver member","Utah member").forEach(name ->
//                repository3.save(new Member(name)));
//


    }



}
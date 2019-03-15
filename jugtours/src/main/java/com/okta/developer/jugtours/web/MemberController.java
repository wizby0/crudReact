package com.okta.developer.jugtours.web;

import com.okta.developer.jugtours.model.Member;
import com.okta.developer.jugtours.model.MemberRepository;
import com.okta.developer.jugtours.model.MemberRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
class MemberController {

//    @Autowired
//    MemberRepository memberRepository;


    private final Logger log = LoggerFactory.getLogger(PostController.class);
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    Collection<Member> members() {
        return memberRepository.findAll();
    }

    @GetMapping("/member/{id}")
    ResponseEntity<?> getMember(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/member")
    ResponseEntity<Member> createMember(@Valid @RequestBody Member member) throws URISyntaxException {
        log.info("Request to create member: {}", member);
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUpw(passwordEncoder.encode(member.getUpw()));
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));

        Member result = memberRepository.save(member);
        return ResponseEntity.created(new URI("/api/member/" + result.getId()))
                .body(result);

    }
    @PutMapping("/member")
    ResponseEntity<Member> updateMember(@Valid @RequestBody Member member) {
        log.info("Request to update member: {}", member);
        Member result = memberRepository.save(member);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        log.info("Request to delete member: {}", id);
        memberRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
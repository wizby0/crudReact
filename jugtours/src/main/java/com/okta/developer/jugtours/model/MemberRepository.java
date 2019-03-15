package com.okta.developer.jugtours.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}

//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
//public interface MemberRepository extends CrudRepository<Member, Long> {
//}
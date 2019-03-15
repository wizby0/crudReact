package com.okta.developer.jugtours.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "uid")
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;

    @NonNull
    private String name;

    @Column(nullable = false, length=200)
    private String upw;

    @Column(nullable = false, unique = true, length=50)
    private String uemail;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="uid")
    private List<MemberRole> roles;


    public Member(String uid, String name, String password, String email) {
        this.uid = uid;
        this.name = name;
        this.upw = password;
        this.uemail = email;
    }
}
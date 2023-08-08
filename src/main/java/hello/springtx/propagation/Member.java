package hello.springtx.propagation;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    public Member(){

    }

    public Member(String username) {
        this.username = username;
    }


}

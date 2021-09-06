package jpabook.jpashop.domain;


import jpabook.jpashop.domain.Item.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    //mapped By ="member"에 의해 매핑된 것일뿐이다.(읽기전용)
    private List<Order> orders = new ArrayList<>();





}

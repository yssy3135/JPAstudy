package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Item.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    //EnumType ORDINAL 는 숫자로 들어간다 나중에 타입이 추가되면 장애가 발생 절대사용하면 안된다.
    private DeliveryStatus status; // READY, COMP

}

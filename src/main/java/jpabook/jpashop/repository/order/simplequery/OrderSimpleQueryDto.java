package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Item.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name; //Lazy 초기화 :  영속성 컨텍스트가 멤버 아이디를 가지고 영속성 컨텍스트를 찾아본다 없으면 조회
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address; ; //Lazy 초기화


    public OrderSimpleQueryDto(Long orderId, String name,LocalDateTime orderDate,OrderStatus orderStatus,Address address) {
        this.orderId =orderId;
        this.name =name;
        this.orderDate =orderDate;
        this.orderStatus =orderStatus;
        this.address =address;
    }


}
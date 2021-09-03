package jpabook.jpashop.api;

import jpabook.jpashop.domain.Item.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){

        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress();//Lazy 강제 초기화

        }

        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){

        //ORDERS 2개
        //N + 1 -> 1 + 회원 N(2) + 배송 N(2)
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDto> result = orderRepository.findAllByString(new OrderSearch()).stream()
         .map(o -> new SimpleOrderDto(o))
         .collect(Collectors.toList());


        return result;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }



    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name; //Lazy 초기화 :  영속성 컨텍스트가 멤버 아이디를 가지고 영속성 컨텍스트를 찾아본다 없으면 조회
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address; ; //Lazy 초기화


        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }



}

/**
 * ORDER -> SQL 1번 -> 결과 주문수 2개
 *
 *
 */
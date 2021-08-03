package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    이거를
//    @PersistenceContext
//    private final EntityManager em;
//
//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

//    @RequiredArgsConstructor 이용 이렇게 줄일 수 있다.
//    스프링부트(스프링 data JPA) 라이브러리를 사용하면 @PersistenceContext 를 @Autowired로도 지원을 해준다. 그래서 가능
    private final EntityManager em;

    /**
     *     //매니저 팩토리를 직접 주입받을수있다( 거의 쓸일없다)
     *     @PersistenceUnit
     *     private EntityManagerFactory enf;
     */


    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }


    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }


}

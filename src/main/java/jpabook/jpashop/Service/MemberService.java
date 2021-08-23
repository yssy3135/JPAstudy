package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//읽기(조회) 전용이면 최적화를 한다.
@Transactional(readOnly = true)
//@AllArgsConstructor //필드를 가지고 생성자를 만들어준다.
@RequiredArgsConstructor //final이 붙은 필드만 생성자를 만들어준다
public class MemberService {

    //스프링이 스프링Bean에 등록되어있는 MemberRepository를 injection => 필드 인젝션

    // final을 쓰면  컴파일 시점에 체크를 할 수 있다. (추천)
    @Autowired
    private final MemberRepository memberRepository;

    //생성자 injection (권장)
    //생서자가 하나만 있을경우 @Autowired를 안써도 자동으로 인젝션 해준다.
/*
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

/*
@Autowired //setter 인젝션
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한명 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }




}

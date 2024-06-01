package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository;

    public MemoryMemberRepositoryTest(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
//        Assertions.assertEquals(member, null);
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();

        assertThat(result.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }
}

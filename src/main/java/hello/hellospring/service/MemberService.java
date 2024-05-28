package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

public class MemberService {
    private final MemberRepository memberRepository =new MemoryMemberRepository();

    public Long join(Member member) {
       validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findByOne(Long id) {
        return memberRepository.findById(id);
    }

}

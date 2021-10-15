package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

  MemberService memberService;

  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  void join() {
    Member member = new Member(1L, "memberA", Grade.VIP);

    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    assertThat(findMember).isEqualTo(member);
  }
}

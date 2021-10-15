package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
  RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP 10% 할인")
  void vip_o() {
    Member member = new Member(1L, "memberA", Grade.VIP);
    int discount = rateDiscountPolicy.discount(member, 10000);
    assertThat(discount).isEqualTo(1000);
  }

  @Test
  @DisplayName("VIP 아니면 할인 없음")
  void vip_x() {
    Member member = new Member(2L, "memberB", Grade.BASIC);
    int discount = rateDiscountPolicy.discount(member, 10000);
    assertThat(discount).isEqualTo(0);
  }
}

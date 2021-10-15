package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

  @Test
  void createOrder() {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    memoryMemberRepository.save(new Member(1L, "A", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new RateDiscountPolicy());
    Order order = orderService.createOrder(1L, "A", 10000);
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }
}

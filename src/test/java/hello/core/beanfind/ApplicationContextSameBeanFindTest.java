package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("동일한 타입이 둘 이상있으면, 오류 발생")
  void findBeanByTypeDuplicate() {
    assertThrows(NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("동일한 타입이 둘 이상있으면, 빈 이름을 지정하자")
  void findBeanByName() {
    MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
    assertThat(bean).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입을 모두 조회하기")
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for (String key: beansOfType.keySet()) {
      System.out.println("key = " + key);
      System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}

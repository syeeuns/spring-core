package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    PrototypeBean bean1 = context.getBean(PrototypeBean.class);
    PrototypeBean bean2 = context.getBean(PrototypeBean.class);
    Assertions.assertThat(bean1).isNotSameAs(bean2);
    context.close();
  }

  @Scope("prototype")
  static class PrototypeBean {
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}

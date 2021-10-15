package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    PrototypeBean bean1 = context.getBean(PrototypeBean.class);
    bean1.addCount();
    assertThat(bean1.getCount()).isEqualTo(1);

    PrototypeBean bean2 = context.getBean(PrototypeBean.class);
    bean2.addCount();
    assertThat(bean2.getCount()).isEqualTo(1);
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        ClientBean.class, PrototypeBean.class);
    ClientBean bean1 = context.getBean(ClientBean.class);
    int count1 = bean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean bean2 = context.getBean(ClientBean.class);
    int count2 = bean2.logic();
    assertThat(count2).isEqualTo(2);
  }

  static class ClientBean {
    private final PrototypeBean prototypeBean;

    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public int getCount() {
      return count;
    }

    public void addCount() {
      count++;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}

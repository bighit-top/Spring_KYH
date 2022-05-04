package hello.core_v1.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient { // implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " | message: " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //빈 생성주기 콜백 : Annotation - PostConstruct, PreDestroy. javax 지원 문법. 스프링 권장.
    // 외부 라이브러리 적용 못함.
    // 의존관계 주입 후 세팅된다.
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    // 빈 종료될 때
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

/*
    //빈 생성주기 콜백 : 빈 등록 초기화, 소멸 메서드
    // 외부 라이브러리 적용 가능
    // 의존관계 주입 후 세팅된다.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    // 빈 종료될 때
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
*/

/*
    //빈 생성주기 콜백 : 인터페이스 InitializingBean, DisposableBean
    // 의존관계 주입 후 세팅된다.
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
    // 빈 종료될 때
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
*/
}

package hello.core_v1.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //static 메서드로 올려놓고 이 메서드에서만 SingletonService 객체를 생성할 수 있도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //private 생성자를 만들어 외부에서 객체를 생성하지 못하도록 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}

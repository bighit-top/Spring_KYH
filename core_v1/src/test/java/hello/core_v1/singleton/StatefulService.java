package hello.core_v1.singleton;

public class StatefulService {

/*
    private int price; //상태를 유지하는 필드 : 공유되는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " | price = " + price);
        this.price = price; //문제 지점
    }

    public int getPrice() {
        return price;
    }
*/
    //무상태로 유지해야한다.
    public int order(String name, int price) {
        return price;
    }
}

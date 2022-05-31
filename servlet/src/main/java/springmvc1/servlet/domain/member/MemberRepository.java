package springmvc1.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 예제에서 동시성 문제 고려하지 않음
 * 실무에서 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    //static 으로 하나만 사용(싱글톤패턴)
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    //접근용 생성자
    public static MemberRepository getInstance() {
        return instance;
    }

    //방어용 생성자: 싱글톤이기 때문에 함부로 생성하지 못하도록함
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

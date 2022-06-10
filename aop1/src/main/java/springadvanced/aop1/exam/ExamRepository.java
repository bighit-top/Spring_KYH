package springadvanced.aop1.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import springadvanced.aop1.exam.annotation.Retry;
import springadvanced.aop1.exam.annotation.Trace;

@Repository
public class ExamRepository {

    private static int seq = 0;

    // 5번에 1번 실패하는 요청
    @Trace
    @Retry(4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외!");
        }
        return "ok";
    }
}

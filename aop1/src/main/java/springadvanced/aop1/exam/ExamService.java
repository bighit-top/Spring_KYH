package springadvanced.aop1.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springadvanced.aop1.exam.annotation.Retry;
import springadvanced.aop1.exam.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    @Retry(4)
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}

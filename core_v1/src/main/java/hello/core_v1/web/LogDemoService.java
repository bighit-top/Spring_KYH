package hello.core_v1.web;

import hello.core_v1.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger; //proxy

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}

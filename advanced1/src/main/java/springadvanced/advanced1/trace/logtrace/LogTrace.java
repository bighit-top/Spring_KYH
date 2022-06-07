package springadvanced.advanced1.trace.logtrace;

import springadvanced.advanced1.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}

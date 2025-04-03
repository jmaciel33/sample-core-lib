package io.jmaciel.traceability;

import io.jmaciel.async.AsyncMessage;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.MDC;


@Interceptor
@AsyncTracing
@Priority(0)
public class AsyncTracingAdapter {

    private void addHeaders(Message<AsyncMessage> message){
        MDC.put(ObservabilityHeaders.X_TRACE_ID.getHeaderName(), message.getPayload().getHeader().getTraceId());
        MDC.put(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName(),   message.getPayload().getHeader().getCorrelationId());
    }

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Object[] parameters = context.getParameters();

        if (parameters.length == 0 || !(parameters[0] instanceof Message)) {
            throw new IllegalArgumentException("Expected Message<AsyncMessage> as first parameter.");
        }

        Message<?> rawMessage = (Message<?>) parameters[0];

        if (!(rawMessage.getPayload() instanceof AsyncMessage)) {
            throw new IllegalArgumentException("Expected payload of type AsyncMessage.");
        }

        @SuppressWarnings("unchecked")
        Message<AsyncMessage> message = (Message<AsyncMessage>) rawMessage;

        addHeaders(message);

        return context.proceed();
    }
}

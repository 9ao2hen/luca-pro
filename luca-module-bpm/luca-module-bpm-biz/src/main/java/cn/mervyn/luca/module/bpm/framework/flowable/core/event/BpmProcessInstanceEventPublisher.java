package cn.mervyn.luca.module.bpm.framework.flowable.core.event;

import cn.mervyn.luca.module.bpm.event.BpmProcessInstanceStatusEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * {@link BpmProcessInstanceStatusEvent} 的生产者
 *
 * @author 9ao2hen
 */
@AllArgsConstructor
@Validated
public class BpmProcessInstanceEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void sendProcessInstanceResultEvent(@Valid BpmProcessInstanceStatusEvent event) {
        publisher.publishEvent(event);
    }

}

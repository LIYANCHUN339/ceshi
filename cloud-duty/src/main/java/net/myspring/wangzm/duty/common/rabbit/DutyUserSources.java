package net.myspring.wangzm.duty.common.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface DutyUserSources {
    String DUTY_USER_INPUT = "dutyUserInput";

    String DUTY_USER_OUTPUT = "dutyUserOutput";

    @Input(DUTY_USER_INPUT)
    SubscribableChannel userInput();

    @Output(DUTY_USER_OUTPUT)
    SubscribableChannel dutyUserOutput();
}

package net.myspring.wangzm.basic.common.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserSource {
    String USER_OUTPUT = "userOutput";

    String USER_INPUT = "userInput";

    String DLX_INPUT = "dlxInput";

    @Output(USER_OUTPUT)
    MessageChannel userOutput();

    @Input(USER_INPUT)
    MessageChannel userInput();

    @Input(DLX_INPUT)
    MessageChannel dlxInput();
}

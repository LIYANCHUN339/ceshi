package net.myspring.wangzm.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.myspring.wangzm.util.time.LocalDateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by wangzm on 2017 2017-09-10.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        LocalDateTime localDateTime = LocalDateTimeUtils.parse(p.getText());
        return localDateTime;
    }
}

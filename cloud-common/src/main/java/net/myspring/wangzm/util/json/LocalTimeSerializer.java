package net.myspring.wangzm.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.myspring.wangzm.util.time.LocalTimeUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by wangzm on 2017 2017-08-20.
 */
public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String string = LocalTimeUtils.format(value);
        gen.writeString(string);
    }
}

package spacedatahub.converter;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    public LocalDateTimeConverter() {
    }

    @Override
    public LocalDateTime convert(String s) {

        return LocalDateTime.parse(s);
    }
}

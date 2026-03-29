package prs.fltoshi.db.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.Converter;
import org.jooq.JSONB;

public class JsonbConverter<T> implements Converter<JSONB, T> {
    private final Class<T> type;
    private final ObjectMapper mapper = new ObjectMapper(); // Можно внедрить общий

    public JsonbConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public T from(JSONB databaseObject) {
        if (databaseObject == null || databaseObject.data() == null) {
            return null;
        }
        try {
            return mapper.readValue(databaseObject.data(), type);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            // Ловим конкретно ошибку парсинга JSON
            throw new RuntimeException("Ошибка парсинга JSONB для типа " + type.getName(), e);
        } catch (java.io.IOException e) {
            // Ловим проблемы с вводом-выводом
            throw new RuntimeException("Ошибка чтения данных JSONB", e);
        }
    }

    @Override
    public JSONB to(T userObject) {
        if (userObject == null) return null;
        try {
            return JSONB.valueOf(mapper.writeValueAsString(userObject));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сериализации в JSONB", e);
        }
    }

    @Override
    public Class<JSONB> fromType() { return JSONB.class; }

    @Override
    public Class<T> toType() { return type; }
}
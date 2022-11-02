package crom.rental.common.utils;

import com.google.gson.Gson;
import crom.rental.common.entity.BaseEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static <T extends BaseEntity> T toEntity(String json, Class<T> clazz) {
        if (json.startsWith("\"")) {
            json = json.substring(1);
        }
        if (json.endsWith("\"")) {
            json = json.substring(0, json.lastIndexOf("\""));
        }
        json = json.replaceAll("\\\\", "");
        return new Gson().fromJson(json, clazz);
    }

}

package com.github.Duankan.redis;

import com.github.Duankan.utils.SerializerUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author duankang
 * @date 2019-06-09
 * @class 自己封装一个redis序列化工具
 * @// FIXME: 2019/6/9  每天学习一点
 */
@Component
public class MyRedisSerializer implements RedisSerializer<Object> {
    private static Class clazz = null;

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return SerializerUtil.EMPTY_ARRAY;
        } else {
            clazz = t.getClass();//class
            try {
                //如果对象是strig对象
                if (t instanceof String) {
                    return SerializerUtil.serializer_jdk(t);
                }
                //如果对象是list集合
                else if (t instanceof List) {
                }
                //如果对象是map集合
                else if (t instanceof Map) {
                }
                //如果对象是set集合
                else if (t instanceof Set) {
                }
                //否则对象是自定义的对象
                else {
//                    String str = MyRedisSerializerUtil.serializationObject_kryo(t);
//                    return str.getBytes();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        } else {
            try {
//                return MyRedisSerializerUtil.deserializationObject(
//                        new String(new Base64().encode(bytes)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

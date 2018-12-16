package com.github.Duankan.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.codec.binary.Base64;
import java.io.*;
import java.util.*;

/**
 * redis的序列化与反序列化工具：
 * 我主要写三种:
 * 1.jdk序列化与反序列化
 * 2.protostuff序列化与反序列化
 * 3.kryo序列化与反序列化(线程不安全)
 */
public class SerializerUtil {
    //jdk序列化 object需要implements Serializable
    public static byte[] serializer_jdk(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);//包装模式？
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //jdk反序列化
    public static Object unSerializer_jdk(byte[] bytes) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //protostuff序列化
    public static <T> byte[] serializer_protostuff(T o) {
        RuntimeSchema<T> schema = RuntimeSchema.createFrom((Class<T>) o.getClass());
//        Schema schema = RuntimeSchema.getSchema(o.getClass());
        return ProtobufIOUtil.toByteArray(o, schema, LinkedBuffer.allocate(256));
    }
    //protostuff反序列化
    public static <T> T unSerializer_protostuff(byte[] bytes, Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
            RuntimeSchema<T> schema = RuntimeSchema.createFrom((Class<T>) obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
    //kryo序列化object
    public static <T extends Serializable> String serializationObject_kryo(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());//注册序列化类型
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
    //kryo反序列化object
    public static <T extends Serializable> T deserializationObject(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }
    //kryo序列化list
    public static <T extends Serializable> String serializationList(List<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();//注册序列化类型
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
    //kryo反序列化list
    public static <T extends Serializable> List<T> deserializationList(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }
    //kryo序列化Map
    public static <T extends Serializable> String serializationMap(Map<String, T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
    //kryo反序列化Map
    public static <T extends Serializable> Map<String, T> deserializationMap(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Map<String, T>) kryo.readObject(input, HashMap.class,
                serializer);
    }
    //kryo序列化Set
    public static <T extends Serializable> String serializationSet(Set<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
    //kryo反序列化Set
    public static <T extends Serializable> Set<T> deserializationSet(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Set<T>) kryo.readObject(input, HashSet.class, serializer);
    }
}

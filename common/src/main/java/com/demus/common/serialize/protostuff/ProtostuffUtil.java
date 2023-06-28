package com.demus.common.serialize.protostuff;

import com.demus.common.serialize.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 12:17
 */
public class ProtostuffUtil implements Serializer {

    public  <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("obj cannot be null");
        }
        RuntimeSchema<T> schema = RuntimeSchema.createFrom((Class<T>) obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化对象
     *
     * @param data  序列化后的字节数组
     * @param clazz 要反序列化的对象类型
     * @return 反序列化后的对象
     */
    public  <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("data cannot be null or empty");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

}
class Person{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("demus");
        Serializer serializer = new ProtostuffUtil();
        byte[] serialize = serializer.serialize(person);
        Person deserialize = serializer.deserialize(serialize, Person.class);
        System.out.print(deserialize.getName());
    }
}

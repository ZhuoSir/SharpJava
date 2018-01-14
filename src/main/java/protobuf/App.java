package protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.protobufbean.PersonEntity;

/**
 *
 * Created by sunny on 18-1-14.
 */
public class App {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
        builder.setId(1);
        builder.setName("chen");
        builder.setEmail("12121@qq.com");
        PersonEntity.Person person = builder.build();
        System.out.println(person.toString());

        System.out.println("===========Person Byte==========");
        for(byte b : person.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        PersonEntity.Person p2 = PersonEntity.Person.parseFrom(byteArray);
        System.out.println("after :" +p2.toString());
    }

}

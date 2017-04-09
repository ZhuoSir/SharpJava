package lombok.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * Created by sunny-chen on 2017/4/9.
 */
@Data
public class Demo implements Serializable {

    private int number;

    private String name;

    private String address;

}

package reflect;

/**
 * Created by sunny-chen on 16/12/6.
 */
public class SubjectImpl implements Subject {

    public String sayHello(String someOne, String word) {
        return someOne + " says: " + word;
    }
}

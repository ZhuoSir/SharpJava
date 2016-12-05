package reflect;

/**
 * Created by sunny on 2016/12/5.
 */
public class DemoClass
        extends DemoAbtract implements DemoInterface {

    private int result;

    private String name;

    public String title;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DemoClass() {
    }

    public DemoClass(int result) {
        this.result = result;
    }

    public DemoClass(String name) {
        this.name = name;
    }

    public DemoClass(int result, String name) {
        this.result = result;
        this.name = name;
    }

    public DemoClass(int result, String name, String title) {
        this.result = result;
        this.name = name;
        this.title = title;
    }

    public double add(int a, int b) {
        return a + b;
    }

    public double substract(int a, int b) {
        return a - b;
    }

    public double multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        return a / b;
    }

    public void sayHello() {
        System.out.println("success...");
    }

    @Override
    public String toString() {
        return "result :" + result + ", name" + name + ", result" + result;
    }
}

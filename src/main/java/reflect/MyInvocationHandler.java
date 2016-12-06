package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by sunny-chen on 16/12/6.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object object = null;

    public Object bind(Object obj) {
        this.object = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object temp = method.invoke(this.object,args);
        return temp;
    }
}

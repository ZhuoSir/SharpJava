package thread.MianShiTi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 *
 * Created by sunny on 2017/5/17.
 */
public class Two {

    private boolean flag = false;

    Lock lock = new ReentrantLock();


}

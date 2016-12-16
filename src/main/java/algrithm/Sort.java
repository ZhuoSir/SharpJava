package algrithm;

/**
 * Created by sunny-chen on 16/12/15.
 */
public class Sort {

    private static int[] array = {5, 2, 4, 9, 7, 11, 10, 6, 21, 8};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        bubbleSort(array);
//        quickSort(array, 0, array.length-1);
//        selectSort(array);
//        insertSelect(array);
        mergeSort(array, 0, array.length - 1);
        print(array);
        System.out.println();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 冒泡法排序<br/>
     * <p>
     * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     *
     * @param array 需要排序的整型数组
     */
    private static void bubbleSort(int[] array) {
        int temp;
        int endNum = array.length - 1;
        for (int i = 0; i < endNum; i++) {
            for (int j = 0; j < (endNum) - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序<br/>
     * <ul>
     * <li>从数列中挑出一个元素，称为“基准”</li>
     * <li>重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割之后，
     * 该基准是它的最后位置。这个称为分割（partition）操作。</li>
     * <li>递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。</li>
     * </ul>
     *
     * @param array
     * @param start
     * @param end
     */
    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int base = array[start];
            int temp;
            int i = start;
            int j = end;

            do {
                while (array[i] < base && i < end) {
                    i++;
                }
                while (array[j] > base && j > start) {
                    j--;
                }
                if (i <= j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);

            if (start < j) {
                quickSort(array, start, j);
            }
            if (end > i) {
                quickSort(array, i, end);
            }
        }
    }

    /**
     * 选择排序<br/>
     * <li>在未排序序列中找到最小元素，存放到排序序列的起始位置</li>
     * <li>再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。</li>
     * <li>以此类推，直到所有元素均排序完毕。</li>
     *
     * @param array
     */
    private static void selectSort(int[] array) {
        int temp, length = array.length;
        for (int i = 0; i < length; i++) {
            int k = i;
            for (int j = length - 1; j > i; j--) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            temp = array[k];
            array[k] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 插入排序<br/>
     * <ul>
     * <li>从第一个元素开始，该元素可以认为已经被排序</li>
     * <li>取出下一个元素，在已经排序的元素序列中从后向前扫描</li>
     * <li>如果该元素（已排序）大于新元素，将该元素移到下一位置</li>
     * <li>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>
     * <li>将新元素插入到该位置中</li>
     * <li>重复步骤2</li>
     * </ul>
     *
     * @param array
     */
    private static void insertSelect(int[] array) {
        int length = array.length, temp, j;
        for (int i = 1; i < length; i++) {
            temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }

    /**
     * 归并排序<br/>
     * <ul>
     * <li>申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>
     * <li>设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>
     * <li>比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置</li>
     * <li>重复步骤3直到某一指针达到序列尾</li>
     * <li>将另一序列剩下的所有元素直接复制到合并序列尾</li>
     * </ul>
     *
     * @param array
     */
    private static void mergeSort(int[] array, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(array, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(array, i, i + (s - 1), right);
        }
    }

    /**
     * 归并算法实现
     *
     * @param array
     * @param low 低位
     * @param mid 中位
     * @param high 高位
     */
    private static void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        int[] tmpArray = new int[high - low + 1];

        while (i <= mid && j <= high) {
            if (array[i] <= array[j]) {
                tmpArray[k] = array[i];
                i++;
            } else {
                tmpArray[k] = array[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            tmpArray[k] = array[i];
            i++;
            k++;
        }
        while (j <= high) {
            tmpArray[k] = array[j];
            j++;
            k++;
        }

        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = tmpArray[k];
        }
    }
}

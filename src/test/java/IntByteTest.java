import org.junit.Test;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午2:32
 **/
public class IntByteTest {

    @Test
    public void int2byte(){
        int n = 3;
        byte[] intArray = new byte[4];
        intArray[0] = (byte) (0xff & n >> 24);
        intArray[1] = (byte) (0xff & n >> 16);
        intArray[2] = (byte) (0xff & n >> 8);
        intArray[3] = (byte) (0xff & n);

        System.out.println(intArray);
    }
}

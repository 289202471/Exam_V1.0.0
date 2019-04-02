import java.util.Random;

public class PaperControllerTest {
    public static void main(String[] args) {
        //在10到20之间产生20个随机数；

        for(int i=0;;i++)		 //控制产生的随机数的个数
        {
            Random random=new Random();	 //使用Random函数产生随机数；
            int b=(int)(Math.random()*670);//[0,10)
            if(b==669)
            System.out.print(" b:"+b);         //输出随机数；
        }
    }
}

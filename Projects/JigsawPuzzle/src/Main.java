import com.xqchai.ui.GameJFrame;
import com.xqchai.ui.LoginJFrame;
import com.xqchai.user.UserInformation;

public class Main {
    /*程序主入口
    **/
    public static void main(String[] args) {
        //想开启谁的界面就创建谁的对象就可以了

        //加载数据列表
        new UserInformation();
        UserInformation.initUser();
        //打开登录界面
        new LoginJFrame();

    }
}
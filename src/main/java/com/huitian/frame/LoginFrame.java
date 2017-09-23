package com.huitian.frame;

import com.bulenkov.iconloader.util.StringUtil;
import com.huitian.constants.ConstantsUI;
import com.huitian.constants.ConstantsURL;
import com.huitian.service.HttpService;
import com.huitian.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 *
 * Created by dingjianelei on 2017/9/20.
 *
 */
public class LoginFrame {
    private JPanel panel1;
    private JLabel apppanel;
    private JPanel imgpanel;
    private JPanel loginpanel;
    private JButton loginButton;
    private JTextField usernametext;
    private JPasswordField passwordtext;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel logininfopanel;
    private JPanel footerpanel;
    private JPanel wechaticon;
    private JPanel authorpanel;
    private static JFrame frame;

    public LoginFrame() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 检查是否是空
                 */
                if (checkBlank()) {
                    JOptionPane.showMessageDialog(frame,
                            "用户名或者密码不能为空~", "Sorry~", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //发送请求验证身份了
                if (checkInfo()) {
                    JOptionPane.showMessageDialog(frame,
                            "用户名或者密码错误，请重试~", "Sorry~", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //如果断网的话给他提示
                if (!checkInternet()) {
                    JOptionPane.showMessageDialog(frame,
                            "请检查网络状态~", "Sorry~", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //关闭当前窗口，并不是真正关闭，释放资源了，后期需要查资料
                frame.dispose();
                //创建等待登录窗体...
                final JFrame frame = new JFrame(ConstantsUI.APP_NAME + " " + ConstantsUI.APP_VERSION);
                frame.setIconImage(ConstantsUI.IMAGE_ICON);//设置小图标
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
                frame.setBounds((int) (screenSize.width * 0.4), (int) (screenSize.height * 0.2), (int) (screenSize.width * 0.26),
                        (int) (screenSize.height * 0.55));

                Dimension preferSize = new Dimension((int) (screenSize.width * 0.26),
                        (int) (screenSize.height * 0.55));
                frame.setPreferredSize(preferSize);
                WaitFrame waitFrame = new WaitFrame();
                //等待登录界面添加点击事件
                waitFrame.getLogincencelButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        initLoginFrame();

                    }
                });
                /**
                 * 添加事件
                 */
                waitFrame.getTestButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        initTerminalForTest();
                    }
                });
                frame.setContentPane(waitFrame.getWaitpanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);


            }
        });
    }

    /**
     * 为测试用跳转页面    先本地测试 正常之后 将它废除
     */
    public static void initTerminalForTest() {
        MainForm mainForm = new MainForm();
        JFrame frame = new JFrame(ConstantsUI.APP_NAME + " " + ConstantsUI.APP_VERSION);
        frame.setIconImage(ConstantsUI.IMAGE_ICON);//设置小图标
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        frame.setBounds((int) (screenSize.width * 0.25), (int) (screenSize.height * 0.2), (int) (screenSize.width * 0.5),
                (int) (screenSize.height * 0.7));

        Dimension preferSize = new Dimension((int) (screenSize.width * 0.5),
                (int) (screenSize.height * 0.7));
        frame.setPreferredSize(preferSize);
        frame.setContentPane(mainForm.getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 检查网络状态
     *
     * @return
     */
    private boolean checkInternet() {
        return HttpService.checkInternet();
    }


    /**
     * 检查密码或者用户名为空 true 为空  false 不为空
     *
     * @return
     */
    private boolean checkBlank() {
        String username = usernametext.getText();
        String password = new String(passwordtext.getPassword());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return true;
        }
        return false;

    }

    /**
     * 发送http请求验证身份是否是系统终端
     *
     * @return
     */
    private boolean checkInfo() {
        String username = usernametext.getText();
        String password = new String(passwordtext.getPassword());
        return HttpService.checkNameAndPass(username, password);
    }

    public static void main(String[] args) {
        initLoginFrame();
    }

    /**
     * 初始化登录界面
     */
    public static void initLoginFrame() {
        Init.initTheme();
        Init.initGlobalFont();  //统一设置字体
        frame = new JFrame("绘天科技-登录  " + ConstantsUI.APP_VERSION);
        frame.setIconImage(ConstantsUI.IMAGE_ICON);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        frame.setBounds((int) (screenSize.width * 0.35), (int) (screenSize.height * 0.2), (int) (screenSize.width * 0.3),
                (int) (screenSize.height * 0.5));

        Dimension preferSize = new Dimension((int) (screenSize.width * 0.3),
                (int) (screenSize.height * 0.5));
        frame.setPreferredSize(preferSize);
        frame.setContentPane(new LoginFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

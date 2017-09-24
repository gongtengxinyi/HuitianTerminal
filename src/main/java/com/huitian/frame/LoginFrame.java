package com.huitian.frame;

import com.huitian.constants.ConstantsUI;
import com.huitian.constants.HttpResponseStatus;
import com.huitian.constants.HuitianResult;
import com.huitian.service.HttpService;
import com.huitian.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dingjianelei on 2017/9/20.
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
        //登录button添加登录事件
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
                initWaitFrame(frame);
                //如果断网的话给他提示
                if (!checkInternet()) {
                    JOptionPane.showMessageDialog(frame,
                            "请检查网络状态~", "Sorry~", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //远程发送请求检查用户名或者密码对不对
                String data = checkInfo();
                HuitianResult huitianResult = JsonUtils.jsonToPojo(data, HuitianResult.class);
                //密码错误
                if (huitianResult.getStatus().equals(HttpResponseStatus.NO_USER)) {
                    JOptionPane.showMessageDialog(frame,
                            huitianResult.getMsg(), "Sorry~", JOptionPane.WARNING_MESSAGE);
                    frame.dispose();
                    initLoginFrame();

                    return;
                }
                //服务器异常
                if (huitianResult.getStatus().equals(HttpResponseStatus.ERROR)) {

                    JOptionPane.showMessageDialog(frame,
                            huitianResult.getMsg(), "Sorry~", JOptionPane.WARNING_MESSAGE);
                    frame.dispose();
                    initLoginFrame();
                    return;
                }
                //正确登录
                if (huitianResult.getStatus().equals(HttpResponseStatus.SUCCESS)) {
                    frame.dispose();
                    initMainFrame(huitianResult);

                }

            }
        });
    }

    private void initWaitFrame(JFrame iframe) {
        try {
            //关闭当前窗口，并不是真正关闭，释放资源了，后期需要查资料
            iframe.dispose();
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
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录成功之后，服务器返回centreaccountid作为websocket推送的账号id
     */

    public static void initMainFrame(HuitianResult huitianResult) {
        MainForm mainForm = new MainForm(huitianResult);
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
    private String checkInfo() {
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

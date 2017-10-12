package com.huitian.frame;

import com.huitian.constants.*;
import com.huitian.dto.IndentDto;
import com.huitian.pojo.ChatMessage;
import com.huitian.service.HttpService;
import com.huitian.util.AppendToFile;
import com.huitian.util.FileUtil;
import com.huitian.util.JsonUtils;
import com.huitian.util.ReadFromFile;
import org.apache.commons.lang3.StringUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.huitian.frame.MainWindow.frame;

/**
 * 主面板的各种字段定义222
 * Created by dingjianlei on 2017/9/21.
 */
public class MainForm {
    private HuitianResult huitianResult;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable workingtable;
    private JPanel toppanel;
    private JScrollPane jscrollpanel;
    private JPanel bottompanel;
    private JPanel workeringpanel;
    private JPanel noworkingpanel;
    private JPanel waitsendgoodspanel;
    private JPanel historyindentpanel;
    private JPanel systemsettingpanel;
    private JPanel pagepanel;
    private JButton startButton;
    private JButton stopButton;
    private JPanel noworkingtoppanel;
    private JButton shouyeButton;
    private JButton gotobutton;
    private JButton lastButton;
    private JButton beforeButton;
    private JButton afterButton;
    private JTextField notextField;
    private JTable noworkingtable;
    private JLabel historylabel;
    private JButton hshouyeButton;
    private JButton hafterButton;
    private JButton hlastButton;
    private JButton hbeforeButton;
    private JButton gotoButton;
    private JTextField historytextField;
    private JLabel htotallabel;
    private JLabel nototallabel;
    private JTable table1;
    private JLabel settinglabel;
    private JTextField orgnalpasswordtext;
    private JTextField newpasswordtext;
    private JButton saveuserButton;
    private JTextField confirmpasswordtext;
    private JTextField motifytext;
    private JButton savefileButton;
    private JLabel filepathlabel;
    private JLabel orignalpasswordlabel;
    private JLabel newpasswordlabel;
    private JLabel confirmpasseordlabel;
    private JLabel motify;
    private JPanel morenlabel;
    private JTabbedPane foreightindenttab;
    private JTable finishtable;
    private JTable sendtable;
    private JPanel finishnosend;
    private JPanel sendprintindent;
    private JPanel finishjpanel;
    private JPanel finishbottom;
    private JScrollPane finishscrollpanel;
    private JPanel printpanel;
    private JLabel sendprintlabel;
    private JPanel sendbottompanel;
    private JScrollPane sendscrollpane;
    private JButton finishshouyebutton;
    private JButton finishgotoButton;
    private JButton finishlastButton;
    private JButton finishbeforeButton;
    private JButton finishafterButton;
    private JTextField finishtext;
    private JButton sendshouyeButton;
    private JButton sendafterButton;
    private JButton sendlastButton;
    private JButton sendbeforeButton;
    private JButton sendgotoButton;
    private JTextField aftertext;
    private JLabel finishlabelall;
    private JLabel finishtotalindent;
    private JLabel sendtotallabel;
    private JLabel sendtotalindent;
    private JScrollPane noworkeringscrollpane;
    private JTable table2;
    private JTable table3;
    private WebSocketClient webSocketClient;
    private ScheduledExecutorService service1 = null;


    //构造函数用来传值链接websocket
    public MainForm(HuitianResult huitianResult) {
        this.huitianResult = huitianResult;
        try {
            String id = "";
            if (huitianResult != null) {
                LinkedHashMap LinkedHashMap = (LinkedHashMap) huitianResult.getData();
                if (LinkedHashMap != null) {
                    id = (String) LinkedHashMap.get("id");
                    CacheConstants.centerAccountId = id;
                }
            }
            webSocketClient = connectWebsocket(id);
            webSocketClient.connect();
            initListenerAndOther();
            checkConnectOnline();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "未连接到服务器websocket，无法接收订单，请检查网络链接~,重新启动软件尝试", "Sorry~", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public MainForm() {
        initListenerAndOther();

    }

    /**
     * 检查心跳，如果断开重新链接
     */
    public void checkConnectOnline() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    ChatMessage chatMessage = createChatMessage(EnumMessageMode.HEART_BREAK.name());
                    String lowDpiKey = JsonUtils.objectToJson(chatMessage);
                    webSocketClient.send(lowDpiKey);
                } catch (Exception e) {
                    JOptionPane op = new JOptionPane("网络状态不稳定，正在进行重新链接~", JOptionPane.INFORMATION_MESSAGE);
                    final JDialog dialog = op.createDialog("Sorry~");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setAlwaysOnTop(true);
                    dialog.setModal(false);
                    dialog.setVisible(true);
                    reconnectChat();
                    // 创建一个新计时器
                    Timer timer = new Timer();

                    // 30秒 后执行该任务
                    timer.schedule(new TimerTask() {
                        public void run() {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    }, 3000);
//                    JOptionPane.showMessageDialog(frame,
//                            "网络状态不稳定，正在进行重新链接~", "Sorry~", JOptionPane.CLOSED_OPTION);


                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * 重连websocket
     */
    public void reconnectChat() {
        try {
            webSocketClient = connectWebsocket(CacheConstants.centerAccountId);
            webSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化各种监听事件和初始化工作
     */
    private void initListenerAndOther() {
        // 设置滚动条速度
        noworkeringscrollpane.getVerticalScrollBar().setUnitIncrement(15);
        noworkeringscrollpane.getVerticalScrollBar().setDoubleBuffered(true);

//        //未加工首页按钮添加事件
//        shouyeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //根据传入的类型进行动态加载Jtable
//                try {
//                    String data = clearAndGetData(new Filter(EnumFilterTab.NO_WORKING_TAB.name(), EnumFilterButtonType.SHOUYE_BUTTON.name()));
//                    noWorkingTabTableFilldata(data);//
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
        //点击开始按加工钮触发的事件
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatMessage chatMessage = createChatMessage(EnumMessageMode.START_MACHINE.name());
                String lowDpiKey = JsonUtils.objectToJson(chatMessage);
                webSocketClient.send(lowDpiKey);
                startThreadFindSingleWork();
                JOptionPane.showMessageDialog(frame,
                        "开始加工！", "绘天科技", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
        //点击停止加工按钮触发的事件
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ChatMessage chatMessage = createChatMessage(EnumMessageMode.STOP_MACHINE.name());
//                String lowDpiKey = JsonUtils.objectToJson(chatMessage);
//                webSocketClient.send(lowDpiKey);

                try {
                    if (service1 != null)
                        service1.shutdown();
                    JOptionPane.showMessageDialog(frame,
                            "停止加工！", "Sorry~", JOptionPane.INFORMATION_MESSAGE);
                    drawNoIndentView();
                    return;
                } catch (Exception e1) {

                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            "停止加工服务异常，请重启软件~~", "Sorry~", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        });
        //保存密码点击事件
        saveuserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //true 是空 false  不是空
                if (checkResetPasswordEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "密码不能为空~~", "Sorry~", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!checkResetPasswordIfSame()) {
                    JOptionPane.showMessageDialog(frame,
                            "您输入的两次密码不一致，请重新确认输入~~", "Sorry~", JOptionPane.INFORMATION_MESSAGE);
                    newpasswordtext.setText("");
                    confirmpasswordtext.setText("");
                    return;
                }
                motifyPassword();


            }
        });
        //保存文件点击事件
        savefileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saveFilePath();
            }
        });
        //添加 tab 鼠标 点击监听事件
        tabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) { //响应鼠标点击事件
                p(e);
            }

            private void p(MouseEvent e) {
                for (int i = 0; i < tabbedPane1.getTabCount(); i++) {
                    Rectangle rect = tabbedPane1.getBoundsAt(i); //拿到标签的边界
                    if (rect.contains(e.getX(), e.getY())) { //判断是否点在边界内
                        System.out.println("在选项卡" + i + "上点击了鼠标！"); //响应 简单输出一句话
                        clickOnTab(i + 1);
                    }
                }
            }
        });
    }

    /**
     * 点击开始加工按钮的时候开启一个线程进行扫描文件找到可以加工的信号时进行加工
     */

    private void startThreadFindSingleWork() {
        Runnable runnable1 = new Runnable() {
            public void run() {
                String filename = "";
                if (StringUtils.isBlank(Init.configer.getProps(CacheConstants.file_path))) {
                    filename = CacheConstants.file_path_dafault;
                } else {
                    filename = Init.configer.getProps(CacheConstants.file_path);
                }
                filename = filename + "/" + CacheConstants.judge_file;
                ReadFromFile.readFileByLines(filename, webSocketClient);
            }
        };
        service1 = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service1.scheduleAtFixedRate(runnable1, 1, 1, TimeUnit.SECONDS);
    }

    private void initPortalNoWorkingTable(ChatMessage chatMessage) {
        // 模板消息Data表
        String[] headerNames = {"属性", "取值"};
        java.util.List<String[]> list = new ArrayList<String[]>();
        if (chatMessage != null) {
            String indentDto = chatMessage.getMessage();
            IndentDto indent = JsonUtils.jsonToPojo(indentDto, IndentDto.class);
            try {
                if (indent != null) {
                    String name = indent.getReceiverName();
                    String mobile = indent.getReceiverMobile();
                    String indentId = indent.getIndentId();
                    String picType = indent.getPicType();
                    String picCode = indent.getPicCode();
                    String piCount = indent.getPicCount();
                    String[] str = {"姓名", name};
                    String[] str1 = {"手机号", mobile};
                    String[] str2 = {"订单编号", indentId};
                    String[] str3 = {"图案类型", picType};
                    String[] str4 = {"图案编码", picCode};
                    String[] str5 = {"图案数量", piCount};
                    list.add(str);
                    list.add(str1);
                    list.add(str2);
                    list.add(str3);
                    list.add(str4);
                    list.add(str5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Object[][] cellData = new String[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // 设置列宽
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int width = screenSize.width / 2;
        DefaultTableModel model1 = new DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setModel(model1);
        table2.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        table2.getColumnModel().getColumn(0).setPreferredWidth(width - 30);
        table2.getColumnModel().getColumn(0).setMaxWidth(width - 30);
        table2.getColumnModel().getColumn(1).setPreferredWidth(width - 30);
        table2.getColumnModel().getColumn(1).setMaxWidth(width - 30);
        table2.setRowHeight(40);
        table2.setRowMargin(10);
        table2.updateUI();
    }

    private void noWorkingTabTableFilldata(String data) {


        // 模板消息Data表
        String[] headerNames = {"姓名", "手机号", "订单号", "详细"};
        java.util.List<String[]> list = makeListForTest();
        Object[][] cellData = new String[list.size()][headerNames.length];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
//        DefaultTableModel model = new DefaultTableModel(cellData, headerNames);
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        noworkingtable.setModel(model);
        noworkingtable.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        noworkingtable.getColumnModel().getColumn(0).setPreferredWidth(100);
        noworkingtable.getColumnModel().getColumn(0).setMaxWidth(100);
        noworkingtable.setRowHeight(30);
        noworkingtable.setRowMargin(5);
        noworkingtable.getColumnModel().getColumn(1).setPreferredWidth(150);
        noworkingtable.getColumnModel().getColumn(1).setMaxWidth(150);
        noworkingtable.getColumnModel().getColumn(2).setPreferredWidth(220);
        noworkingtable.getColumnModel().getColumn(2).setMaxWidth(220);
        noworkingtable.getColumnModel().getColumn(3).setPreferredWidth(130);
        noworkingtable.getColumnModel().getColumn(3).setMaxWidth(130);
        noworkingtable.updateUI();
    }

    private String clearAndGetData(Filter filter) {

        String data = HttpService.tabGetDate(filter);
        return data;
    }

    private void saveFilePath() {
        String text = motifytext.getText();
        if (StringUtils.isBlank(text)) {
            JOptionPane.showMessageDialog(frame,
                    "文件默认路径不能为空！", "文件默认路径修改", JOptionPane.INFORMATION_MESSAGE);
            if (StringUtils.isBlank(Init.configer.getProps(CacheConstants.file_path))) {
                text = CacheConstants.file_path_dafault;
            } else {
                text = Init.configer.getProps(CacheConstants.file_path);
            }

        } else {
            Init.configer.setProps(CacheConstants.file_path, text);
        }
        Init.configer.save();
        if (FileUtil.changeDir()) {
            JOptionPane.showMessageDialog(frame,
                    "文件默认路径修改成功！！", "文件默认路径修改", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message = String.format("路径修改失败，现在路径是%s,请联系绘天科技咨询", CacheConstants.file_path);
            JOptionPane.showMessageDialog(frame, message, "文件默认路径修改", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * 发送HTTP请求 修改密码
     */
    private void motifyPassword() {
        String data = HttpService.motifyPassword(CacheConstants.centerAccountId, newpasswordtext.getText());
        HuitianResult huitianResult = JsonUtils.jsonToPojo(data, HuitianResult.class);
        if (huitianResult.getStatus().equals(HttpResponseStatus.SUCCESS)) {
            JOptionPane.showMessageDialog(frame,
                    "密码修改成功！！", "密码修改", JOptionPane.INFORMATION_MESSAGE);
        } else if (huitianResult.getStatus().equals(HttpResponseStatus.MOTIFYPASSWORD_ERROR)) {
            JOptionPane.showMessageDialog(frame,
                    "密码修改失败~~", "密码修改", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * 检查两次密码是否是一直的
     *
     * @return
     */
    private boolean checkResetPasswordIfSame() {
        boolean res = false;
        try {
            if (StringUtils.isNotBlank(newpasswordtext.getText().trim()) &&
                    StringUtils.isNotBlank(confirmpasswordtext.getText().trim())) {
                if (StringUtils.equals(newpasswordtext.getText().trim(), confirmpasswordtext.getText().trim())) {
                    res = true;
                    return res;
                }

            }
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 检查重置密码是否是空
     *
     * @return
     */
    private boolean checkResetPasswordEmpty() {
        if (StringUtils.isNotBlank(orgnalpasswordtext.getText().trim()) &&
                StringUtils.isNotBlank(newpasswordtext.getText().trim()) &&
                StringUtils.isNotBlank(confirmpasswordtext.getText().trim())) {
            return false;

        }
        return true;

    }

    /**
     * 响应tab上面的点击事件
     *
     * @param i
     */
    private void clickOnTab(int i) {
        if (i == 1) {

        } else if (i == 2) {
            findHaveManyIndentAfter();
//           initNoWorkIndent();

        } else if (i == 3) {

        } else if (i == 4) {

        } else if (i == 5) {
            //修改密码
//            motifyPassword();
            //设置文件默认路径
            String text = "";
            if (StringUtils.isBlank(Init.configer.getProps(CacheConstants.file_path))) {
                text = CacheConstants.file_path_dafault;
            } else {
                text = Init.configer.getProps(CacheConstants.file_path);
            }
            motifytext.setText(text);
        }
    }

    /**
     * 向服务器发送请求，看有多少订单
     */
    private void findHaveManyIndentAfter() {
        try {
            ChatMessage chatMessage = createChatMessage(EnumMessageMode.CENTER_ALLINDENT.name());
            String lowDpiKey = JsonUtils.objectToJson(chatMessage);
            webSocketClient.send(lowDpiKey);
        } catch (NotYetConnectedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化未加工订单列表
     */
    private void initNoWorkIndent() {
        // 模板消息Data表
        String[] headerNames = {"状态", "状态"};
        java.util.List<String[]> list = makeListForPortalNoIndent();

        Object[][] cellData = new String[list.size()][headerNames.length];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
//        DefaultTableModel model = new DefaultTableModel(cellData, headerNames);
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        noworkingtable.setModel(model);
        noworkingtable.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        noworkingtable.getColumnModel().getColumn(0).setPreferredWidth(100);
        noworkingtable.getColumnModel().getColumn(0).setMaxWidth(100);
        noworkingtable.setRowHeight(30);
        noworkingtable.setRowMargin(5);
        noworkingtable.getColumnModel().getColumn(1).setPreferredWidth(150);
        noworkingtable.getColumnModel().getColumn(1).setMaxWidth(150);
        noworkingtable.updateUI();

    }

    /**
     * 制作list 填充表格
     *
     * @return
     */
    private List<String[]> makeList() {
        String[] str = {"丁建磊", "13687672481", "adjkfjalksjdlkfjlasjka", "dd"};
        java.util.List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < 50; i++) {
            list.add(str);
        }
        return list;
    }

    /**
     * 制作list 填充表格
     *
     * @return
     */
    private List<String[]> makeListForTest() {
        String[] str = {"王顺通", "21341", "sdafsdfasdgasdg", "rrrr"};
        java.util.List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < 50; i++) {
            list.add(str);
        }
        return list;
    }

    /**
     * 制作list 填充表格
     *
     * @return
     */
    private List<String[]> makeListForPortalNoIndent() {
        String[] str = {"状态", "当前没有订单！"};
        java.util.List<String[]> list = new ArrayList<String[]>();
        list.add(str);
        return list;
    }


    public static ChatMessage createChatMessage(String MessageMode) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageMode(MessageMode);
        chatMessage.setMessage(CacheConstants.centerAccountId);
        return chatMessage;
    }

    public WebSocketClient connectWebsocket(final String centerAccountId) {
        WebSocketClient WebSocketClient = null;
        try {
            String websockerUrl = String.format("%s?CENTERACCOUNTID=%s&TERMINALTYPE=PC", ConstantsURL.WEBSOCKET_ADDRESS,
                    centerAccountId);
            URI uri = new URI(websockerUrl);
            WebSocketClient = new WebSocketClient(uri) {
                public void onMessage(String message) {
                    try {
                        if (message != null) {
                            System.out.println(message);
                            parseMessage(message);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                public void onOpen(ServerHandshake handshake) {

                    System.out.println("窗体初始化，websocket连接成功 onopen"
                    );
                }


                public void onClose(int code, String reason, boolean remote) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(code).append(reason).append(remote);
                    System.out.println(sb.toString());
                }


                public void onError(Exception ex) {
                    System.out.println("onerror");
                }
            };

            return WebSocketClient;
        } catch (Exception e2) {
            // TODO: handle exception
            return WebSocketClient;
        }

    }

    /**
     * 处理服务器发送过来的消息体
     *
     * @param message
     */

    private void parseMessage(String message) {
        try {
            ChatMessage chatMessage = JsonUtils.jsonToPojo(message, ChatMessage.class);
            if (chatMessage != null) {
                if (StringUtils.equals(chatMessage.getMessageMode(), EnumMessageMode.NO_INDENT.name())) {
                    drawNoIndentView();

                } else if (StringUtils.equals(chatMessage.getMessageMode(), EnumMessageMode.HAVE_INDENT.name())) {
                    initPortalNoWorkingTable(chatMessage);
                    createWorkingFile(chatMessage);
                } else if (StringUtils.equals(chatMessage.getMessageMode(), EnumMessageMode.CENTER_QUEUE_ALLINDENT.name())) {
                    dealWithCenterHaveManyAfterIndent(chatMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理一个加工中心有多少个后续订单
     *
     * @param chatMessage
     */
    private void dealWithCenterHaveManyAfterIndent(ChatMessage chatMessage) {
        if (chatMessage != null) {
            String indentList = chatMessage.getIndentList();
            if (StringUtils.isNotBlank(indentList)) {

                List<IndentDto> indentDtos = null;
                try {
                    indentDtos = JsonUtils.jsonToList(indentList, IndentDto.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                initAfterIndentList(indentDtos);
            }
        }
    }

    /**
     * 创造后续table还有多少订单的列表
     *
     * @param indentDtos
     */

    private void initAfterIndentList(List<IndentDto> indentDtos) {
        // 模板消息Data表
        String[] headerNames = {"业主姓名", "业主手机号", "订单id"};
        java.util.List<String[]> list = new ArrayList<String[]>();

        for (IndentDto indentDto : indentDtos) {
            String[] str = {"", "", ""};
            if (indentDto != null) {
                String name = indentDto.getReceiverName();
                String mobile = indentDto.getReceiverMobile();
                String indentId = indentDto.getIndentId();
                if (name == null) {
                    name = " ";
                }
                if (mobile == null) {
                    mobile = " ";
                }
                if (indentId == null) {
                    indentId = " ";
                }

                str[0] = name;
                str[1] = mobile;
                str[2] = indentId;
            }
            list.add(str);
        }
        Object[][] cellData = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        noworkingtable.setModel(model);
        noworkingtable.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int width = screenSize.width / 3;
        noworkingtable.getColumnModel().getColumn(0).setPreferredWidth(ConstantsUI.PORTAL_TABLE_COL_WIDTH);
        noworkingtable.getColumnModel().getColumn(0).setMaxWidth(width * 2);
        noworkingtable.getColumnModel().getColumn(1).setPreferredWidth(ConstantsUI.PORTAL_TABLE_COL_WIDTH);
        noworkingtable.getColumnModel().getColumn(1).setMaxWidth(width * 2);
        noworkingtable.getColumnModel().getColumn(2).setPreferredWidth(ConstantsUI.PORTAL_TABLE_COL_WIDTH);
        noworkingtable.getColumnModel().getColumn(2).setMaxWidth(width * 2);

        noworkingtable.setRowHeight(40);
        noworkingtable.setRowMargin(10);
        noworkingtable.updateUI();
    }

    /**
     * 创建加工文件
     *
     * @param chatMessage
     */
    private void createWorkingFile(ChatMessage chatMessage) {
        String filePath = "";

        StringBuilder sb = new StringBuilder();
        if (StringUtils.isBlank(Init.configer.getProps(CacheConstants.file_path))) {
            filePath = CacheConstants.file_path_dafault;
        } else {
            filePath = Init.configer.getProps(CacheConstants.file_path);
        }
        sb.append(filePath).append("/").append(CacheConstants.file_name).append(CacheConstants.file_suffix);
        ReadFromFile.clearInfoForFile(sb.toString());
        String s = JsonUtils.objectToJson(chatMessage);
        AppendToFile.appendMethodA(sb.toString(), s);
    }

    /**
     * 如果收到服务器没有订单的推送时候显示在table里
     */
    private void drawNoIndentView() {
        // 模板消息Data表

        String[] headerNames = {"属性", "取值"};
        java.util.List<String[]> list = makeListForPortalNoIndent();
        Object[][] cellData = new String[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//        table3.setModel(model);
//        table3.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int width = screenSize.width / 3;
//        table3.getColumnModel().getColumn(0).setPreferredWidth(ConstantsUI.PORTAL_TABLE_COL_WIDTH);
//        table3.getColumnModel().getColumn(0).setMaxWidth(width * 2);
//        table3.setRowHeight(40);
//        table3.setRowMargin(10);
//        table3.updateUI();

        DefaultTableModel model1 = new DefaultTableModel(cellData, headerNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setModel(model1);
        table2.getTableHeader().setResizingAllowed(true);
        // 设置列宽
        table2.getColumnModel().getColumn(0).setPreferredWidth(width);
        table2.getColumnModel().getColumn(0).setMaxWidth(width);
        table2.getColumnModel().getColumn(1).setPreferredWidth(width);
        table2.getColumnModel().getColumn(1).setMaxWidth(width);
        table2.setRowHeight(40);
        table2.setRowMargin(10);
        table2.updateUI();
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public void setTabbedPane1(JTabbedPane tabbedPane1) {
        this.tabbedPane1 = tabbedPane1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTable getWorkingtable() {
        return workingtable;
    }

    public void setWorkingtable(JTable workingtable) {
        this.workingtable = workingtable;
    }

    public JPanel getToppanel() {
        return toppanel;
    }

    public void setToppanel(JPanel toppanel) {
        this.toppanel = toppanel;
    }

    public JScrollPane getJscrollpanel() {
        return jscrollpanel;
    }

    public void setJscrollpanel(JScrollPane jscrollpanel) {
        this.jscrollpanel = jscrollpanel;
    }

    public JPanel getBottompanel() {
        return bottompanel;
    }

    public void setBottompanel(JPanel bottompanel) {
        this.bottompanel = bottompanel;
    }

    public JPanel getWorkeringpanel() {
        return workeringpanel;
    }

    public void setWorkeringpanel(JPanel workeringpanel) {
        this.workeringpanel = workeringpanel;
    }

    public JPanel getNoworkingpanel() {
        return noworkingpanel;
    }

    public void setNoworkingpanel(JPanel noworkingpanel) {
        this.noworkingpanel = noworkingpanel;
    }

    public JPanel getWaitsendgoodspanel() {
        return waitsendgoodspanel;
    }

    public void setWaitsendgoodspanel(JPanel waitsendgoodspanel) {
        this.waitsendgoodspanel = waitsendgoodspanel;
    }

    public JPanel getHistoryindentpanel() {
        return historyindentpanel;
    }

    public void setHistoryindentpanel(JPanel historyindentpanel) {
        this.historyindentpanel = historyindentpanel;
    }

    public JPanel getSystemsettingpanel() {
        return systemsettingpanel;
    }

    public void setSystemsettingpanel(JPanel systemsettingpanel) {
        this.systemsettingpanel = systemsettingpanel;
    }

    public JPanel getPagepanel() {
        return pagepanel;
    }

    public void setPagepanel(JPanel pagepanel) {
        this.pagepanel = pagepanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JPanel getNoworkingtoppanel() {
        return noworkingtoppanel;
    }

    public void setNoworkingtoppanel(JPanel noworkingtoppanel) {
        this.noworkingtoppanel = noworkingtoppanel;
    }

    public JButton getShouyeButton() {
        return shouyeButton;
    }

    public void setShouyeButton(JButton shouyeButton) {
        this.shouyeButton = shouyeButton;
    }

    public JButton getGotobutton() {
        return gotobutton;
    }

    public void setGotobutton(JButton gotobutton) {
        this.gotobutton = gotobutton;
    }

    public JButton getLastButton() {
        return lastButton;
    }

    public void setLastButton(JButton lastButton) {
        this.lastButton = lastButton;
    }

    public JButton getBeforeButton() {
        return beforeButton;
    }

    public void setBeforeButton(JButton beforeButton) {
        this.beforeButton = beforeButton;
    }

    public JButton getAfterButton() {
        return afterButton;
    }

    public void setAfterButton(JButton afterButton) {
        this.afterButton = afterButton;
    }

    public JTextField getNotextField() {
        return notextField;
    }

    public void setNotextField(JTextField notextField) {
        this.notextField = notextField;
    }

    public JTable getNoworkingtable() {
        return noworkingtable;
    }

    public void setNoworkingtable(JTable noworkingtable) {
        this.noworkingtable = noworkingtable;
    }

    public JLabel getHistorylabel() {
        return historylabel;
    }

    public void setHistorylabel(JLabel historylabel) {
        this.historylabel = historylabel;
    }

    public JButton getHshouyeButton() {
        return hshouyeButton;
    }

    public void setHshouyeButton(JButton hshouyeButton) {
        this.hshouyeButton = hshouyeButton;
    }

    public JButton getHafterButton() {
        return hafterButton;
    }

    public void setHafterButton(JButton hafterButton) {
        this.hafterButton = hafterButton;
    }

    public JButton getHlastButton() {
        return hlastButton;
    }

    public void setHlastButton(JButton hlastButton) {
        this.hlastButton = hlastButton;
    }

    public JButton getHbeforeButton() {
        return hbeforeButton;
    }

    public void setHbeforeButton(JButton hbeforeButton) {
        this.hbeforeButton = hbeforeButton;
    }

    public JButton getGotoButton() {
        return gotoButton;
    }

    public void setGotoButton(JButton gotoButton) {
        this.gotoButton = gotoButton;
    }

    public JTextField getHistorytextField() {
        return historytextField;
    }

    public void setHistorytextField(JTextField historytextField) {
        this.historytextField = historytextField;
    }

    public JLabel getHtotallabel() {
        return htotallabel;
    }

    public void setHtotallabel(JLabel htotallabel) {
        this.htotallabel = htotallabel;
    }

    public JLabel getNototallabel() {
        return nototallabel;
    }

    public void setNototallabel(JLabel nototallabel) {
        this.nototallabel = nototallabel;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JLabel getSettinglabel() {
        return settinglabel;
    }

    public void setSettinglabel(JLabel settinglabel) {
        this.settinglabel = settinglabel;
    }

    public JTextField getOrgnalpasswordtext() {
        return orgnalpasswordtext;
    }

    public void setOrgnalpasswordtext(JTextField orgnalpasswordtext) {
        this.orgnalpasswordtext = orgnalpasswordtext;
    }

    public JTextField getNewpasswordtext() {
        return newpasswordtext;
    }

    public void setNewpasswordtext(JTextField newpasswordtext) {
        this.newpasswordtext = newpasswordtext;
    }

    public JButton getSaveuserButton() {
        return saveuserButton;
    }

    public void setSaveuserButton(JButton saveuserButton) {
        this.saveuserButton = saveuserButton;
    }

    public JTextField getConfirmpasswordtext() {
        return confirmpasswordtext;
    }

    public void setConfirmpasswordtext(JTextField confirmpasswordtext) {
        this.confirmpasswordtext = confirmpasswordtext;
    }

    public JTextField getMotifytext() {
        return motifytext;
    }

    public void setMotifytext(JTextField motifytext) {
        this.motifytext = motifytext;
    }

    public JButton getSavefileButton() {
        return savefileButton;
    }

    public void setSavefileButton(JButton savefileButton) {
        this.savefileButton = savefileButton;
    }

    public JLabel getFilepathlabel() {
        return filepathlabel;
    }

    public void setFilepathlabel(JLabel filepathlabel) {
        this.filepathlabel = filepathlabel;
    }

    public JLabel getOrignalpasswordlabel() {
        return orignalpasswordlabel;
    }

    public void setOrignalpasswordlabel(JLabel orignalpasswordlabel) {
        this.orignalpasswordlabel = orignalpasswordlabel;
    }

    public JLabel getNewpasswordlabel() {
        return newpasswordlabel;
    }

    public void setNewpasswordlabel(JLabel newpasswordlabel) {
        this.newpasswordlabel = newpasswordlabel;
    }

    public JLabel getConfirmpasseordlabel() {
        return confirmpasseordlabel;
    }

    public void setConfirmpasseordlabel(JLabel confirmpasseordlabel) {
        this.confirmpasseordlabel = confirmpasseordlabel;
    }

    public JLabel getMotify() {
        return motify;
    }

    public void setMotify(JLabel motify) {
        this.motify = motify;
    }

    public JPanel getMorenlabel() {
        return morenlabel;
    }

    public void setMorenlabel(JPanel morenlabel) {
        this.morenlabel = morenlabel;
    }

    public JTabbedPane getForeightindenttab() {
        return foreightindenttab;
    }

    public void setForeightindenttab(JTabbedPane foreightindenttab) {
        this.foreightindenttab = foreightindenttab;
    }

    public JTable getFinishtable() {
        return finishtable;
    }

    public void setFinishtable(JTable finishtable) {
        this.finishtable = finishtable;
    }

    public JTable getSendtable() {
        return sendtable;
    }

    public void setSendtable(JTable sendtable) {
        this.sendtable = sendtable;
    }

    public JPanel getFinishnosend() {
        return finishnosend;
    }

    public void setFinishnosend(JPanel finishnosend) {
        this.finishnosend = finishnosend;
    }

    public JPanel getSendprintindent() {
        return sendprintindent;
    }

    public void setSendprintindent(JPanel sendprintindent) {
        this.sendprintindent = sendprintindent;
    }

    public JPanel getFinishjpanel() {
        return finishjpanel;
    }

    public void setFinishjpanel(JPanel finishjpanel) {
        this.finishjpanel = finishjpanel;
    }

    public JPanel getFinishbottom() {
        return finishbottom;
    }

    public void setFinishbottom(JPanel finishbottom) {
        this.finishbottom = finishbottom;
    }

    public JScrollPane getFinishscrollpanel() {
        return finishscrollpanel;
    }

    public void setFinishscrollpanel(JScrollPane finishscrollpanel) {
        this.finishscrollpanel = finishscrollpanel;
    }

    public JPanel getPrintpanel() {
        return printpanel;
    }

    public void setPrintpanel(JPanel printpanel) {
        this.printpanel = printpanel;
    }

    public JLabel getSendprintlabel() {
        return sendprintlabel;
    }

    public void setSendprintlabel(JLabel sendprintlabel) {
        this.sendprintlabel = sendprintlabel;
    }

    public JPanel getSendbottompanel() {
        return sendbottompanel;
    }

    public void setSendbottompanel(JPanel sendbottompanel) {
        this.sendbottompanel = sendbottompanel;
    }

    public JScrollPane getSendscrollpane() {
        return sendscrollpane;
    }

    public void setSendscrollpane(JScrollPane sendscrollpane) {
        this.sendscrollpane = sendscrollpane;
    }

    public JButton getFinishshouyebutton() {
        return finishshouyebutton;
    }

    public void setFinishshouyebutton(JButton finishshouyebutton) {
        this.finishshouyebutton = finishshouyebutton;
    }

    public JButton getFinishgotoButton() {
        return finishgotoButton;
    }

    public void setFinishgotoButton(JButton finishgotoButton) {
        this.finishgotoButton = finishgotoButton;
    }

    public JButton getFinishlastButton() {
        return finishlastButton;
    }

    public void setFinishlastButton(JButton finishlastButton) {
        this.finishlastButton = finishlastButton;
    }

    public JButton getFinishbeforeButton() {
        return finishbeforeButton;
    }

    public void setFinishbeforeButton(JButton finishbeforeButton) {
        this.finishbeforeButton = finishbeforeButton;
    }

    public JButton getFinishafterButton() {
        return finishafterButton;
    }

    public void setFinishafterButton(JButton finishafterButton) {
        this.finishafterButton = finishafterButton;
    }

    public JTextField getFinishtext() {
        return finishtext;
    }

    public void setFinishtext(JTextField finishtext) {
        this.finishtext = finishtext;
    }

    public JButton getSendshouyeButton() {
        return sendshouyeButton;
    }

    public void setSendshouyeButton(JButton sendshouyeButton) {
        this.sendshouyeButton = sendshouyeButton;
    }

    public JButton getSendafterButton() {
        return sendafterButton;
    }

    public void setSendafterButton(JButton sendafterButton) {
        this.sendafterButton = sendafterButton;
    }

    public JButton getSendlastButton() {
        return sendlastButton;
    }

    public void setSendlastButton(JButton sendlastButton) {
        this.sendlastButton = sendlastButton;
    }

    public JButton getSendbeforeButton() {
        return sendbeforeButton;
    }

    public void setSendbeforeButton(JButton sendbeforeButton) {
        this.sendbeforeButton = sendbeforeButton;
    }

    public JButton getSendgotoButton() {
        return sendgotoButton;
    }

    public void setSendgotoButton(JButton sendgotoButton) {
        this.sendgotoButton = sendgotoButton;
    }

    public JTextField getAftertext() {
        return aftertext;
    }

    public void setAftertext(JTextField aftertext) {
        this.aftertext = aftertext;
    }

    public JLabel getFinishlabelall() {
        return finishlabelall;
    }

    public void setFinishlabelall(JLabel finishlabelall) {
        this.finishlabelall = finishlabelall;
    }

    public JLabel getFinishtotalindent() {
        return finishtotalindent;
    }

    public void setFinishtotalindent(JLabel finishtotalindent) {
        this.finishtotalindent = finishtotalindent;
    }

    public JLabel getSendtotallabel() {
        return sendtotallabel;
    }

    public void setSendtotallabel(JLabel sendtotallabel) {
        this.sendtotallabel = sendtotallabel;
    }

    public JLabel getSendtotalindent() {
        return sendtotalindent;
    }

    public void setSendtotalindent(JLabel sendtotalindent) {
        this.sendtotalindent = sendtotalindent;
    }

    public HuitianResult getHuitianResult() {
        return huitianResult;
    }

    public void setHuitianResult(HuitianResult huitianResult) {
        this.huitianResult = huitianResult;
    }

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public void setWebSocketClient(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

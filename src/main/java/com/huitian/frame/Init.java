package com.huitian.frame;

import com.alee.laf.WebLookAndFeel;
import com.huitian.constants.ConstantsUI;
import com.huitian.util.FileUtil;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import org.apache.commons.lang3.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * dingjianlei 初始化的类
 */
public class Init {

    // 配置文件管理器对象
    public static Config configer = Config.getInstance();

    /**
     * 设置全局字体
     */
    public static void initGlobalFont() {
        //修改默认路径
        FileUtil.changeDir();
        String lowDpiKey = "lowDpiInit";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        if (screenSize.width <= 1366 && StringUtils.isEmpty(configer.getProps(lowDpiKey))) {
            configer.setFontSize(15);
            configer.setProps(lowDpiKey, "true");
            configer.save();
        }

        Font fnt = new Font(configer.getFont(), Font.PLAIN, configer.getFontSize());
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {

            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    /**
     * 其他初始化
     */
    public static void initOthers() {
        // 设置滚动条速度
        MainWindow.mainWindow.getSettingScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        MainWindow.mainWindow.getSettingScrollPane().getVerticalScrollBar().setDoubleBuffered(true);
        // 设置版本
        MainWindow.mainWindow.getVersionLabel().setText(ConstantsUI.APP_VERSION);
    }

    /**
     * 初始化look and feel
     */
    public static void initTheme() {

        try {
            switch (configer.getTheme()) {
                case "BeautyEye":
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);
                    break;
                case "weblaf":
                    UIManager.setLookAndFeel(new WebLookAndFeel());
                    break;
                case "系统默认":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case "Windows":
                    UIManager.setLookAndFeel(WindowsLookAndFeel.class.getName());
                    break;
                case "Nimbus":
                    UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
                    break;
                case "Metal":
                    UIManager.setLookAndFeel(MetalLookAndFeel.class.getName());
                    break;
                case "Motif":
                    UIManager.setLookAndFeel(MotifLookAndFeel.class.getName());
                    break;
                case "Darcula(推荐)":
                default:
                    UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化消息tab
     */
    public static void initMsgTab(boolean isInitFromHisComboxChang) {
        // 模板消息Data表
        String[] headerNames = {"Name", "Value", "Color", "操作"};
        java.util.List<String[]> list = new ArrayList<String[]>();
        Object[][] cellData = new String[list.size()][headerNames.length];
        for (int i = 0; i < list.size(); i++) {
            cellData[i] = list.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(cellData, headerNames);
        MainWindow.mainWindow.getTemplateMsgDataTable().setModel(model);
//        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().
//                getColumn(headerNames.length - 1).
//                setCellRenderer(new ButtonColumn(MainWindow.mainWindow.getTemplateMsgDataTable(), headerNames.length - 1));
//        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().
//                getColumn(headerNames.length - 1).
//                setCellEditor(new ButtonColumn(MainWindow.mainWindow.getTemplateMsgDataTable(), headerNames.length - 1));

        // 设置列宽
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(0).setPreferredWidth(150);
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(0).setMaxWidth(150);
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(2).setPreferredWidth(130);
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(2).setMaxWidth(130);
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(3).setPreferredWidth(130);
        MainWindow.mainWindow.getTemplateMsgDataTable().getColumnModel().getColumn(3).setMaxWidth(130);

        MainWindow.mainWindow.getTemplateMsgDataTable().updateUI();
    }




/**
 * 自定义单元格按钮渲染器
 */
public static class ButtonColumn extends AbstractCellEditor implements
        TableCellRenderer, TableCellEditor, ActionListener {
    JTable table;
    JButton renderButton;
    JButton editButton;

    public ButtonColumn(JTable table, int column) {
        super();
        this.table = table;
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText("移除");
        renderButton.setIcon(new ImageIcon(getClass().getResource("/icon/remove.png")));
        return renderButton;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        editButton.setText("移除");
        editButton.setIcon(new ImageIcon(getClass().getResource("/icon/remove.png")));
        return editButton;
    }

    public Object getCellEditorValue() {
        return "移除";
    }

    public void actionPerformed(ActionEvent e) {
        int isDelete = JOptionPane.showConfirmDialog(MainWindow.mainWindow.getMessagePanel(), "确认移除？", "确认",
                JOptionPane.INFORMATION_MESSAGE);
        if (isDelete == JOptionPane.YES_OPTION) {
            fireEditingStopped();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(table.getSelectedRow());
        }
    }
}

/**
 * 自定义单元格单选框渲染器
 */
public static class MyCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Boolean b = (Boolean) value;//这一列必须都是integer类型(0-100)
        setSelected(b);
        return this;
    }
}

}

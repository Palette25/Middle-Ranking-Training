import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lenovo on 2018/4/10.
 */

public final class Calculator {
    private static JFrame frame = null;
    private static JPanel panel = null;

    private Calculator(){}

    public static void main(String[] para){
        //Start running Swing window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createUI();
            }
        });
    }

    private static void createUI(){
        //Whole Frame Initialize
        frame = new JFrame("Easy Caculator");
        int width = 600, height = 300;
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Add caculate panel
        panel = new JPanel();
        frame.add(panel);
        //Place components
        placeCom();
        frame.setVisible(true);
    }

    private static double caculate(double x, double y, String op){
        double result = 0;
        switch(op){
            case "+": result = x+y; 
                        break;
            case "-": result = x-y; 
                        break;
            case "*": result = x*y; 
                        break;
            default: result = x/y; 
        }
        return result;
    }

    //Place layout for panel
    private static void placeCom(){
        GridLayout layout = new GridLayout(2, 5);
        layout.setHgap(30);
        layout.setVgap(30);
        panel.setLayout(layout);
        addTextAndBtn();
    }

    private static void addTextAndBtn(){
        //Text Components Initialize
        JTextField textx = new JTextField();
        JTextField texty = new JTextField();
        JTextField op = new JTextField();
        op.setEditable(false);
        JTextField equal = new JTextField("=");
        equal.setEditable(false);
        JTextField result = new JTextField();
        result.setEditable(false);
        //Add them to panel
        panel.add(textx);
        panel.add(op);
        panel.add(texty);
        panel.add(equal);
        panel.add(result);
        //Button Components Initialize
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op.setText("+");
            }
        });
        JButton sub = new JButton("-");
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op.setText("-");
            }
        });
        JButton mul = new JButton("*");
        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op.setText("*");
            }
        });
        JButton div = new JButton("/");
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op.setText("/");
            }
        });
        JButton ok = new JButton("OK");
        //Add click button event listener
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkres = checkValid(textx.getText(), texty.getText(), op.getText());
                if (!checkres.equals("")) {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(frame, checkres,
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                }else {
                    double x = Double.parseDouble(textx.getText());
                    double y = Double.parseDouble(texty.getText());
                    String res = Double.toString(caculate(x, y, op.getText()));
                    result.setText(res);
                }
            }
        });
        //Too long
        panel.add(plus);
        panel.add(sub);
        panel.add(mul);
        panel.add(div);
        panel.add(ok);
    }

    //Check if the inputs are all valid
    private static String checkValid(String x, String y, String op){
        String result = "";
        result = checkNum(x, y, result);
        if(!result.equals("")){
            return result;
        }
        if(x.equals("")||y.equals("")||op.equals("")){
            result = "请输入一个完整的算式!";
            return result;
        }
        if(op.equals("/")&&Double.parseDouble(y)==0){
            result = "进行除法操作时，被除数不能为0!";
        }
        return result;
    }

    //Check if the input have invalid signs
    private static String checkNum(String x, String y, String result){
        String temp = result;
        for(int i=0;i<x.length();i++){
                if(!Character.isDigit(x.charAt(i))){
                    temp = "不能输入数字以外的运算数";
                    break;
                }
            }
            if(temp.equals("")){
                for(int i=0;i<y.length();i++){
                if(!Character.isDigit(y.charAt(i))){
                    temp = "不能输入数字以外的运算数";
                    break;
                }
            }
        }
        return temp;
    }

}

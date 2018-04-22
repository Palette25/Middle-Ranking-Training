import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lenovo on 2018/4/10.
 */
public class Caculator {
    public static JFrame frame = null;
    public static JPanel panel = null;

    public static void main(String[] para){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateUI();
            }
        });
    }

    private static void CreateUI(){
        //Whole Frame Initialize
        frame = new JFrame("Easy Caculator");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Add caculate panel
        panel = new JPanel();
        frame.add(panel);
        //Place components
        placeCom();
        frame.setVisible(true);
    }

    private static double Caculate(double x, double y, String op){
        double result = 0;
        switch(op){
            case "+": result = x+y; break;
            case "-": result = x-y; break;
            case "*": result = x*y; break;
            default: result = x/y; break;
        }
        return result;
    }

    private static void placeCom(){
        GridLayout layout = new GridLayout(2, 5);
        layout.setHgap(30);
        layout.setVgap(30);
        panel.setLayout(layout);
        addTextAndBtn();
    }

    private static void addTextAndBtn(){
        //Text Components Initialize
        JTextField text_x = new JTextField();
        JTextField text_y = new JTextField();
        JTextField op = new JTextField();
        op.setEditable(false);
        JTextField equal = new JTextField("=");
        equal.setEditable(false);
        JTextField result = new JTextField();
        result.setEditable(false);
        //Add them to panel
        panel.add(text_x);
        panel.add(op);
        panel.add(text_y);
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
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String check_res = CheckValid(text_x.getText(), text_y.getText(), op.getText());
                if (!check_res.equals("")) {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(frame, check_res,
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                }else {
                    double x = Double.parseDouble(text_x.getText());
                    double y = Double.parseDouble(text_y.getText());
                    String res = Double.toString(Caculate(x, y, op.getText()));
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

    private static String CheckValid(String x, String y, String op){
        String result = "";
        if(op.equals("/")){
            if(Double.parseDouble(y)==0) result = "进行除法操作时，被除数不能为0!";
        }else if(x.equals("")||y.equals("")){
            result = "请输入两个需要进行运算的数!";
        }
        return result;
    }

}

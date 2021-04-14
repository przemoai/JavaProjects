import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private final static JFrame frame = new JFrame("Kalkulator");
    private JPanel panel;
    private JTextField input;
    private JTextField display;
    private JButton plus;
    private JButton minus;
    private JButton divide;
    private JButton multiply;
    private JButton equal;
    private JButton clearButton;

    public class Globals{
        public static double a=0;
        public static double b=0;
        public static int operation_type=0;


    }

    public Calculator() {


        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.operation_type=1;
                if(Globals.a==0) {
                    Globals.a = Double.parseDouble(input.getText());
                    display.setText(String.valueOf(Globals.a));

                    input.setText("");
                }
                else{
                    Globals.a= Double.parseDouble(display.getText());
                    Globals.b= Double.parseDouble(input.getText());
                    display.setText((String.valueOf(Globals.a+Globals.b)));
                    input.setText("");
                }
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.operation_type=2;
                if(Globals.a==0) {
                    Globals.a = Double.parseDouble(input.getText());
                    display.setText(String.valueOf(Globals.a));

                    input.setText("");
                }
                else{
                    Globals.a= Double.parseDouble(display.getText());
                    Globals.b= Double.parseDouble(input.getText());
                    display.setText((String.valueOf(Globals.a-Globals.b)));
                    input.setText("");
                }
            }
        });

        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.operation_type=3;
                if(Globals.a==0) {
                    Globals.a = Double.parseDouble(input.getText());
                    display.setText(String.valueOf(Globals.a));

                    input.setText("");
                }
                else{
                    Globals.a= Double.parseDouble(display.getText());
                    Globals.b= Double.parseDouble(input.getText());
                    display.setText((String.valueOf(Globals.a*Globals.b)));
                    input.setText("");
                }
            }
        });
        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.operation_type=4;
                if(Globals.a==0) {
                    Globals.a = Double.parseDouble(input.getText());
                    display.setText(String.valueOf(Globals.a));

                    input.setText("");
                }
                else{
                    Globals.a= Double.parseDouble(display.getText());
                    Globals.b= Double.parseDouble(input.getText());
                    display.setText((String.valueOf(Globals.a-Globals.b)));
                    input.setText("");
                }
            }
        });

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.a= Double.parseDouble(display.getText());
                Globals.b= Double.parseDouble(input.getText());
                if(Globals.operation_type==1){
                    display.setText((String.valueOf(Globals.a+Globals.b)));
                }else if (Globals.operation_type==2){
                    display.setText((String.valueOf(Globals.a-Globals.b)));
                }else if (Globals.operation_type==3){
                    display.setText((String.valueOf(Globals.a*Globals.b)));
                }else if (Globals.operation_type==4){
                    display.setText((String.valueOf(Globals.a/Globals.b)));
                }

                input.setText("");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.a=0;
                Globals.b=0;
                display.setText("");
                input.setText("");
            }
        });

    }


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        frame.setContentPane(new Calculator().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(frame);
        frame.pack();
    }
}



import javax.swing.*;
import java.util.ArrayList;

public class PassGen {

    static JFrame window = new JFrame("Password generator.");
    static int amount;
    static int size;
    static String letters_small = "abcdefghijklmnopqrstuvwxyz";
    static String letters_big = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String numbers = "0123456789";
    static String symbols = "!@#$%^&*(){}-_=+;:<>/?";
    static ArrayList<String> passwords = new ArrayList<>();
    static JTextField text1 = new JTextField();
    static JTextField text2 = new JTextField();
    static JCheckBox special = new JCheckBox("Special symbols.");
    static JButton rig = new JButton(">");
    static JButton lef = new JButton("<");
    static int page = 1;
    static JLabel info = new JLabel();
    static boolean temp = true;












    static void generate(){

        temp = true;

        if(passwords.size() > 0){

            passwords.clear();
            page = 1;

        }
            try {

                amount = Integer.parseInt(text1.getText());
                size = Integer.parseInt(text2.getText());

                if (amount <= 0 || amount > 100 || size <= 0 || size > 30) {
                    JOptionPane.showMessageDialog(null, "Incorrect numbers to enter.");
                    text1.setText("");
                    text2.setText("");

                    temp = false;
                } else if (size < 5) {
                    JOptionPane.showMessageDialog(null, "Password less than 5 characters is too small.");
                    text2.setText("");

                    temp = false;
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "You have entered incorrect values in the input fields.");
                text1.setText("");
                text2.setText("");

                temp = false;
            }


            if(temp) {
                for (int i = 0; i < amount; i++) {

                    StringBuilder password = new StringBuilder();

                    for (int a = 0; a < size; a++) {

                        int random;

                        boolean isCheck = special.isSelected();

                        if (isCheck) {
                            random = (int) Math.floor(Math.random() * 4);
                        } else {
                            random = (int) Math.floor(Math.random() * 3);
                        }

                        if (random == 0) {
                            password.append(letters_small.charAt((int) Math.floor(Math.random() * letters_small.length())));
                        } else if (random == 1) {
                            password.append(letters_big.charAt((int) Math.floor(Math.random() * letters_big.length())));
                        } else if (random == 2) {
                            password.append(numbers.charAt((int) Math.floor(Math.random() * numbers.length())));
                        } else if (random == 3) {
                            password.append(symbols.charAt((int) Math.floor(Math.random() * symbols.length())));
                        }
                    }

                    passwords.add(password.toString());

                }
            }
        }


    public static void main(String[] args) {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(400, 300);
        window.setResizable(false);
        window.setLayout(null);

        JLabel desc1 = new JLabel("- How many passwords you need (Max: 100).");
        desc1.setBounds(40, 5, 300, 25);
        window.getContentPane().add(desc1);

        JLabel desc2 = new JLabel(" - Preferred password length (Max: 30).");
        desc2.setBounds(40, 45, 300, 25);
        window.getContentPane().add(desc2);

        text1.setBounds(5, 5, 35, 25);
        window.getContentPane().add(text1);

        text2.setBounds(5, 45, 35, 25);
        window.getContentPane().add(text2);

        special.setBounds(5, 75, 200, 25);
        window.getContentPane().add(special);

        JButton start = new JButton("Generate.");
        start.setBounds(5, 105, 125, 25);
        window.getContentPane().add(start);

        info.setBounds(5, 175, 200, 25);
        window.getContentPane().add(info);
        info.setVisible(false);

        lef.setBounds(210, 175, 50, 25);
        window.getContentPane().add(lef);
        lef.setVisible(false);

        rig.setBounds(260, 175, 50, 25);
        window.getContentPane().add(rig);
        rig.setVisible(false);

        JLabel ps = new JLabel();
        ps.setBounds(5,205,300,25);
        window.getContentPane().add(ps);
        ps.setVisible(false);


        start.addActionListener(e -> {



            generate();

            if(passwords.size() > 0 && temp) {
                info.setText("Password " + page + " from " + passwords.size());
                info.setVisible(true);
                lef.setVisible(true);
                rig.setVisible(true);
                ps.setVisible(true);
                ps.setText(passwords.get(page - 1));
            }
            else{
                info.setVisible(false);
                lef.setVisible(false);
                rig.setVisible(false);
                ps.setVisible(false);
            }
        });
        lef.addActionListener(e -> {
            if(page > 1){
                page = page - 1;
            }
            info.setText("Password " + page + " from " + passwords.size());
            ps.setText(passwords.get(page - 1));
        });

        rig.addActionListener(e -> {
            if(page < passwords.size()) {
                page = page + 1;
            }
            info.setText("Password " + page + " from " + passwords.size());
            ps.setText(passwords.get(page - 1));
        });





        window.setVisible(true);

    }

}
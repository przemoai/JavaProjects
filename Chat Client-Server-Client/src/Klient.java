import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Klient{
    String nazwa_uzytkownika;

    public Klient(String nazwa_uzytkownika) {
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    };

    JTextArea odbiorWiadomosci;
    JTextField wiadomosc;
    BufferedReader czytelnik;
    PrintWriter pisarz;
    Socket gniazdo;

    public static void main(String[] args) {
        Klient klient = new Klient("USER1");
        klient.polaczMnie();

        Klient klient2 = new Klient("USER2");
        klient2.polaczMnie();
    }

    public void polaczMnie() {
        JFrame frame = new JFrame(nazwa_uzytkownika);
        JPanel panel = new JPanel();
        odbiorWiadomosci = new JTextArea(15, 50);
        odbiorWiadomosci.setLineWrap(true);
        odbiorWiadomosci.setWrapStyleWord(true);
        odbiorWiadomosci.setEditable(false);
        JScrollPane przewijanie = new JScrollPane(odbiorWiadomosci);

        przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        wiadomosc = new JTextField(20);
        JButton przyciskWyslij = new JButton("Wyslij");
        przyciskWyslij.addActionListener(new SluchaczPrzycisku());
        panel.add(przewijanie);
        panel.add(wiadomosc);
        panel.add(przyciskWyslij);
        konfiguruj();
        Thread watekOdbiorcy = new Thread(new Odbiorca());
        watekOdbiorcy.start();
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(new Dimension(600, 400));
        frame.setVisible(true);
    }

    private void konfiguruj() {
        try {
            gniazdo = new Socket("127.0.0.1", 2020);
            InputStreamReader czytelnikStrm = new InputStreamReader(gniazdo.getInputStream());
            czytelnik = new BufferedReader(czytelnikStrm);
            pisarz = new PrintWriter(gniazdo.getOutputStream());
            System.out.println("Zakończono konfiguracje sieci");
        } catch (IOException ex) {
            System.out.println("Konfiguracja sieci nie powiodła się !");
            ex.printStackTrace();
        }
    }

    private class SluchaczPrzycisku implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                pisarz.println(nazwa_uzytkownika +": "+ wiadomosc.getText());
                pisarz.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            wiadomosc.setText("");
            wiadomosc.requestFocus();
        }
    }

    public class Odbiorca implements Runnable {
        @Override
        public void run() {
            String wiad;
            try {
                while ((wiad = czytelnik.readLine()) != null) {
                    System.out.println(nazwa_uzytkownika + wiad);
                    odbiorWiadomosci.append(wiad + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

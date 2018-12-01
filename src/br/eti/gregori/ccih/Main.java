package br.eti.gregori.ccih;

import br.eti.gregori.ccih.ui.MainForm;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Perfil Microbiológico");
        frame.setContentPane(new MainForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

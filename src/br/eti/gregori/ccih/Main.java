package br.eti.gregori.ccih;

import br.eti.gregori.ccih.dao.*;
import br.eti.gregori.ccih.model.AntibioticTest;
import br.eti.gregori.ccih.ui.MainForm;
import br.eti.gregori.ccih.util.PropertyParser;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PropertyParser pp = new PropertyParser();

        if (pp.getDbSetup()) {
            System.out.println("DbSetup");
            initializeDb();
            pp.disableDbSetup();
        }

        JFrame frame = new JFrame("Perfil Microbiológico");
        frame.setContentPane(new MainForm().getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds(0,0,(int)(dim.width*0.7), (int)(dim.height*0.7));
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);

    }

    private static void initializeDb() {
        try {
            BacteriaDao.getInstance().createTable(true);
            AntibioticDao.getInstance().createTable(true);
            LocationDao.getInstance().createTable(true);
            MaterialDao.getInstance().createTable(true);
            AntibiogramDao.getInstance().createTable(true);
            AntibioticTestDao.getInstance().createTable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

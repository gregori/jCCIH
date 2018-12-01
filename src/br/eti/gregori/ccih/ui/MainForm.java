package br.eti.gregori.ccih.ui;

import br.eti.gregori.ccih.dao.BacteriaDao;
import br.eti.gregori.ccih.model.Bacteria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MainForm {
    private JTabbedPane tabbedPane1;
    private JTextField txtSearchBacteria;
    private JButton btnSearchBacteria;
    private JButton btnNewBacteria;
    private JButton btnEditBacteria;
    private JTable tblBacteria;
    private JTextField txtSearchAtb;
    private JButton btnSearchAtb;
    private JButton btnNewAtb;
    private JButton btnEditAtb;
    private JTable tblAntibiotic;
    private JTextField txtSearchLocation;
    private JButton btnSearchLocation;
    private JButton btnNewLocation;
    private JButton btnEditLocation;
    private JTable tblLocation;
    private JTextField txtSearchMaterial;
    private JButton btnSearchMaterial;
    private JButton btnNewMaterial;
    private JButton btnEditMaterial;
    private JTable tblMaterial;
    private JComboBox cmbMonth;
    private JComboBox comboBox1;
    private JButton btnNewAntibiogram;
    private JButton btnEditAntibiogram;
    private JButton btnSearchAntibiogram;
    private JTable tblAntibiogram;
    private JPanel contentPane;
    private DefaultTableModel bacteriaTableModel;

    public MainForm() {
        MainForm mainFormFrame = this;

        String[] bacteriaColumns = { "id", "Nome", "Gram" };
        bacteriaTableModel = new DefaultTableModel(new Object[][]{}, bacteriaColumns);
        tblBacteria.setModel(bacteriaTableModel);
        findBacterias();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void findBacterias() {
        List<Bacteria> bacterias = BacteriaDao.getInstance().getAll();
        bacteriaTableModel.setRowCount(0);

        for (Bacteria b : bacterias) {
            bacteriaTableModel.addRow(
                    new Object[] {
                            b.getId(),
                            b.getName(),
                            b.getGram()
                    }
            );
        }

    }

}

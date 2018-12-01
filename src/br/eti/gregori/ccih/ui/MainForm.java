package br.eti.gregori.ccih.ui;

import br.eti.gregori.ccih.dao.BacteriaDao;
import br.eti.gregori.ccih.model.Bacteria;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton btnDeleteBacteria;
    private JButton btnDeleteAntibiogram;
    private JButton btnDeleteAntibiotic;
    private JButton brnDeleteLocation;
    private JButton btnDeleteMaterial;
    private NonCellEditableTableModel bacteriaTableModel;
    private TableRowSorter<TableModel> bacteriaTableRowSorter;

    public MainForm() {
        MainForm mainFormFrame = this;

        String[] bacteriaColumns = { "id", "Nome", "Gram" };
        bacteriaTableModel = new NonCellEditableTableModel(new Object[][]{}, bacteriaColumns);

        tblBacteria.setModel(bacteriaTableModel);
        findBacterias();
        bacteriaTableRowSorter = new TableRowSorter<>(tblBacteria.getModel());

        btnNewBacteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BacteriaForm(mainFormFrame, false);
            }
        });
        btnEditBacteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int id = getIdFromTable(tblBacteria);
                if (id != 0) {
                    BacteriaForm bForm = new BacteriaForm(mainFormFrame, true);
                    bForm.setBacteria(BacteriaDao.getInstance().getById(id));
                }
            }
        });
        btnDeleteBacteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getIdFromTable(tblBacteria);
                if (id != 0) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Tem certeza que quer remover o item selecionado?", "Apagar bactéria", JOptionPane.YES_NO_OPTION) == 0) {
                        BacteriaDao.getInstance().deleteById(id);
                        findBacterias();
                    }
                }
            }
        });
        txtSearchBacteria.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchBacteria.getText();

                if (text.trim().length() == 0) {
                    bacteriaTableRowSorter.setRowFilter(null);
                } else {
                    bacteriaTableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchBacteria.getText();

                if (text.trim().length() == 0) {
                    bacteriaTableRowSorter.setRowFilter(null);
                } else {
                    bacteriaTableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private int getIdFromTable(JTable table) {
        return (int) table.getValueAt(table.getSelectedRow(), 0);
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

    private class NonCellEditableTableModel extends DefaultTableModel {

        public NonCellEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

}

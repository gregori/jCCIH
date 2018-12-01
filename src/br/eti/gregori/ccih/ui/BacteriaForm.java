package br.eti.gregori.ccih.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.eti.gregori.ccih.dao.BacteriaDao;
import br.eti.gregori.ccih.model.Bacteria;
import br.eti.gregori.ccih.model.Gram;

public class BacteriaForm {
    private JTextField txtName;
    private JComboBox cmbGram;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnClear;
    private JPanel panelBacteria;
    private boolean isEdit;
    private Bacteria bacteria;

    public BacteriaForm(MainForm frmMainForm, boolean isEdit) {
        bacteria = new Bacteria();
        this.isEdit = isEdit;

        JFrame frame = new JFrame("Cadastro de Bactérias");
        frame.setContentPane(panelBacteria);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frmMainForm.getContentPane());
        //frame.setLocation(frmMainForm.getContentPane().getX()/2-frame.getSize().width/2, frmMainForm.getContentPane().getY()/2-frame.getSize().height/2);
        frame.setVisible(true);
        cmbGram.addItem("Escolha um");
        for (Gram gram : Gram.values()) {
            cmbGram.addItem(gram);
        }

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Nome não pode estar em branco!", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                } else if (cmbGram.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Você deve selecionar o Gram!", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Gram gram = (Gram) cmbGram.getSelectedItem();
                    bacteria.setName(txtName.getText());
                    bacteria.setGram(gram);
                    int newId = BacteriaDao.getInstance().modify(bacteria);
                    if (newId > 0) {
                        String type = isEdit ? "editada" : "criada";

                        JOptionPane.showMessageDialog(null, "Bactéria " + type + " com sucesso.", "Cadastro de bactérias", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        frmMainForm.findBacterias();
                    }


                }
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                cmbGram.setSelectedIndex(0);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void setBacteria(Bacteria bacteria) {
        this.bacteria = bacteria;
        txtName.setText(bacteria.getName());
        cmbGram.setSelectedItem(bacteria.getGram());
    }
}

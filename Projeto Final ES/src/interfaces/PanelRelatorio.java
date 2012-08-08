package interfaces;

import dao.DaoPassagem;
import entidades.Passagem;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class PanelRelatorio extends JFrame {

    public PanelRelatorio() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setTitle("Relatorio");
        this.setMinimumSize(new Dimension(300, 200));
        this.setResizable(false);
        //this.setUndecorated(true);
        Container frame = this.getContentPane();
        this.setSize(300, 200);
        Toolkit tk = Toolkit.getDefaultToolkit();
        //  Obtendo a dimensão da tela
        Dimension screenSize = tk.getScreenSize();
        //  Centralizando
        setLocation((screenSize.width - getSize().width) / 2,
                (screenSize.height - getSize().height) / 2);

        daoPassagem = new DaoPassagem();
        lblSelecioneData = new JLabel("Selecione a data que deseja gerar relatorio:");
        cboDia = new JComboBox(new String[]{"-"});
        cboMes = new JComboBox(new String[]{"-"});
        cboAno = new JComboBox(new String[]{"-"});
        btnGerarRelatorio = new JButton("Gerar Relatorio");
        btnFecharFrame = new JButton("Cancelar");

        lblSelecioneData.setBounds(20, 5, 290, 30);
        cboDia.setBounds(30, 50, 50, 30);
        cboMes.setBounds(100, 50, 50, 30);
        cboAno.setBounds(170, 50, 70, 30);
        btnGerarRelatorio.setBounds(15, 100, 130, 40);
        btnFecharFrame.setBounds(160, 100, 110, 40);

        frame.add(lblSelecioneData);
        frame.add(cboDia);
        frame.add(cboMes);
        frame.add(cboAno);
        frame.add(btnGerarRelatorio);
        frame.add(btnFecharFrame);

        for (int i = 1; i <= 31; i++) {
            cboDia.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            cboMes.addItem(i);
        }
        for (int i = 2000; i <= 2012; i++) {
            cboAno.addItem(i);
        }

        btnGerarRelatorio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                gerarClick(evt);
            }
        });

        btnFecharFrame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                fecharClick(evt);
            }
        });

    }

    private void gerarClick(ActionEvent evt) {
        if (cboDia.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um dia.");
            cboDia.requestFocus();
        } else if (cboMes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um mes.");
            cboMes.requestFocus();
        } else if (cboAno.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um ano.");
            cboAno.requestFocus();
        } else {


            String local = System.getProperty("user.dir");
            //escreve relatorio
            try {
                ArrayList<ArrayList<String>> passagens = daoPassagem.consultarTodasPassagensDoDia(String.valueOf(cboDia.getSelectedItem()) + "/" + String.valueOf(cboMes.getSelectedItem()) + "/" + String.valueOf(cboAno.getSelectedItem()));
                System.out.println("num array: " + passagens.size());
                String barraN = System.clearProperty("line.separator");
                FileWriter f0 = new FileWriter(local + "/src/imagens/Sem Titulo.txt");
                f0.write("Relatorio do dia " + String.valueOf(cboDia.getSelectedItem()) + "/" + String.valueOf(cboMes.getSelectedItem()) + "/" + String.valueOf(cboAno.getSelectedItem()) + barraN + barraN + barraN);
                for (int i = 0; i < (passagens.size()); i++) {
                    int j = i;
                    System.out.println(j);
                    while ((passagens.get(j).get(1).equals(passagens.get(j + 1).get(1))) && (passagens.get(j).get(13).equals(passagens.get(j + 1).get(13)))) {
                        j++;
                        if (j == passagens.size()-1) {
                            System.out.println("tem q sair agr");
                            break;
                        }
                    }

                    f0.write(barraN + "Passageiro: " + passagens.get(i).get(2));
                    f0.write(barraN + "RG: " + passagens.get(i).get(1));
                    f0.write(barraN + "Assento comprado: " + passagens.get(i).get(3));
                    f0.write(barraN + "Viagem: " + passagens.get(i).get(7) + "-" + passagens.get(i).get(8) + " até " + passagens.get(j).get(9) + "-" + passagens.get(j).get(10));
                    f0.write(barraN + "Horario de Saida: " + passagens.get(i).get(4));
                    f0.write(barraN + "Horario de Chegada: " + passagens.get(j).get(5));
                    f0.write(barraN + "Placa do Onibus que fez esta viagem: " + passagens.get(i).get(11));
                    //f0.write(barraN+"Motorista que fez esta viagem: "+passagens.get(i).get(12));
                    f0.write(barraN + barraN);
                    i = j;
                    System.out.println("i modificado: "+ i);
                }
                f0.close();
                java.awt.Desktop.getDesktop().open(new File(local + "/src/imagens/Sem Titulo.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fecharClick(ActionEvent evt) {
        this.setVisible(false);
    }
    private DaoPassagem daoPassagem;
    private JLabel lblSelecioneData;
    private JComboBox cboDia;
    private JComboBox cboMes;
    private JComboBox cboAno;
    private JButton btnGerarRelatorio;
    private JButton btnFecharFrame;
}

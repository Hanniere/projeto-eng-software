package interfaces;

import dao.DaoItinerario;
import dao.DaoRotaItinerario;
import entidades.Horario;
import entidades.Itinerario;
import entidades.Rota;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelHorario extends JPanel {

    public JPanel inserirPnlHorario() {
        format = NumberFormat.getNumberInstance();
        format.setMinimumIntegerDigits(2);
        gridLayout2_4 = new GridLayout(2, 4);
        fontePadrao = new Font("Segoe UI", 1, 14);

        botaoEscolhido = -1;

        pnlHorario = new JPanel(null);
        pnlHorario.setSize(695, 590);
        pnlHorario.setPreferredSize(new Dimension(885, 650));
        pnlHorario.setBorder(BorderFactory.createTitledBorder(null, " HORARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlHorario.setBackground(new Color(240, 240, 240));
        pnlHorario.setOpaque(true);
        pnlHorario.setVisible(false);

        btnCadastrar = new JButton("Associar Horario");
        btnCadastrar.setBounds(180, 40, 150, 40);

        btnRemover = new JButton("Remover Horario");
        btnRemover.setBounds(350, 40, 150, 40);

        lblItinerario = new JLabel("Itinerario:");
        lblItinerario.setBounds(27, 50, 155, 20);

        cboItinerario = new JComboBox(new String[]{"Selecione"});
        cboItinerario.setBounds(90, 45, 222, 30);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(515, 525, 150, 40);
        btnCancelar.setVisible(false);

        inserirPnlCadastro();
        inserirPnlRemocao();

        pnlCadastro.setVisible(false);
        pnlRemocao.setVisible(false);

        pnlHorario.add(btnCancelar);
        pnlHorario.add(btnCadastrar);
        pnlHorario.add(btnRemover);

        pnlHorario.add(cboItinerario);
        pnlHorario.add(lblItinerario);

        cboItinerario.setVisible(false);
        lblItinerario.setVisible(false);

        pnlHorario.add(pnlCadastro);
        pnlHorario.add(pnlRemocao);

        carregaComboItinerario();
        carregaComboMotorista();
        carregaComboOnibus();
        carregaComboMinuto();
        carregaComboHora();

        daoItinerario = new DaoItinerario();

        cboItinerario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    if (!(cboItinerario.getSelectedItem().equals("Selecione"))) {
                        if (botaoEscolhido == 1) {
                            listTabelaCidadesCadastro.removeAll();
                            arrayAuxPnlCadastro = new ArrayList<JPanel>();
                            Itinerario itinerario = new Itinerario();
                            itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex() - 1).getId());
                            ArrayList<Rota> rotasItinerario = new ArrayList<Rota>();
                            rotasItinerario = daoItinerario.consultarRotasdoItinerario(itinerario);
                            for (int i = 0; i < rotasItinerario.size(); i++) {
                                arrayAuxPnlCadastro.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                                arrayAuxPnlCadastro.get(i).setPreferredSize(new Dimension(600, 100));
                                arrayAuxPnlCadastro.get(i).add(new JLabel("Parada " + (i + 1) + ": " + rotasItinerario.get(i).getRota_cidadeDestino()));
                                arrayAuxPnlCadastro.get(i).add(new JLabel());
                                arrayAuxPnlCadastro.get(i).add(new JLabel("Horario Saida:   X (inserir)"));
                                arrayAuxPnlCadastro.get(i).add(new JLabel("Preço: "));
                                arrayAuxPnlCadastro.get(i).add(new JTextField("", 8));
                                arrayAuxPnlCadastro.get(i).add(new JLabel("Motorista: "));
                                arrayAuxPnlCadastro.get(i).add(new JComboBox(new String[]{"Selecione"}));
                                for (int j = 0; j < 7; j++) {
                                    arrayAuxPnlCadastro.get(i).getComponent(j).setEnabled(false);
                                }
                                arrayAuxPnlCadastro.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                                arrayAuxPnlCadastro.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                                arrayAuxPnlCadastro.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                                arrayAuxPnlCadastro.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                                arrayAuxPnlCadastro.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                                arrayAuxPnlCadastro.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                                listTabelaCidadesCadastro.add(arrayAuxPnlCadastro.get(i));
                            }
                            listTabelaCidadesCadastro.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                            listTabelaCidadesCadastro.setEnabled(false);
                        } else {
                            listTabelaCidadesRemocao.removeAll();
                            arrayAuxPnlRemocao = new ArrayList<JPanel>();
                            Itinerario itinerario = new Itinerario();
                            itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex() - 1).getId());
                            ArrayList<Rota> rotasItinerario = new ArrayList<Rota>();
                            rotasItinerario = daoItinerario.consultarRotasdoItinerario(itinerario);
                            for (int i = 0; i < rotasItinerario.size(); i++) {
                                arrayAuxPnlRemocao.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                                arrayAuxPnlRemocao.get(i).setPreferredSize(new Dimension(600, 100));
                                arrayAuxPnlRemocao.get(i).add(new JLabel("Parada " + (i + 1) + ": " + rotasItinerario.get(i).getRota_cidadeDestino()));
                                arrayAuxPnlRemocao.get(i).add(new JLabel());
                                arrayAuxPnlRemocao.get(i).add(new JLabel("Horario Saida:   X (inserir)"));
                                arrayAuxPnlRemocao.get(i).add(new JLabel("Preço: "));
                                arrayAuxPnlRemocao.get(i).add(new JTextField("", 8));
                                arrayAuxPnlRemocao.get(i).add(new JLabel("Motorista: "));
                                arrayAuxPnlRemocao.get(i).add(new JComboBox(new String[]{"Selecione"}));
                                for (int j = 0; j < 7; j++) {
                                    arrayAuxPnlRemocao.get(i).getComponent(j).setEnabled(false);
                                }
                                arrayAuxPnlRemocao.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                                arrayAuxPnlRemocao.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                                arrayAuxPnlRemocao.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                                arrayAuxPnlRemocao.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                                arrayAuxPnlRemocao.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                                arrayAuxPnlRemocao.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                                listTabelaCidadesRemocao.add(arrayAuxPnlRemocao.get(i));
                            }
                            listTabelaCidadesRemocao.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                            listTabelaCidadesRemocao.setEnabled(false);
                        }
                    } else {
                        listTabelaCidadesCadastro.removeAll();
                        arrayAuxPnlCadastro = new ArrayList<JPanel>();
                        Itinerario itinerario = new Itinerario();
                        itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex()).getId());
                        ArrayList<Rota> rotasItinerario = new ArrayList<Rota>();
                        for (int i = 0; i < 5; i++) {
                            arrayAuxPnlCadastro.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                            arrayAuxPnlCadastro.get(i).setPreferredSize(new Dimension(600, 100));
                            arrayAuxPnlCadastro.get(i).add(new JLabel(""));
                            arrayAuxPnlCadastro.get(i).add(new JLabel());
                            arrayAuxPnlCadastro.get(i).add(new JLabel(""));
                            arrayAuxPnlCadastro.get(i).add(new JLabel(""));
                            arrayAuxPnlCadastro.get(i).add(new JTextField("", 8));
                            arrayAuxPnlCadastro.get(i).add(new JLabel(""));
                            arrayAuxPnlCadastro.get(i).add(new JComboBox(new String[]{"Selecione"}));

                            arrayAuxPnlCadastro.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                            arrayAuxPnlCadastro.get(i).getComponent(4).setVisible(false);
                            arrayAuxPnlCadastro.get(i).getComponent(6).setVisible(false);
                            listTabelaCidadesCadastro.add(arrayAuxPnlCadastro.get(i));
                        }
                        listTabelaCidadesCadastro.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                        
                        listTabelaCidadesRemocao.removeAll();
                        arrayAuxPnlRemocao = new ArrayList<JPanel>();
                        itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex()).getId());
                        for (int i = 0; i < 5; i++) {
                            arrayAuxPnlRemocao.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                            arrayAuxPnlRemocao.get(i).setPreferredSize(new Dimension(600, 100));
                            arrayAuxPnlRemocao.get(i).add(new JLabel(""));
                            arrayAuxPnlRemocao.get(i).add(new JLabel());
                            arrayAuxPnlRemocao.get(i).add(new JLabel(""));
                            arrayAuxPnlRemocao.get(i).add(new JLabel(""));
                            arrayAuxPnlRemocao.get(i).add(new JTextField("", 8));
                            arrayAuxPnlRemocao.get(i).add(new JLabel(""));
                            arrayAuxPnlRemocao.get(i).add(new JComboBox(new String[]{"Selecione"}));

                            arrayAuxPnlRemocao.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                            arrayAuxPnlRemocao.get(i).getComponent(4).setVisible(false);
                            arrayAuxPnlRemocao.get(i).getComponent(6).setVisible(false);
                            listTabelaCidadesRemocao.add(arrayAuxPnlRemocao.get(i));
                        }
                        listTabelaCidadesRemocao.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                    }
                }
            }
        });

        cboMinCadastro.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                }
            }
        });

        cboOnibusCadastro.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                pnlCadastro.setVisible(false);
                pnlRemocao.setVisible(false);
                btnCancelar.setVisible(false);
                cboItinerario.setSelectedItem("Selecione");
                cboItinerario.setVisible(false);
                lblItinerario.setVisible(false);
                btnCadastrar.setVisible(true);
                btnRemover.setVisible(true);
                botaoEscolhido = 0;
                pnlHorario.setBorder(BorderFactory.createTitledBorder(null, " HORARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
            }
        });

        btnConfirmaCadastro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //if(!(cboItinerario.getSelectedItem().equals("Selecione"))){
                btnCancelar.setVisible(true);
                pnlCadastro.setVisible(true);
                pnlRemocao.setVisible(false);
                cboItinerario.setSelectedItem("Selecione");
                cboItinerario.setVisible(true);
                lblItinerario.setVisible(true);
                btnCadastrar.setVisible(false);
                btnRemover.setVisible(false);
                botaoEscolhido = 1;
                pnlHorario.setBorder(BorderFactory.createTitledBorder(null, " ASSOCIAR HORARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
                //} else {
                //	JOptionPane.showMessageDialog(null, "Selecione um itinerario para associar horarios.");
                //}

            }
        });

        btnRemover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //if(!(cboItinerario.getSelectedItem().equals("Selecione"))){
                btnCancelar.setVisible(true);
                pnlCadastro.setVisible(false);
                pnlRemocao.setVisible(true);
                cboItinerario.setSelectedItem("Selecione");
                cboItinerario.setVisible(true);
                lblItinerario.setVisible(true);
                btnCadastrar.setVisible(false);
                btnRemover.setVisible(false);
                botaoEscolhido = 2;
                pnlHorario.setBorder(BorderFactory.createTitledBorder(null, " REMOVER HORARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
                //} else {
                //JOptionPane.showMessageDialog(null, "Selecione um itinerario para remover.");
                //}
            }
        });

        return pnlHorario;

    }
    
    private void inserirPnlCadastro() {
        pnlCadastro = new JPanel(null);
        pnlCadastro.setBounds(27, 90, 645, 480);
        pnlCadastro.setBackground(new Color(240, 240, 240));

        cboHoraCadastro = new JComboBox();
        cboHoraCadastro.setBounds(203, 110, 60, 30);
        cboHoraCadastro.setEnabled(false);

        cboMinCadastro = new JComboBox();
        cboMinCadastro.setBounds(123, 110, 60, 30);
        cboMinCadastro.setEnabled(false);

        cboOnibusCadastro = new JComboBox();
        cboOnibusCadastro.setBounds(453, 110, 170, 30);
        cboOnibusCadastro.setEnabled(false);

        lblHoraSaidaCadastro = new JLabel("Horario de saida:");
        lblHoraSaidaCadastro.setBounds(0, 110, 120, 30);

        lblTipoOnibusCadastro = new JLabel("Selecione o onibus:");
        lblTipoOnibusCadastro.setBounds(313, 110, 155, 30);

        btnConfirmaCadastro = new JButton("Associar Horarios");
        btnConfirmaCadastro.setBounds(323, 435, 150, 40);
        btnConfirmaCadastro.setEnabled(false);

        listTabelaCidadesCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listTabelaCidadesCadastro.setPreferredSize(new Dimension(630, 260));
        sListTabelaCidadesCadastro = new JScrollPane(listTabelaCidadesCadastro);
        sListTabelaCidadesCadastro.setBounds(0, 155, 640, 270);

        inserirPnlDiasCadastro();

        pnlCadastro.add(cboHoraCadastro);
        pnlCadastro.add(cboMinCadastro);
        pnlCadastro.add(cboOnibusCadastro);
        pnlCadastro.add(lblHoraSaidaCadastro);
        pnlCadastro.add(lblTipoOnibusCadastro);
        pnlCadastro.add(pnlItinerarioDiasCadastro);
        pnlCadastro.add(btnConfirmaCadastro);
        pnlCadastro.add(pnlItinerarioDiasCadastro);
        pnlCadastro.add(sListTabelaCidadesCadastro);

    }

    private void inserirPnlRemocao() {
        pnlRemocao = new JPanel(null);
        pnlRemocao.setBounds(27, 90, 645, 480);
        pnlRemocao.setBackground(new Color(240, 240, 240));

        btnConfirmaRemocao = new JButton("Remover Horario");
        btnConfirmaRemocao.setBounds(323, 435, 150, 40);
        btnConfirmaRemocao.setEnabled(false);

        cboHoraRemocao = new JComboBox();
        cboHoraRemocao.setBounds(203, 110, 60, 30);
        cboHoraRemocao.setEnabled(false);

        cboMinRemocao = new JComboBox();
        cboMinRemocao.setBounds(123, 110, 60, 30);
        cboMinRemocao.setEnabled(false);

        lblHoraSaidaRemocao = new JLabel("Horario de saida:");
        lblHoraSaidaRemocao.setBounds(0, 110, 120, 30);

        listTabelaCidadesRemocao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listTabelaCidadesRemocao.setPreferredSize(new Dimension(630, 260));
        sListTabelaCidadesRemocao = new JScrollPane(listTabelaCidadesRemocao);
        sListTabelaCidadesRemocao.setBounds(0, 155, 640, 270);

        inserirPnlDiasRemocao();

        pnlRemocao.add(pnlItinerarioDiasRemocao);
        pnlRemocao.add(btnConfirmaRemocao);
        pnlRemocao.add(lblHoraSaidaRemocao);
        pnlRemocao.add(cboMinRemocao);
        pnlRemocao.add(cboHoraRemocao);
        pnlRemocao.add(sListTabelaCidadesRemocao);

        btnConfirmaRemocao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });
    }

    private void inserirPnlDiasCadastro() {
        pnlItinerarioDiasCadastro = new JPanel();

        pnlItinerarioDiasCadastro.setBounds(0, 0, 640, 100);
        //pnlItinerarioDias.setPreferredSize(new Dimension(885, 650));
        pnlItinerarioDiasCadastro.setBorder(BorderFactory.createTitledBorder(null, " Dias de Operacao ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlItinerarioDiasCadastro.setBackground(new Color(240, 240, 240));
        pnlItinerarioDiasCadastro.setLayout(gridLayout2_4);


        chBxSegundaFeiraCadastro = new JCheckBox("Segunda-feira");
        chBxTercaFeiraCadastro = new JCheckBox("Terca-feira");
        chBxQuartaFeiraCadastro = new JCheckBox("Quarta-feira");
        chBxQuintaFeiraCadastro = new JCheckBox("Quinta-feira");
        chBxSextaFeiraCadastro = new JCheckBox("Sexta-feira");
        chBxSabadoCadastro = new JCheckBox("Sabado");
        chBxDomingoCadastro = new JCheckBox("Domingo");
        chBxFeriadosCadastro = new JCheckBox("Feriados");


        pnlItinerarioDiasCadastro.add(chBxSegundaFeiraCadastro);
        pnlItinerarioDiasCadastro.add(chBxTercaFeiraCadastro);
        pnlItinerarioDiasCadastro.add(chBxQuartaFeiraCadastro);
        pnlItinerarioDiasCadastro.add(chBxQuintaFeiraCadastro);
        pnlItinerarioDiasCadastro.add(chBxSextaFeiraCadastro);
        pnlItinerarioDiasCadastro.add(chBxSabadoCadastro);
        pnlItinerarioDiasCadastro.add(chBxDomingoCadastro);
        pnlItinerarioDiasCadastro.add(chBxFeriadosCadastro);

        chBxDomingoCadastro.setEnabled(false);
        chBxSegundaFeiraCadastro.setEnabled(false);
        chBxTercaFeiraCadastro.setEnabled(false);
        chBxQuartaFeiraCadastro.setEnabled(false);
        chBxQuintaFeiraCadastro.setEnabled(false);
        chBxSextaFeiraCadastro.setEnabled(false);
        chBxSabadoCadastro.setEnabled(false);
        chBxFeriadosCadastro.setEnabled(false);

    }

    private void inserirPnlDiasRemocao() {
        pnlItinerarioDiasRemocao = new JPanel();

        pnlItinerarioDiasRemocao.setBounds(0, 0, 640, 100);
        pnlItinerarioDiasRemocao.setBorder(BorderFactory.createTitledBorder(null, " Dias de Operacao ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlItinerarioDiasRemocao.setBackground(new Color(240, 240, 240));
        pnlItinerarioDiasRemocao.setLayout(gridLayout2_4);

        chBxSegundaFeiraRemocao = new JCheckBox("Segunda-feira");
        chBxTercaFeiraRemocao = new JCheckBox("Terca-feira");
        chBxQuartaFeiraRemocao = new JCheckBox("Quarta-feira");
        chBxQuintaFeiraRemocao = new JCheckBox("Quinta-feira");
        chBxSextaFeiraRemocao = new JCheckBox("Sexta-feira");
        chBxSabadoRemocao = new JCheckBox("Sabado");
        chBxDomingoRemocao = new JCheckBox("Domingo");
        chBxFeriadosRemocao = new JCheckBox("Feriados");


        pnlItinerarioDiasRemocao.add(chBxSegundaFeiraRemocao);
        pnlItinerarioDiasRemocao.add(chBxTercaFeiraRemocao);
        pnlItinerarioDiasRemocao.add(chBxQuartaFeiraRemocao);
        pnlItinerarioDiasRemocao.add(chBxQuintaFeiraRemocao);
        pnlItinerarioDiasRemocao.add(chBxSextaFeiraRemocao);
        pnlItinerarioDiasRemocao.add(chBxSabadoRemocao);
        pnlItinerarioDiasRemocao.add(chBxDomingoRemocao);
        pnlItinerarioDiasRemocao.add(chBxFeriadosRemocao);

        chBxDomingoRemocao.setEnabled(false);
        chBxSegundaFeiraRemocao.setEnabled(false);
        chBxTercaFeiraRemocao.setEnabled(false);
        chBxQuartaFeiraRemocao.setEnabled(false);
        chBxQuintaFeiraRemocao.setEnabled(false);
        chBxSextaFeiraRemocao.setEnabled(false);
        chBxSabadoRemocao.setEnabled(false);
        chBxFeriadosRemocao.setEnabled(false);
    }

    public void carregaComboItinerario() {
        daoRotaItinerario = new DaoRotaItinerario();
        arrayItinerario = daoRotaItinerario.consultarItinerariosCadastrados();
        cboItinerario.removeAllItems();
        cboItinerario.addItem("Selecione");
        for (int i = 0; i < arrayItinerario.size(); i++) {
            cboItinerario.addItem(arrayItinerario.get(i).getItinerario_cidadeOrigem() + " - " + arrayItinerario.get(i).getItinerario_cidadeDestino());
        }
    }

    private void carregaComboMinuto() {//SALVAR IDS
    }

    private void carregaComboHora() {//SALVAR IDS
    }

    private void carregaComboMotorista() {
    }

    private void carregaComboOnibus() {
    }

    private Horario clonaHorario(Horario horario, int dia) {
        Horario novoHorario = new Horario();
        novoHorario.setHorario_MotoristaId(horario.getHorario_MotoristaId());
        novoHorario.setHorario_OnibusId(horario.getHorario_OnibusId());
        novoHorario.setHorario_RotaItinerarioId(horario.getHorario_RotaItinerarioId());
        novoHorario.setHorarioChegada(horario.getHorarioChegada());
        novoHorario.setHorarioDia(dia);
        novoHorario.setHorarioId(0);
        novoHorario.setHorarioPreco(horario.getHorarioPreco());
        novoHorario.setHorarioSaida(horario.getHorarioSaida());
        return novoHorario;
    }

    public void reinicia() {

        cboItinerario.setEnabled(true);
        cboItinerario.setSelectedIndex(0);

        cboOnibusCadastro.setEnabled(false);
        cboOnibusCadastro.setSelectedIndex(0);

        cboHoraCadastro.setSelectedIndex(0);
        cboHoraCadastro.setEnabled(false);

        cboMinCadastro.setEnabled(false);
        cboMinCadastro.setSelectedIndex(0);

        btnConfirmaCadastro.setEnabled(false);

        chBxDomingoCadastro.setSelected(false);
        chBxSegundaFeiraCadastro.setSelected(false);
        chBxTercaFeiraCadastro.setSelected(false);
        chBxQuartaFeiraCadastro.setSelected(false);
        chBxQuintaFeiraCadastro.setSelected(false);
        chBxSextaFeiraCadastro.setSelected(false);
        chBxSabadoCadastro.setSelected(false);
        chBxFeriadosCadastro.setSelected(false);
        chBxDomingoCadastro.setEnabled(false);
        chBxSegundaFeiraCadastro.setEnabled(false);
        chBxTercaFeiraCadastro.setEnabled(false);
        chBxQuartaFeiraCadastro.setEnabled(false);
        chBxQuintaFeiraCadastro.setEnabled(false);
        chBxSextaFeiraCadastro.setEnabled(false);
        chBxSabadoCadastro.setEnabled(false);
        chBxFeriadosCadastro.setEnabled(false);
    }
    private DaoRotaItinerario daoRotaItinerario;
    private DaoItinerario daoItinerario;
    private NumberFormat format;
    private Font fontePadrao;
    private GridLayout gridLayout2_4;
    private ArrayList<Itinerario> arrayItinerario;
    private int botaoEscolhido;
    //Jpanel
    private JPanel pnlHorario;
    private JPanel pnlItinerarioDiasCadastro;
    private JPanel pnlItinerarioDiasRemocao;
    private JPanel pnlCadastro;
    private JPanel pnlRemocao;
    //JLbabels
    private JLabel lblItinerario;
    private JLabel lblHoraSaidaCadastro;
    private JLabel lblHoraSaidaRemocao;
    private JLabel lblTipoOnibusCadastro;
    //JButtons
    private JButton btnConfirmaCadastro;
    private JButton btnConfirmaRemocao;
    private JButton btnCancelar;
    private JButton btnRemover;
    private JButton btnCadastrar;
    //ComboBoxes
    private JComboBox cboItinerario;
    private JComboBox cboMinCadastro;
    private JComboBox cboHoraCadastro;
    private JComboBox cboMinRemocao;
    private JComboBox cboHoraRemocao;
    private JComboBox cboOnibusCadastro;
    //CheckBox
    private JCheckBox chBxSegundaFeiraCadastro;
    private JCheckBox chBxTercaFeiraCadastro;
    private JCheckBox chBxQuartaFeiraCadastro;
    private JCheckBox chBxQuintaFeiraCadastro;
    private JCheckBox chBxSextaFeiraCadastro;
    private JCheckBox chBxSabadoCadastro;
    private JCheckBox chBxDomingoCadastro;
    private JCheckBox chBxFeriadosCadastro;
    private JCheckBox chBxSegundaFeiraRemocao;
    private JCheckBox chBxTercaFeiraRemocao;
    private JCheckBox chBxQuartaFeiraRemocao;
    private JCheckBox chBxQuintaFeiraRemocao;
    private JCheckBox chBxSextaFeiraRemocao;
    private JCheckBox chBxSabadoRemocao;
    private JCheckBox chBxDomingoRemocao;
    private JCheckBox chBxFeriadosRemocao;
    //Tabelona Cidades Cadastro
    private JPanel listTabelaCidadesCadastro;
    private JScrollPane sListTabelaCidadesCadastro;
    private ArrayList<JPanel> arrayAuxPnlCadastro;
    //Tabelona Cidades Remocao
    private JPanel listTabelaCidadesRemocao;
    private JScrollPane sListTabelaCidadesRemocao;
    private ArrayList<JPanel> arrayAuxPnlRemocao;
}
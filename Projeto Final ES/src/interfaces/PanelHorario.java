package interfaces;

import dao.DaoHorario;
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
import java.text.DateFormat;
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

    public JPanel inserirPnlItinerario() {
        format = NumberFormat.getNumberInstance();
        format.setMinimumIntegerDigits(2);
        gridLayout2_4 = new GridLayout(2, 4);
        fontePadrao = new Font("Segoe UI", 1, 14);

        pnlItinerario = new JPanel();
        pnlItinerario.setSize(695, 590);
        pnlItinerario.setPreferredSize(new Dimension(885, 650));
        pnlItinerario.setBorder(BorderFactory.createTitledBorder(null, " ASSOCIAR HORARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlItinerario.setBackground(new Color(240, 240, 240));
        pnlItinerario.setLayout(null);
        pnlItinerario.setOpaque(true);
        pnlItinerario.setVisible(false);

        lblItinerario = new JLabel();
        lblItinerario.setText("Selecione o Itinerario:");
        lblItinerario.setBounds(27, 50, 155, 20);

        cboItinerario = new JComboBox(new String[]{"Selecione"});
        cboItinerario.setBounds(187, 45, 222, 30);

        cboItinerarioHora = new JComboBox();
        cboItinerarioHora.setBounds(230, 200, 60, 30);
        cboItinerarioHora.setEnabled(false);

        cboItinerarioMin = new JComboBox();
        cboItinerarioMin.setBounds(150, 200, 60, 30);
        cboItinerarioMin.setEnabled(false);

        cboItinerarioOnibus = new JComboBox();
        cboItinerarioOnibus.setBounds(480, 200, 170, 30);
        cboItinerarioOnibus.setEnabled(false);

        lblItinerarioHoraSaida = new JLabel("Horario de saida:");
        lblItinerarioHoraSaida.setBounds(27, 200, 120, 30);

        lblItinerarioTipoOnibus = new JLabel("Selecione o onibus:");
        lblItinerarioTipoOnibus.setBounds(340, 200, 155, 30);

        btnItinerarioConfirma = new JButton("Associar Horarios");
        btnItinerarioConfirma.setBounds(350, 525, 150, 40);
        btnItinerarioConfirma.setEnabled(false);

        btnItinerarioCancela = new JButton("Cancelar");
        btnItinerarioCancela.setBounds(515, 525, 150, 40);

        inserirPnlItinerarioDias();
        inserirPnlRotas();

        pnlItinerario.add(btnItinerarioConfirma);
        pnlItinerario.add(btnItinerarioCancela);

        pnlItinerario.add(cboItinerario);
        pnlItinerario.add(cboItinerarioHora);
        pnlItinerario.add(cboItinerarioMin);
        pnlItinerario.add(cboItinerarioOnibus);

        pnlItinerario.add(lblItinerario);
        pnlItinerario.add(lblItinerarioHoraSaida);
        pnlItinerario.add(lblItinerarioTipoOnibus);

        pnlItinerario.add(pnlItinerarioDias);

        carregaComboItinerario();
        carregaComboMotorista();
        carregaComboOnibus();
        carregaComboMinuto();
        carregaComboHora();

        daoHorario = new DaoHorario();
        daoItinerario = new DaoItinerario();

        cboItinerario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    
                    if (!(cboItinerario.getSelectedItem().equals("Selecione"))) {
                        listTabelaCidades.removeAll();
                        arrayAuxPnl = new ArrayList<JPanel>();
                        Itinerario itinerario = new Itinerario();
                        itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex() - 1).getId());
                        ArrayList<Rota> rotasItinerario = new ArrayList<Rota>();
                        rotasItinerario = daoItinerario.consultarRotasdoItinerario(itinerario);
                        for (int i = 0; i < rotasItinerario.size(); i++) {
                            arrayAuxPnl.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                            arrayAuxPnl.get(i).setPreferredSize(new Dimension(600, 100));
                            arrayAuxPnl.get(i).add(new JLabel("Parada " + (i + 1) + ": " + rotasItinerario.get(i).getRota_cidadeDestino()));
                            arrayAuxPnl.get(i).add(new JLabel());
                            arrayAuxPnl.get(i).add(new JLabel("Horario Saida:   X (inserir)"));
                            arrayAuxPnl.get(i).add(new JLabel("Preço: "));
                            arrayAuxPnl.get(i).add(new JTextField("", 8));
                            arrayAuxPnl.get(i).add(new JLabel("Motorista: "));
                            arrayAuxPnl.get(i).add(new JComboBox(new String[]{"Selecione"}));
                            for(int j=0;j<7;j++){
                                arrayAuxPnl.get(i).getComponent(j).setEnabled(false);
                            }
                            arrayAuxPnl.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                            arrayAuxPnl.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                            arrayAuxPnl.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                            arrayAuxPnl.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                            arrayAuxPnl.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                            arrayAuxPnl.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                            listTabelaCidades.add(arrayAuxPnl.get(i));
                        }
                        listTabelaCidades.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                        listTabelaCidades.setEnabled(false);
                    } else {
                        listTabelaCidades.removeAll();
                        arrayAuxPnl = new ArrayList<JPanel>();
                        Itinerario itinerario = new Itinerario();
                        itinerario.setId(arrayItinerario.get(cboItinerario.getSelectedIndex()).getId());
                        ArrayList<Rota> rotasItinerario = new ArrayList<Rota>();
                        for (int i = 0; i < 5; i++) {
                            arrayAuxPnl.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0)));
                            arrayAuxPnl.get(i).setPreferredSize(new Dimension(600, 100));
                            arrayAuxPnl.get(i).add(new JLabel(""));
                            arrayAuxPnl.get(i).add(new JLabel());
                            arrayAuxPnl.get(i).add(new JLabel(""));
                            arrayAuxPnl.get(i).add(new JLabel(""));
                            arrayAuxPnl.get(i).add(new JTextField("", 8));
                            arrayAuxPnl.get(i).add(new JLabel(""));
                            arrayAuxPnl.get(i).add(new JComboBox(new String[]{"Selecione"}));

                            arrayAuxPnl.get(i).getComponent(0).setPreferredSize(new Dimension(200, 30));
                            arrayAuxPnl.get(i).getComponent(1).setPreferredSize(new Dimension(300, 30));
                            arrayAuxPnl.get(i).getComponent(2).setPreferredSize(new Dimension(150, 30));
                            arrayAuxPnl.get(i).getComponent(3).setPreferredSize(new Dimension(40, 30));
                            arrayAuxPnl.get(i).getComponent(5).setPreferredSize(new Dimension(60, 30));
                            arrayAuxPnl.get(i).getComponent(6).setPreferredSize(new Dimension(170, 30));
                            arrayAuxPnl.get(i).getComponent(4).setVisible(false);
                            arrayAuxPnl.get(i).getComponent(6).setVisible(false);
                            listTabelaCidades.add(arrayAuxPnl.get(i));
                        }
                        listTabelaCidades.setPreferredSize(new Dimension(610, rotasItinerario.size() * 100));
                    }
                }
            }
        });

        cboItinerarioMin.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                }
            }
        });

        cboItinerarioOnibus.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                }
            }
        });

        btnItinerarioCancela.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });

        btnItinerarioConfirma.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });

        return pnlItinerario;

    }

    private void inserirPnlItinerarioDias() {
        pnlItinerarioDias = new JPanel();

        pnlItinerarioDias.setBounds(25, 90, 640, 100);
        //pnlItinerarioDias.setPreferredSize(new Dimension(885, 650));
        pnlItinerarioDias.setBorder(BorderFactory.createTitledBorder(null, " Dias de Operacao ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlItinerarioDias.setBackground(new Color(240, 240, 240));
        pnlItinerarioDias.setLayout(gridLayout2_4);


        chBxSegundaFeira = new JCheckBox("Segunda-feira");
        chBxTercaFeira = new JCheckBox("Terca-feira");
        chBxQuartaFeira = new JCheckBox("Quarta-feira");
        chBxQuintaFeira = new JCheckBox("Quinta-feira");
        chBxSextaFeira = new JCheckBox("Sexta-feira");
        chBxSabado = new JCheckBox("Sabado");
        chBxDomingo = new JCheckBox("Domingo");
        chBxFeriados = new JCheckBox("Feriados");


        pnlItinerarioDias.add(chBxSegundaFeira);
        pnlItinerarioDias.add(chBxTercaFeira);
        pnlItinerarioDias.add(chBxQuartaFeira);
        pnlItinerarioDias.add(chBxQuintaFeira);
        pnlItinerarioDias.add(chBxSextaFeira);
        pnlItinerarioDias.add(chBxSabado);
        pnlItinerarioDias.add(chBxDomingo);
        pnlItinerarioDias.add(chBxFeriados);

        chBxDomingo.setEnabled(false);
        chBxSegundaFeira.setEnabled(false);
        chBxTercaFeira.setEnabled(false);
        chBxQuartaFeira.setEnabled(false);
        chBxQuintaFeira.setEnabled(false);
        chBxSextaFeira.setEnabled(false);
        chBxSabado.setEnabled(false);
        chBxFeriados.setEnabled(false);

    }

    private void inserirPnlRotas() {
        listTabelaCidades = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listTabelaCidades.setPreferredSize(new Dimension(630, 260));
        sListTabelaCidades = new JScrollPane(listTabelaCidades);
        sListTabelaCidades.setBounds(25, 245, 640, 270);
        pnlItinerario.add(sListTabelaCidades);
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
    //Geral

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

        cboItinerarioOnibus.setEnabled(false);
        cboItinerarioOnibus.setSelectedIndex(0);

        cboItinerarioHora.setSelectedIndex(0);
        cboItinerarioHora.setEnabled(false);

        cboItinerarioMin.setEnabled(false);
        cboItinerarioMin.setSelectedIndex(0);

        btnItinerarioConfirma.setEnabled(false);

        chBxDomingo.setSelected(false);
        chBxSegundaFeira.setSelected(false);
        chBxTercaFeira.setSelected(false);
        chBxQuartaFeira.setSelected(false);
        chBxQuintaFeira.setSelected(false);
        chBxSextaFeira.setSelected(false);
        chBxSabado.setSelected(false);
        chBxFeriados.setSelected(false);
        chBxDomingo.setEnabled(false);
        chBxSegundaFeira.setEnabled(false);
        chBxTercaFeira.setEnabled(false);
        chBxQuartaFeira.setEnabled(false);
        chBxQuintaFeira.setEnabled(false);
        chBxSextaFeira.setEnabled(false);
        chBxSabado.setEnabled(false);
        chBxFeriados.setEnabled(false);
    }
    
    private DaoRotaItinerario daoRotaItinerario;
    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    private NumberFormat format;
    private Font fontePadrao;
    private GridLayout gridLayout2_4;
    //Jpanel
    private JPanel pnlItinerario;
    private JPanel pnlItinerarioDias;
    //JLbabels
    private JLabel lblItinerario;
    private JLabel lblItinerarioHoraSaida;
    private JLabel lblItinerarioTipoOnibus;
    //JButtons
    private JButton btnItinerarioConfirma;
    private JButton btnItinerarioCancela;
    //ComboBoxes
    private JComboBox cboItinerario;
    private JComboBox cboItinerarioMin;
    private JComboBox cboItinerarioHora;
    private JComboBox cboItinerarioOnibus;
    //CheckBox
    private JCheckBox chBxSegundaFeira;
    private JCheckBox chBxTercaFeira;
    private JCheckBox chBxQuartaFeira;
    private JCheckBox chBxQuintaFeira;
    private JCheckBox chBxSextaFeira;
    private JCheckBox chBxSabado;
    private JCheckBox chBxDomingo;
    private JCheckBox chBxFeriados;
    //Tabelona Cidades
    private int acumuladorTabelaCidades;
    private JPanel listTabelaCidades;
    private JScrollPane sListTabelaCidades;
    private ArrayList<Itinerario> arrayItinerario;
    private ArrayList<JPanel> arrayAuxPnl;
    private DaoHorario daoHorario;
    private DaoItinerario daoItinerario;
}
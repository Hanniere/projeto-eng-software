package interfaces;

import dao.DaoCidade;
import entidades.RotaItinerario;
import dao.DaoItinerario;
import dao.DaoRota;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import dao.DaoRotaItinerario;
import entidades.Cidade;
import entidades.Itinerario;
import entidades.Rota;
import java.awt.FlowLayout;

public class PanelRotaItinerario extends JPanel {

    public JPanel inserirPnlItinerario() {
        fontePadrao = new Font("Segoe UI", 1, 14);

        pnlItinerario = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pnlItinerario.setSize(695, 590);
        pnlItinerario.setBorder(BorderFactory.createTitledBorder(null, " ASSOCIAR ROTAS A ITINERARIOS ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlItinerario.setBackground(new Color(240, 240, 240));
        pnlItinerario.setOpaque(true);
        pnlItinerario.setVisible(false);

        inserirPnlCadastro();
        inserirPnlRemocao();

        pnlItinerario.add(pnlCadastro);
        pnlItinerario.add(pnlRemocao);

        carregaComboItinerario();

        cboItinerarioCadastro.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    if (!(cboItinerarioCadastro.getSelectedItem().equals("Selecione"))) {
                        selectedCboIndexItinerario = cboItinerarioCadastro.getSelectedIndex() - 1;
                        carregaComboDestino(arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeOrigemId()); //carregando destinos alcancaveis da cidade selecionada
                        txtCadastroOrigem.setText(arrayRotaAtualCadastro.get(0).getRota_cidadeOrigem()); //setando primeiramente Origem           
                        cboItinerarioCadastro.setEnabled(false);
                        cboCadastroDestino.setEnabled(true);
                        btnCadastroAddRota.setEnabled(true);

                    }
                }
            }
        });

        btnCadastroCancela.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                //initComponents();
                reinicia();


            }
        });

        btnCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                daoRotaItinerario = new DaoRotaItinerario();
                for (int i = 0; i < arrayRotaItinerarioCadastro.size(); i++) {
                    daoRotaItinerario.cadastrarNovoRotaItinerario(arrayRotaItinerarioCadastro.get(i));
                }
                ordemRota = 0;
                reinicia();
            }
        });

        return pnlItinerario;

    }

    private void inserirPnlCadastro() {
        pnlCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 10));
        pnlCadastro.setPreferredSize(new Dimension(650, 290));
        pnlCadastro.setBackground(new Color(240, 240, 240));
        pnlCadastro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Cadastro ", TitledBorder.LEFT, TitledBorder.TOP, fontePadrao));

        //pnlRotaCadastro        
        pnlCadastroRota = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        pnlCadastroRota.setPreferredSize(new Dimension(310, 160));
        pnlCadastroRota.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Rotas do Itinerario ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlCadastroRota.setBackground(new Color(240, 240, 240));

        lblCadastroOrigem = new JLabel("Origem:");
        lblCadastroDestino = new JLabel("Destino:");
        txtCadastroOrigem = new JTextField();
        cboCadastroDestino = new JComboBox(new String[]{"Selecione"});
        btnCadastroAddRota = new JButton("Adicionar Rota");
        arrayRotasAddicionadasCadastro = new ArrayList<Rota>();
        btnCadastrar = new JButton("Cadastrar");
        btnCadastroCancela = new JButton("Cancela");
        lblItinerarioCadastro = new JLabel("Selecione o Itinerario:");
        cboItinerarioCadastro = new JComboBox(new String[]{"Selecione"});
        arrayRotaAtualCadastro = new ArrayList<Rota>();
        arrayRotaItinerarioCadastro = new ArrayList<RotaItinerario>(); //inicializando novo RotaItinerario

        lblItinerarioCadastro.setPreferredSize(new Dimension(155, 20));
        cboItinerarioCadastro.setPreferredSize(new Dimension(222, 30));
        btnCadastroCancela.setPreferredSize(new Dimension(150, 40));
        btnCadastrar.setPreferredSize(new Dimension(150, 40));
        lblCadastroOrigem.setPreferredSize(new Dimension(70, 20));
        lblCadastroDestino.setPreferredSize(new Dimension(70, 20));
        txtCadastroOrigem.setPreferredSize(new Dimension(180, 30));
        cboCadastroDestino.setPreferredSize(new Dimension(180, 30));
        btnCadastroAddRota.setPreferredSize(new Dimension(180, 30));

        txtCadastroOrigem.setEnabled(false);
        cboCadastroDestino.setEnabled(false);
        btnCadastroAddRota.setEnabled(false);
        btnCadastrar.setEnabled(false);

        pnlCadastroRota.add(lblCadastroOrigem);
        pnlCadastroRota.add(txtCadastroOrigem);
        pnlCadastroRota.add(lblCadastroDestino);
        pnlCadastroRota.add(cboCadastroDestino);
        pnlCadastroRota.add(btnCadastroAddRota);

        //pnlListaCadastro
        pnlCadastroLista = new JPanel(null);
        pnlCadastroLista.setPreferredSize(new Dimension(325, 160));
        pnlCadastroLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Rotas Adicionadas ", TitledBorder.CENTER, TitledBorder.CENTER, fontePadrao));
        pnlCadastroLista.setBackground(new Color(240, 240, 240));

        tbmCadastro = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        tableRotasCadastro = new JTable(tbmCadastro);
        JScrollPane scrollPaneCadastro = new JScrollPane(tableRotasCadastro);
        scrollPaneCadastro.setBounds(2, 20, 321, 138);
        tableRotasCadastro.setVisible(false);

        tableRotasCadastro.setToolTipText("Clique duas vezes para alterar rotas.");
        tbmCadastro.addColumn("Origem");
        tbmCadastro.addColumn("Destino");
        tableRotasCadastro.setFillsViewportHeight(true);

        pnlCadastroLista.add(scrollPaneCadastro, BorderLayout.CENTER);

        pnlCadastroBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlCadastroBotao.setPreferredSize(new Dimension(635, 45));
        pnlCadastroBotao.setBackground(new Color(240, 240, 240));

        pnlCadastro.add(lblItinerarioCadastro);
        pnlCadastro.add(cboItinerarioCadastro);
        pnlCadastro.add(pnlCadastroRota);
        pnlCadastro.add(pnlCadastroLista);
        pnlCadastro.add(pnlCadastroBotao);

        pnlCadastroBotao.add(btnCadastrar);
        pnlCadastroBotao.add(btnCadastroCancela);

        btnCadastroAddRota.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cboCadastroDestino.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Campos obrigatorios!");
                    cboCadastroDestino.requestFocus();

                } else if (cboCadastroDestino.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um destino!");
                    cboCadastroDestino.requestFocus();
                } else {
                    int idRotaItinerario = 1;
                    int idRota = arrayRotaAtualCadastro.get(cboCadastroDestino.getSelectedIndex() - 1).getId();
                    Rota rotaAux = arrayRotaAtualCadastro.get(cboCadastroDestino.getSelectedIndex() - 1);
                    arrayRotasAddicionadasCadastro.add(rotaAux); //salvando Array de rotas Para possivel utilizacao
                    tbmCadastro.addRow(new Object[]{rotaAux.getRota_cidadeOrigem(), rotaAux.getRota_cidadeDestino()});
                    int idItinerario = arrayItinerario.get(selectedCboIndexItinerario).getId();
                    if (arrayRotaItinerarioCadastro.isEmpty()) {
                        ordemRota++;
                    } else {
                        ordemRota = (arrayRotaItinerarioCadastro.get(arrayRotaItinerarioCadastro.size() - 1).getRotaitinerarioOrdem()) + 1; //somando um a ordem anterior
                        idRotaItinerario = (arrayRotaItinerarioCadastro.get(arrayRotaItinerarioCadastro.size() - 1).getRotaItinerarioId()) + 1; //somando um ao id anterior          		
                    }
                    RotaItinerario RI = new RotaItinerario();
                    RI.setRotaItinerarioId(idRotaItinerario);
                    RI.setRotaitinerario_rotaId(idRota);
                    RI.setRotaitinerario_itinerarioId(idItinerario);
                    RI.setRotaitinerarioOrdem(ordemRota);
                    arrayRotaItinerarioCadastro.add(RI);
                    // Nao esquecer de zerar ordem quando finalizar a insercao

                    if (rotaAux.getRota_cidadeDestinoId() == arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeDestinoId()) {
                        btnCadastrar.setEnabled(true);
                    }
                    int idCidadeOrigemAux = arrayRotaAtualCadastro.get(cboCadastroDestino.getSelectedIndex() - 1).getRota_cidadeDestinoId(); //salvando id da nova cidade Origem
                    DaoCidade daoCidade = new DaoCidade();
                    Cidade cidade = new Cidade();
                    cidade.setId(idCidadeOrigemAux);
                    cidade = daoCidade.consultaCidade(cidade);
                    txtCadastroOrigem.setText(cidade.getNome()); //atualizando txt origem
                    carregaComboDestino(idCidadeOrigemAux); //remotando comboDestino

                    tableRotasCadastro.setVisible(true);
                    btnCadastroCancela.setEnabled(true);


                    if (arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeDestino().equals(arrayRotasAddicionadasCadastro.get(arrayRotasAddicionadasCadastro.size() - 1).getRota_cidadeDestino())) {
                        btnCadastroAddRota.setEnabled(false);
                        btnCadastrar.setEnabled(true);
                        cboCadastroDestino.setEnabled(false);
                    }
                }
            }
        });

        tableRotasCadastro.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    rowIndexSelected = target.getSelectedRow();
                    System.out.println(rowIndexSelected);
                    qtdeCliquesTabela++;
                    if (rowIndexSelected != -1) {
                        qtdeCliquesTabela = 0;
                        //btnItinerarioAlterarRota.setEnabled(true);
                        if (rowIndexSelected == 0) {
                            tableRotasCadastro.setVisible(false);
                        }

                        cboCadastroDestino.removeAllItems();
                        cboCadastroDestino.addItem("Selecione");
                        arrayRotaAtualCadastro.clear();

                        //recarregando o cbo de destino
                        txtCadastroOrigem.setText(arrayRotasAddicionadasCadastro.get(rowIndexSelected).getRota_cidadeOrigem());
                        carregaComboDestino(arrayRotasAddicionadasCadastro.get(rowIndexSelected).getRota_cidadeOrigemId());

                        for (int j = arrayRotasAddicionadasCadastro.size() - 1; j >= rowIndexSelected; j--) {
                            arrayRotasAddicionadasCadastro.remove(j);
                            tbmCadastro.removeRow(j);
                            arrayRotaItinerarioCadastro.remove(j);
                        }
                        tbmCadastro.fireTableDataChanged(); //atualizando table de rotas
                        btnCadastrar.setEnabled(false);
                        btnCadastroAddRota.setEnabled(true);
                        cboCadastroDestino.setEnabled(true);
                    }
                }
            }
        });

    }

    private void inserirPnlRemocao() {
        pnlRemocao = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 10));
        pnlRemocao.setPreferredSize(new Dimension(650, 210));
        pnlRemocao.setBackground(new Color(240, 240, 240));
        pnlRemocao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Remocao ", TitledBorder.LEFT, TitledBorder.TOP, fontePadrao));

        pnlRemocaoCampos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 10));
        pnlRemocaoCampos.setPreferredSize(new Dimension(310, 130));
        pnlRemocaoCampos.setBackground(new Color(240, 240, 240));

        pnlRemocaoLista = new JPanel(null);
        pnlRemocaoLista.setPreferredSize(new Dimension(325, 170));
        pnlRemocaoLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Rotas Adicionadas ", TitledBorder.CENTER, TitledBorder.CENTER, fontePadrao));
        pnlRemocaoLista.setBackground(new Color(240, 240, 240));

        tbmRemocao = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tableRotasRemocao = new JTable(tbmRemocao);
        JScrollPane scrollPaneRemocao = new JScrollPane(tableRotasRemocao);
        scrollPaneRemocao.setBounds(2, 20, 321, 148);
        tableRotasRemocao.setVisible(false);
//        tableRotasCadastro.getCellEditor().cancelCellEditing();

        tableRotasRemocao.setToolTipText("Clique duas vezes para alterar rotas.");
        tbmRemocao.addColumn("Origem");
        tbmRemocao.addColumn("Destino");
        tableRotasRemocao.setFillsViewportHeight(true);

        pnlRemocaoLista.add(scrollPaneRemocao, BorderLayout.CENTER);

        lblItinerarioRemocao = new JLabel("Selecione o Itinerario:");
        cboItinerarioRemocao = new JComboBox(new String[]{"Selecione"});
        btnRemover = new JButton("Remover");

        lblItinerarioRemocao.setPreferredSize(new Dimension(255, 20));
        cboItinerarioRemocao.setPreferredSize(new Dimension(255, 30));
        btnRemover.setPreferredSize(new Dimension(150, 40));

        pnlRemocaoCampos.add(lblItinerarioRemocao);
        pnlRemocaoCampos.add(cboItinerarioRemocao);
        pnlRemocaoCampos.add(btnRemover);
        pnlRemocao.add(pnlRemocaoCampos);
        pnlRemocao.add(pnlRemocaoLista);
    }

    public void carregaComboItinerario() {
        daoItinerario = new DaoItinerario();
        arrayItinerario = daoItinerario.consultarTodosItinerarios();

        cboItinerarioCadastro.removeAllItems();
        cboItinerarioCadastro.addItem("Selecione");
        for (int i = 0; i < arrayItinerario.size(); i++) {
            cboItinerarioCadastro.addItem(arrayItinerario.get(i).getItinerario_cidadeOrigem() + " - " + arrayItinerario.get(i).getItinerario_cidadeDestino());
        }
    }

    private void carregaComboDestino(int idCidadeOrigem) {
        daoRota = new DaoRota();
        arrayRotaAtualCadastro = daoRota.carregaRotas(idCidadeOrigem);
        cboCadastroDestino.removeAllItems();
        cboCadastroDestino.addItem("Selecione");
        for (int i = 0; i < arrayRotaAtualCadastro.size(); i++) {
            cboCadastroDestino.addItem(arrayRotaAtualCadastro.get(i).getRota_cidadeDestino());

        }
    }

    public void reinicia() {

        cboCadastroDestino.removeAllItems();
        cboCadastroDestino.addItem("Selecione");
        txtCadastroOrigem.setText("");

        arrayRotaAtualCadastro.clear();
        arrayRotaItinerarioCadastro.clear();
        arrayRotasAddicionadasCadastro.clear();

        tbmCadastro.getDataVector().removeAllElements();
        tbmCadastro.fireTableDataChanged();

        cboItinerarioCadastro.setEnabled(true);
        cboItinerarioCadastro.setSelectedIndex(0);

        tableRotasCadastro.setVisible(false);

        cboCadastroDestino.setSelectedIndex(0);
        cboCadastroDestino.setEnabled(false);

        btnCadastroAddRota.setEnabled(false);
        btnCadastrar.setEnabled(false);

    }
    JTable tableRotasCadastro;
    DefaultTableModel tbmCadastro;
    JTable tableRotasRemocao;
    DefaultTableModel tbmRemocao;
    private DaoItinerario daoItinerario;
    private DaoRota daoRota;
    private DaoRotaItinerario daoRotaItinerario;
    private ArrayList<Itinerario> arrayItinerario;
    private ArrayList<Rota> arrayRotaAtualCadastro;
    private ArrayList<Rota> arrayRotasAddicionadasCadastro;
    private ArrayList<RotaItinerario> arrayRotaItinerarioCadastro;
    private int selectedCboIndexItinerario;
    private int ordemRota = 0;
    private int rowIndexSelected;
    private int qtdeCliquesTabela = 0;
    private Font fontePadrao;
    //JTextFields
    private JTextField txtCadastroOrigem;
    //Jpanel
    private JPanel pnlCadastro;
    private JPanel pnlRemocao;
    private JPanel pnlCadastroRota;
    private JPanel pnlCadastroLista;
    private JPanel pnlCadastroBotao;
    private JPanel pnlRemocaoLista;
    private JPanel pnlRemocaoCampos;
    private JPanel pnlItinerario;
    //JLbabels
    private JLabel lblItinerarioCadastro;
    private JLabel lblItinerarioRemocao;
    private JLabel lblCadastroOrigem;
    private JLabel lblCadastroDestino;
    //JButtons
    private JButton btnCadastroAddRota;
    private JButton btnCadastrar;
    private JButton btnCadastroCancela;
    private JButton btnRemover;
    //ComboBoxes
    private JComboBox cboItinerarioCadastro;
    private JComboBox cboItinerarioRemocao;
    private JComboBox cboCadastroDestino;
}
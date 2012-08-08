package projetos;

import entidades.Passagem;
import dao.DaoPassagem;
import util.ImagePanel;
import entidades.Horario;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import entidades.Cidade;

public class PrincipalPassageiro extends JFrame implements MouseListener {

    public PrincipalPassageiro() {
        //abrir com aparencia "Nimbus"
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        this.setTitle("SISTEMA DE COMPRAS DE PASSAGENS DE ONIBUS");
        this.setMinimumSize(new Dimension(900, 700));
        this.setExtendedState(PrincipalPassageiro.MAXIMIZED_BOTH); //abrir tela maximizada
        frame = this.getContentPane();
        frame.setBackground(new Color(240, 240, 240));
        fontePadrao = new Font("Segoe UI", 1, 14);
        passagem = new Passagem();
        daoPassagem = new DaoPassagem();
        arrayCidadesOrigemId = new ArrayList<Integer>();
        arrayCidadesDestinoId = new ArrayList<Integer>();
        arrayRotaItinerarioIdCidadeDestino = new ArrayList<Integer>();
        arrayHorariosChegada = new ArrayList<String>();
        arrayPrecos = new ArrayList<Double>();
        arrayHorariosId = new ArrayList<Integer>();
        arrayPoltronasOcupadas = new ArrayList<Integer>();

        pnlInicioCompra = new JPanel(null);
        pnlInicioCompra.setBackground(new Color(240, 240, 240));
        pnlInicioCompra.setMinimumSize(new Dimension(885, 650));
        pnlInicioCompra.setPreferredSize(new Dimension(885, 650));
        pnlInicioCompra.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), " Primeira Etapa da Compra ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        //pnlInicioCompra.setVisible(false);

        pnlFimCompra = new JPanel(null);
        pnlFimCompra.setBackground(new Color(240, 240, 240));
        pnlFimCompra.setMinimumSize(new Dimension(885, 650));
        pnlFimCompra.setPreferredSize(new Dimension(885, 650));
        //pnlFimCompra.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), " Finalizar Compra ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlFimCompra.setVisible(false);

        inserePnlInicioCompra();
        inserePnlFimCompra();

        //montagem no frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(pnlInicioCompra, 885, 885, 885).addComponent(pnlFimCompra, 885, 885, 885).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(pnlInicioCompra, 650, 650, 650).addComponent(pnlFimCompra, 650, 650, 650).addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }

    private void inserePnlInicioCompra() {

        caminhoLocal = System.getProperty("user.dir"); //pega diretorio do projeto
        pnlCompraFundoMotorista = new ImagePanel(caminhoLocal + "/src/imagens/motoristaCinza.jpg");
        pnlCompraFundoOnibus = new ImagePanel(caminhoLocal + "/src/imagens/fundoCinza.jpg");
        arrayLabelPoltronas = new ArrayList<JLabel>();
        arrayEscolhas = new ArrayList<Integer>();

        caminhoLocal = caminhoLocal + "/src/imagens/cad_";
        caminhoImagemEscolhida = "_G";
        caminhoImagemLivre = "_N";
        caminhoImagemOcupada = "_R";
        tipoImagem = ".png";

        lblCompraSelecioneCampos = new JLabel("Selecione os campos para comprar passagens:");
        lblCompraSelecionePoltronas = new JLabel("Clique nas poltronas que deseja comprar:");
        lblCompraOrigem = new JLabel("Origem:");
        cboCompraOrigem = new JComboBox(new String[]{"Selecione"});
        lblCompraDestino = new JLabel("Destino:");
        cboCompraDestino = new JComboBox(new String[]{"Selecione"});
        lblCompraData = new JLabel("Data:");
        cboCompraDataDia = new JComboBox(new String[]{"-"});
        cboCompraDataMes = new JComboBox(new String[]{"-"});
        cboCompraDataAno = new JComboBox(new String[]{"-"});
        lblCompraHorario = new JLabel("Horario:");
        cboCompraHorario = new JComboBox(new String[]{"Selecione"});
        lblCompraHorarioChegada = new JLabel("Horario Previsto Chegada: ");
        lblCompraHorarioChegadaR = new JLabel();
        lblCompraHorarioPreco = new JLabel("Preco Viagem:    R$ ");
        lblCompraHorarioPrecoR = new JLabel();
        btnCompraAvancar = new JButton("Proxima Etapa");

        int xLbl = 100;
        int xTxt = 200;
        int xCombos = 50;
        int yCampos = 30;
        int yOnibus = 250;
        int gapHCampos = 92;
        int gapVConstante = 40;
        int gapVCampos = 40;
        int gapHOnibus = 92;
        int gapVOnibus;

        JButton a = new JButton("Teste");
        pnlInicioCompra.add(a);
        pnlInicioCompra.add(lblCompraSelecioneCampos);
        pnlInicioCompra.add(lblCompraOrigem);
        pnlInicioCompra.add(cboCompraOrigem);
        pnlInicioCompra.add(lblCompraDestino);
        pnlInicioCompra.add(cboCompraDestino);
        pnlInicioCompra.add(lblCompraData);
        pnlInicioCompra.add(cboCompraDataDia);
        pnlInicioCompra.add(cboCompraDataMes);
        pnlInicioCompra.add(cboCompraDataAno);
        pnlInicioCompra.add(lblCompraHorario);
        pnlInicioCompra.add(cboCompraHorario);
        pnlInicioCompra.add(lblCompraHorarioPreco);
        pnlInicioCompra.add(lblCompraHorarioPrecoR);
        pnlInicioCompra.add(lblCompraHorarioChegada);
        pnlInicioCompra.add(lblCompraHorarioChegadaR);
        pnlInicioCompra.add(lblCompraSelecionePoltronas);
        pnlInicioCompra.add(pnlCompraFundoMotorista);
        pnlInicioCompra.add(pnlCompraFundoOnibus);
        pnlInicioCompra.add(btnCompraAvancar);
        inserePoltronas();

        lblCompraSelecioneCampos.setBounds(gapHCampos, gapVCampos, 300, yCampos);
        gapVCampos += gapVConstante + 15;
        lblCompraOrigem.setBounds(gapHCampos, gapVCampos, xLbl, yCampos);
        cboCompraOrigem.setBounds(gapHCampos + xLbl, gapVCampos, xTxt, yCampos);
        gapVCampos += gapVConstante;
        lblCompraDestino.setBounds(gapHCampos, gapVCampos, xLbl, yCampos);
        cboCompraDestino.setBounds(gapHCampos + xLbl, gapVCampos, xTxt, yCampos);
        gapVCampos += gapVConstante;
        lblCompraData.setBounds(gapHCampos, gapVCampos, xLbl, yCampos);
        cboCompraDataDia.setBounds(gapHCampos + xLbl, gapVCampos, xCombos, yCampos);
        cboCompraDataMes.setBounds(gapHCampos + xLbl + 60, gapVCampos, xCombos, yCampos);
        cboCompraDataAno.setBounds(gapHCampos + xLbl + 120, gapVCampos, xCombos + 30, yCampos);
        gapVCampos += gapVConstante;
        lblCompraHorario.setBounds(gapHCampos, gapVCampos, xLbl, yCampos);
        cboCompraHorario.setBounds(gapHCampos + xLbl, gapVCampos, xTxt, yCampos);
        lblCompraHorarioPreco.setBounds(gapHCampos + xLbl + xTxt + 20, gapVCampos, xTxt, yCampos);
        lblCompraHorarioPrecoR.setBounds(gapHCampos + xLbl + xTxt + 30 + xLbl, gapVCampos, xTxt, yCampos);
        lblCompraHorarioChegada.setBounds(gapHCampos + xLbl + xTxt + 10 + xLbl + xLbl, gapVCampos, xTxt, yCampos);
        lblCompraHorarioChegadaR.setBounds(gapHCampos + xLbl + xTxt + 60 + xLbl + xLbl + xLbl, gapVCampos, xTxt, yCampos);
        gapVCampos += gapVConstante + 15;
        lblCompraSelecionePoltronas.setBounds(gapHCampos, gapVCampos, 300, yCampos);
        gapVOnibus = gapVCampos + gapVConstante;
        pnlCompraFundoMotorista.setBounds(gapHOnibus, gapVOnibus, 100, yOnibus);
        pnlCompraFundoOnibus.setBounds(gapHOnibus + 100, gapVOnibus, 600, yOnibus);
        btnCompraAvancar.setBounds(gapHOnibus + 500, gapVOnibus + 265, xTxt, 50);

        cboCompraDataAno.setEnabled(false);
        cboCompraDataDia.setEnabled(false);
        cboCompraDataMes.setEnabled(false);
        cboCompraDestino.setEnabled(false);
        cboCompraHorario.setEnabled(false);
        lblCompraHorarioPreco.setVisible(false);
        lblCompraHorarioChegada.setVisible(false);
        lblCompraHorarioPrecoR.setVisible(false);
        lblCompraHorarioChegadaR.setVisible(false);
        pnlCompraFundoMotorista.setVisible(false);
        pnlCompraFundoOnibus.setVisible(false);
        lblCompraSelecionePoltronas.setVisible(false);
        btnCompraAvancar.setVisible(false);

        for (int i = 1; i <= 31; i++) {
            cboCompraDataDia.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            cboCompraDataMes.addItem(i);
        }
        for (int i = 2012; i <= 2013; i++) {
            cboCompraDataAno.addItem(i);
        }
        ArrayList<Cidade> cidade = daoPassagem.carregaCidadesOrigem();
        arrayCidadesOrigemId.add(0);
        for (int i = 0; i < cidade.size(); i++) {
            cboCompraOrigem.addItem(cidade.get(i).getNome());
            arrayCidadesOrigemId.add(cidade.get(i).getId());
        }

        a.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });

        btnCompraAvancar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnCompraAvancarClick(evt);
            }
        });

        cboCompraOrigem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraOrigemClick(evt);
            }
        });

        cboCompraDestino.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraDestinoClick(evt);
            }
        });

        cboCompraDataAno.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraDataAnoClick(evt);
            }
        });

        cboCompraDataMes.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraDataMesClick(evt);
            }
        });

        cboCompraDataDia.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraDataDiaClick(evt);
            }
        });

        cboCompraHorario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboCompraHorarioClick(evt);
            }
        });
    }

    //modificar
    private void inserePnlFimCompra() {
        listCompraFim = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listCompraFim.setSize(new Dimension(700, 900));
        listCompraFim.setPreferredSize(new Dimension(700, 100));
        sListCompraFim = new JScrollPane(listCompraFim);
        sListCompraFim.setSize(new Dimension(885, 650));
        pnlFimCompra.add(sListCompraFim);
    }

    private void iniciaFimCompra(int qtde, ArrayList<Integer> arrayAssentos) {
        listCompraFim.removeAll();
        arrayAuxPnl = new ArrayList<JPanel>();
        for (int i = 0; i < qtde; i++) {
            arrayAuxPnl.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 20)));
            arrayAuxPnl.get(i).setPreferredSize(new Dimension(400, 200));
            arrayAuxPnl.get(i).add(new JLabel("Assento:"));
            arrayAuxPnl.get(i).add(new JLabel("     " + (arrayLabelPoltronas.get(arrayAssentos.get(i)).getText())));
            arrayAuxPnl.get(i).add(new JLabel("Nome Passageiro:"));
            arrayAuxPnl.get(i).add(new JTextField("", 15));
            arrayAuxPnl.get(i).add(new JLabel("RG Passageiro:"));
            arrayAuxPnl.get(i).add(new JTextField("", 15));
            ((JTextField) arrayAuxPnl.get(i).getComponent(5)).setDocument(limitaCaracteres(12));
            arrayAuxPnl.get(i).getComponent(1).setPreferredSize(new Dimension(150, 30));
            for (int j = 0; j < 6; j += 2) {
                arrayAuxPnl.get(i).getComponent(j).setPreferredSize(new Dimension(120, 30));
            }
            listCompraFim.add(arrayAuxPnl.get(i));
        }
        if (qtde % 2 == 0) {
            listCompraFim.setPreferredSize(new Dimension(700, (qtde / 2) * 200 + 100));
        } else {
            listCompraFim.setPreferredSize(new Dimension(700, (qtde / 2 + 1) * 200 + 100));
        }
        JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER, 180, 0));
        aux.setPreferredSize(new Dimension(880, 100));
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setPreferredSize(new Dimension(250, 50));
        btnVoltarEtapaCompra = new JButton("Voltar");
        btnVoltarEtapaCompra.setPreferredSize(new Dimension(250, 50));
        aux.add(btnVoltarEtapaCompra);
        aux.add(btnFinalizarCompra);
        listCompraFim.add(aux);
        arrayAuxPnl.get(0).getComponent(3).requestFocus();

        btnFinalizarCompra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnFinalizarCompraClick(evt);
            }
        });

        btnVoltarEtapaCompra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnVoltarEtapaCompraClick(evt);
            }
        });
    }

    private PlainDocument limitaCaracteres(final int limiteCaracteres) {
        return new PlainDocument() {

            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (getLength() + str.length() <= limiteCaracteres) {
                    super.insertString(offs, str, a);
                }
            }
        };
    }

    private void carregaPoltronas() {
        arrayEscolhas.clear();
        int contadorAssentos = 0;
        for (int k = 4; k <= 48; k += 4) {
            if (arrayPoltronasOcupadas.contains(k)) {
                arrayEscolhas.add(2);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemOcupada + tipoImagem));
                System.out.println("ocupada " + k);
            } else {
                arrayEscolhas.add(0);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemLivre + tipoImagem));
            }
            contadorAssentos++;
        }
        for (int k = 3; k <= 47; k += 4) {
            if (arrayPoltronasOcupadas.contains(k)) {
                arrayEscolhas.add(2);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemOcupada + tipoImagem));
                System.out.println("ocupada " + k);
            } else {
                arrayEscolhas.add(0);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemLivre + tipoImagem));
            }
            contadorAssentos++;
        }
        for (int k = 2; k <= 46; k += 4) {
            if (arrayPoltronasOcupadas.contains(k)) {
                arrayEscolhas.add(2);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemOcupada + tipoImagem));
                System.out.println("ocupada " + k);
            } else {
                arrayEscolhas.add(0);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemLivre + tipoImagem));
            }
            contadorAssentos++;
        }
        for (int k = 1; k <= 45; k += 4) {
            if (arrayPoltronasOcupadas.contains(k)) {
                arrayEscolhas.add(2);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemOcupada + tipoImagem));
                System.out.println("ocupada " + k);
            } else {
                arrayEscolhas.add(0);
                arrayLabelPoltronas.get(contadorAssentos).setIcon(new ImageIcon(caminhoLocal + k + caminhoImagemLivre + tipoImagem));
            }
            contadorAssentos++;
        }
    }

    private void inserePoltronas() {
        //arrayLabelPoltronas.clear();
        //pnlCompraFundoOnibus = new ImagePanel(caminhoLocal + "/src/imagens/motoristaCinza.jpg");
        int contadorAssentos = 0;
        int contadorColunas = 0;
        int j = 0;
        for (int k = 4; k <= 48; k += 4) {
            arrayLabelPoltronas.add(new JLabel("" + k));
            arrayLabelPoltronas.get(contadorAssentos).setBounds((contadorColunas * 50), (j * 50), 50, 50);
            arrayLabelPoltronas.get(contadorAssentos).setName("" + contadorAssentos);
            arrayLabelPoltronas.get(contadorAssentos).addMouseListener(this);
            pnlCompraFundoOnibus.add(arrayLabelPoltronas.get(contadorAssentos));
            contadorAssentos++;
            contadorColunas++;
        }

        contadorColunas = 0;
        j++;
        for (int k = 3; k <= 47; k += 4) {
            arrayLabelPoltronas.add(new JLabel("" + k));
            arrayLabelPoltronas.get(contadorAssentos).setBounds((contadorColunas * 50), (j * 50), 50, 50);
            arrayLabelPoltronas.get(contadorAssentos).setName("" + contadorAssentos);
            arrayLabelPoltronas.get(contadorAssentos).addMouseListener(this);
            pnlCompraFundoOnibus.add(arrayLabelPoltronas.get(contadorAssentos));
            contadorAssentos++;
            contadorColunas++;
        }

        contadorColunas = 0;
        j += 2;
        for (int k = 2; k <= 46; k += 4) {
            arrayLabelPoltronas.add(new JLabel("" + k));
            arrayLabelPoltronas.get(contadorAssentos).setBounds((contadorColunas * 50), (j * 50), 50, 50);
            arrayLabelPoltronas.get(contadorAssentos).setName("" + contadorAssentos);
            arrayLabelPoltronas.get(contadorAssentos).addMouseListener(this);
            pnlCompraFundoOnibus.add(arrayLabelPoltronas.get(contadorAssentos));
            contadorAssentos++;
            contadorColunas++;
        }

        contadorColunas = 0;
        j++;
        for (int k = 1; k <= 45; k += 4) {
            arrayLabelPoltronas.add(new JLabel("" + k));
            arrayLabelPoltronas.get(contadorAssentos).setBounds((contadorColunas * 50), (j * 50), 50, 50);
            arrayLabelPoltronas.get(contadorAssentos).setName("" + contadorAssentos);
            arrayLabelPoltronas.get(contadorAssentos).addMouseListener(this);
            pnlCompraFundoOnibus.add(arrayLabelPoltronas.get(contadorAssentos));
            contadorAssentos++;
            contadorColunas++;
        }
    }

    private void btnCompraAvancarClick(ActionEvent evt) {
        int contadorPoltronas = 0;
        ArrayList<Integer> arrayAssentos = new ArrayList<Integer>();
        for (int i = 0; i < arrayEscolhas.size(); i++) {
            if (arrayEscolhas.get(i) == 1) {
                contadorPoltronas++;
                arrayAssentos.add(i);
            }
        }

        if (contadorPoltronas == 0) {
            JOptionPane.showMessageDialog(null, "Selecione as poltronas desejadas para continuar.");
        } else {
            pnlInicioCompra.setVisible(false);
            pnlFimCompra.setVisible(true);
            iniciaFimCompra(contadorPoltronas, arrayAssentos);
        }
    }

    private void btnFinalizarCompraClick(ActionEvent evt) {
        for (int i = 0; i < arrayAuxPnl.size(); i++) {
            for (int j = 3; j < 6; j += 2) {
                JTextField txtAux = (JTextField) arrayAuxPnl.get(i).getComponent(j);
                if (txtAux.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos para finalizar a compra!");
                    txtAux.requestFocus();
                    return;
                }
            }
        }
        for (int i = 0; i < arrayAuxPnl.size(); i++) {
            for (int j = i + 1; j < arrayAuxPnl.size(); j++) {
                JTextField txtAux = (JTextField) arrayAuxPnl.get(i).getComponent(5);
                JTextField txtAux1 = (JTextField) arrayAuxPnl.get(j).getComponent(5);
                txtAux.setBackground(new Color(255, 255, 255));
                txtAux1.setBackground(new Color(255, 255, 255));
            }
        }
        for (int i = 0; i < arrayAuxPnl.size(); i++) {
            for (int j = i + 1; j < arrayAuxPnl.size(); j++) {
                JTextField txtAux = (JTextField) arrayAuxPnl.get(i).getComponent(5);
                JTextField txtAux1 = (JTextField) arrayAuxPnl.get(j).getComponent(5);
                if (txtAux.getText().equals(txtAux1.getText())) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel finalizar a compra. Digite CPFs diferentes para cada assento.");
                    txtAux.setBackground(new Color(255, 200, 200));
                    txtAux1.setBackground(new Color(255, 200, 200));
                    txtAux.requestFocus();
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso!");
        pnlFimCompra.setVisible(false);
        pnlInicioCompra.removeAll();
        inserePnlInicioCompra();
        pnlInicioCompra.setVisible(true);
        String local = System.getProperty("user.dir");
        //escreve comprovante de compra
        try {
            FileWriter f0 = new FileWriter(local + "/src/imagens/Sem Titulo.txt");
            String barraN = System.getProperty ("line.separator");
            f0.write("Comprovante de Compra" + barraN + barraN + barraN);
            for (int i = 0; i < arrayAuxPnl.size(); i++) {
                passagem = new Passagem();
                JLabel lblAux = (JLabel) arrayAuxPnl.get(i).getComponent(0);
                JLabel lblAuxAssento = (JLabel) arrayAuxPnl.get(i).getComponent(1);
                passagem.setAssentoComprado(Integer.parseInt(lblAuxAssento.getText().trim()));
                f0.write(lblAux.getText() + " ");
                f0.write(lblAuxAssento.getText() + barraN);
                for (int j = 2; j < 6; j += 2) {
                    JLabel lblAux1 = (JLabel) arrayAuxPnl.get(i).getComponent(j);
                    JTextField txtAux1 = (JTextField) arrayAuxPnl.get(i).getComponent(j + 1);
                    if (j == 3) {
                        passagem.setClienteNome(txtAux1.getText());
                    }
                    if (j == 5) {
                        passagem.setClienteRg(txtAux1.getText());
                    }
                    f0.write(lblAux1.getText() + " ");
                    f0.write(txtAux1.getText() + barraN);
                }
                f0.write(barraN + barraN);
                daoPassagem = new DaoPassagem();
                daoPassagem.cadastrarPassagemComprada(passagem);
            }
            f0.close();
            java.awt.Desktop.getDesktop().open(new File(local + "/src/imagens/Sem Titulo.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fazer insercao no banco
    }

    private void btnVoltarEtapaCompraClick(ActionEvent evt) {
        pnlInicioCompra.setVisible(true);
        pnlFimCompra.setVisible(false);
    }

    private void cboCompraOrigemClick(ItemEvent evt) {
        cboCompraDataAno.setEnabled(false);
        cboCompraDataAno.setSelectedItem("-");
        cboCompraDataDia.setEnabled(false);
        cboCompraDataDia.setSelectedItem("-");
        cboCompraDataMes.setEnabled(false);
        cboCompraDataMes.setSelectedItem("-");
        cboCompraDestino.setEnabled(false);
        cboCompraDestino.setSelectedItem("Selecione");
        cboCompraHorario.setEnabled(false);
        cboCompraHorario.setSelectedItem("Selecione");
        pnlCompraFundoMotorista.setVisible(false);
        pnlCompraFundoOnibus.setVisible(false);
        lblCompraSelecionePoltronas.setVisible(false);
        lblCompraSelecionePoltronas.setVisible(false);
        lblCompraHorarioPreco.setVisible(false);
        lblCompraHorarioChegada.setVisible(false);
        lblCompraHorarioPrecoR.setVisible(false);
        lblCompraHorarioChegadaR.setVisible(false);
        btnCompraAvancar.setVisible(false);
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraOrigem.getSelectedItem().equals("Selecione"))) {
                cboCompraDestino.setEnabled(true);
                Cidade cidade = new Cidade();
                cidade.setId(arrayCidadesOrigemId.get(cboCompraOrigem.getSelectedIndex()));
                cidade.setNome(String.valueOf(cboCompraOrigem.getSelectedItem()));
                ArrayList<Cidade> cidades = daoPassagem.carregaCidadesDestino(cidade);
                cboCompraDestino.removeAllItems();
                cboCompraDestino.addItem("Selecione");
                arrayRotaItinerarioIdCidadeDestino.clear();
                arrayRotaItinerarioIdCidadeDestino.add(0);
                for (int i = 0; i < cidades.size(); i++) {
                    cboCompraDestino.addItem(cidades.get(i).getNome());
                    arrayCidadesDestinoId.add(cidades.get(i).getId());
                    arrayRotaItinerarioIdCidadeDestino.add(Integer.parseInt(cidades.get(i).getEstado()));
                }
                cboCompraDestino.requestFocus();
            }
        }
    }

    private void cboCompraDestinoClick(ItemEvent evt) {
        cboCompraDataAno.setEnabled(false);
        cboCompraDataAno.setSelectedItem("-");
        cboCompraDataDia.setEnabled(false);
        cboCompraDataDia.setSelectedItem("-");
        cboCompraDataMes.setEnabled(false);
        cboCompraDataMes.setSelectedItem("-");
        cboCompraHorario.setEnabled(false);
        cboCompraHorario.setSelectedItem("Selecione");
        pnlCompraFundoMotorista.setVisible(false);
        pnlCompraFundoOnibus.setVisible(false);
        lblCompraSelecionePoltronas.setVisible(false);
        lblCompraHorarioPreco.setVisible(false);
        lblCompraHorarioChegada.setVisible(false);
        lblCompraHorarioPrecoR.setVisible(false);
        lblCompraHorarioChegadaR.setVisible(false);
        lblCompraSelecionePoltronas.setVisible(false);
        btnCompraAvancar.setVisible(false);
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraDestino.getSelectedItem().equals("Selecione"))) {
                cboCompraDataAno.setEnabled(true);
                cboCompraDataDia.setEnabled(true);
                cboCompraDataMes.setEnabled(true);
                cboCompraDataDia.requestFocus();
            }
        }
    }

    private void cboCompraDataAnoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraDataAno.getSelectedItem().equals("-"))) {
                if (!(cboCompraDataDia.getSelectedItem().equals("-")) && !(cboCompraDataMes.getSelectedItem().equals("-"))) {
                    ArrayList<Horario> horarios = daoPassagem.carregaHorarios(arrayRotaItinerarioIdCidadeDestino.get(cboCompraDestino.getSelectedIndex()), Integer.parseInt(String.valueOf(cboCompraDataDia.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataMes.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataAno.getSelectedItem())));
                    cboCompraHorario.setEnabled(true);
                    cboCompraHorario.removeAllItems();
                    cboCompraHorario.addItem("Selecione");
                    arrayHorariosChegada.clear();
                    arrayHorariosChegada.add("");
                    arrayPrecos.clear();
                    arrayPrecos.add(0.0);
                    arrayHorariosId.clear();
                    arrayHorariosId.add(0);
                    for (int i = 0; i < horarios.size(); i++) {
                        cboCompraHorario.addItem(horarios.get(i).getHorarioSaida());
                        arrayHorariosChegada.add(horarios.get(i).getHorarioChegada());
                        arrayPrecos.add(horarios.get(i).getHorarioPreco());
                        arrayHorariosId.add(horarios.get(i).getHorarioId());
                    }
                    cboCompraHorario.requestFocus();
                }

            }
        }
    }

    private void cboCompraDataMesClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraDataMes.getSelectedItem().equals("-"))) {
                if (!(cboCompraDataDia.getSelectedItem().equals("-")) && !(cboCompraDataAno.getSelectedItem().equals("-"))) {
                    ArrayList<Horario> horarios = daoPassagem.carregaHorarios(arrayRotaItinerarioIdCidadeDestino.get(cboCompraDestino.getSelectedIndex()), Integer.parseInt(String.valueOf(cboCompraDataDia.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataMes.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataAno.getSelectedItem())));
                    cboCompraHorario.setEnabled(true);
                    cboCompraHorario.removeAllItems();
                    cboCompraHorario.addItem("Selecione");
                    arrayHorariosChegada.clear();
                    arrayHorariosChegada.add("");
                    arrayPrecos.clear();
                    arrayPrecos.add(0.0);
                    arrayHorariosId.clear();
                    arrayHorariosId.add(0);
                    for (int i = 0; i < horarios.size(); i++) {
                        cboCompraHorario.addItem(horarios.get(i).getHorarioSaida());
                        arrayHorariosChegada.add(horarios.get(i).getHorarioChegada());
                        arrayPrecos.add(horarios.get(i).getHorarioPreco());
                        arrayHorariosId.add(horarios.get(i).getHorarioId());
                    }
                }
                cboCompraDataAno.requestFocus();
            }
        }
    }

    private void cboCompraDataDiaClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraDataDia.getSelectedItem().equals("-"))) {
                if (!(cboCompraDataAno.getSelectedItem().equals("-")) && !(cboCompraDataMes.getSelectedItem().equals("-"))) {
                    ArrayList<Horario> horarios = daoPassagem.carregaHorarios(arrayRotaItinerarioIdCidadeDestino.get(cboCompraDestino.getSelectedIndex()), Integer.parseInt(String.valueOf(cboCompraDataDia.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataMes.getSelectedItem())), Integer.parseInt(String.valueOf(cboCompraDataAno.getSelectedItem())));
                    cboCompraHorario.setEnabled(true);
                    cboCompraHorario.removeAllItems();
                    cboCompraHorario.addItem("Selecione");
                    arrayHorariosChegada.clear();
                    arrayHorariosChegada.add("");
                    arrayPrecos.clear();
                    arrayPrecos.add(0.0);
                    arrayHorariosId.clear();
                    arrayHorariosId.add(0);
                    for (int i = 0; i < horarios.size(); i++) {
                        cboCompraHorario.addItem(horarios.get(i).getHorarioSaida());
                        arrayHorariosChegada.add(horarios.get(i).getHorarioChegada());
                        arrayPrecos.add(horarios.get(i).getHorarioPreco());
                        arrayHorariosId.add(horarios.get(i).getHorarioId());
                    }
                }
                cboCompraDataMes.requestFocus();
            }
        }
    }

    private void cboCompraHorarioClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboCompraHorario.getSelectedItem().equals("Selecione"))) {
                lblCompraHorarioChegada.setVisible(true);
                lblCompraHorarioChegadaR.setVisible(true);
                lblCompraHorarioPreco.setVisible(true);
                lblCompraHorarioPrecoR.setVisible(true);
                pnlCompraFundoMotorista.setVisible(true);
                pnlCompraFundoOnibus.setVisible(true);
                btnCompraAvancar.setVisible(true);
                lblCompraSelecionePoltronas.setVisible(true);
                lblCompraHorarioChegadaR.setText(arrayHorariosChegada.get(cboCompraHorario.getSelectedIndex()));
                lblCompraHorarioPrecoR.setText(String.valueOf(arrayPrecos.get(cboCompraHorario.getSelectedIndex())));
                arrayPoltronasOcupadas.clear();
                System.out.println("aaaaa");
                for(int i=0; i<arrayCidadesDestinoId.size();i++){
                    System.out.println(arrayCidadesDestinoId.get(i));
                }
                System.out.println("cidade:"+ arrayCidadesDestinoId.get(cboCompraDestino.getSelectedIndex()-1));
                arrayPoltronasOcupadas = daoPassagem.consultaPoltronasCompradas(arrayHorariosId.get(cboCompraHorario.getSelectedIndex()), (cboCompraDataDia.getSelectedItem() + "/" + cboCompraDataMes.getSelectedItem() + "/" + cboCompraDataAno.getSelectedItem()),arrayCidadesDestinoId.get(cboCompraDestino.getSelectedIndex()-1));
                for (int i = 0; i < arrayPoltronasOcupadas.size(); i++) {
                    System.out.println(arrayPoltronasOcupadas.get(i));
                }
                carregaPoltronas();
            } else {
                pnlCompraFundoMotorista.setVisible(false);
                pnlCompraFundoOnibus.setVisible(false);
                lblCompraSelecionePoltronas.setVisible(false);
                lblCompraHorarioPreco.setVisible(false);
                lblCompraHorarioChegada.setVisible(false);
                lblCompraHorarioPrecoR.setVisible(false);
                lblCompraHorarioChegadaR.setVisible(false);
                lblCompraSelecionePoltronas.setVisible(false);
                btnCompraAvancar.setVisible(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        JLabel label = (JLabel) evt.getSource();
        int indiceLabel = (Integer.parseInt(label.getName()));
        if (arrayEscolhas.get(indiceLabel) == 0) {
            label.setIcon(new ImageIcon(caminhoLocal + (Integer.parseInt(label.getText())) + caminhoImagemEscolhida + tipoImagem));
            System.out.println("clicou 0");
            //label.setIcon(new ImageIcon(caminhoImagemEscolhida + tipoImagem));
            arrayEscolhas.set(indiceLabel, 1);
        } else {
            if (arrayEscolhas.get(indiceLabel) == 1) {
                label.setIcon(new ImageIcon(caminhoLocal + (Integer.parseInt(label.getText())) + caminhoImagemLivre + tipoImagem));
                //label.setIcon(new ImageIcon(caminhoImagemLivre + tipoImagem));
                arrayEscolhas.set(indiceLabel, 0);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        JLabel label = (JLabel) evt.getSource();
        int indiceLabel = (Integer.parseInt(label.getName()));
        if (arrayEscolhas.get(indiceLabel) == 0) {
            label.setIcon(new ImageIcon(caminhoLocal + (Integer.parseInt(label.getText())) + caminhoImagemEscolhida + tipoImagem));
            //label.setIcon(new ImageIcon(caminhoImagemEscolhida + tipoImagem));
        }
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        JLabel label = (JLabel) evt.getSource();
        int indiceLabel = (Integer.parseInt(label.getName()));
        if (arrayEscolhas.get(indiceLabel) == 0) {
            System.out.println("exited = 0");
            label.setIcon(new ImageIcon(caminhoLocal + (Integer.parseInt(label.getText())) + caminhoImagemLivre + tipoImagem));
            //label.setIcon(new ImageIcon(caminhoImagemLivre + tipoImagem));
        }
    }

    //nao usa
    @Override
    public void mousePressed(MouseEvent evt) {
    }

    //nao usa
    public void mouseClicked(MouseEvent evt) {
    }

    public static void main(String[] args) {
        PrincipalPassageiro esse = new PrincipalPassageiro();
        esse.setVisible(true);
    }
    private Font fontePadrao;
    private JPanel pnlInicioCompra;
    private JPanel pnlFimCompra;
    private Container frame;
    private ArrayList<JLabel> arrayLabelPoltronas;
    private ArrayList<Integer> arrayEscolhas;
    private ArrayList<Integer> arrayCidadesOrigemId;
    private ArrayList<Integer> arrayCidadesDestinoId;
    private ArrayList<Integer> arrayRotaItinerarioIdCidadeDestino;
    private ArrayList<Integer> arrayHorariosId;
    private ArrayList<Integer> arrayPoltronasOcupadas;
    private ArrayList<Double> arrayPrecos;
    private ArrayList<String> arrayHorariosChegada;
    private Passagem passagem;
    private DaoPassagem daoPassagem;
    //pnlInicioCompra
    private ImagePanel pnlCompraFundoMotorista;
    private ImagePanel pnlCompraFundoOnibus;
    private JLabel lblCompraSelecioneCampos;
    private JLabel lblCompraSelecionePoltronas;
    private JLabel lblCompraOrigem;
    private JComboBox cboCompraOrigem;
    private JLabel lblCompraDestino;
    private JComboBox cboCompraDestino;
    private JLabel lblCompraData;
    private JComboBox cboCompraDataDia;
    private JComboBox cboCompraDataMes;
    private JComboBox cboCompraDataAno;
    private JLabel lblCompraHorario;
    private JComboBox cboCompraHorario;
    private JLabel lblCompraHorarioPreco;
    private JLabel lblCompraHorarioPrecoR;
    private JLabel lblCompraHorarioChegada;
    private JLabel lblCompraHorarioChegadaR;
    private JButton btnCompraAvancar;
    private String caminhoLocal;
    private String caminhoImagemLivre;
    private String caminhoImagemOcupada;
    private String caminhoImagemEscolhida;
    private String tipoImagem;
    //pnlFimCompra
    private JPanel listCompraFim;
    private JScrollPane sListCompraFim;
    private JButton btnFinalizarCompra;
    private JButton btnVoltarEtapaCompra;
    private ArrayList<JPanel> arrayAuxPnl;
}

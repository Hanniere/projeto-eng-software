package interfaces;

import entidades.RotaItinerario;
import entidades.Horario;
import dao.DaoItinerario;
import dao.DaoRota;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoMotorista;
import dao.DaoOnibus;

import sistema_final_es.Cidade;
import sistema_final_es.Motorista;
import sistema_final_es.Onibus;





public class PanelHorario extends JFrame{
	public PanelHorario() {
        //abrir com aparencia "Nimbus"
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        format = NumberFormat.getNumberInstance();
		format.setMinimumIntegerDigits(2);
        initComponents();
    }


    private void initComponents() {
    	this.setTitle("SISTEMA DE COMPRAS DE PASSAGENS DE ONIBUS");
        this.setMinimumSize(new Dimension(900, 700));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //this.setExtendedState(Principal.MAXIMIZED_BOTH); //abrir tela maximizada
        this.setLayout(null);
        
        gridLayout2_4=new GridLayout(2,4);
        fontePadrao = new Font("Segoe UI", 1, 14);

        
        inserirPnlItinerario();
        
        
        add(pnlItinerario);
       // pack();
        
   

    }
    
    private void inserirPnlItinerario() {
        pnlItinerario = new JPanel();
	 	pnlItinerario.setSize(695, 590);
        //panel.setBounds(200, 100, 695,695);
	 	pnlItinerario.setPreferredSize(new Dimension(885, 650));
	 	pnlItinerario.setBorder(BorderFactory.createTitledBorder(null, " CADASTRO ITINERARIO ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
	 	pnlItinerario.setBackground(new Color(240, 240, 240));
	 	pnlItinerario.setLayout(null);
	 	pnlItinerario.setOpaque(true);
	 	
	 	
	 	lblItinerario = new JLabel();
	 	lblItinerario.setText("Selecione o Itinerario:");
	 	lblItinerario.setFont(fontePadrao);
	 	lblItinerario.setBounds(27,50 , 155, 20);
	 	
	 	cboItinerario = new JComboBox(new String[]{"Selecione"});
	 	cboItinerario.setBounds(187, 45, 222, 30);
	 	
        cboItinerarioHora = new JComboBox();
        cboItinerarioHora.setBounds(538, 45, 60, 30);
        cboItinerarioHora.setEnabled(false);
        
        cboItinerarioMin = new JComboBox();
        cboItinerarioMin.setBounds(603, 45, 60, 30);
        cboItinerarioMin.setEnabled(false);
        
        
       
        cboItinerarioOnibus = new JComboBox();
        cboItinerarioOnibus.setBounds(187, 90, 200, 30);
        cboItinerarioOnibus.setEnabled(false);
	 	
	 	lblItinerarioHoraSaida = new JLabel("Horario de saida:");
	 	lblItinerarioHoraSaida.setFont(fontePadrao);
	 	lblItinerarioHoraSaida.setBounds(414, 45, 120, 30);
	 	
	 	lblItinerarioTipoOnibus = new JLabel("Selecione o onibus:");
	 	lblItinerarioTipoOnibus.setFont(fontePadrao);
	 	lblItinerarioTipoOnibus.setBounds(27,95 , 155, 20);
	 	
        btnItinerarioConfirma = new JButton("Confirma");
        btnItinerarioConfirma.setBounds(400, 535, 100, 30);
        btnItinerarioConfirma.setFont(fontePadrao);
        btnItinerarioConfirma.setEnabled(false);
        
        btnItinerarioCancela = new JButton("Cancela");
        btnItinerarioCancela.setBounds(550, 535, 100, 30);
        btnItinerarioCancela.setFont(fontePadrao);
      
        
        btnItinerarioAlterarRota = new JButton("Alterar Rota");
        btnItinerarioAlterarRota.setBounds(450, 480, 150, 30);
        btnItinerarioAlterarRota.setFont(fontePadrao);
        btnItinerarioAlterarRota.setEnabled(false);
	 	
        arrayRotaAtual = new ArrayList<Rota>();
    	arrayRotaItinerario = new ArrayList<RotaItinerario>(); //inicializando novo RotaItinerario
    	arrayHorario = new ArrayList<Horario>();
	 	
	 	inserirPnlItinerarioDias();
	 	inserirPnlItinerarioRotas();
	 	inserirPnlItinerarioLista();
	 	
	 	pnlItinerario.add(btnItinerarioAlterarRota);
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
	 	pnlItinerario.add(pnlItinerarioRotas);
	 	pnlItinerario.add(pnlItinarioLista);
	 	
	 	carregaComboItinerario();
	 	carregaComboMotorista();
	 	carregaComboOnibus();
	 	carregaComboMinuto();
	 	carregaComboHora();
	 	
	    cboItinerario.addItemListener(new ItemListener() {

	        @Override
	        public void itemStateChanged(ItemEvent evt) {
	            if (evt.getStateChange() == ItemEvent.SELECTED) {
	                if (!(cboItinerario.getSelectedItem().equals("Selecione"))) {
	                	selectedCboIndexItinerario = cboItinerario.getSelectedIndex()-1;
	                	
	                	carregaComboDestino(arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeOrigemId()); //carregando destinos alcancaveis da cidade selecionada
	                	txtItinerarioOrigem.setText(arrayRotaAtual.get(0).getRota_cidadeOrigem()); //setando primeiramente Origem           
	                	
	                	cboItinerario.setEnabled(false);
	                	
	                
	                	cboItinerarioOnibus.setEnabled(true);
	                	
	                	
	                }
	                
	            }
	        }
	    });
	    
	    cboItinerarioMin.addItemListener(new ItemListener() {

	        @Override
	        public void itemStateChanged(ItemEvent evt) {
	        	if (evt.getStateChange() == ItemEvent.SELECTED) {
	        		if (cboItinerarioHora.getSelectedIndex()==0 && flagMin==0) {
	            		JOptionPane.showMessageDialog(pnlItinerario, "Selecione o campo hora!");
	            		flagMin=1;
	            		cboItinerarioMin.setSelectedIndex(0);
					}
	        		else if((chBxDomingo.isSelected()||chBxSegundaFeira.isSelected()||chBxTercaFeira.isSelected()||chBxQuartaFeira.isSelected()
	        				||chBxQuintaFeira.isSelected()||chBxSextaFeira.isSelected()||chBxSabado.isSelected()
	        				||chBxFeriados.isSelected())&&flagMin==0){

	        			cboItinerarioHora.setEnabled(false);
	        			cboItinerarioMin.setEnabled(false);
	        			txtItinerarioSaida.setText(cboItinerarioHora.getSelectedItem()+":"+cboItinerarioMin.getSelectedItem());
	        			chBxDomingo.setEnabled(false);
	        			chBxSegundaFeira.setEnabled(false);
	        			chBxTercaFeira.setEnabled(false);
	        			chBxQuartaFeira.setEnabled(false);
	        			chBxQuintaFeira.setEnabled(false);
	        			chBxSextaFeira.setEnabled(false);
	        			chBxSabado.setEnabled(false);
	        			chBxFeriados.setEnabled(false);
	        			txtItinerarioPreco.setEnabled(true);
	        			cboItinerarioDestino.setEnabled(true);
	        			cboItinerarioMotorista.setEnabled(true);
	        			btnItinerarioAddRota.setEnabled(true);

	        		}
		            else if(flagMin==0){
		            		JOptionPane.showMessageDialog(pnlItinerario, "Selecione pelo menos um dia!");
		            		flagMin=1;
		            		cboItinerarioMin.setSelectedIndex(0);
		            		cboItinerarioHora.setSelectedIndex(0);
		            		
		           	} else flagMin=0;
	        		
		      
	        	}
	        }
	    });
	    
	    cboItinerarioOnibus.addItemListener(new ItemListener() {

	        @Override
	        public void itemStateChanged(ItemEvent evt) {
	            if (evt.getStateChange() == ItemEvent.SELECTED) {
	            	if(!chBxDomingo.isSelected()){
	            		
	            	}
	            	chBxDomingo.setEnabled(true);
	            	chBxSegundaFeira.setEnabled(true);
	            	chBxTercaFeira.setEnabled(true);
	            	chBxQuartaFeira.setEnabled(true);
	            	chBxQuintaFeira.setEnabled(true);
	            	chBxSextaFeira.setEnabled(true);
	            	chBxSabado.setEnabled(true);
	            	chBxFeriados.setEnabled(true);
	            	cboItinerarioOnibus.setEnabled(false);
	            	cboItinerarioHora.setEnabled(true);
                	cboItinerarioMin.setEnabled(true);

	            }
	        }
	    });
    	btnItinerarioCancela.addActionListener(new ActionListener() {

    	     
            @Override
			public void actionPerformed(ActionEvent evt) {
                
            	//initComponents();
            	reinicia();
					
				
            }
        });
    	
    	btnItinerarioAlterarRota.addActionListener(new ActionListener() {

   	     
            @Override
			public void actionPerformed(ActionEvent evt) {
                	
            		if (rowIndexSelected==0) {
            			tableRotas.setVisible(false);
					}
            	
                	int idRotaItinerario = arrayRotaItinerario.get(rowIndexSelected).getRotaItinerarioId();
                	int i =0;
                	while(arrayHorario.get(i).getHorario_RotaItinerarioId() != idRotaItinerario){
                		i++;
                	}
                	txtItinerarioPreco.setText(Double.toString(arrayHorario.get(i).getHorarioPreco()));
                	txtItinerarioSaida.setText(arrayHorario.get(i).getHorarioSaida().substring(0, 5));
                	
                	for (int j = arrayHorario.size()-1; j >= i; j--) {
						arrayHorario.remove(j);
						System.out.println("Excluindo linha: "+j);
					}
                	
            		cboItinerarioDestino.removeAllItems();
            		cboItinerarioDestino.addItem("Selecione");
					arrayRotaAtual.clear();
					
					//recarregando o cbo de destino
					txtItinerarioOrigem.setText(arrayRotasAddicionadas.get(rowIndexSelected).getRota_cidadeOrigem());
					carregaComboDestino(arrayRotasAddicionadas.get(rowIndexSelected).getRota_cidadeOrigemId());
					
					for (int j = arrayRotasAddicionadas.size()-1; j >= rowIndexSelected; j--) {
						arrayRotasAddicionadas.remove(j);
						tbm.removeRow(j);
						arrayRotaItinerario.remove(j);
					}
					tbm.fireTableDataChanged(); //atualizando table de rotas
					btnItinerarioAlterarRota.setEnabled(false);
				    btnItinerarioConfirma.setEnabled(false);
				    btnItinerarioAddRota.setEnabled(true);
				    cboItinerarioDestino.setEnabled(true);
				    txtItinerarioPreco.setEnabled(true);
				    cboItinerarioMotorista.setEnabled(true);
					
            }
        });
    	
    	btnItinerarioConfirma.addActionListener(new ActionListener() {

   	     
            @Override
			public void actionPerformed(ActionEvent evt) {
            	
            	daoHorario = new DaoHorario();
            	daoRotaItinerario = new DaoRotaItinerario();
            	for (int i = 0; i < arrayRotaItinerario.size(); i++) {
					daoRotaItinerario.cadastrarNovoRotaItinerario(arrayRotaItinerario.get(i));
				}
            	for (int j = 0; j < arrayHorario.size(); j++) {
            		daoHorario.cadastrarNovoHorario(arrayHorario.get(j));
				}
            	ordemRota=0;
            	reinicia();
            }
        });
	 	
	 	
    	
    }
    
    private void inserirPnlItinerarioDias() {
    	pnlItinerarioDias = new JPanel();
    	   
    	pnlItinerarioDias.setBounds(25, 125, 640,100);
    	//pnlItinerarioDias.setPreferredSize(new Dimension(885, 650));
    	pnlItinerarioDias.setBorder(BorderFactory.createTitledBorder(null, " Dias de operacao ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
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
    
    private void inserirPnlItinerarioRotas() {
    	pnlItinerarioRotas = new JPanel();
    	   
    	pnlItinerarioRotas.setBounds(25, 230, 360,340);
    	pnlItinerarioRotas.setBorder(BorderFactory.createTitledBorder(null, " Rotas do Itinerario ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
    	pnlItinerarioRotas.setBackground(new Color(240, 240, 240));
    	pnlItinerarioRotas.setLayout(null);
    	
    	lblItinerarioOrigem = new JLabel("Origem:");
    	lblItinerarioOrigem.setFont(fontePadrao);
    	lblItinerarioOrigem.setBounds(30, 43, 70,20);
    	
    	
    	lblItinerarioDestino = new JLabel("Destino:");
    	lblItinerarioDestino.setFont(fontePadrao);
    	lblItinerarioDestino.setBounds(30, 93, 70, 20);
    	
    	lblItinerarioSaida = new JLabel("Saida:");
    	lblItinerarioSaida.setFont(fontePadrao);
    	lblItinerarioSaida.setBounds(30, 143, 70, 20);
    	
    	lblItinerarioPreco = new JLabel("Preco:");
    	lblItinerarioPreco.setFont(fontePadrao);
    	lblItinerarioPreco.setBounds(30, 193, 70, 20);
    	
    	lblItinerarioMotorista = new JLabel("Motorista:");
    	lblItinerarioMotorista.setFont(fontePadrao);
    	lblItinerarioMotorista.setBounds(30, 243, 75, 20);
    	
        txtItinerarioOrigem = new JTextField();
        txtItinerarioOrigem.setFont(fontePadrao);
        txtItinerarioOrigem.setBounds(150, 40, 180,30);
        txtItinerarioOrigem.setEnabled(false);
        
        
        txtItinerarioSaida = new JTextField();
        txtItinerarioSaida.setFont(fontePadrao);
        txtItinerarioSaida.setBounds(150, 140, 180, 30);
        txtItinerarioSaida.setEnabled(false);
        
        txtItinerarioPreco = new JTextField();
        txtItinerarioPreco.setFont(fontePadrao);
        txtItinerarioPreco.setBounds(150, 190, 180, 30);
        txtItinerarioPreco.setEnabled(false);

        
        
        cboItinerarioDestino = new JComboBox();
        cboItinerarioDestino.setBounds(150, 90, 180, 30);
        cboItinerarioDestino.addItem("Selecione");
        cboItinerarioDestino.setEnabled(false);
        
 
        cboItinerarioMotorista = new JComboBox();
        cboItinerarioMotorista.setBounds(150, 240, 180, 30);
        cboItinerarioMotorista.setEnabled(false);
        
        
        
        btnItinerarioAddRota = new JButton("Adicionar Rota");
        btnItinerarioAddRota.setBounds(150, 290, 180, 30);
        btnItinerarioAddRota.setFont(fontePadrao);  
        btnItinerarioAddRota.setEnabled(false);
        
        
    	pnlItinerarioRotas.add(lblItinerarioOrigem);
    	pnlItinerarioRotas.add(lblItinerarioDestino);
    	pnlItinerarioRotas.add(lblItinerarioSaida);
    	pnlItinerarioRotas.add(lblItinerarioPreco);
    	pnlItinerarioRotas.add(lblItinerarioMotorista);
    	
    	pnlItinerarioRotas.add(txtItinerarioOrigem);
    	pnlItinerarioRotas.add(txtItinerarioSaida);
    	pnlItinerarioRotas.add(txtItinerarioPreco);
    	
    	
    	pnlItinerarioRotas.add(cboItinerarioDestino);
    	pnlItinerarioRotas.add(cboItinerarioMotorista);
    	
    	pnlItinerarioRotas.add(btnItinerarioAddRota);

    	
    	arrayRotasAddicionadas = new ArrayList<Rota>();
    	

    	
    	
    	btnItinerarioAddRota.addActionListener(new ActionListener() {

     
            @Override
			public void actionPerformed(ActionEvent evt) {
            	if(cboItinerarioDestino.getSelectedIndex() == 0 && txtItinerarioPreco.getText().equals("") && cboItinerarioMotorista.getSelectedItem().equals("Selecione")){
            		JOptionPane.showMessageDialog(pnlItinerario, "Campos obrigatorios!");
                    cboItinerarioDestino.requestFocus();
                    cboItinerarioMotorista.requestFocus();
                    txtItinerarioPreco.requestFocus();
                    
            	}
            	else if(cboItinerarioDestino.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(pnlItinerario, "Selecione um destino!");
                    cboItinerarioDestino.requestFocus();
            	}
            	else if (txtItinerarioPreco.getText().equals("")) {
                    JOptionPane.showMessageDialog(pnlItinerario, "Coloque um preco!");
                    txtItinerarioPreco.requestFocus();
				}
            	else if (cboItinerarioMotorista.getSelectedItem().equals("Selecione")) {
                    JOptionPane.showMessageDialog(pnlItinerario, "Selecione um motorista");
                    cboItinerarioMotorista.requestFocus();
				}
            	
            	
            	else{
	            	int idRotaItinerario = 1;
	            	int idRota = arrayRotaAtual.get(cboItinerarioDestino.getSelectedIndex()-1).getId();
	            	Rota rotaAux = arrayRotaAtual.get(cboItinerarioDestino.getSelectedIndex()-1);
	            	arrayRotasAddicionadas.add(rotaAux); //salvando Array de rotas Para possivel utiliza��o
	            	tbm.addRow(new Object[]{rotaAux.getRota_cidadeOrigem(),rotaAux.getRota_cidadeDestino()});
	            	int idItinerario = arrayItinerario.get(selectedCboIndexItinerario).getId();
	            	if(arrayRotaItinerario.isEmpty()){
	            		ordemRota++;
	            	}
	            	else{
	            		ordemRota = (arrayRotaItinerario.get(arrayRotaItinerario.size()-1).getRotaitinerarioOrdem())+1; //somando um a ordem anterior
	            		idRotaItinerario = (arrayRotaItinerario.get(arrayRotaItinerario.size()-1).getRotaItinerarioId())+1; //somando um ao id anterior          		
	            	}
	            	arrayRotaItinerario.add(new RotaItinerario(idRotaItinerario,idRota , idItinerario,ordemRota));
	                 // Nao esquecer de zerar ordem quando finalizar a insercao
	                
	            	
	            	
	                if(rotaAux.getRota_cidadeDestinoId() == arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeDestinoId()){
	                	btnItinerarioConfirma.setEnabled(true);
	                }
	                int idCidadeOrigemAux = arrayRotaAtual.get(cboItinerarioDestino.getSelectedIndex()-1).getRota_cidadeDestinoId(); //salvando id da nova cidade Origem
	                txtItinerarioOrigem.setText(daoItinerario.consultaCidade(String.valueOf(idCidadeOrigemAux))); //atualizando txt origem
	                carregaComboDestino(idCidadeOrigemAux); //remotando comboDestino
	                
	                tableRotas.setVisible(true);
	                btnItinerarioCancela.setEnabled(true);
	                
	                
	                Horario horario = new Horario();
	                if(arrayRotasAddicionadas.size() ==1){
	                	Time timeSaida = Time.valueOf(txtItinerarioSaida.getText()+":00"); //pegando horario de saida do TXT na primeira vez
	                	int duracao = Integer.parseInt(arrayRotasAddicionadas.get(0).getRotaDuracao()); //pegando duracao da primeira rota
	                	Calendar calAux = Calendar.getInstance();
	                	calAux.setTime(timeSaida);
	                	calAux.add(Calendar.MINUTE, duracao); //somando a duração (em minutos)para obter horario de chegada
	                	String horarioChegada = dateFormat.format(calAux.getTime())+":00";
	                	//System.out.println("Chegadaaaaaa" +horarioChegada);
	                	Double preco = Double.parseDouble(txtItinerarioPreco.getText());
	                	int idMotorista = arrayIdMotorista.get(cboItinerarioMotorista.getSelectedIndex()-1);
	                	int idOnibus = arrayIdOnibus.get(cboItinerarioOnibus.getSelectedIndex()-1);
	                	
	                	horario.setHorario_RotaItinerarioId(idRotaItinerario);
	                	horario.setHorarioSaida(timeSaida.toString());
	                	horario.setHorarioChegada(horarioChegada);
	                	horario.setHorarioPreco(preco);
	                	horario.setHorario_MotoristaId(idMotorista);
	                	horario.setHorario_OnibusId(idOnibus);
	                	
	                	calAux.add(Calendar.MINUTE, 15);//atualizando txt horario d saida do proximo
	                	horarioChegada = dateFormat.format(calAux.getTime());
	                	txtItinerarioSaida.setText(horarioChegada);
	                }
	
	                else{ //caso tenha 1 rota add comeca a adicionar 15 min e e calc hor d chegada
	                	Time horarioChegadaAnt = Time.valueOf(arrayHorario.get(arrayHorario.size()-1).getHorarioChegada());
	                	int duracao = Integer.parseInt(arrayRotasAddicionadas.get(arrayRotasAddicionadas.size()-1).getRotaDuracao());
	                	Calendar calAux = Calendar.getInstance();
	                	calAux.setTime(horarioChegadaAnt);
	                	calAux.add(Calendar.MINUTE, 15);
	                	String horarioSaida = dateFormat.format(calAux.getTime())+":00";
	                	calAux.add(Calendar.MINUTE, duracao);
	                	String horarioChegada = dateFormat.format(calAux.getTime())+":00";
	                	Double preco = Double.parseDouble(txtItinerarioPreco.getText());
	                	int idMotorista = arrayIdMotorista.get(cboItinerarioMotorista.getSelectedIndex()-1);
	                	int idOnibus = arrayIdOnibus.get(cboItinerarioOnibus.getSelectedIndex()-1);
	                	horario.setHorario_RotaItinerarioId(idRotaItinerario);
	                	horario.setHorarioSaida(horarioSaida);
	                	horario.setHorarioChegada(horarioChegada);
	                	horario.setHorarioPreco(preco);
	                	horario.setHorario_MotoristaId(idMotorista);
	                	horario.setHorario_OnibusId(idOnibus);
	                	
	                	calAux.add(Calendar.MINUTE, 15);//atualizando txt horario d saida do proximo
	                	horarioChegada = dateFormat.format(calAux.getTime());
	                	txtItinerarioSaida.setText(horarioChegada);
	                }
	                
	                	if (chBxDomingo.isSelected()) {
	    
							arrayHorario.add(clonaHorario(horario, 1));
						}
	                	if (chBxSegundaFeira.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 2));
							
						}
	                	if (chBxTercaFeira.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 3));
						}
	                	if (chBxQuartaFeira.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 4));
						}
	                	if (chBxQuintaFeira.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 5));
						}
	                	if (chBxSextaFeira.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 6));
						}
	                	if (chBxSabado.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 7));
						}
	                	if (chBxFeriados.isSelected()) {
	                		arrayHorario.add(clonaHorario(horario, 8));
						}
	
	                if (arrayItinerario.get(selectedCboIndexItinerario).getItinerario_cidadeDestino().equals(arrayRotasAddicionadas.get(arrayRotasAddicionadas.size()-1).getRota_cidadeDestino())){
	                	btnItinerarioAddRota.setEnabled(false);
	                	btnItinerarioConfirma.setEnabled(true);
					    cboItinerarioDestino.setEnabled(false);
					    
					    txtItinerarioPreco.setEnabled(false);
					    txtItinerarioPreco.setText("");
					    txtItinerarioSaida.setText("");
					    cboItinerarioMotorista.setEnabled(false);
						
					}
	                for (int i = 0; i < arrayHorario.size(); i++) {
	                	 System.out.println("Dia:"+arrayHorario.get(i).getHorarioDiaId());
	                     System.out.println("Horario saida: "+arrayHorario.get(i).getHorarioSaida());
	                     System.out.println("Horario Chegada: "+arrayHorario.get(i).getHorarioChegada());
	                     System.out.println("Rota:"+arrayHorario.get(i).getHorario_RotaItinerarioId());
	                     System.out.println("Quantidade tabela horario:"+arrayHorario.size());
	                     System.out.println("---------------------------------------------------------");
					}
	                
	               
	                
	            }
            }
        });
	 
        
        KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				if( Character.isDigit( arg0.getKeyChar() ) == false )
					arg0.setKeyChar( KeyEvent.CHAR_UNDEFINED );
				else
				{
					if( txtItinerarioPreco.getText().length() + 1 >= 3)
					{
						String aux = "";
						int indice = txtItinerarioPreco.getText().indexOf( "." );
						
						if( indice != -1 )
						{
							aux = txtItinerarioPreco.getText().substring( 0, indice );
							aux += txtItinerarioPreco.getText().substring( indice + 1, txtItinerarioPreco.getText().length() );
							txtItinerarioPreco.setText( aux );
						}					
						
						aux = txtItinerarioPreco.getText().substring( 0, txtItinerarioPreco.getText().length() -1 );
						aux += ".";
						aux += txtItinerarioPreco.getText().substring( txtItinerarioPreco.getText().length() -1, txtItinerarioPreco.getText().length() );
						
						txtItinerarioPreco.setText( aux );
					}
						
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			
			}
		};
        
        txtItinerarioPreco.addKeyListener( keyListener );
    }
    
    private void inserirPnlItinerarioLista() {
    	pnlItinarioLista = new JPanel();
    	   
    	pnlItinarioLista.setBounds(393, 230, 275,340);
    	pnlItinarioLista.setBackground(new Color(240, 240, 240));
    	pnlItinarioLista.setLayout(null);
	 	
    	
		

    	tbm = new DefaultTableModel();
    	tableRotas = new JTable(tbm);
    	
    	tbm.addColumn("Origem");
    	tbm.addColumn("Destino");
    	
//    	tbm.removeRow(1);
//    	tbm.getDataVector().removeAllElements();
//    	tbm.fireTableDataChanged();
//    	for(int i = tbm.getRowCount()-1; i >= 0; i--){ 
//            tbm.setValueAt("haha", 0, 0);
//        }

    	
		tableRotas.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(tableRotas);
		scrollPane.setBounds(0, 0,271,250);
		scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Rotas Adicionadas", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, fontePadrao));
		tableRotas.setVisible(false);
		pnlItinarioLista.add(scrollPane,BorderLayout.CENTER);
		
		tableRotas.addMouseListener(new MouseAdapter() {
			  @Override
			public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 1) {
			      JTable target = (JTable)e.getSource();
			      rowIndexSelected = target.getSelectedRow();
			      System.out.println(rowIndexSelected);
			      btnItinerarioAlterarRota.setEnabled(true);
			    }
			  }
			});
    	
    }
    
    private void carregaComboItinerario(){
    	daoItinerario = new DaoItinerario();
    	arrayItinerario  = daoItinerario.consultarTodosItinerarios();
        cboItinerario.removeAllItems();
        cboItinerario.addItem("Selecione");
        for (int i = 0; i < arrayItinerario.size(); i++) {
        	cboItinerario.addItem(arrayItinerario.get(i).getItinerario_cidadeOrigem()+" - "+arrayItinerario.get(i).getItinerario_cidadeDestino());
        }
    }
    
    private void carregaComboMinuto(){//SALVAR IDS
    	
        
    	arrayMinuto = new ArrayList<String>();
    	for (int i = 0; i < 60; i+=5) {
			arrayMinuto.add(format.format(i));
		}
        cboItinerarioMin.removeAllItems();
        cboItinerarioMin.addItem("--");
        for (int i = 0; i < arrayMinuto.size(); i++) {

        	cboItinerarioMin.addItem(arrayMinuto.get(i));
        }
    	
    }
    private void carregaComboHora(){//SALVAR IDS
        
    	arrayHora = new ArrayList<String>();
    	for (int i = 0; i < 24; i++) {
    		arrayHora.add(format.format(i));
		}
        cboItinerarioHora.removeAllItems();
        cboItinerarioHora.addItem("--");
        for (int i = 0; i < arrayHora.size(); i++) {

        	cboItinerarioHora.addItem(arrayHora.get(i));
        }
    	
    }
    
    private void carregaComboDestino(int idCidadeOrigem){
    	daoRota = new DaoRota();
    	arrayRotaAtual = daoRota.carregaRotas(idCidadeOrigem);
    	cboItinerarioDestino.removeAllItems();
    	cboItinerarioDestino.addItem("Selecione");
        for (int i = 0; i < arrayRotaAtual.size(); i++) {
        	cboItinerarioDestino.addItem(arrayRotaAtual.get(i).getRota_cidadeDestino());
        	
        }
    }
    
    private void carregaComboMotorista(){
        daoItinerarioMotorista = new DaoMotorista();
        arrayItinerarioMotoristaList = new ArrayList<Motorista>();
        arrayItinerarioMotoristaList = daoItinerarioMotorista.consultarTodosMotoristas();
        arrayIdMotorista = new ArrayList<Integer>();
        cboItinerarioMotorista.removeAllItems();
        cboItinerarioMotorista.addItem("Selecione");
        for (int i = 0; i < arrayItinerarioMotoristaList.size(); i++) {
        	arrayIdMotorista.add(arrayItinerarioMotoristaList.get(i).getId()); //salvando ids Motorista
        	cboItinerarioMotorista.addItem(arrayItinerarioMotoristaList.get(i).getId());
        }
    	
    }
    
    private void carregaComboOnibus(){
        daoItinerarioOnibus = new DaoOnibus();
        arrayItinerarioOnibus = new ArrayList<Onibus>();
        arrayIdOnibus = new ArrayList<Integer>();
        arrayItinerarioOnibus = daoItinerarioOnibus.consultarTodosOnibus();
        cboItinerarioOnibus.removeAllItems();
        cboItinerarioOnibus.addItem("Selecione");
        
        for (int i = 0; i < arrayItinerarioOnibus.size(); i++) {
        	arrayIdOnibus.add(arrayItinerarioOnibus.get(i).getId());
        	cboItinerarioOnibus.addItem(arrayItinerarioOnibus.get(i).getPlaca());
        }
    	
    }
    //Geral
    private Horario clonaHorario(Horario horario, int dia){
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
    
    public void reinicia(){
    		
    		cboItinerarioDestino.removeAllItems();
    		cboItinerarioDestino.addItem("Selecione");
    		txtItinerarioOrigem.setText("");
    		
    		txtItinerarioPreco.setText("");
    		txtItinerarioPreco.setEnabled(false);
			arrayRotaAtual.clear();
			arrayRotaItinerario.clear();
			arrayRotasAddicionadas.clear();
			arrayHorario.clear();
			
			tbm.getDataVector().removeAllElements();
			tbm.fireTableDataChanged();
			
			cboItinerario.setEnabled(true);
			cboItinerario.setSelectedIndex(0);
			
			cboItinerarioOnibus.setEnabled(false);
        	cboItinerarioOnibus.setSelectedIndex(0);
        	tableRotas.setVisible(false);
        	
        	cboItinerarioHora.setSelectedIndex(0);
        	cboItinerarioHora.setEnabled(false);
        	
        	flagMin =1;
        	cboItinerarioMin.setEnabled(false);
        	cboItinerarioMin.setSelectedIndex(0);
        	
        	cboItinerarioMotorista.setSelectedIndex(0);
        	cboItinerarioMotorista.setEnabled(false);
        	
        	cboItinerarioDestino.setSelectedIndex(0);
        	cboItinerarioDestino.setEnabled(false);
        	
        	btnItinerarioAddRota.setEnabled(false);
        	btnItinerarioConfirma.setEnabled(false);
        	btnItinerarioAlterarRota.setEnabled(false);
        	
        	txtItinerarioSaida.setText("");
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
    
    DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    private NumberFormat format;
    JTable tableRotas;
    DefaultTableModel tbm;
    private DaoOnibus daoItinerarioOnibus;
    private int flagMin=0;
    private DaoMotorista daoItinerarioMotorista;
    private ArrayList<Motorista> arrayItinerarioMotoristaList;
    private ArrayList<Onibus> arrayItinerarioOnibus;
    private DaoItinerario daoItinerario;
    private DaoRota daoRota;
    private DaoHorario daoHorario;
    private DaoRotaItinerario daoRotaItinerario;
    private ArrayList<Integer> arrayIdOnibus;
    private ArrayList<Integer> arrayIdMotorista;
    private ArrayList<Itinerario> arrayItinerario;
    private ArrayList<Rota> arrayRotaAtual;
    private ArrayList<Rota> arrayRotasAddicionadas;
    private ArrayList<RotaItinerario> arrayRotaItinerario;
    private ArrayList<Horario> arrayHorario;
    private ArrayList<String>arrayMinuto;
    private ArrayList<String>arrayHora;
  
    private int selectedCboIndexItinerario;
    private int ordemRota=0;
    private int rowIndexSelected;

    
    private Font fontePadrao;
    private GridLayout gridLayout2_4;
    
    //JTextFields
    private JTextField txtItinerarioOrigem;
    private JTextField txtItinerarioSaida;
    private JTextField txtItinerarioPreco;

    
    //Jpanel
    private JPanel pnlItinerarioRotas;
    private JPanel pnlItinarioLista;
    private JPanel pnlItinerario;
    private JPanel pnlItinerarioDias;
    //JLbabels
    private JLabel lblItinerario;
    private JLabel lblItinerarioHoraSaida;
    private JLabel lblItinerarioTipoOnibus;
    private JLabel lblItinerarioOrigem;
    private JLabel lblItinerarioDestino;
    private JLabel lblItinerarioSaida;
    private JLabel lblItinerarioPreco;
    private JLabel lblItinerarioMotorista;
    //JButtons
    private JButton btnItinerarioAddRota;
    private JButton btnItinerarioConfirma;
    private JButton btnItinerarioCancela;
    private JButton btnItinerarioAlterarRota;
    
    //ComboBoxes
    private JComboBox cboItinerario;
    private JComboBox cboItinerarioMotorista;
    private JComboBox cboItinerarioDestino;
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
	
    
    public static void main(String[] args) {
    	java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PanelHorario().setVisible(true);
            }
        });
		
	}
}
//JTable e ScroolPane // TableModel

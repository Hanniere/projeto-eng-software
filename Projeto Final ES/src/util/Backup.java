package util;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;

public class Backup {


    public void CriarBackup() throws URISyntaxException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o caminho para realizar o backup");

        int variableChooser = fileChooser.showSaveDialog(null);

        if (variableChooser != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Backup não realizado", "Janela finalizada", JOptionPane.CANCEL_OPTION);
        } else {
            String commandDump = "";
            if (System.getProperty("os.name").toUpperCase().compareTo("LINUX") == 0) {
                commandDump = "/usr/bin/mysqldump";
            } else {
            	final String uri;
    	        String EnderecoDoJar = PastaCorrente();
            	commandDump = EnderecoDoJar.concat("/mysqldump.exe");// =  getClass().getResource("/imagens/mysqldump.exe").getPath();
            }
            String location = fileChooser.getSelectedFile() + ".sql";
            ProcessBuilder pb = new ProcessBuilder(commandDump, "--user=root", "--password=root", "ProjES", "--result-file=" + location);
            try {
                pb.start();
                JOptionPane.showMessageDialog(null, "Backup efetuado com sucesso");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void RestaurarBackup() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o arquivo que deseja usar para restaurar o Bakcup");

        int variableChooser = fileChooser.showOpenDialog(null);
        if (variableChooser == JFileChooser.APPROVE_OPTION) {
            String cmd = null;
            String location = fileChooser.getSelectedFile().getAbsolutePath() + "";
            if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
                cmd = "/bin/sh -c mysql --user=root --password=root --host=localhost ProjES < \"" + location + "\"";
            } else {
                if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
                    JFileChooser fileChooserMysql = new JFileChooser();
                    fileChooserMysql.setDialogTitle("Selecione o executavel do mysql");
                    int variableChooser1 = fileChooserMysql.showOpenDialog(null);
                    if (variableChooser1 == JFileChooser.APPROVE_OPTION) {
                    	String locationMysql = fileChooserMysql.getSelectedFile().getAbsolutePath() + "";
                    	locationMysql = locationMysql.substring(0, locationMysql.lastIndexOf(".exe"));
                    	cmd = "cmd.exe /c \""+locationMysql+"\" -hlocalhost -uroot -proot ProjES < " + location;
                    }
                    cmd = "cmd.exe /c \"C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql\" -hlocalhost -uroot -proot ProjES < " + location;
                }
            }
            try {
                Runtime.getRuntime().exec(cmd);
                JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public String PastaCorrente() throws URISyntaxException {  
        String caminho = Backup.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();  
        caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);  
        return caminho;
}
}

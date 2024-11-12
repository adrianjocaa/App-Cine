package AppCine.maven;


import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;

public class Cine {

    private final int NUMERO_FILAS = 10;  // 
    private final int NUMERO_COLUNAS = 10;  // 
    private Character[][] sala;
    private List<String> historicoCompras;
    private List<String> clientes;

    public int getNUMERO_FILAS(){
        return NUMERO_FILAS;
    }

    public int getNUMERO_COLUNAS(){
        return NUMERO_COLUNAS;
    }

    public Cine() {
        this.sala = new Character[NUMERO_FILAS + 1][NUMERO_COLUNAS + 1];
        this.historicoCompras = new ArrayList<>();
        this.clientes = new ArrayList<>();
        preencherSala();


    }

    
    public void preencherSala() {
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[0].length; j++) {
                if (i == 0 && j == 0) sala[i][j] = ' ';
                else if (i == 0) sala[i][j] = Character.forDigit(j, 10);
                else if (j == 0) sala[i][j] = Character.forDigit(i, 10);
                else sala[i][j] = 'D';
            }
        }
    }

    public String imprimirSala() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < sala.length; i++) {
            for (int j = 1; j < sala[0].length; j++) {
                if (sala[i][j] == 'D') {
                    sb.append("D ");  
                } else {
                    sb.append("R ");  
                }
            }
            sb.append("\n");
        }
        return sb.toString();  
    }

   
    public boolean verificarPoltrona(int fila, int coluna) {
        if (fila > NUMERO_FILAS || coluna > NUMERO_COLUNAS || fila == 0 || coluna == 0) {
            return false; 
        }
        return sala[fila][coluna] == 'D'; 
    }

    public void comprarIngresso(int fila, int coluna, String nomeCliente) {
        sala[fila][coluna] = 'O'; 
        historicoCompras.add("Assento [" + fila + "," + coluna + "] comprado por " + nomeCliente);
        clientes.add(nomeCliente); 
    }

    
    public String getHistoricoCompras() {
        StringBuilder historico = new StringBuilder();
        for (String compra : historicoCompras) {
            historico.append(compra).append("\n");
        }
        return historico.toString();


    }
}

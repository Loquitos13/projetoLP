import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;


public class testeficheiros {
    public static void main(String[] args) {
        // Caminho do arquivo .dat
        List<String> Filelist = new ArrayList<>(Arrays.asList("circulo_coimbra.dat", "circulo_coimbra.dat", "Orange")) ;

        for(String file:Filelist){
            try {
                ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));

                // Loop para ler e imprimir votos de cada concelho
                while (true) {
                    try {
                        VotosCirculoEleitoral obj = (VotosCirculoEleitoral) dis.readObject();
                        if (obj == null) break; // Se não houver mais objetos para ler, sai do loop

                        System.out.println(obj.getNomeCirculo());
                        // Itera sobre o mapa de votos por concelho
                        for (Map.Entry<String, VotosConcelho> entry : obj.getVotosPorConcelho().entrySet()) {
                            String concelho = entry.getKey();
                            VotosConcelho votosConcelho = entry.getValue();
                            System.out.println(concelho + ": " + votosConcelho.getVotosPorPartido());
                        }
                        System.out.println(); // Adiciona uma linha em branco para separar os resultados dos diferentes círculos
                    } catch (EOFException e) {
                        // EOFException é lançada quando não há mais objetos para ler
                        break;
                    }
                }
                
                dis.close();
                
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
            }
        }
        
    }
}

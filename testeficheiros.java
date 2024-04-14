import java.io.*;
import java.util.HashMap;
import java.util.Map;

class VotosCirculoEleitoral implements Serializable {
    private String nomeCirculo;
    private Map<String, VotosConcelho> votosPorConcelho;
    
    public VotosCirculoEleitoral(String nomeCirculo) {
        this.nomeCirculo = nomeCirculo;
        this.votosPorConcelho = new HashMap<>();
    }

    public void adicionarVotosConcelho(String concelho, VotosConcelho dadosVotosConcelho) {
        votosPorConcelho.put(concelho, dadosVotosConcelho);
    }

    public String getNomeCirculo() {
        return nomeCirculo;
    }

    public Map<String, VotosConcelho> getVotosPorConcelho() {
        return votosPorConcelho;
    }
}

class VotosConcelho implements Serializable {
    private String concelho;
    private Map<String, Integer> votosPorPartido;
    private Map<String, Integer> votosNulos;
    private Map<String, Integer> votosBrancos;

    public VotosConcelho(String concelho) {
        this.concelho = concelho;
        this.votosPorPartido = new HashMap<>();
        this.votosNulos = new HashMap<>();
        this.votosBrancos = new HashMap<>();
    }

    public void adicionarVotos(String partido, int votos) {
        votosPorPartido.put(partido, votos);
    }

    public String getConcelho() {
        return concelho;
    }

    public Map<String, Integer> getVotosPorPartido() {
        return votosPorPartido;
    }

    public Map<String, Integer> getVotosNulos(){
        return votosNulos;
    }

    public Map<String, Integer> getVotosBrancos(){
        return votosBrancos;
    }
}

public class testeficheiros {
    public static void main(String[] args) {
        // Caminho do arquivo .dat
        String inputFile = "C:\\Users\\LOQUITOS\\Downloads\\tp lp1\\circulo_coimbra.dat";

        try {
            ObjectInputStream dis = new ObjectInputStream(new FileInputStream(inputFile));

            // Loop para ler e imprimir os votos de cada concelho
            while (true) {
                try {
                    VotosCirculoEleitoral obj = (VotosCirculoEleitoral) dis.readObject();
                    if (obj == null) break; // Se não houver mais objetos para ler, sai do loop

                    System.out.println(obj.getNomeCirculo());
                    // Integra sobre o mapa de votos por concelho
                    for (Map.Entry<String, VotosConcelho> entry : obj.getVotosPorConcelho().entrySet()) {
                        String concelho = entry.getKey();
                        VotosConcelho votosConcelho = entry.getValue();
                        System.out.println(concelho + ": " + votosConcelho.getVotosPorPartido());
                    }
                    System.out.println(); // Adiciona uma linha em branco para separar os resultados dos diferentes círculos
                } catch (EOFException e) {
                    // EOFException é iniciada quando não há mais objetos para ler
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
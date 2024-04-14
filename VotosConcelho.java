
import java.io.*;
import java.util.*;

public class VotosConcelho implements Serializable {
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
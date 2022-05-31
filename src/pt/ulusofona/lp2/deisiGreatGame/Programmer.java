package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.TreeSet;

public class Programmer {
    private final String name;
    private final TreeSet<String> linguagensFavoritas;
    private final int id;
    private int posicao;
    private int nDadoAnterior;
    private final ProgrammerColor cor;
    private final ArrayList<Tool> ferramentas;
    private boolean emJogo, imunidade, infiniteLoop;

    public Programmer(String name, TreeSet<String> linguagensFavoritas, int id, int posicao, ProgrammerColor cor) {
        this.name = name;
        this.linguagensFavoritas = linguagensFavoritas;
        this.id = id;
        this.posicao = posicao;
        this.cor = cor;
        this.ferramentas = new ArrayList<>();
        this.emJogo = true;
        this.imunidade = false;
        this.infiniteLoop = false;
    }

    public Programmer(String name, TreeSet<String> linguagensFavoritas, int id, int posicao, int nDadoAnterior, ProgrammerColor cor, ArrayList<Tool> ferramentas, boolean emJogo, boolean imunidade, boolean infiniteLoop) {
        this.name = name;
        this.linguagensFavoritas = linguagensFavoritas;
        this.id = id;
        this.posicao = posicao;
        this.nDadoAnterior = nDadoAnterior;
        this.cor = cor;
        this.ferramentas = ferramentas;
        this.emJogo = emJogo;
        this.imunidade = imunidade;
        this.infiniteLoop = infiniteLoop;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ProgrammerColor getColor(){
        return this.cor;
    }

    public ArrayList<Tool> getFerramentas(){
        return ferramentas;
    }

    public void addFerramenta(Tool novaFerramenta){
        ferramentas.add(novaFerramenta);
    }

    public void removeFerramenta(Tool novaFerramenta){
        ferramentas.remove(novaFerramenta);
    }

    public int getPosicao(){
        return posicao;
    }

    public String getLinguagensString(){
        StringBuilder languages = new StringBuilder();
        int count = 0;
        for(String nome : linguagensFavoritas){
            if(count != linguagensFavoritas.size() - 1){
                languages.append(nome).append("; ");
            }else{
                languages.append(nome);
            }
            count++;
        }
        return languages.toString();
    }

    public TreeSet<String> getLinguagensFavoritas(){
        return linguagensFavoritas;
    }

    public void adicionarPosicao(int nrPosicoes){
        posicao += nrPosicoes;
    }

    public void retirarPosicao(int nrPosicoes){
        posicao -= nrPosicoes;
    }

    public void setPosicao(int position){
        posicao = position;
    }

    public String getFerramentasString(){
        StringBuilder tool = new StringBuilder();
        int count = 0;
        if(ferramentas.isEmpty()){
            return "No tools";
        }
        for(Tool ferramenta : ferramentas){
            if(count != ferramentas.size() - 1){
                tool.append(ferramenta).append(";");
            }else{
                tool.append(ferramenta);
            }
            count++;
        }
        return tool.toString();
    }

    public String getToStringEstado(){
        if(emJogo){
            return "Em Jogo";
        }
        return "Derrotado";
    }

    public boolean getEstado(){
        return this.emJogo;
    }

    public void setEstado(boolean novoEstado){
        this.emJogo = novoEstado;
    }

    public boolean getInfinteLoop(){
        return this.infiniteLoop;
    }

    public void setInfinteLoop(boolean valor){
        this.infiniteLoop = valor;
    }

    public boolean getImunidade(){
        return this.imunidade;
    }

    public void setImunidade(boolean estado){
        imunidade = estado;
    }

    public int getnDadoAnterior(){
        return nDadoAnterior;
    }

    public void setnDadoAnterior(int numero){
        nDadoAnterior = numero;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + posicao + " | " + getFerramentasString() + " | " + getLinguagensString() + " | " + getToStringEstado();
    }
}
package pt.ulusofona.lp2.deisiGreatGame;

public abstract class Casa {
    protected int id, posicao,abyssOrTool;
    protected String titulo;

    public Casa() {
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getAbyssOrTool(){
        return abyssOrTool;
    }

    public int getPosicao(){
        return posicao;
    }

    public abstract String reactToSquare(Programmer programador, GameManager jogo);
}
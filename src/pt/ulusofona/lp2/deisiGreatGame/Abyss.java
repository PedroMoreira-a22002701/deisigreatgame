package pt.ulusofona.lp2.deisiGreatGame;

public abstract class Abyss extends Casa {

    public Abyss(int id, String titulo, int numeroCasa) {
        this.id = id;
        this.posicao = numeroCasa;
        this.titulo = titulo;
        this.abyssOrTool = 0;
    }
}


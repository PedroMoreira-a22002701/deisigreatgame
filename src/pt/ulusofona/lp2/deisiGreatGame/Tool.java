package pt.ulusofona.lp2.deisiGreatGame;
public class Tool extends Casa {

    public Tool(int id, String titulo, int numeroCasa) {
        this.id = id;
        this.posicao = numeroCasa;
        this.titulo = titulo;
        this.abyssOrTool = 1;
    }

    public Tool(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        StringBuilder append = new StringBuilder();
        boolean naoTemFerramenta = true;

        if (jogador.getPosicao() == posicao) {
            if (jogador.getFerramentas() != null && !jogador.getFerramentas().isEmpty()) {
                for (Tool tool : jogador.getFerramentas()) {
                    if (tool.getTitulo().equals(this.titulo)) {
                        append.append("O jogador ja contém esta ferramenta");
                        naoTemFerramenta = false;
                        break;
                    }
                }
            }
            if(naoTemFerramenta) {
                jogador.addFerramenta(this);
                append.append("Pegou a ferramenta: ").append(this.titulo);
            }
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogo.trocaTurno();
        return append.toString();
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
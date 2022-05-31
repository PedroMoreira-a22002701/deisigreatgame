package pt.ulusofona.lp2.deisiGreatGame;

public class Syntax extends Abyss {
    public Syntax(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(0);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 4 || jogador.getFerramentas().get(i).getId() == 5) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Erro de Sintaxe - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                }
            }
        }
        if ((jogador.getPosicao()) > 1) {
            jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
            jogador.retirarPosicao(1);
            jogo.getCurrentBoard().get(jogador.getPosicao()).add(jogador);
            jogador.setImunidade(true);
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogo.trocaTurno();
        return "Caíste no abismo: Erro de sintaxe - Volte uma casa";
    }
}

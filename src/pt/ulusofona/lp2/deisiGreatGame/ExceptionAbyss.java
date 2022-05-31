package pt.ulusofona.lp2.deisiGreatGame;

public class ExceptionAbyss extends Abyss {
    public ExceptionAbyss(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(2);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 5 || jogador.getFerramentas().get(i).getId() == 3) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Exception - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";

                }
            }
        }
        if ((jogador.getPosicao()) > 2) {
            jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
            jogador.retirarPosicao(2);
            jogo.getCurrentBoard().get(jogador.getPosicao()).add(jogador);

        } else {
            jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
            jogador.setPosicao(1);
            jogo.getCurrentBoard().get(1).add(jogador);
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogador.setImunidade(true);
        jogo.trocaTurno();
        return "Caíste no abismo: Exception - Volte duas casas";
    }
}

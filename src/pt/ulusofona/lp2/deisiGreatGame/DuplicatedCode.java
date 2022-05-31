package pt.ulusofona.lp2.deisiGreatGame;

public class DuplicatedCode extends Abyss {

    public DuplicatedCode(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(5);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 0) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Duplicated Code - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                }
            }
        }
        if (jogador.getPosicao() - jogo.getnDadoAtual() > 0) {
            jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
            jogador.retirarPosicao(jogo.getnDadoAtual());
            jogo.getCurrentBoard().get(jogador.getPosicao()).add(jogador);
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogador.setImunidade(true);
        jogo.trocaTurno();
        return "Caíste no abismo: Duplicated Code - Voltas à posição que estavas na rodada anterior";
    }
}

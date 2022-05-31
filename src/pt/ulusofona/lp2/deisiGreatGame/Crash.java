package pt.ulusofona.lp2.deisiGreatGame;

public class Crash extends Abyss {
    public Crash(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(4);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 3) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Crash - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                }
            }
        }
        jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
        jogador.setPosicao(1);
        jogo.getCurrentBoard().get(1).add(jogador);
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogador.setImunidade(true);
        jogo.trocaTurno();
        return "Caíste no abismo: Crash (aka Rebentanço) - Tens de voltar para a primeira casa do jogo!";
    }
}

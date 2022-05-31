package pt.ulusofona.lp2.deisiGreatGame;

public class SideEffects extends Abyss {
    public SideEffects(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(6);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        int posicaoARemover = jogador.getnDadoAnterior() + jogo.getnDadoAtual();
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 1) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Efeitos Secundários - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                }
            }
        }
        jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
        jogador.retirarPosicao(posicaoARemover);
        jogo.getCurrentBoard().get(jogador.getPosicao()).add(jogador);

        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogador.setImunidade(true);
        jogo.trocaTurno();
        return "Caíste no abismo: Efeitos secundários - Voltas à casa que estavas a duas rodadas atrás";
    }
}

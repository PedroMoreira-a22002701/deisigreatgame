package pt.ulusofona.lp2.deisiGreatGame;

public class Logic extends Abyss {
    public Logic(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(1);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        int n = jogo.getnDadoAtual() / 2;
        if (n == 0) {
            n = 1;
        }
        if (jogador.getFerramentas() != null) {
            for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                if (jogador.getFerramentas().get(i).getId() == 2 || jogador.getFerramentas().get(i).getId() == 5) {
                    String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                    jogador.removeFerramenta(jogador.getFerramentas().get(i));
                    jogador.setnDadoAnterior(jogo.getnDadoAtual());
                    jogo.trocaTurno();
                    return "Escapaste do abismo: Erro de Lógica - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                }
            }
        }
        if ((jogador.getPosicao()) > 1) {
            jogo.getCurrentBoard().get(jogador.getPosicao()).remove(jogador);
            jogador.retirarPosicao(n);
            jogo.getCurrentBoard().get(jogador.getPosicao()).add(jogador);
            jogador.setImunidade(true);
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogo.trocaTurno();
        return "Caíste no abismo: Erro de lógica - Volte n casas (n = numero do dado atual/ 2)";
    }
}

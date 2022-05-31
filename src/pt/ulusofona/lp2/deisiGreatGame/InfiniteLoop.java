package pt.ulusofona.lp2.deisiGreatGame;

public class InfiniteLoop extends Abyss {
    public InfiniteLoop(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(8);
        if (!jogador.getImunidade()) {
            if (jogo.getCurrentBoard().get(jogador.getPosicao()).size() == 1) {
                if (jogador.getFerramentas() != null) {
                    for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                        if (jogador.getFerramentas().get(i).getId() == 1) {
                            String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                            jogador.removeFerramenta(jogador.getFerramentas().get(i));
                            jogador.setInfinteLoop(false);
                            jogador.setnDadoAnterior(jogo.getnDadoAtual());
                            jogo.trocaTurno();
                            jogador.setImunidade(false);
                            return "Escapaste do abismo: Ciclo Infinito - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                        }
                    }
                }
                jogador.setnDadoAnterior(jogo.getnDadoAtual());
                jogador.setInfinteLoop(true);
                jogo.trocaTurno();
                jogador.setImunidade(false);
                return "Caíste no Ciclo infinito - Estás preso até outro jogador entrar nesta casa";

            } else {
                if (jogador.getFerramentas() != null) {
                    for (int i = 0; i < jogador.getFerramentas().size(); i++) {
                        if (jogador.getFerramentas().get(i).getId() == 1) {
                            String nomeFerramenta = jogador.getFerramentas().get(i).getTitulo();
                            jogador.removeFerramenta(jogador.getFerramentas().get(i));
                            jogador.setInfinteLoop(false);
                            jogador.setnDadoAnterior(jogo.getnDadoAtual());
                            jogo.trocaTurno();
                            return "Escapaste do abismo: Ciclo Infinito - A ferramenta: " + nomeFerramenta + " foi retirada do seu inventário!";
                        }
                    }
                }
                jogador.setnDadoAnterior(jogo.getnDadoAtual());
                jogador.setInfinteLoop(true);
                jogo.getCurrentBoard().get(jogador.getPosicao()).get(0).setInfinteLoop(false);
                jogo.trocaTurno();
                jogador.setImunidade(false);
                return "Caíste no Ciclo infinito - Estás preso até outro jogador entrar nesta casa. Liberaste o jogador que já estava nesta posição";
            }
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogo.trocaTurno();
        jogador.setImunidade(false);
        return "Relaxa, não caíste no Ciclo infinito";
    }
}

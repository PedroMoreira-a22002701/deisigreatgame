package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class SegmentationFault extends Abyss {
    public SegmentationFault(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        String nomeFerramenta = "";
        jogo.addCountAbismosPassados(9);
        if (jogo.getCurrentBoard().get(jogador.getPosicao()).size() > 1) {
            ArrayList<Programmer> jogadoresNaPosicao = new ArrayList<>(jogo.getCurrentBoard().get(jogador.getPosicao()));
            for (Programmer programmer : jogadoresNaPosicao) {
                boolean temFerramenta = false;
                if (programmer.getFerramentas() != null) {
                    for (int x = 0; x < programmer.getFerramentas().size(); x++) {
                        if (programmer.getFerramentas().get(x).getId() == 3) {
                            nomeFerramenta = programmer.getFerramentas().get(x).getTitulo();
                            temFerramenta = true;
                            programmer.removeFerramenta(programmer.getFerramentas().get(x));
                            break;
                        }
                    }
                }
                if (!temFerramenta) {
                    if (jogador.getPosicao() > 3) {
                        jogo.getCurrentBoard().get(jogador.getPosicao()).remove(programmer);
                        programmer.retirarPosicao(3);
                        jogo.getCurrentBoard().get(jogador.getPosicao()).add(programmer);
                    }else{
                        jogo.getCurrentBoard().get(jogador.getPosicao()).remove(programmer);
                        programmer.setPosicao(1);
                        jogo.getCurrentBoard().get(1).add(programmer);
                    }
                }
            }
        }
        jogador.setnDadoAnterior(jogo.getnDadoAtual());
        jogador.setImunidade(true);
        jogo.trocaTurno();
        return "Caíste no abismo Segmentation Fault - Todos os jogadores que estão nesta casa (sem a ferramenta " + nomeFerramenta + ") voltam 3 casas";
    }
}

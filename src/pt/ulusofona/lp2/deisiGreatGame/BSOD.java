package pt.ulusofona.lp2.deisiGreatGame;

public class BSOD extends Abyss {
    public BSOD(int id, String titulo, int numeroCasa) {
        super(id, titulo, numeroCasa);
    }

    @Override
    public String reactToSquare(Programmer jogador, GameManager jogo) {
        jogo.addCountAbismosPassados(7);
        jogador = jogo.getJogadoresTurno().get(jogo.getCountPlayer());
        jogador.setEstado(false);
        jogo.getJogadoresTurno().remove(jogador);
        if (jogo.getCountPlayer() >= jogo.getJogadoresTurno().size()) {
            jogo.setCountPlayer(0);
        }
        jogo.addCountTurno();
        return "Caíste no abismo: Blue Screen of Death - Perdeste o jogo :(";
    }
}

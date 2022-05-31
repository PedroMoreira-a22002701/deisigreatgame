package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {

    private TreeSet<String> sortLanguages;
    private final ArrayList<Programmer> jogadores;
    private final ArrayList<Programmer> jogadoresTurno;
    private final ArrayList<Programmer> jogadoresTemp;
    private final HashMap<Integer, Integer> casasPassadas;
    private final HashMap<Integer, Integer> abismosPassados;
    private ProgrammerColor color;
    private final HashMap<Integer, ArrayList<Programmer>> currentBoard;
    private final HashMap<Integer, Casa> currentBoardWithAbyssOrTool;
    private int countPlayer, countTurno, nDadoAtual;

    public GameManager() {
        this.sortLanguages = new TreeSet<>();
        this.jogadores = new ArrayList<>();
        this.jogadoresTurno = new ArrayList<>();
        this.jogadoresTemp = new ArrayList<>();
        this.currentBoard = new HashMap<>();
        this.currentBoardWithAbyssOrTool = new HashMap<>();
        this.casasPassadas = new HashMap<>();
        this.abismosPassados = new HashMap<>();
        this.countPlayer = 0;
        this.countTurno = 1;
    }

    public ArrayList<Programmer> getJogadoresTurno() {
        return jogadoresTurno;
    }

    public ArrayList<Programmer> getJogadores() {
        return jogadores;
    }

    public HashMap<Integer, ArrayList<Programmer>> getCurrentBoard() {
        return currentBoard;
    }

    public int getCountPlayer() {
        return countPlayer;
    }

    public HashMap<Integer, Integer> getCasasPassadas() {
        return casasPassadas;
    }

    public HashMap<Integer, Integer> getAbismosPassados() {
        return abismosPassados;
    }

    public void addCountAbismosPassados(int id) {
        if(abismosPassados.get(id) != null) {
            abismosPassados.put(id, abismosPassados.get(id) + 1);
        }
    }

    public HashMap<Integer, Casa> getCurrentBoardWithAbyssOrTool() {
        return currentBoardWithAbyssOrTool;
    }

    public int getnDadoAtual() {
        return nDadoAtual;
    }

    public void setCountPlayer(int countPlayer) {
        this.countPlayer = countPlayer;
    }

    public void addCountTurno() {
        this.countTurno++;
    }

    public void createAbyss(int idTipo , int posicao) {
        switch (idTipo) {
            case 0 -> currentBoardWithAbyssOrTool.put(posicao, new Syntax(idTipo, "Erro de sintaxe", posicao));
            case 1 -> currentBoardWithAbyssOrTool.put(posicao, new Logic(idTipo, "Erro de lógica", posicao));
            case 2 -> currentBoardWithAbyssOrTool.put(posicao, new ExceptionAbyss(idTipo, "Exception", posicao));
            case 3 -> currentBoardWithAbyssOrTool.put(posicao, new FileNotFoundException(idTipo, "File Not Found Exception", posicao));
            case 4 -> currentBoardWithAbyssOrTool.put(posicao, new Crash(idTipo, "Crash (aka Rebentanço)", posicao));
            case 5 -> currentBoardWithAbyssOrTool.put(posicao, new DuplicatedCode(idTipo, "Duplicated Code", posicao));
            case 6 -> currentBoardWithAbyssOrTool.put(posicao, new SideEffects(idTipo, "Efeitos secundários", posicao));
            case 7 -> currentBoardWithAbyssOrTool.put(posicao, new BSOD(idTipo, "Blue Screen Of Death", posicao));
            case 8 -> currentBoardWithAbyssOrTool.put(posicao, new InfiniteLoop(idTipo, "Ciclo Infinito", posicao));
            case 9 -> currentBoardWithAbyssOrTool.put(posicao, new SegmentationFault(idTipo, "Segmentation Fault", posicao));
        }

        abismosPassados.put(idTipo, 0);
    }

    public String getToolName(int idTool) {

        switch (idTool) {
            case 0:
                return "Herança";
            case 1:
                return "Programação Funcional";
            case 2:
                return "Testes unitários";
            case 3:
                return "Tratamento de Excepções";
            case 4:
                return "IDE";
            case 5:
                return "Ajuda Do Professor";
            default:
                return null;
        }
    }

    public void trocaTurno() {
        if (countPlayer == jogadoresTurno.size() - 1) {
            countPlayer = 0;
        } else {
            countPlayer++;
        }
        countTurno++;
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) throws InvalidInitialBoardException {
        currentBoard.clear();
        currentBoardWithAbyssOrTool.clear();
        jogadores.clear();
        jogadoresTurno.clear();
        jogadoresTemp.clear();
        sortLanguages.clear();
        casasPassadas.clear();
        countTurno = 1;
        countPlayer = 0;

        for (int i = 0; i < playerInfo.length; i++) {
            if (Integer.parseInt(playerInfo[i][0]) < 0 || (playerInfo[i][0] == null || playerInfo[i][0].isEmpty())) {
                throw new InvalidInitialBoardException("Erro 1 no playerInfo");
            }
            if (playerInfo[i][1].equals("") || playerInfo[i][1] == null || playerInfo[i][1].equals(" ")) {
                throw new InvalidInitialBoardException("Erro 2 no playerInfo");
            }
            if (!(playerInfo[i][3].equals(ProgrammerColor.PURPLE.toString()) || playerInfo[i][3].equals(ProgrammerColor.BLUE.toString()) ||
                    playerInfo[i][3].equals(ProgrammerColor.GREEN.toString()) || playerInfo[i][3].equals(ProgrammerColor.BROWN.toString()))) {
                throw new InvalidInitialBoardException("Erro 3 no playerInfo");
            }

            for (int j = i + 1; j < playerInfo.length; j++) {
                if (playerInfo[i][0].equals(playerInfo[j][0])) {
                    throw new InvalidInitialBoardException("Erro 4 no playerInfo");
                }
                if (playerInfo[i][3].equals(playerInfo[j][3])) {
                    throw new InvalidInitialBoardException("Erro 5 no playerInfo");
                }
            }
        }
        if (abyssesAndTools != null) {
            for (String[] abyssesAndTool : abyssesAndTools) {
                int tipo = Integer.parseInt(abyssesAndTool[0]);
                int idTipo = Integer.parseInt(abyssesAndTool[1]);
                int posicao = Integer.parseInt(abyssesAndTool[2]);

                if (tipo != 0 && tipo != 1) {
                    throw new InvalidInitialBoardException("Erro 1 no abismo");
                }
                if (tipo == 0 && (idTipo < 0 || idTipo > 9)) {
                    throw new InvalidInitialBoardException("Erro 2 no abismo", 0);
                }
                if (tipo == 1 && (idTipo < 0 || idTipo > 5)) {
                    throw new InvalidInitialBoardException("Erro 1 na ferramenta", 1);
                }
                if (posicao < 1 || posicao > worldSize) {
                    throw new InvalidInitialBoardException("Erro 2 na ferramenta", 1);
                }

                if (tipo == 0) {
                    createAbyss(idTipo, posicao);
                } else {
                    String tituloFerramenta = getToolName(idTipo);
                    Tool ferramenta = new Tool(idTipo, tituloFerramenta, posicao);
                    currentBoardWithAbyssOrTool.put(posicao, ferramenta);
                }
            }
        }

        for (String[] strings : playerInfo) {
            String[] linguagens = strings[2].split(";");
            sortLanguages = new TreeSet<>(Arrays.asList(linguagens));
            switch (strings[3]) {
                case "Purple" -> color = ProgrammerColor.PURPLE;
                case "Blue" -> color = ProgrammerColor.BLUE;
                case "Green" -> color = ProgrammerColor.GREEN;
                case "Brown" -> color = ProgrammerColor.BROWN;
            }
            Programmer programmer = new Programmer(strings[1], sortLanguages, Integer.parseInt(strings[0]), 1, color);
            jogadores.add(programmer);
            jogadoresTurno.add(programmer);
            jogadoresTemp.add(programmer);
        }
        jogadores.sort(Comparator.comparing(Programmer::getId));
        jogadoresTurno.sort(Comparator.comparing(Programmer::getId));
        jogadoresTemp.sort(Comparator.comparing(Programmer::getId));
        currentBoard.put(1, jogadoresTemp);

        if (worldSize < (jogadoresTurno.size() * 2)) {
            throw new InvalidInitialBoardException("Erro no tamanho do tabuleiro");
        }

        if (jogadoresTurno.size() < 2 || jogadoresTurno.size() > 4) {
            throw new InvalidInitialBoardException("Erro no numero de jogadores");
        }

        for (int i = 2; i <= worldSize; i++) {
            currentBoard.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= worldSize; i++) {
            casasPassadas.put(i, 0);
        }
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize) throws InvalidInitialBoardException {
        createInitialBoard(playerInfo, worldSize, null);
    }

    public boolean saveGame(File file) {
        StringBuilder appendPlayers = new StringBuilder();
        StringBuilder appendAbyssAndTools = new StringBuilder();
        StringBuilder appendCasaPassadas = new StringBuilder();
        StringBuilder appendAbismosPassados = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file.getName())) {
            for (Programmer jogador : jogadores) {
                appendPlayers.append(jogador.getId()).append("@").append(jogador.getName()).append("@").append(jogador.getPosicao()).append("@").append(jogador.getFerramentasString()).append("@").append(jogador.getLinguagensString()).append("@").append(jogador.getToStringEstado()).append("@").append(jogador.getImunidade()).append("@").append(jogador.getInfinteLoop()).append("@").append(jogador.getnDadoAnterior()).append("@").append(jogador.getColor()).append("\n");
            }

            for (int i = 0; i < currentBoard.size(); i++) {
                if (currentBoardWithAbyssOrTool.get(i) != null) {
                    appendAbyssAndTools.append(currentBoardWithAbyssOrTool.get(i).getPosicao()).append("@").append(currentBoardWithAbyssOrTool.get(i).getAbyssOrTool()).append("@").append(currentBoardWithAbyssOrTool.get(i).getId()).append("@").append(currentBoardWithAbyssOrTool.get(i).getTitulo()).append("\n");
                }
            }

            if(!casasPassadas.isEmpty()){
                for(int x = 2; x < casasPassadas.size(); x++){
                    if(x != casasPassadas.size() - 1) {
                        appendCasaPassadas.append(x).append(":").append(casasPassadas.get(x)).append("@");
                    }else{
                        appendCasaPassadas.append(x).append(":").append(casasPassadas.get(x));
                    }
                }
            }

            if(!abismosPassados.isEmpty()){
                for(int x = 0; x < abismosPassados.size(); x++){
                    if(x != abismosPassados.size() - 1) {
                        appendAbismosPassados.append(x).append(":").append(abismosPassados.get(x)).append("@");
                    }else{
                        appendAbismosPassados.append(x).append(":").append(abismosPassados.get(x));
                    }
                }
            }
            String fileContent = countTurno + "\n" + "$" + "\n" + countPlayer + "\n" + "$" + "\n" + nDadoAtual + "\n" + "$" + "\n" + currentBoard.size() + "\n" + "$" + "\n" + appendPlayers + "$" + "\n" + appendAbyssAndTools + "$" + "\n" + appendCasaPassadas + "\n" + "$" + "\n" + appendAbismosPassados;
            fileWriter.write(fileContent);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadGame(File file) {
        try {
            currentBoard.clear();
            currentBoardWithAbyssOrTool.clear();
            jogadores.clear();
            jogadoresTurno.clear();
            jogadoresTemp.clear();
            sortLanguages.clear();
            casasPassadas.clear();
            abismosPassados.clear();
            countTurno = 1;
            countPlayer = 0;

            int countLine = 0;

            FileReader fr = new FileReader(file.getName());
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("$")) {
                    countLine++;
                } else {
                    switch (countLine) {
                        case 0:
                            countTurno = Integer.parseInt(line);
                            break;
                        case 1:
                            countPlayer = Integer.parseInt(line);
                            break;
                        case 2:
                            nDadoAtual = Integer.parseInt(line);
                            break;
                        case 3: {
                            for (int i = 1; i <= Integer.parseInt(line); i++) {
                                currentBoard.put(i, new ArrayList<>());
                            }
                            break;
                        }
                        case 4: {
                            int id, posicao, nDadoAnterior;
                            String nome;
                            boolean estado, imunidade, infiniteLoop;
                            TreeSet<String> linguagens;
                            ArrayList<String> ferramentasString;
                            ArrayList<Tool> ferramentas = new ArrayList<>();
                            String[] playerInfo = line.split("@");

                            id = Integer.parseInt(playerInfo[0]);
                            nome = playerInfo[1];
                            posicao = Integer.parseInt(playerInfo[2]);
                            nDadoAnterior = Integer.parseInt(playerInfo[8]);
                            linguagens = new TreeSet<>(Arrays.asList(playerInfo[4].split("; ")));
                            switch (playerInfo[9]) {
                                case "Purple" -> color = ProgrammerColor.PURPLE;
                                case "Blue" -> color = ProgrammerColor.BLUE;
                                case "Green" -> color = ProgrammerColor.GREEN;
                                case "Brown" -> color = ProgrammerColor.BROWN;
                            }

                            ferramentasString = new ArrayList<>(Arrays.asList(playerInfo[3].split(";")));
                            if (!playerInfo[3].equals("No tools")) {
                                for (String titulo : ferramentasString) {
                                    int idTool;
                                    idTool = switch (titulo) {
                                        case "Programação Funcional" -> 1;
                                        case "Testes unitários" -> 2;
                                        case "Tratamento de Excepções" -> 3;
                                        case "IDE" -> 4;
                                        case "Ajuda Do Professor" -> 5;
                                        default -> 0;
                                    };
                                    Tool ferramenta = new Tool(idTool, titulo);
                                    ferramentas.add(ferramenta);
                                }
                            }
                            estado = playerInfo[5].equals("Em Jogo");
                            imunidade = playerInfo[6].equals("true");
                            infiniteLoop = playerInfo[7].equals("true");
                            Programmer jogador = new Programmer(nome, linguagens, id, posicao, nDadoAnterior, color, ferramentas, estado, imunidade, infiniteLoop);
                            jogadores.add(jogador);
                            if (estado) {
                                jogadoresTurno.add(jogador);
                            }
                            currentBoard.get(posicao).add(jogador);
                            break;
                        }
                        case 5: {
                            String[] abyssOrToolInfo = line.split("@");
                            int idTipo = Integer.parseInt(abyssOrToolInfo[2]), posicao = Integer.parseInt(abyssOrToolInfo[0]);

                            if (Integer.parseInt(abyssOrToolInfo[1]) == 0) {
                                if (idTipo == 0) {
                                    currentBoardWithAbyssOrTool.put(posicao, new Syntax(idTipo, "Erro de sintaxe", posicao));
                                } else if (idTipo == 1) {
                                    currentBoardWithAbyssOrTool.put(posicao, new Logic(idTipo, "Erro de lógica", posicao));
                                } else if (idTipo == 2) {
                                    currentBoardWithAbyssOrTool.put(posicao, new ExceptionAbyss(idTipo, "Exception", posicao));
                                } else if (idTipo == 3) {
                                    currentBoardWithAbyssOrTool.put(posicao, new FileNotFoundException(idTipo, "File Not Found Exception", posicao));
                                } else if (idTipo == 4) {
                                    currentBoardWithAbyssOrTool.put(posicao, new Crash(idTipo, "Crash (aka Rebentanço)", posicao));
                                } else if (idTipo == 5) {
                                    currentBoardWithAbyssOrTool.put(posicao, new DuplicatedCode(idTipo, "Duplicated Code", posicao));
                                } else if (idTipo == 6) {
                                    currentBoardWithAbyssOrTool.put(posicao, new SideEffects(idTipo, "Efeitos secundários", posicao));
                                } else if (idTipo == 7) {
                                    currentBoardWithAbyssOrTool.put(posicao, new BSOD(idTipo, "Blue Screen Of Death", posicao));
                                } else if (idTipo == 8) {
                                    currentBoardWithAbyssOrTool.put(posicao, new InfiniteLoop(idTipo, "Ciclo Infinito", posicao));
                                } else {
                                    currentBoardWithAbyssOrTool.put(posicao, new SegmentationFault(idTipo, "Segmentation Fault", posicao));
                                }
                            } else {
                                String tituloFerramenta = getToolName(idTipo);
                                Tool ferramenta = new Tool(idTipo, tituloFerramenta, posicao);
                                currentBoardWithAbyssOrTool.put(posicao, ferramenta);
                            }
                            break;
                        }
                        case 6:{
                            String[] casas = line.split("@");
                            for (String casa : casas) {
                                String[] countCasa = casa.split(":");
                                casasPassadas.put(Integer.parseInt(countCasa[0]), Integer.parseInt(countCasa[1]));
                            }
                        }
                        case 7:{
                            String[] abismos = line.split("@");
                            for (String abismo : abismos) {
                                String[] countAbismo = abismo.split(":");
                                if(!countAbismo[0].equals("null") && !countAbismo[1].equals("null")) {
                                    abismosPassados.put(Integer.parseInt(countAbismo[0]), Integer.parseInt(countAbismo[1]));
                                }
                            }
                        }
                        default:
                            break;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getImagePng(int position) {
        if (position < 1 || position > currentBoard.size()) {
            return null;
        }
        if (position == 1) {
            return "start.png";
        }
        if (position == currentBoard.size()) {
            return "glory.png";
        } else if (currentBoardWithAbyssOrTool.containsKey(position)) {
            if (currentBoardWithAbyssOrTool.get(position).getAbyssOrTool() == 0) {
                int idAbyss = currentBoardWithAbyssOrTool.get(position).getId();
                switch (idAbyss) {
                    case 0:
                        return "syntax.png";
                    case 1:
                        return "logic.png";
                    case 2:
                        return "exception.png";
                    case 3:
                        return "file-not-found-exception.png";
                    case 4:
                        return "crash.png";
                    case 5:
                        return "duplicated-code.png";
                    case 6:
                        return "secondary-effects.png";
                    case 7:
                        return "bsod.png";
                    case 8:
                        return "infinite-loop.png";
                    case 9:
                        return "core-dumped.png";
                }
            }
            if (currentBoardWithAbyssOrTool.get(position).getAbyssOrTool() != 0) {
                int idTool = currentBoardWithAbyssOrTool.get(position).getId();
                switch (idTool) {
                    case 0:
                        return "inheritance.png";
                    case 1:
                        return "functional.png";
                    case 2:
                        return "unit-tests.png";
                    case 3:
                        return "catch.png";
                    case 4:
                        return "IDE.png";
                    case 5:
                        return "ajuda-professor.png";
                }
            }
        }
        return null;
    }

    public String getTitle(int position) {
        if (position < 1 || position > currentBoard.size()) {
            return null;
        }
        if (currentBoardWithAbyssOrTool.get(position) != null) {
            return currentBoardWithAbyssOrTool.get(position).getTitulo();
        }
        return null;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {
        ArrayList<Programmer> players = new ArrayList<>();
        for (Programmer jogador : jogadores) {
            if (jogador != null && includeDefeated) {
                players.add(jogador);
            } else if (jogador != null) {
                if (jogador.getEstado()) {
                    players.add(jogador);
                }
            }
        }
        return players;
    }

    public List<Programmer> getProgrammers(int position) {
        ArrayList<Programmer> playersInPostion = new ArrayList<>();
        if (currentBoard.get(position) == null || !currentBoard.containsKey(position) || currentBoard.get(position).isEmpty()) {
            return null;
        }
        for (Programmer jogador : jogadores) {
            if (jogador.getPosicao() == position) {
                playersInPostion.add(jogador);
            }
        }
        return playersInPostion;
    }

    public String getProgrammersInfo() {
        StringBuilder append = new StringBuilder();
        jogadoresTurno.sort(Comparator.comparing(Programmer::getId));
        for (int i = 0; i < jogadoresTurno.size(); i++) {
            if (jogadoresTurno.get(i).getEstado()) {
                append.append(jogadoresTurno.get(i).getName()).append(" : ");
                append.append(jogadoresTurno.get(i).getFerramentasString());
            }
            if (i != jogadoresTurno.size() - 1) {
                append.append(" | ");
            }
        }
        return append.toString();
    }

    public int getCurrentPlayerID() {
        return jogadoresTurno.get(countPlayer).getId();
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        Programmer jogador = jogadoresTurno.get(countPlayer);
        nDadoAtual = nrSpaces;
        int posicaoAMais;
        if (nrSpaces < 1 || nrSpaces > 6) {
            return false;
        }
        if (!jogador.getInfinteLoop()) {
            currentBoard.get(jogador.getPosicao()).remove(jogador);
            jogador.adicionarPosicao(nrSpaces);
            if (jogador.getPosicao() > currentBoard.size()) {
                posicaoAMais = jogador.getPosicao() - currentBoard.size();
                jogador.setPosicao(currentBoard.size());
                jogador.retirarPosicao(posicaoAMais);
            }
            currentBoard.get(jogador.getPosicao()).add(jogador);
            if(casasPassadas.get(jogador.getPosicao()) != null) {
                casasPassadas.put(jogador.getPosicao(), casasPassadas.get(jogador.getPosicao()) + 1);
            }
            jogador.setImunidade(false);
            return true;
        }
        return false;
    }

    public String reactToAbyssOrTool() {
        Programmer jogador = jogadoresTurno.get(countPlayer);
        if (currentBoardWithAbyssOrTool.get(jogador.getPosicao()) != null) {
            return currentBoardWithAbyssOrTool.get(jogador.getPosicao()).reactToSquare(jogador, this);
        }
        jogador.setnDadoAnterior(nDadoAtual);
        trocaTurno();
        return null;
    }

    public boolean gameIsOver() {
        return jogadoresTurno.size() == 1 || !currentBoard.get(currentBoard.size()).isEmpty();
    }

    public List<String> getGameResults() {
        ArrayList<Programmer> temp = jogadores;
        ArrayList<String> append = new ArrayList<>();
        temp.sort(Collections.reverseOrder(Comparator.comparing(Programmer::getPosicao)).thenComparing(Programmer::getName));
        append.add("O GRANDE JOGO DO DEISI");
        append.add("");
        append.add("NR. DE TURNOS");
        append.add(countTurno + "");
        append.add("");
        append.add("VENCEDOR");
        if (jogadoresTurno.size() == 1) {
            append.add(jogadoresTurno.get(0).getName());
            append.add("");
            append.add("RESTANTES");
            for (Programmer programmer : temp) {
                if (programmer.getPosicao() != jogadoresTurno.get(0).getPosicao()) {
                    append.add(programmer.getName() + " " + programmer.getPosicao());
                }
            }
        } else {
            append.add(currentBoard.get(currentBoard.size()).get(0).getName());
            append.add("");
            append.add("RESTANTES");
            for (Programmer programmer : temp) {
                if (currentBoard.get(currentBoard.size()) != null && !currentBoard.get(currentBoard.size()).isEmpty()) {
                    if (!programmer.getName().equals(currentBoard.get(currentBoard.size()).get(0).getName())) {
                        append.add(programmer.getName() + " " + programmer.getPosicao());
                    }
                }
            }
        }
        return append;
    }

    public JPanel getAuthorsPanel() {
        JPanel creditos = new JPanel();
        JLabel texto = new JLabel("Obrigado por jogar o Grande Jogo Deisi!");
        JLabel texto1 = new JLabel("Desenvolvedores do Jogo:");
        JLabel texto2 = new JLabel("Erick Pina - a22006182");
        JLabel texto3 = new JLabel("Pedro Moreira - a22002701");

        creditos.add(texto1);
        creditos.add(texto2);
        creditos.add(texto3);
        creditos.add(texto);

        return creditos;
    }
}
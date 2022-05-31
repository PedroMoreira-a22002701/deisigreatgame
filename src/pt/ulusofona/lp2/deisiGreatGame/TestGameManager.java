package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestGameManager {

    GameManager jogo = new GameManager();
    String[][] players = {
            {"1","sla","D;Clojure;Java","Purple"},
            {"2","sla2","D;Clojure;Common Lisp","Green"},
            {"3","sla3","D;Clojure;Python","Blue"},
            {"4","sla4","Python","Brown"}
    };
    String[][] abyssOrTool = {
            {"0","0","10"},
            {"1","3","11"}
    };

    String[][] abyssOrTool1 = {
            {"0","0","10"},
            {"0","1","12"},
            {"0","2","13"},
            {"0","3","14"},
            {"0","4","15"},
            {"0","5","16"},
            {"0","6","17"},
            {"0","7","18"},
            {"0","8","19"},
            {"0","9","69"},

            {"1","0","20"},
            {"1","1","21"},
            {"1","2","22"},
            {"1","3","23"},
            {"1","4","24"},
            {"1","5","70"}

    };

    @Test
    public void testCreateInitialBoard01() {
        try {
            jogo.createInitialBoard(players, 9);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        try {
            jogo.createInitialBoard(players, 7);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        assertEquals("Purple", jogo.getJogadoresTurno().get(0).getColor().toString());
        assertEquals("sla", jogo.getJogadoresTurno().get(0).getName());
        assertEquals("1 | sla | 1 | No tools | Clojure; D; Java | Em Jogo", jogo.getJogadoresTurno().get(0).toString());
    }

    @Test
    public void testCreateInitialBoard02() {
        try {
            jogo.createInitialBoard(players, 20, abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        try {
            jogo.createInitialBoard(players, 80, abyssOrTool1);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertNull(jogo.getImagePng(-69));
        for(int i = 1; i < 81; i++){
            jogo.getImagePng(i);
        }

        try {
            jogo.createInitialBoard(players, 4, abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertNull(jogo.getToolName(69));
    }

    @Test
    public void testCreateInitialBoard03() {
        String[][] players = {
                {"-69","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"},
                {"4","sla4","Python","Brown"}
        };
        try {
            jogo.createInitialBoard(players, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] players1 = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"},
                {"4","sla4","Python","Brown"}
        };
        try {
            jogo.createInitialBoard(players1, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] players2 = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","RED"},
                {"4","sla4","Python","Brown"}
        };
        try {
            jogo.createInitialBoard(players2, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] players3 = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"1","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"},
                {"4","sla4","Python","Brown"}
        };
        try {
            jogo.createInitialBoard(players3, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] players4 = {
                {"1","sla","D;Clojure;Java","Blue"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"},
                {"4","sla4","Python","Brown"}
        };
        try {
            jogo.createInitialBoard(players4, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] players5 = {
                {"1","sla","D;Clojure;Java","Blue"},
        };
        try {
            jogo.createInitialBoard(players5, 69);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testCreateInitialBoard04() {
        String[][] abyssOrTool = {
                {"69","0","10"},
                {"1","3","11"}
        };
        try {
            jogo.createInitialBoard(players, 69, abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
            exception.printStackTrace();
            assertEquals("Erro 1 no abismo",exception.getMessage());
            assertEquals("-1",exception.getTypeId());
            assertFalse(exception.isInvalidAbyss());
            assertFalse(exception.isInvalidTool());
        }

        String[][] abyssOrTool1 = {
                {"0","69","10"},
                {"1","3","11"}
        };
        try {
            jogo.createInitialBoard(players, 69, abyssOrTool1);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] abyssOrTool2 = {
                {"0","0","10"},
                {"1","69","11"}
        };
        try {
            jogo.createInitialBoard(players, 69, abyssOrTool2);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        String[][] abyssOrTool3 = {
                {"0","0","-70"},
                {"1","3","11"}
        };
        try {
            jogo.createInitialBoard(players, 69, abyssOrTool3);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testMoveCurrentPlayer01(){
        try {
            jogo.createInitialBoard(players,8);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(3));
        assertFalse(jogo.moveCurrentPlayer(7));
        assertEquals("[1 | sla | 4 | No tools | Clojure; D; Java | Em Jogo, 2 | sla2 | 1 | No tools | Clojure; Common Lisp; D | Em Jogo, 3 | sla3 | 1 | No tools | Clojure; D; Python | Em Jogo, 4 | sla4 | 1 | No tools | Python | Em Jogo]", jogo.getProgrammers(true).toString());
        assertEquals("[1 | sla | 4 | No tools | Clojure; D; Java | Em Jogo, 2 | sla2 | 1 | No tools | Clojure; Common Lisp; D | Em Jogo, 3 | sla3 | 1 | No tools | Clojure; D; Python | Em Jogo, 4 | sla4 | 1 | No tools | Python | Em Jogo]", jogo.getProgrammers(false).toString());
    }

    @Test
    public void testMoveCurrentPlayer02(){
        try {
            jogo.createInitialBoard(players,8);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertFalse(jogo.moveCurrentPlayer(-2));
        assertFalse(jogo.moveCurrentPlayer(0));
    }

    @Test
    public void testMoveCurrentPlayer03() {
        try {
            jogo.createInitialBoard(players,8);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        assertTrue(jogo.moveCurrentPlayer(6));
        assertTrue(jogo.moveCurrentPlayer(6));
        assertEquals(3, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void teste(){
        String[][] abyssOrTool = {
                {"1","3","10"},
                {"1","0","12"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        Tool tool = new Tool(0, "Herança");
        jogo.getJogadores().get(0).addFerramenta(tool);
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));

    }

    @Test
    public void teste02(){
        String[][] abyssOrTool = {
                {"1","3","10"},
                {"1","0","12"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }

        Tool tool = new Tool(0, "Herança");
        jogo.getJogadores().get(0).addFerramenta(tool);
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        File file = new File("jogo1.txt");
        assertFalse(jogo.saveGame(new File("")));
        assertFalse(jogo.loadGame(new File("nExiste")));

    }

    @Test
    public void testReactToAbyssSyntax(){
        String[][] abyssOrTool = {
                {"0","0","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(9, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));

    }

    @Test
    public void testReactToSyntaxInTheFirstSquare(){
        String[][] abyssOrTool = {
                {"0","0","1"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSyntaxVsTool01(){
        String[][] abyssOrTool = {
                {"0","0","10"},
                {"1","4","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        HashMap<Integer,Integer> nsei = new HashMap<>(jogo.getAbismosPassados());
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSyntaxVsTool02(){
        String[][] abyssOrTool = {
                {"0","0","10"},
                {"1","5","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSyntaxVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","0","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(9, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssLogicError(){
        String[][] abyssOrTool = {
                {"0","1","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(9, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssLogicErrorVsTool01(){
        String[][] abyssOrTool = {
                {"0","1","10"},
                {"1","2","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        HashMap<Integer,Integer> nsei2 = new HashMap<>(jogo.getCasasPassadas());
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssLogicErrorVsTool02(){
        String[][] abyssOrTool = {
                {"0","1","10"},
                {"1","5","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssLogicErrorVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","1","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(9, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToLogicErrorInTheFirstSquare(){
        String[][] abyssOrTool = {
                {"0","1","1"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssException(){
        String[][] abyssOrTool = {
                {"0","2","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();
        HashMap<Integer,Casa> nsei3 = new HashMap<>(jogo.getCurrentBoardWithAbyssOrTool());

        assertEquals(8, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssExceptionVsTool01(){
        String[][] abyssOrTool = {
                {"0","2","10"},
                {"1","3","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        assertNull(jogo.getTitle(0));
        assertEquals("Exception", jogo.getTitle(10));
        assertNull(jogo.getTitle(69));
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssExceptionVsTool02(){
        String[][] abyssOrTool = {
                {"0","2","10"},
                {"1","5","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssExceptionVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","2","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(8, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToExceptionInTheFirstSquare(){
        String[][] abyssOrTool = {
                {"0","2","2"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        jogo.moveCurrentPlayer(1);
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssFileNotFoundException(){
        String[][] abyssOrTool = {
                {"0","3","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssFileNotFoundExceptionVsTool01(){
        String[][] abyssOrTool = {
                {"0","3","10"},
                {"1","3","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssFileNotFoundExceptionVsTool02(){
        String[][] abyssOrTool = {
                {"0","3","10"},
                {"1","5","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssFileNotFoundExceptionVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","3","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToFileNotFoundExceptionInTheFirstSquare(){
        String[][] abyssOrTool = {
                {"0","3","1"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssCrash(){
        String[][] abyssOrTool = {
                {"0","4","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssCrashVsTool01(){
        String[][] abyssOrTool = {
                {"0","4","10"},
                {"1","3","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssCrashVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","4","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)\
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssDuplicatedCode(){
        String[][] abyssOrTool = {
                {"0","5","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssDuplicatedCodeVsTool01(){
        String[][] abyssOrTool = {
                {"0","5","10"},
                {"1","0","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssDuplicatedCodeVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","5","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo erro de sintaxe)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffects01(){
        String[][] abyssOrTool = {
                {"0","6","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffects02(){
        String[][] abyssOrTool = {
                {"0","6","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffects03(){
        String[][] abyssOrTool = {
                {"0","6","15"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(5));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffectsInTheFirstSquare(){
        String[][] abyssOrTool = {
                {"0","6","1"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffectsVsTool01(){
        String[][] abyssOrTool = {
                {"0","6","10"},
                {"1","1","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSideEffectsVsWrongTool(){
        String[][] abyssOrTool = {
                {"0","6","10"},
                {"1","2","7"},
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7 (pega a ferramenta para o abismo)
        jogo.reactToAbyssOrTool();//ID2
        jogo.trocaTurno();//foi para o ID3
        jogo.trocaTurno();//foi para o ID4
        jogo.trocaTurno();//foi para o ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10 (entra no abismo e passa)
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssBSOD01(){
        String[][] abyssOrTool = {
                {"0","7","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertFalse(jogo.getJogadores().get(0).getEstado());
        assertEquals(2, jogo.getCurrentPlayerID());
        assertEquals(3, jogo.getJogadoresTurno().size());

        jogo.trocaTurno();
        jogo.trocaTurno();

        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(0, jogo.getCountPlayer());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssBSOD02(){
        String[][] abyssOrTool = {
                {"0","7","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(2,jogo.getCurrentPlayerID());
        assertEquals("Derrotado",jogo.getJogadores().get(0).getToStringEstado());
        assertFalse(jogo.getJogadores().get(0).getEstado());
        assertEquals("sla2 : No tools | sla3 : No tools | sla4 : No tools", jogo.getProgrammersInfo());
        assertFalse(jogo.gameIsOver());

        assertTrue(jogo.moveCurrentPlayer(6)); //P2 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P2 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(6)); //P3 = 7
        assertTrue(jogo.moveCurrentPlayer(3));//P3 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.gameIsOver());
        assertEquals("[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 4, , VENCEDOR, sla4, , RESTANTES, sla 10, sla2 10, sla3 10]", jogo.getGameResults().toString());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssInfiniteLoop01(){
        String[][] abyssOrTool = {
                {"0","8","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertFalse(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals("[1 | sla | 7 | No tools | Clojure; D; Java | Em Jogo]", jogo.getProgrammers(7).toString());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssInfiniteLoop02(){
        String[][] abyssOrTool = {
                {"0","8","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(6)); //P2 = 7
        jogo.reactToAbyssOrTool();//ID3

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertEquals("[1 | sla | 7 | No tools | Clojure; D; Java | Em Jogo, 2 | sla2 | 7 | No tools | Clojure; Common Lisp; D | Em Jogo]", jogo.getProgrammers(7).toString());
        assertNull(jogo.getProgrammers(69));

        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertFalse(jogo.moveCurrentPlayer(4)); //P2 = 7
        jogo.reactToAbyssOrTool();//ID3

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssInfiniteLoop03(){
        String[][] abyssOrTool = {
                {"0","8","7"},
                {"0","2","9"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(5)); //P1 = 6
        assertTrue(jogo.moveCurrentPlayer(3)); //P1 = 9
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();
        jogo.trocaTurno();
        jogo.trocaTurno();

        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();
        jogo.trocaTurno();
        jogo.trocaTurno();

        assertTrue(jogo.moveCurrentPlayer(3)); //P1 = 10

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals("[Clojure, D, Java]", jogo.getJogadores().get(0).getLinguagensFavoritas().toString());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssInfiniteLoopVsTool01(){
        String[][] abyssOrTool = {
                {"0","8","10"},
                {"1", "1", "7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 13
        jogo.reactToAbyssOrTool();

        assertEquals(13, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssInfiniteLoopVsTool02(){
        String[][] abyssOrTool = {
                {"0","8","10"},
                {"1", "1", "7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(5)); //P1 = 6
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(6)); //P2 = 7
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(4));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(3)); //P2 = 10
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertFalse(jogo.moveCurrentPlayer(3));//P1 = 13
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(3)); //P2 = 13
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals(13, jogo.getJogadoresTurno().get(1).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSegmentationFault01(){
        String[][] abyssOrTool = {
                {"0","9","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();
        jogo.trocaTurno();//ID3
        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1
        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSegmentationFault02(){
        String[][] abyssOrTool = {
                {"0","9","10"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(6)); //P2 = 7
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(3));//P2 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals(7, jogo.getJogadoresTurno().get(1).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSegmentationFault03(){
        String[][] abyssOrTool = {
                {"0","9","10"},
                {"1","3","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(6)); //P1 = 7
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(5)); //P2 = 6
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(3));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(4));//P2 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(10, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals(7, jogo.getJogadoresTurno().get(1).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSegmentationFault04(){
        String[][] abyssOrTool = {
                {"0","9","10"},
                {"1","3","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(5)); //P1 = 6
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(6)); //P2 = 7
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();//ID4
        jogo.trocaTurno();//ID1

        assertTrue(jogo.moveCurrentPlayer(4));//P1 = 10
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(3));//P2 = 10
        jogo.reactToAbyssOrTool();

        assertEquals(7, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals(10, jogo.getJogadoresTurno().get(1).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToAbyssSegmentationFault05(){
        String[][] abyssOrTool = {
                {"0","9","2"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(1)); //P1 = 6
        jogo.reactToAbyssOrTool();

        assertTrue(jogo.moveCurrentPlayer(1)); //P2 = 7
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getPosicao());
        assertEquals(1, jogo.getJogadoresTurno().get(1).getPosicao());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToToolMoreThen1Time(){
        String[][] abyssOrTool = {
                {"1","4","2"},
                {"1","4","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(1)); //P1 = 2
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();
        jogo.trocaTurno();
        jogo.trocaTurno();

        assertTrue(jogo.moveCurrentPlayer(5)); //P2 = 7
        jogo.reactToAbyssOrTool();

        assertEquals(1, jogo.getJogadoresTurno().get(0).getFerramentas().size());
        assertEquals(1, jogo.getJogadoresTurno().get(0).getFerramentas().get(0).getAbyssOrTool());
        assertEquals("IDE", jogo.getJogadoresTurno().get(0).getFerramentas().get(0).toString());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

    @Test
    public void testReactToDifferentTools(){
        String[][] abyssOrTool = {
                {"1","4","2"},
                {"1","5","7"}
        };
        try {
            jogo.createInitialBoard(players,100,abyssOrTool);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(1)); //P1 = 2
        jogo.reactToAbyssOrTool();

        jogo.trocaTurno();
        jogo.trocaTurno();
        jogo.trocaTurno();

        assertTrue(jogo.moveCurrentPlayer(5)); //P2 = 7
        jogo.reactToAbyssOrTool();

        assertEquals(2, jogo.getJogadoresTurno().get(0).getFerramentas().size());
        assertEquals(1, jogo.getJogadoresTurno().get(0).getFerramentas().get(0).getAbyssOrTool());
        assertEquals("IDE;Ajuda Do Professor", jogo.getJogadoresTurno().get(0).getFerramentasString());
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));

    }

    /*@Test
    public void testMoveCurrentPlayer06() {
        jogo.createInitialBoard(players,20, abyssOrTool);
        jogo.moveCurrentPlayer(2); //P1 entrou no ciclo infinito e ficou preso
        jogo.reactToAbyssOrTool(); //foi para o ID 2
        jogo.trocaTurno(); //foi para o ID 3
        jogo.trocaTurno(); //foi para o ID 4
        jogo.trocaTurno(); //foi para o ID 1
        assertFalse(jogo.moveCurrentPlayer(1)); //P1 tenta andar mas n pode
        jogo.reactToAbyssOrTool(); //foi para o ID 2
        assertTrue(jogo.moveCurrentPlayer(1)); //P2 pega a ferramenta para escapar do ciclo infinito
        jogo.reactToAbyssOrTool(); //foi para o ID 3
        jogo.trocaTurno(); //foi para o ID 4
        jogo.trocaTurno(); //foi para o ID 1
        assertFalse(jogo.moveCurrentPlayer(2)); //P1 tenta andar mas n pode
        jogo.reactToAbyssOrTool(); //foi para o ID 2
        assertTrue(jogo.moveCurrentPlayer(1)); //P2 entra no ciclo infinito com ferramenta e nao fica preso (P1 continua preso)
        jogo.reactToAbyssOrTool(); //foi para o ID 3
        jogo.trocaTurno(); //foi para o ID 4
        jogo.trocaTurno(); //foi para o ID 1
        assertFalse(jogo.moveCurrentPlayer(2)); //P1 tenta andar mas n pode
        jogo.reactToAbyssOrTool(); //foi para o ID 2
        assertTrue(jogo.moveCurrentPlayer(2)); //P2 sai do ciclo infinitoe vai para a casa 5
        jogo.reactToAbyssOrTool(); //foi para o ID 3
        assertEquals(3,jogo.jogadoresTurno.get(0).getPosicao());
        assertEquals(5,jogo.jogadoresTurno.get(1).getPosicao());
    }*/

    @Test
    public void testGetGameResults() {

        String[][] players = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},

        };

        try {
            jogo.createInitialBoard(players,5);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(4));
        assertEquals("[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 1, , VENCEDOR, sla, , RESTANTES, sla2 1]", jogo.getGameResults().toString());

    }

    @Test
    public void testGetAuthorsPanel() {

        String[][] players = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
        };
        try {
            jogo.createInitialBoard(players,5);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        assertTrue(jogo.moveCurrentPlayer(4));
        jogo.getAuthorsPanel();
    }

    @Test
    public void testMoveCurrentPlayer08(){
        GameManager jogo = new GameManager();
        String[][] players = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"}
        };
        try {
            jogo.createInitialBoard(players,8);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        Assert.assertFalse(jogo.moveCurrentPlayer(8));
    }

    @Test
    public void testSaveGame01(){
        GameManager jogo = new GameManager();
        String[][] players = {
                {"1","sla","D;Clojure;Java","Purple"},
                {"2","sla2","D;Clojure;Common Lisp","Green"},
                {"3","sla3","D;Clojure;Python","Blue"}
        };
        try {
            jogo.createInitialBoard(players,8);
        } catch (InvalidInitialBoardException exception) {
            exception.printStackTrace();
        }
        File file = new File("jogo1.txt");
        assertTrue(jogo.saveGame(file));
        assertTrue(jogo.loadGame(file));
        assertFalse(jogo.loadGame(new File("nExiste")));
    }

}
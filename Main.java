public class Main {

    public static void main(String[] args) {
        
        char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
        char[] cfgFim = {'1','2','3','4','5','6','7',' ','8'}; 
S
        Puzzle8 puzzleInicial = new Puzzle8();
        puzzleInicial.setEstado(cfgIni);
        puzzleInicial.setCusto(0);
        puzzleInicial.setAvaliacao(puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO));

        Puzzle8 puzzleFinal = new Puzzle8();
        puzzleFinal.setEstado(cfgFim);
        puzzleFinal.setCusto(0);
        puzzleFinal.setAvaliacao(0);

        BuscaEmProfundidadeLimitadaIterativa busca = new BuscaEmProfundidadeLimitadaIterativa();

        busca.setInicio(puzzleInicial);
        busca.setObjetivo(puzzleFinal);
        busca.buscar();

        for (Estado e : busca.getCaminhoSolucao()) {
            System.out.println(e);
        }

        System.exit(0);
    }
}


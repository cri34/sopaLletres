public class Tauler {
    private final int files=10;
    private final int columnes=10;
    private final Lletra [][] lletres;
    private final Paraula[] paraules;
    public Tauler(){
        this.lletres= new Lletra[columnes][files];
       inicialitzarTab();
        this.paraules= new Paraula[]{
                new Paraula("ESCOLA"),
                new Paraula("BIBLIOTECA"),
                new Paraula("ESTUDIS"),
                new Paraula("AULA"),
                new Paraula("FUTUR"),
        };
        ordenarParaules();
        invertirParaules();
        printParaules();
    }
    private void inicialitzarTab(){
        for (int r = 0; r < files;r++){
            for (int c=0;c < columnes;c++){
                lletres[r][c]= new Lletra();
            }

        }
    }
    private void ordenarParaules(){
        Paraula temp;
        while(!checkOrdenat()) {
            for (int i = 0; i < this.paraules.length - 1; i++) {
                if (paraules[i].getLletres().length < paraules[i + 1].getLletres().length) {
                    temp = paraules[i];
                    paraules[i] = paraules[i + 1];
                    paraules[i + 1] = temp;
                }

            }
        }
    }
    private boolean checkOrdenat(){
        for (int i =0; i < paraules.length-1;i++){
            if (paraules[i].getLletres().length < paraules[i+1].getLletres().length){
                return false;
            }
        }
        return true;
    }
    public void printParaules(){
        for (int i = 0; i < paraules.length;i++){
            for (int e = 0;e < paraules[i].getLletres().length;e++) {
                System.out.print(paraules[i].getLletres()[e].getLletra()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printTablero(){
        for (int r =0; r < lletres.length;r++){
            for (int c =0;c < lletres[0].length;c++){
                System.out.print(lletres[r][c].getLletra()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public int retNumRand(int numero){
       return (int)(Math.random() * numero);
    }
    public void colocarParaulesHorizontalAleatoriament(int indParaula){
        int fila = retNumRand(files);
        int columna = retNumRand(columnes);
        boolean [][] posVista = new boolean[files][columnes];
            while (!(filaLibre(fila,columna,indParaula))){
                posVista[fila][columna] = true;
                if (totesPosVistes(posVista))
                    return;
                while (posicioVista(fila,columna,posVista)){
                        fila = retNumRand(files);
                        columna = retNumRand(columnes);
                }

            }
            colocarParaulaHoritzontal(indParaula,fila,columna);
    }
    private boolean posicioVista(int fila, int columna, boolean[][] b){
        return b[fila][columna];
    }
    private boolean totesPosVistes(boolean[][]b){
        for (int r =0; r < b.length;r++){
            for (int c=0; c < b[0].length;c++){
                if (!b[r][c]){
                  return false;
                }
            }
        }
        return true;
    }
    private boolean filaLibre(int fila, int col,int indParaula){
        final int maxLParaula = paraules[indParaula].getLletres().length;
        if (!dentroTablero(fila,col + maxLParaula -1))
            return false;
        for (int i = 0;i < maxLParaula;i++){
            if (lletres[fila][col + i].getLletra() != lletres[fila][col + i].getLletraBuida() && lletres[fila][col + i].getLletra() != paraules[indParaula].getLletres()[i].getLletra() )
                return false;
        }
        return true;
    }
    private void colocarParaulaHoritzontal(int indParaula,int fila, int columna){
        for (int i = 0;i < paraules[indParaula].getLletres().length;i++){
            lletres[fila][columna++].setLletra( paraules[indParaula].getLletres()[i].getLletra() );
        }
        paraules[indParaula].setParaulaColocada(true);
    }
    public void colocarPalabrasVerticalAleatoriament(int indParaula){
        int fila = retNumRand(files);
        int columna = retNumRand(columnes);
        boolean [][] posVista = new boolean[files][columnes];
            while(!(columnaLibre(columna,fila,indParaula))){
                posVista[fila][columna] = true;
                if (totesPosVistes(posVista))
                    return;
                while (posicioVista(fila,columna,posVista)){
                    fila = retNumRand(files);
                    columna = retNumRand(columnes);
                }
            }
            colocarPalabraVertical(indParaula,fila,columna);
    }
    private boolean columnaLibre(int columna,int fila,int indParaula){
        int maxLParaula = paraules[indParaula].getLletres().length;
        if (!dentroTablero(fila + maxLParaula-1 ,columna))
            return false;
        for (int i =0; i < maxLParaula;i++){
            if (lletres[i + fila][columna].getLletra() != lletres[i + fila][columna].getLletraBuida() && lletres[i + fila][columna].getLletra() != paraules[indParaula].getLletres()[i].getLletra() )
                return false;
        }
        return true;
    }
    private void colocarPalabraVertical(int indParaula,int fila,int columna){
        for (int i = 0; i < paraules[indParaula].getLletres().length;i++){
            lletres[fila++][columna].setLletra( paraules[indParaula].getLletres()[i].getLletra() );
        }
        paraules[indParaula].setParaulaColocada(true);
    }
    public void colocarPalabraDiagonalAleatoriament( int indParaula){
        int fila = retNumRand(files);
        int columna = retNumRand(columnes);
        boolean [][] posVista = new boolean[files][columnes];
            while (!diagonalLibre(fila,columna,indParaula)){
                posVista[fila][columna] = true;
                if (totesPosVistes(posVista))
                    return;
                while (posicioVista(fila,columna,posVista)){
                    fila = retNumRand(files);
                    columna = retNumRand(columnes);
                }
            }
            colocarPalabraDiagonal(indParaula,fila,columna);
    }
    private void colocarPalabraDiagonal(int indParaula,int fila,int columna){
        for(int i = 0;i < paraules[indParaula].getLletres().length;i++){
           lletres[fila++][columna++].setLletra( paraules[indParaula].getLletres()[i].getLletra() );
        }
        paraules[indParaula].setParaulaColocada(true);
    }
    private boolean diagonalLibre(int fila,int columna,int indParaula) {
        int maxLPalabra = paraules[indParaula].getLletres().length;
        if (!dentroTablero(fila + maxLPalabra-1, columna + maxLPalabra-1))
            return false;
        for (int actDif = 0; actDif < maxLPalabra; actDif++) {
            if (lletres[fila + actDif][columna + actDif].getLletra() != lletres[fila + actDif][columna + actDif].getLletraBuida() && lletres[fila + actDif][columna + actDif].getLletra() != paraules[indParaula].getLletres()[actDif].getLletra())
                return false;
        }
        return true;
    }
    public void colocarAleatoriamentVerticalHoritzontalDiagonal(){
        for (int i = 0; i < paraules.length; i++){
            selectorModos(i);
        }
    }
    //refactorizar
    private void selectorModos(int indParaula){
        final int cantModos = 3;
        final int horit = 0;
        final int vertical = 1;
        final int diagonal  =  2;
        int tipoColocacion = retNumRand(cantModos);
        boolean [] tipoColocacionVisto = new boolean[cantModos];
        while (!modosVistos(tipoColocacionVisto)) {
            while(modoVisto(tipoColocacionVisto,tipoColocacion))
                tipoColocacion = retNumRand(cantModos);
            tipoColocacionVisto[tipoColocacion] = true;
            if (tipoColocacion == horit)
                colocarParaulesHorizontalAleatoriament(indParaula);
            if (tipoColocacion == vertical)
                colocarPalabrasVerticalAleatoriament(indParaula);
            if (tipoColocacion == diagonal)
                colocarPalabraDiagonalAleatoriament(indParaula);
            if (paraules[indParaula].getParaulaColocada())
                break;
        }
    }

    private boolean modosVistos(boolean[] tipoColocacionVisto){
        for (int i =0; i < tipoColocacionVisto.length;i++){
            if (!tipoColocacionVisto[i])
                return false;
        }
        return true;
    }
    private boolean modoVisto(boolean [] tipoColocacionVisto,int tipoColocacion){
        return tipoColocacionVisto[tipoColocacion];
    }
    private boolean dentroTablero(int fila,int col){
        return fila < files && col < columnes;
    }
    private void invertirParaules(){
        boolean invertir;
        for (int i = 0; i < paraules.length;i++){
            invertir = retNumRand(2) == 0;
            if (invertir)
                invertirParaula(i);
        }
    }
    private void invertirParaula(int indParaula){
        Lletra l;
        for (int i = 0;i < paraules[indParaula].getLletres().length / 2 ;i++){
            l = paraules[indParaula].getLletres()[i];
            paraules[indParaula].getLletres()[i] = paraules[indParaula].getLletres()[paraules[indParaula].getLletres().length-1 - i];
            paraules[indParaula].getLletres()[paraules[indParaula].getLletres().length-1 - i] = l;
        }
    }
}

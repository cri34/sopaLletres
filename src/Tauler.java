public class Tauler {
    private final int files=10;
    private final int columnes=10;
    private final int minFila=1;
    private final int minCol=1;
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
        selectorModos();
        printTablero();
        llenarSopaLetra();
        printTablero();
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
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < paraules.length;i++){
            for (int e = 0;e < paraules[i].getLletres().length;e++) {
                out.append(paraules[i].getLletres()[e].getLletra());
                out.append(" ");
            }
            out.append("\n");
        }
        System.out.println(out);
    }
    public void printTablero(){
        StringBuilder out = new StringBuilder();
        for (int r =0; r < lletres.length;r++){
            for (int c =0;c < lletres[0].length;c++){
                out.append(lletres[r][c].getLletra());
                out.append(" ");
            }
            out.append("\n");
        }
        System.out.println(out);
    }
    private int retNumRand(int numero){
       return (int)(Math.random() * numero);
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
    private void colocarPalabraEnTablero( int indParaula,int df,int dc){
        int fila = retNumRand(files);
        int columna = retNumRand(columnes);
        boolean [][] posVista = new boolean[files][columnes];
        while (!lineaLibre(fila,columna,indParaula,df,dc)){
            posVista[fila][columna] = true;
            if (totesPosVistes(posVista))
                return;
            while (posVista[fila][columna]){
                fila = retNumRand(files);
                columna = retNumRand(columnes);
            }
        }
        colocarPalabra(fila,columna,indParaula,df,dc);
    }
    private void colocarPalabra(int fila,int columna,int indParaula,int df,int dc){
        for(int i = 0;i < paraules[indParaula].getLletres().length;i++){
            lletres[fila + df * i][columna + dc * i].setLletra( paraules[indParaula].getLletres()[i].getLletra() );
        }
        paraules[indParaula].setParaulaColocada(true);
    }
    private boolean lineaLibre(int fila,int columna,int indParaula,int df,int dc) {
        int maxLPalabra = paraules[indParaula].getLletres().length;
        if (!dentroTablero(fila + df * (maxLPalabra-1), columna + dc * (maxLPalabra-1)))
            return false;
        for (int actDif = 0; actDif < maxLPalabra; actDif++) {
            if(!lletres[fila + df * actDif][columna + dc * actDif].getVacia() && lletres[fila + df * actDif][columna + dc * actDif].getLletra() != paraules[indParaula].getLletres()[actDif].getLletra())
                return false;
        }
        return true;
    }
    public void selectorModos(){
        final int cantModos = 4;
        final int horit = 0;
        final int vertical = 1;
        final int diagonal  =  2;
        final int diagonalInv = 3;
        int tipoColocacion;
        boolean [] tipoColocacionVisto;
        for (int i = 0; i < paraules.length; i++) {
            tipoColocacionVisto = retBArrayVacio(cantModos);
            tipoColocacion = retNumRand(cantModos);
            while (!modosVistos(tipoColocacionVisto)) {
                while (tipoColocacionVisto[tipoColocacion])
                    tipoColocacion = retNumRand(cantModos);

                tipoColocacionVisto[tipoColocacion] = true;
                if (tipoColocacion == horit)
                    colocarPalabraEnTablero(i, 0, 1);
                if (tipoColocacion == vertical)
                    colocarPalabraEnTablero(i, 1, 0);
                if (tipoColocacion == diagonal)
                    colocarPalabraEnTablero(i, 1, 1);
                if (tipoColocacion == diagonalInv)
                    colocarPalabraEnTablero(i, 1, -1);
                if (paraules[i].getParaulaColocada())
                    break;
            }
        }
    }
    private boolean[] retBArrayVacio(int tam){
        boolean [] a = new boolean [tam];
        return a;
    }
    private boolean modosVistos(boolean[] tipoColocacionVisto){
        for (int i =0; i < tipoColocacionVisto.length;i++){
            if (!tipoColocacionVisto[i])
                return false;
        }
        return true;
    }
    private boolean dentroTablero(int fila,int col){
        return fila >=  minFila-1 && fila < files && col >= minCol-1 && col < columnes;
    }
    private void invertirParaules(){
        boolean invertir;
        Lletra l;
        for (int i = 0; i < paraules.length;i++){
            invertir = retNumRand(2) == 0;
            if(invertir) {
                for (int e = 0; e < paraules[i].getLletres().length / 2; e++) {
                    l = paraules[i].getLletres()[e];
                    paraules[i].getLletres()[e] = paraules[i].getLletres()[paraules[i].getLletres().length - 1 - e];
                    paraules[i].getLletres()[paraules[i].getLletres().length - 1 - e] = l;
                }
            }
        }
    }
    private void llenarSopaLetra(){
        final char firstLetter = 'A';
        final char lastLetter = 'Z';
        for (int r =0; r < files; r++){
            for (int c = 0 ; c < columnes;c++){
                if (lletres[r][c].getLletra() == lletres[r][c].getLletraVacia()){
               lletres[r][c].setLletra((char)(firstLetter + retNumRand(lastLetter - firstLetter +1))); }
            }
        }
    }
}

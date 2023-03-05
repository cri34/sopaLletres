public class Paraula {
    private final Lletra [] lletres;
    private boolean paraulaColocada = false;
    public Paraula(String paraula){
       lletres = new Lletra[paraula.length()];
       for (int i = 0; i < paraula.length(); i++){
           this.lletres[i] = new Lletra(paraula.charAt(i));
       }
    }
    public Paraula(Lletra [] lletres){
        this.lletres = lletres;

    }
    public Lletra [] getLletres(){
        return this.lletres;
    }
    public boolean getParaulaColocada(){
        return paraulaColocada;
    }
    public void setParaulaColocada(boolean colocada){
        paraulaColocada = colocada;
    }


}

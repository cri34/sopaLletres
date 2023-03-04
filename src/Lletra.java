public class Lletra {
    private char lletra;
    private final char lletraBuida = '-';

    public Lletra(char lletra){
        this.lletra = lletra;
    }
    public Lletra(){
        this.lletra = lletraBuida;
    }
    public char getLletra(){
        return lletra;
    }
    public char getLletraBuida(){
        return lletraBuida;
    }
    public void setLletra(char lletra){
        this.lletra = lletra;
    }
}

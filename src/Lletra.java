public class Lletra {
    private char lletra;
    private final char lletraVacia='-';

    private boolean vacia;

    public Lletra(char lletra){
        this.lletra = lletra;
        this.vacia = false;
    }
    public Lletra(){
        this.lletra = lletraVacia;
        this.vacia = true;
    }
    public char getLletra(){
        return lletra;
    }
    public char getLletraVacia(){
        return this.lletraVacia;
    }
    public boolean getVacia(){
        return  this.vacia;
    }
    public void setLletra(char lletra){
        vacia = false;
        this.lletra = lletra;
    }
    public void setVacia(boolean vacia){
        this.vacia = vacia;
    }
}

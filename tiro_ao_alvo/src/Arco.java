public class Arco extends Item {
    private int alcance;

    public Arco(String nome, int preco,int alcance) {
        super(nome, preco);
        this.alcance = alcance;
    }

    public int getAlcance() {
        return alcance;
    }
}
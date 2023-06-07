public class Armadura extends Item {
    private int tentativas;

    public Armadura(String nome, int preco, int tentativas) {
        super(nome,preco);
        this.tentativas = tentativas;
    }

    public int getTentativas() {
        return tentativas;
    }
}
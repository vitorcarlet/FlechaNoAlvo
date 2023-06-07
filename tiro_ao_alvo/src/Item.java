import java.util.Objects;

public class Item {
    protected String nome;
    protected int preco;

    public Item(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
    }
    public Item(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item otherItem = (Item) obj;
        return Objects.equals(this.nome, otherItem.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Item(int preco) {
        this.preco = preco;
    }



    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }



}


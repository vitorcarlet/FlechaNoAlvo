import java.util.*;

public class Player {
    private String name;
    private int score;
    private int moedas;
    private List<Item> itens;



    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.moedas = 0;
        this.itens = new ArrayList<>();

    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(int points) {
        score += points;
    }

    public int getMoedas() {
        return moedas;
    }

    public void adicionarMoedas(int quantidade) {
        moedas += quantidade;
    }

    public void removerMoedas(int quantidade) {
        moedas -= quantidade;
    }

    public void adicionarItem(Item item) {
        if (!possuiItem(item)){
            itens.add(item);
        }
    }



    private void setScore(int score) {
        this.score = score;
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }

    public boolean possuiItem(Item item) {
        return itens.contains(item);
    }

    protected void zerarScore() {
        setScore(0);
    }

    public int escolherArmadura() {
        int tentativas = 0;
        System.out.println("Itens disponíveis:");

        for (int i = 0; i < itens.size(); i++) {
            if(itens.get(i) instanceof Armadura)
            System.out.println((i + 1) + ". " + itens.get(i).getNome());
        }

        System.out.print("Digite o número do item que deseja usar para se defender: ");
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();

        if (escolha >= 1 && escolha <= itens.size()) {
            Item itemEscolhido = itens.get(escolha - 1);

            if (itemEscolhido instanceof Armadura) {
                Armadura armadura = (Armadura) itemEscolhido;
                System.out.println(armadura.getTentativas());
                tentativas = armadura.getTentativas();
                return tentativas;
            }  else {
                System.out.println("Item inválido.");
            }
        } else {
            System.out.println("Escolha inválida.");
        }
        return tentativas;
    }

    public int escolherArco() {
        int alcance=0;
        System.out.println("Itens disponíveis:");

        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i) instanceof Arco)
                System.out.println((i + 1) + ". " + itens.get(i).getNome());
        }

        System.out.print("Digite o número do item que deseja usar para acertar o alvo: ");
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();

        if (escolha >= 1 && escolha <= itens.size()) {
            Item itemEscolhido = itens.get(escolha - 1);
            if (itemEscolhido instanceof Arco) {
                Arco arc = (Arco) itemEscolhido;
                //System.out.println(arc.getAlcance());
                alcance = arc.getAlcance();
                return alcance;
            } else {
                System.out.println("Item inválido.");
            }
        } else {
            System.out.println("Escolha inválida.");
        }
        return alcance;
    }
}

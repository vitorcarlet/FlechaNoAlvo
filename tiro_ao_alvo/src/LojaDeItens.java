import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaDeItens {
    private List<Item> itens;
    private List<Item> itensDisponiveis;
    private Player player;
    private Scanner scanner;

    public LojaDeItens(Player player) {
        this.player = player;
        this.itens = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.itensDisponiveis = new ArrayList<>();

        // Adicionar itens de armadura
        Armadura armadura1 = new Armadura("Armadura Leve", 10,1);
        Armadura armadura2 = new Armadura("Armadura Média", 20,2);
        Armadura armadura3 = new Armadura("Armadura Pesada", 30,3);
        itensDisponiveis.add(armadura2);
        itensDisponiveis.add(armadura3);

        // Adicionar itens de arco

        Arco arco1 = new Arco("Arco Curto", 20,10);
        Arco arco2 = new Arco("Arco Longo", 40,20);
        Arco arco3 = new Arco("Arco Composto", 60,30);
        itensDisponiveis.add(arco2);
        itensDisponiveis.add(arco3);

        if(!player.possuiItem(armadura1) && !player.possuiItem(arco1)) {
            player.adicionarItem(armadura1);
            player.adicionarItem(arco1);
        }

    }

    public void adicionarItem(Item item) {
            itens.add(item);
    }

    public void mostrarItensDisponiveis() {
        System.out.println("Itens disponíveis para compra:");
        for (int i = 0; i < itensDisponiveis.size(); i++) {
            Item item = itensDisponiveis.get(i);
            System.out.println((i + 1) + ". " + item.getNome() + " - " + item.getPreco() + " moedas");
        }
        System.out.println("Seu saldo: "+player.getMoedas());
        System.out.println();
    }

    public void comprarItem() {
        TiroAoAlvo tiroaoalvo = new TiroAoAlvo();
        mostrarItensDisponiveis();
        System.out.print("Escolha o número do item que deseja comprar (0 para sair): ");
        int escolha = scanner.nextInt();
        if (escolha == 0) {
            tiroaoalvo.continuarJogando(player);
            return;
        }
        Item itemEscolhido = itensDisponiveis.get(escolha - 1);
        int index = escolha - 1;
        if (index >= 0 && index < itensDisponiveis.size()) {
            Item item = itensDisponiveis.get(index);
            if (player.getMoedas() >= item.getPreco()) {
                if (player.possuiItem(item) == false) {
                    player.removerMoedas(item.getPreco());
                    itensDisponiveis.remove(item); // Remove o item da lista de itens disponíveis na loja
                    if (itemEscolhido instanceof Armadura) {
                        Armadura armadura = (Armadura) itemEscolhido;
                        player.adicionarItem(armadura);
                        System.out.println("Você comprou uma armadura! Ela lhe dará " + armadura.getTentativas() + " tentativas adicionais.");
                        tiroaoalvo.continuarJogando(player);
                    } else if (itemEscolhido instanceof Arco) {
                        Arco arco = (Arco) itemEscolhido;
                        player.adicionarItem(arco);
                        System.out.println("Você comprou um arco! O tamanho da matriz aumentou para: " + arco.getAlcance());
                        tiroaoalvo.continuarJogando(player);
                    }
                } else {
                    System.out.println("Você já possui esse item.");
                    comprarItem();
                }
            } else {
                System.out.println("Você não possui moedas suficientes para comprar esse item.");
                comprarItem();
            }
        } else {
            System.out.println("Opção inválida.");
            comprarItem();
        }
        System.out.println();
    }
}
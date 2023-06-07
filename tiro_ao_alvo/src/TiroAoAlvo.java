import java.util.Random;
import java.util.Scanner;

public class TiroAoAlvo
{

    protected Player player;
    private LimpaTela limpatela;

    private LojaDeItens lojaDeItens;

    private int tamanho_ambiente = 20;
    private int tentativas = 0;
    private static final double PI = 3.14159265358979323846;

    private int[][] matrix;
    private double v0;
    private double theta;
    private double g;
    private double alvo_x;
    private double alvo_y;
    private double raio;
    private double vy;
    private int k;
    private int l;
    private double dt;
    private double tmax;
    private int acertou;
    private int levelup = 1;
    private double tempo;
    private double[][] trajetoria;
    double verifica = 1;




    public TiroAoAlvo() {
        matrix = new int[tamanho_ambiente][tamanho_ambiente];
        g = 9.81;
        dt = 0.1;
        tmax = 10.0;
        trajetoria = new double[(int) (tmax / dt) + 1][2];
    }

    private void initialize(double alvo_x, double alvo_y) {
        matrix = new int[tamanho_ambiente][tamanho_ambiente];
        for (int i = 0; i < tamanho_ambiente; i++) {
            for (int j = 0; j < tamanho_ambiente; j++) {
                if (i + 1 == alvo_y && j + 1 == alvo_x) {
                    matrix[i][j] = 2;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void display() {
        for (int i = tamanho_ambiente - 1; i >= 0; i--) {
            for (int j = 0; j < tamanho_ambiente; j++) {
                switch (matrix[i][j]) {
                    case 0:
                        System.out.print(".\t");
                        break;
                    case 1:
                        System.out.print(" / \t");
                        break;
                    case 2:
                        System.out.print("X\t");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void calculateTrajectory(Player player) {
        int N = trajetoria.length;

        trajetoria[0][0] = 0.01; // x0
        trajetoria[0][1] = 0.01; // y0

        for (int i = 1; i < N; i++) {
            double vx = v0 * Math.cos(theta * PI / 180.0);
            vy = v0 * Math.sin(theta * PI / 180.0) - g * i * dt;

            trajetoria[i][0] = trajetoria[i - 1][0] + vx * dt;
            trajetoria[i][1] = trajetoria[i - 1][1] + vy * dt;


            verifica = trajetoria[i - 1][1] + vy * dt;

            if (verifica <= 0) {
                tempo = i * dt;
                break;
            }

            tempo = i * dt;

            if (tempo == 10) {
                System.out.println("Você Errou o alvo!, a flecha desintegrou-se");
                break;
            }

            System.out.printf("Trajetoria no instante %.2f: (%.2f, %.2f)\n", i * dt, trajetoria[i][0],
                    trajetoria[i][1]);

            if (alvo_x - trajetoria[i][0] > 0 && alvo_y - trajetoria[i][1] > 0) {
                if (alvo_x - trajetoria[i][0] <= raio && alvo_y - trajetoria[i][1] <= raio) {
                    System.out.println("\n\nParabéns, Você Acertou o alvo!\n");
                    player.incrementScore(1);

                    acertou++;

                    break;
                }
            } else if (alvo_x - trajetoria[i][0] < 0 && alvo_y - trajetoria[i][1] < 0) {
                if (Math.abs(alvo_x - trajetoria[i][0]) <= raio && Math.abs(alvo_y - trajetoria[i][1]) <= raio) {
                    System.out.println("\n\nParabéns, Você Acertou o alvo!\n");
                    player.incrementScore(1);

                    acertou ++;
                    break;
                }
            } else if (alvo_x - trajetoria[i][0] < 0 && alvo_y - trajetoria[i][1] > 0) {
                if (Math.abs(alvo_x - trajetoria[i][0]) <= raio && alvo_y - trajetoria[i][1] <= raio) {
                    System.out.println("\n\nParabéns, Você Acertou o alvo!\n");
                    player.incrementScore(1);

                    acertou ++;
                    break;
                }
            } else if (alvo_x - trajetoria[i][0] > 0 && alvo_y - trajetoria[i][1] < 0) {
                if (alvo_x - trajetoria[i][0] <= raio && Math.abs(alvo_y - trajetoria[i][1]) <= raio) {
                    System.out.println("\n\nParabéns, Você Acertou o alvo!\n");
                    player.incrementScore(1);

                    acertou ++;
                    break;
                }
            }

            k = (int) Math.floor(trajetoria[i][0])-1;
            l = (int) Math.floor(trajetoria[i][1])-1;
            if (k >= 0 && l >= 0 && k < tamanho_ambiente && l < tamanho_ambiente) {
                matrix[l][k] = 1;
            }
        }
    }

    public void inicializaJogo(Player player){
        if(player == null){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome do jogador: ");
            String playerName = scanner.nextLine();
            player = new Player(playerName);
            lojaDeItens = new LojaDeItens(player);
        }
        startGame(player);
    }

    public void startGame(Player player) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        //LimpaTela limpaTela = new LimpaTelaImp();

        tentativas = 0;
        int errou = 0;
        int escolha;
        int escolha2;
        acertou = 0;
        int contGame = 0;



        while (true) {
            if (contGame < 1) {
                 escolha = player.escolherArmadura();
                while (escolha == 0) {
                    escolha = player.escolherArmadura();
                }
                 escolha2 = player.escolherArco();
                while (escolha2 == 0) {
                    escolha2 = player.escolherArco();
                }
                tamanho_ambiente = escolha2;
                tentativas = escolha+1;
            }





                alvo_x = random.nextInt(tamanho_ambiente) +1;
                alvo_y = random.nextInt(tamanho_ambiente) +1;


            //limpaTela.clearScreen();
            System.out.printf("O alvo na horizontal está a %.2f metros de distância\n", alvo_x);
            System.out.printf("O alvo na vertical está a %.2f metros de distância\n", alvo_y);

            initialize(alvo_x, alvo_y);
            display();

            System.out.print("Digite a força (em m/s): ");
            v0 = scanner.nextDouble();
            System.out.print("Digite o ângulo (em graus 0 a 90): ");
            theta = scanner.nextDouble();
            System.out.print("Digite o raio do alvo (em metros): ");
            raio = scanner.nextDouble();

            calculateTrajectory(player);
            System.out.println();
            display();


            if (verifica <= 0) {
                tentativas--;
                System.out.println("Você perdeu uma chance \t|\t tentativas restantes: "+tentativas);
                errou++;
                if(tentativas == 0){
                    System.out.println("Você Errou o alvo "+errou+" vezes, sequência Zerada!");
                    zerarAtributos(player);
                    continuarJogando(player);
                }

            }

            System.out.println("Pontuação atual de "+player.getName() + ": " + player.getScore());
            contGame++;


            if(acertou == 3){
                recompensa(player);
                ParabensASCII(player);
                break;
            }





        }
        scanner.close();
    }

    private void zerarAtributos(Player player){
        player.zerarScore();
        acertou = 0;
        tamanho_ambiente = 20;
        levelup = 1;
    }

    private void ParabensASCII(Player player) {
        System.out.println("    ,  ,");
        System.out.println("   /(  )\\");
        System.out.println("   \\ \\_/ /");
        System.out.println("    \\_/_/");
        System.out.println("    _|_|_");
        System.out.println("   ('o_o')\t\t Parabéns "+player.getName() +",você Venceu e ganhou seu suado dinheirinho!!!!");
        System.out.println("    \\   /");
        System.out.println("   __\\_/_");
        System.out.println("  /   |   \\");
        System.out.println("Quantidade de moedas: "+player.getMoedas());
        System.out.println("Pontuação Total: "+player.getScore());
        continuarJogando(player);
    }

 protected void continuarJogando(Player player){
     char input;
     Scanner scanner = new Scanner(System.in);
     System.out.print("\n\nDeseja continuar jogando ou visitar a loja de itens? ((S/N)/L): ");
     input = scanner.next().charAt(0);
     this.lojaDeItens = new LojaDeItens(player);

     switch (input) {
         case 'S':
         case 's':
             inicializaJogo(player);
             break;
         case 'L':
         case 'l':
             lojaDeItens.comprarItem();
             break;
         default:
             System.out.println("Saindo.");
             break;
     }
 }

 public void bemVindo(){
     char input;
     Scanner scanner = new Scanner(System.in);

     System.out.println("---Bem vindo ao jogo AcertaFlecha---");
     System.out.println("aperte S para iniciar o jogo");



     input = scanner.next().charAt(0);
     switch (input) {
         case 'S':
         case 's':
             digitaNome();
             startGame(player);
         default:
             System.out.println("Saindo.");
             break;
     }
 }
 private void recompensa(Player player){
     Random random = new Random();
     int moedasRecompensa = random.nextInt(999) + 1;
     player.adicionarMoedas(moedasRecompensa);
 }

public void digitaNome(){
    if(player == null){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        lojaDeItens = new LojaDeItens(player);
    }

    startGame(player);
}

}




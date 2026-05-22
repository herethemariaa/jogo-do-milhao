package projetoLPA;
import java.util.Scanner;
public class JogoMilhao {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        iniciarJogo();

    }


    // Visual inicial do projeto + escolha do modo de jogo
    public static void iniciarJogo() {
        Scanner scan = new Scanner(System.in);
        int opc;

        System.out.println("-------------------------------------------");
        System.out.println("|  Bem vindo ao Jogo do Milhão Interativo |");
        System.out.println("-------------------------------------------\n");

        while (true) {
            System.out.println("Escolha o modo de jogo: ");
            System.out.println("1 - Modo Clássico");
            System.out.println("2 - Modo Competitivo\n");
            System.out.print("-> ");
            opc = scan.nextInt();

            if(opc == 1) {
                System.out.println("Voce escolheu a o modo classico!");
                modoClassico();
                break;
            }
            else if(opc == 2) {
                System.out.println("Voce escolheu o modo competitivo");

                break;
            }
            else {
                System.out.println("Por favor escolha entre as duas opções!\n");
            }
        }


    }

    // inicio do jogo
    public static void modoClassico() {
        Scanner scan = new Scanner(System.in);

        String[] perguntas = new String[10];
        String[][] alternativas = new String[10][4];
        char[] respostas = new char[10];
        int[] premios = new int[10];

        perguntas(perguntas, alternativas, respostas, premios);

        for(int i = 0; i < 9; i++) {
            exibirPerguntas(perguntas[i], alternativas[i], premios[i]);

            System.out.println("Sua resposta: ");
            char respostaUsuario = scan.next().toUpperCase().charAt(0);

            if(verificarResposta(respostaUsuario, respostas[i])) {
                System.out.println("Resposta correta!");
                System.out.println("Próxima pergunta \n");
            }
            else {
                System.out.println("Resposta errada!");
                System.out.println("Você perdeu!");
                break;
            }

        }

    }

    // Banco de perguntas manual.
    public static void perguntas(String[] perguntas, String[][] alternativas, char[] respostas, int[] premios) {
        // -----------------------------------------------------------------------------------------------------
        perguntas[0] = "Qual a capital do Brasil?";

        alternativas[0][0] = "São Paulo";
        alternativas[0][1] = "Rio de Janeiro";
        alternativas[0][2] = "Brasília";
        alternativas[0][3] = "Salvador";

        respostas[0] = 'C';

        premios[0] = 500;

        // -----------------------------------------------------------------------------------------------------
        perguntas[1] = "O pó-de-mico quando entra em contato com a pele provoca qual tipo de reação?";

        alternativas[1][0] = "Cócegas";
        alternativas[1][1] = "Dor";
        alternativas[1][2] = "Frio";
        alternativas[1][3] = "Tristeza";

        respostas[1] = 'A';

        premios[1] = 1000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[2] = "Em qual país reinou a dinastia Romanov?";

        alternativas[2][0] = "Itália";
        alternativas[2][1] = "Espanha";
        alternativas[2][2] = "Alemanha";
        alternativas[2][3] = "Rússia";

        respostas[2] = 'D';

        premios[2] = 5000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[3] = "Bagaceira é o nome dado à aguardente de: ";

        alternativas[3][0] = "Jaboticaba";
        alternativas[3][1] = "Pêra";
        alternativas[3][2] = "Maçã";
        alternativas[3][3] = "Uva";

        respostas[3] = 'D';

        premios[3] = 10000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[4] = "Quantos dias tem um ano bissexto? ";

        alternativas[4][0] = "365";
        alternativas[4][1] = "364";
        alternativas[4][2] = "366";
        alternativas[4][3] = "360";

        respostas[4] = 'C';

        premios[4] = 50000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[5] = "Qual é o maior oceano da Terra? ";

        alternativas[5][0] = "Atlântico";
        alternativas[5][1] = "Ìndico";
        alternativas[5][2] = "Ártico";
        alternativas[5][3] = "Pacífico";

        respostas[5] = 'D';

        premios[5] = 100000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[6] = "Quem pintou a obra Mona Lisa? ";

        alternativas[6][0] = "Pablo Picasso";
        alternativas[6][1] = "Vicent Van Gogh";
        alternativas[6][2] = "Leonardo da Vinci";
        alternativas[6][3] = "Michelangelo";

        respostas[6] = 'C';

        premios[6] = 250000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[7] = "Quem formulou as três leis do movimento clássico? ";

        alternativas[7][0] = "Albert Einstein";
        alternativas[7][1] = "Isaac Newton";
        alternativas[7][2] = "Galileu Galilei";
        alternativas[7][3] = "Johannes Kepler";

        respostas[7] = 'B';

        premios[7] = 500000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[8] = "Em qual ano ocorreu a queda do Muro de Berlim? ";

        alternativas[8][0] = "1987";
        alternativas[8][1] = "1988";
        alternativas[8][2] = "1989";
        alternativas[8][3] = "1991";

        respostas[8] = 'C';

        premios[8] = 750000;

        // -----------------------------------------------------------------------------------------------------
        perguntas[9] = "Em qual ano ocorreu a queda do Muro de Berlim? ";

        alternativas[9][0] = "1987";
        alternativas[9][1] = "1988";
        alternativas[9][2] = "1989";
        alternativas[9][3] = "1991";

        respostas[9] = 'C';

        premios[9] = 1000000;
    }


    // Exibição de perguntas
    public static void exibirPerguntas(String pergunta, String[] alternativas, int premio) {

        System.out.println("Valendo R$ " + premio);

        System.out.println(pergunta);

        System.out.println("A) " + alternativas[0]);
        System.out.println("B) " + alternativas[1]);
        System.out.println("C) " + alternativas[2]);
        System.out.println("D) " + alternativas[3]);
    }


    // Verificar se a resposta esta correta
    public static boolean verificarResposta(char respUsuario, char respCorreta) {

        return respUsuario == respCorreta;
    }





    public static void modoCompetitivo() {

    }



}
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class JogoMilhao {

	public static void main(String[] args) {
			
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
				//modoCompetitivo();
				break;
			}
			else {
				System.out.println("Por favor escolha entre as duas opções!\n");
			}
		}
		
		scan.close();
	}
	
	
	// É aqui que o jogo acontece!
	public static void modoClassico() {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		// ArrayList para armazenar os Indices/perguntas sorteadas.
		ArrayList<Integer> indicesUsados = new ArrayList<>();
			
		String[] perguntas = new String[20];
		String[][] alternativas = new String[20][4];
		char[] respostas = new char[20];
		    
		// Chamar o metodo de perguntas
		perguntas(perguntas, alternativas, respostas);
		    
		for(int i = 0; i < 10; i++) {
		   int indicePergunta;
		   // Sorteia um indice(Pergunta)
		   do {
			   indicePergunta = rand.nextInt(20);
		   } while (indicesUsados.contains(indicePergunta));
		    		
		   indicesUsados.add(indicePergunta);
		    		
		   int premio = mostrarPremio(i);
		    		
		   if(i == 9) {
			   System.out.println("Agora, a pergunta valendo 1 milhão!");
	       }
		    		
		   exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);
		    		
		   System.out.println("Sua resposta: ");
		    		
		    		
		   char respostaUsuario = scan.next().toUpperCase().charAt(0);
		    		
		   if(verificarResposta(respostaUsuario, respostas[indicePergunta])) {
			   System.out.println("\nResposta correta!");
		    			
			   if(i == 9) {
				   System.out.println("Parabéns! Você ganhou 1 milhão! Você venceu o jogo!");
			   }
			   else {
				   System.out.println("Próxima pergunta... \n");
			   }
		    					    			
		    }
		    	else {
		    		System.out.println("\nResposta errada!");
		    		System.out.println("Você perdeu!");
		    		System.out.println("Finalizando jogo...");
		    		break;
		    	}
		    		
		}
		scan.close();
	}
	
	
	// Banco de Perguntas manual.
	public static void perguntas(String[] perguntas, String[][] alternativas, char[] respostas) {
     // -----------------------------------------------------------------------------------------------------
		perguntas[0] = "Qual a capital do Brasil?";

	    alternativas[0][0] = "São Paulo";
	    alternativas[0][1] = "Rio de Janeiro";
	    alternativas[0][2] = "Brasília";
	    alternativas[0][3] = "Salvador";

	    respostas[0] = 'C';
	   
	 // -----------------------------------------------------------------------------------------------------
	    perguntas[1] = "O pó-de-mico quando entra em contato com a pele provoca qual tipo de reação?";
	    
	    alternativas[1][0] = "Cócegas";
	    alternativas[1][1] = "Dor";
	    alternativas[1][2] = "Frio";
	    alternativas[1][3] = "Tristeza";
	    
	    respostas[1] = 'A';
	    
	 // -----------------------------------------------------------------------------------------------------
	    perguntas[2] = "Em qual país reinou a dinastia Romanov?";
	    
	    alternativas[2][0] = "Itália";
	    alternativas[2][1] = "Espanha";
	    alternativas[2][2] = "Alemanha";
	    alternativas[2][3] = "Rússia";
	    
	    respostas[2] = 'D';
	   
	 // -----------------------------------------------------------------------------------------------------
	    perguntas[3] = "Bagaceira é o nome dado à aguardente de: ";
	    
	    alternativas[3][0] = "Jaboticaba";
	    alternativas[3][1] = "Pêra";
	    alternativas[3][2] = "Maçã";
	    alternativas[3][3] = "Uva";
	    
	    respostas[3] = 'D';
	    
	 // -----------------------------------------------------------------------------------------------------
	    
        perguntas[4] = "Quantos dias tem um ano bissexto? ";

        alternativas[4][0] = "365";
        alternativas[4][1] = "364";
        alternativas[4][2] = "366";
        alternativas[4][3] = "360";

        respostas[4] = 'C';

     // -----------------------------------------------------------------------------------------------------
        perguntas[5] = "Qual é o maior oceano da Terra? ";

        alternativas[5][0] = "Atlântico";
        alternativas[5][1] = "Índico";
        alternativas[5][2] = "Ártico";
        alternativas[5][3] = "Pacífico";

        respostas[5] = 'D';

     // -----------------------------------------------------------------------------------------------------
        perguntas[6] = "Quem pintou a obra Mona Lisa? ";

        alternativas[6][0] = "Pablo Picasso";
        alternativas[6][1] = "Vicent Van Gogh";
        alternativas[6][2] = "Leonardo da Vinci";
        alternativas[6][3] = "Michelangelo";

        respostas[6] = 'C';

     // -----------------------------------------------------------------------------------------------------
        perguntas[7] = "Quem formulou as três leis do movimento clássico? ";

        alternativas[7][0] = "Albert Einstein";
        alternativas[7][1] = "Isaac Newton";
        alternativas[7][2] = "Galileu Galilei";
        alternativas[7][3] = "Johannes Kepler";

        respostas[7] = 'B';

     // -----------------------------------------------------------------------------------------------------
        perguntas[8] = "Em qual ano ocorreu a queda do Muro de Berlim? ";

        alternativas[8][0] = "1987";
        alternativas[8][1] = "1988";
        alternativas[8][2] = "1989";
        alternativas[8][3] = "1991";

        respostas[8] = 'C';

     // -----------------------------------------------------------------------------------------------------
        perguntas[9] = "Qual é o menor estado do Brasil em território? ";

        alternativas[9][0] = "Sergipe";
        alternativas[9][1] = "Alagoas";
        alternativas[9][2] = "Rio de Janeiro";
        alternativas[9][3] = "Espírito Santo";

        respostas[9] = 'A';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[10] = "Qual é o nome do processo em que a água vira vapor? ";

        alternativas[10][0] = "Condensação";
        alternativas[10][1] = "Solidificação";
        alternativas[10][2] = "Evaporação";
        alternativas[10][3] = "Fusão";

        respostas[10] = 'C';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[11] = "Quantos estados tem o Brasil? ";

        alternativas[11][0] = "24";
        alternativas[11][1] = "25";
        alternativas[11][2] = "26";
        alternativas[11][3] = "27";

        respostas[11] = 'D';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[12] = "Quem foi o primeiro presidente do Brasil? ";

        alternativas[12][0] = "Getúlio Vargas";
        alternativas[12][1] = "Deodoro da Fonseca";
        alternativas[12][2] = "Juscelino Kubitschek";
        alternativas[12][3] = "Floriano Peixoto";

        respostas[12] = 'B';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[13] = "Qual é o maior animal do planeta? ";

        alternativas[13][0] = "Elefante";
        alternativas[13][1] = "Tubarão-branco";
        alternativas[13][2] = "Baleia-azul";
        alternativas[13][3] = "Girafa";

        respostas[13] = 'C';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[14] = "Qual é o símbolo químico do ouro? ";

        alternativas[14][0] = "Ag";
        alternativas[14][1] = "Au";
        alternativas[14][2] = "O";
        alternativas[14][3] = "Gd";

        respostas[14] = 'B';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[15] = "Qual é o país do tango? ";

        alternativas[15][0] = "Brasil";
        alternativas[15][1] = "Espanha";
        alternativas[15][2] = "Argentina";
        alternativas[15][3] = "Cuba";

        respostas[15] = 'C';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[16] = "Qual destes números é primo? ";

        alternativas[16][0] = "9";
        alternativas[16][1] = "15";
        alternativas[16][2] = "21";
        alternativas[16][3] = "13";

        respostas[16] = 'D';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[17] = "Qual destes países tem o maior território do mundo? ";

        alternativas[17][0] = "China";
        alternativas[17][1] = "Canadá";
        alternativas[17][2] = "Rússia";
        alternativas[17][3] = "Estados Unidos";

        respostas[17] = 'C';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[18] = "Lichia é um tipo de: ";

        alternativas[18][0] = "Fruto";
        alternativas[18][1] = "Queijo";
        alternativas[18][2] = "Pedra";
        alternativas[18][3] = "Água";

        respostas[18] = 'A';
        
     // -----------------------------------------------------------------------------------------------------
        perguntas[19] = "Eros era um Deus de qual Mitologia? ";

        alternativas[19][0] = "Germânica";
        alternativas[19][1] = "Japonesa";
        alternativas[19][2] = "Eslava";
        alternativas[19][3] = "Grega";

        respostas[19] = 'D';        		
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
	
	// Metodo dos premios
	public static int mostrarPremio(int p) {
		
		 int[] premios = {500, 1000, 5000, 10000, 50000, 100000, 250000, 500000, 750000, 1000000};
		 return premios[p];
	}
	
	
	
	public static void modoCompetitivo() {
		
	}
	


}

// PARA REQUISITOS EXTRAS: 
// Metodo de ajuda(pular, eliminar 2 e ajuda do universitarios) 
// Jogar alternado 
// Condional: Considerando que estão jogando 2 pequenos genios do Domingo com Huck, caso os 2 acertem as 10 perguntas, deu EMPATE
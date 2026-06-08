package br.ucsal.ads.lpa;
import java.util.Scanner;
import java.util.Random;

public class JogoDoMilhaoEquipe2 {

	public static void main(String[] args) {
		iniciarJogo();
	}

	public static void iniciarJogo() {
		Scanner scan = new Scanner(System.in);
		int opc;

		System.out.println("-------------------------------------------");
        System.out.println("        BEM-VINDO AO JOGO DO MILHÃO!");
        System.out.println("-------------------------------------------");					

		while (true) {
			System.out.println("Escolha o modo de jogo: ");
			System.out.println("1 - Modo Clássico");
			System.out.println("2 - Modo Competitivo\n");
			System.out.print("-> ");
			opc = scan.nextInt();

			if (opc == 1) {
				System.out.println("Voce escolheu a o modo classico!");
				modoClassico();
				break;
			} else if (opc == 2) {
				System.out.println("Voce escolheu o modo competitivo");
				modoCompetitivo();
				break;
			} else {
				System.out.println("Por favor escolha entre as duas opções!\n");
			}
		}

		scan.close();
	}

	public static void modoClassico() {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		int[] indicesUsados = new int[22];
		String[] perguntas = new String[22];
		String[][] alternativas = new String[22][4];
		char[] respostas = new char[22];

		boolean pularUsado = false;
		boolean eliminarUsado = false;
		boolean consultarUsado = false;
		
		for (int i = 0; i < indicesUsados.length; i++) {
		    indicesUsados[i] = -1;
		}

		perguntas(perguntas, alternativas, respostas);

		for (int i = 0; i < 10; i++) {
			int indicePergunta = sortearPerguntaAleatoria(indicesUsados, rand, i);

			int premio = mostrarPremio(i);

			if (i == 9) {
				System.out.println("\n--------------------------------------");
                System.out.println("     PERGUNTA VALENDO 1 MILHÃO ");
                System.out.println("--------------------------------------");					
			}

			exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);

			System.out.println("\n ESCOLHA UMA OPÇÃO");
            System.out.println("-------------------------------------------");

            System.out.println("[A] [B] [C] [D] → Responder");

            System.out.println("\nAJUDAS: ");
            System.out.println("[U] → Consultar o Amigo");
            System.out.println("[P] → Pular Pergunta");
            System.out.println("[E] → Eliminar 2 Alternativas");

            System.out.println("-------------------------------------------");
            System.out.print("➜ Sua escolha: ");

			char respostaUsuario = scan.next().toUpperCase().charAt(0); //confuso

			while (respostaUsuario == 'U' || respostaUsuario == 'E' || respostaUsuario == 'P') {
				
				if (respostaUsuario == 'U') {

					if (!consultarUsado) {

						consultarAmigo(perguntas[indicePergunta], alternativas[indicePergunta],
								respostas[indicePergunta]);

						consultarUsado = true;

						System.out.println("\nAgora digite sua resposta:");
						respostaUsuario = scan.next().toUpperCase().charAt(0);
					}

					else {
						System.out.println("Você já utilizou essa ajuda!");

						System.out.println("Digite sua resposta:");
						respostaUsuario = scan.next().toUpperCase().charAt(0);
					}
				}
				
				if (respostaUsuario == 'E') {
					if (!eliminarUsado) {

						eliminarRespostas(perguntas[indicePergunta], alternativas[indicePergunta],
								respostas[indicePergunta]);
						
						eliminarUsado = true;

						System.out.println("\nAgora digite sua resposta:");
						respostaUsuario = scan.next().toUpperCase().charAt(0);
					} else {
						System.out.println("Você já utilizou essa ajuda!");

						System.out.println("Digite sua resposta:");
						respostaUsuario = scan.next().toUpperCase().charAt(0);
					}
				}
				
				if (respostaUsuario == 'P') {
					if (!pularUsado) {
						indicePergunta = pularPergunta(indicesUsados, rand, i);

						pularUsado = true;

						exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);
						System.out.println("\n ESCOLHA UMA OPÇÃO");
                        System.out.println("-------------------------------------------");

                        System.out.println("[A] [B] [C] [D] → Responder");

                        System.out.println("\nAJUDAS: ");
                        System.out.println("[U] → Consultar o Amigo");
                        System.out.println("[P] → Pular Pergunta");
                        System.out.println("[E] → Eliminar 2 Alternativas");

                        System.out.println("-------------------------------------------");
                        System.out.print("➜ Sua escolha: ");

						respostaUsuario = scan.next().toUpperCase().charAt(0);
					} else {
						System.out.println("Você já utilizou essa ajuda!");

						System.out.println("Digite sua resposta:");
						respostaUsuario = scan.next().toUpperCase().charAt(0);
					}

				}
			}

			if (verificarResposta(respostaUsuario, respostas[indicePergunta])) {
				System.out.println("\nResposta correta!");

				if (i == 9) {
					System.out.println("---------------------------------------------------");
					System.out.println("Parabéns! Você ganhou 1 milhão! Você venceu o jogo!");
					System.out.println("---------------------------------------------------");
				} else {
					System.out.println("Próxima pergunta... \n");
				}

			} else {
				System.out.println("\nResposta errada!");
				System.out.println("Você perdeu!");
				System.out.println("Finalizando jogo...");
				break;
			}

		}
		scan.close();
	}

	public static void exibirPerguntas(String pergunta, String[] alternativas, int premio) {
		
		System.out.println("Valendo R$ " + premio);

		System.out.println(pergunta);

		System.out.println("A) " + alternativas[0]);
		System.out.println("B) " + alternativas[1]);
		System.out.println("C) " + alternativas[2]);
		System.out.println("D) " + alternativas[3]);
	}

	public static boolean verificarResposta(char respUsuario, char respCorreta) {
		return respUsuario == respCorreta;
	}

	public static int mostrarPremio(int p) {

		int[] premios = { 500, 1000, 5000, 10000, 50000, 100000, 250000, 500000, 750000, 1000000 };
		return premios[p];
	}

	public static int sortearPerguntaAleatoria(int[] indicesUsados, Random rand, int rodada) {
		boolean duplicado;
		int i, indice;

		do {
			indice = rand.nextInt(22);
			duplicado = false;

			for (i = 0; i < indicesUsados.length; i++) {
				if (indicesUsados[i] == indice) {
					duplicado = true;
					break;
				}
			}
		} while (duplicado);

		indicesUsados[rodada] = indice;
		return indice;
	}

	public static void modoCompetitivo() {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		int[] indicesUsados = new int[22];
		String[] perguntas = new String[22];
		String[][] alternativas = new String[22][4];
		char[] respostas = new char[22];

		boolean pularUsadoJ1 = false;
		boolean eliminarUsadoJ1 = false;
		boolean consultarUsadoJ1 = false;
		boolean pularUsadoJ2 = false;
		boolean eliminarUsadoJ2 = false;
		boolean consultarUsadoJ2 = false;

		String jogador1, jogador2; 

		System.out.println("Digite o nome do 1º Jogador: ");
		jogador1 = scan.nextLine();

		System.out.println("Digite o nome do 2º Jogador: ");
		jogador2 = scan.nextLine();

		for (int i = 0; i < indicesUsados.length; i++) {
			indicesUsados[i] = -1;
		}

		perguntas(perguntas, alternativas, respostas);

		int premioPlayer1 = 0;
		int premioPlayer2 = 0;

		boolean p1Ativo = true;
		boolean p2Ativo = true;

		int rodadaP1 = 0;
		int rodadaP2 = 0;

		for (int i = 0; i < 20; i++) {

			if (!p1Ativo && !p2Ativo) {
				System.out.println("Ambos os jogadores erraram! Fim de jogo antecipado.");
				break;
			}

			if (i % 2 == 0) {
				if (p1Ativo) {
					System.out.println("----------------------------------------");
					System.out.println("            VEZ DO J1: " + jogador1);
					System.out.println("----------------------------------------");

					int indicePergunta = sortearPerguntaAleatoria(indicesUsados, rand, i);

					int premio = mostrarPremio(rodadaP1);

					exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);

					System.out.println("\n ESCOLHA UMA OPÇÃO");
                    System.out.println("-------------------------------------------");

                    System.out.println("[A] [B] [C] [D] → Responder");

                    System.out.println("\nAJUDAS: ");
                    System.out.println("[U] → Consultar o Amigo");
                    System.out.println("[P] → Pular Pergunta");
                    System.out.println("[E] → Eliminar 2 Alternativas");

                    System.out.println("-------------------------------------------");
                    System.out.print("➜ Sua escolha: ");
					char respostaUsuario = scan.next().toUpperCase().charAt(0);

					while (respostaUsuario == 'U' || respostaUsuario == 'E' || respostaUsuario == 'P') { 
						
						if (respostaUsuario == 'U') {

							if (!consultarUsadoJ1) {

								consultarAmigo(perguntas[indicePergunta], alternativas[indicePergunta],
										respostas[indicePergunta]);

								consultarUsadoJ1 = true;

								System.out.println("\nAgora digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}

							else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}
						}
						
						if (respostaUsuario == 'E') {
							if (!eliminarUsadoJ1) {

								eliminarRespostas(perguntas[indicePergunta], alternativas[indicePergunta],
										respostas[indicePergunta]);

								eliminarUsadoJ1 = true;

								System.out.println("\nAgora digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							} else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}
						}
					
						if (respostaUsuario == 'P') {
							if (!pularUsadoJ1) {
								indicePergunta = pularPergunta(indicesUsados, rand, i);

								pularUsadoJ1 = true;
								System.out.println("----------------------------------------");
								System.out.println("            VEZ DO J1: " + jogador1);
								System.out.println("----------------------------------------");

								exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);
								System.out.println("\n ESCOLHA UMA OPÇÃO");
		                        System.out.println("-------------------------------------------");

		                        System.out.println("[A] [B] [C] [D] → Responder");

		                        System.out.println("\nAJUDAS: ");
		                        System.out.println("[U] → Consultar o Amigo");
		                        System.out.println("[P] → Pular Pergunta");
		                        System.out.println("[E] → Eliminar 2 Alternativas");

		                        System.out.println("-------------------------------------------");
		                        System.out.print("➜ Sua escolha: ");

								respostaUsuario = scan.next().toUpperCase().charAt(0);
							} else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}

						}
					}

					if (verificarResposta(respostaUsuario, respostas[indicePergunta])) {
						System.out.println("\n-> Resposta correta, Jogador 1");
						premioPlayer1 = premio;
						rodadaP1++;
					} else {
						System.out.println("\n-> Resposta errada! Jogador 1 (" + jogador1 + ") foi eliminado");
						p1Ativo = false;
					}
				} else {
					System.out.println("(Jogador 1 (" + jogador1 + ") eliminado - Passando a vez)");
				}
			}
			
			else {
				if (p2Ativo) {
					System.out.println("----------------------------------------");
					System.out.println("          VEZ DO J2: " + jogador2);
					System.out.println("----------------------------------------");

					int indicePergunta = sortearPerguntaAleatoria(indicesUsados, rand, i);
					int premio = mostrarPremio(rodadaP2);

					exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);

					System.out.println("\n ESCOLHA UMA OPÇÃO");
                    System.out.println("-------------------------------------------");

                    System.out.println("[A] [B] [C] [D] → Responder");

                    System.out.println("\nAJUDAS: ");
                    System.out.println("[U] → Consultar o Amigo");
                    System.out.println("[P] → Pular Pergunta");
                    System.out.println("[E] → Eliminar 2 Alternativas");

                    System.out.println("-------------------------------------------");
                    System.out.print("➜ Sua escolha: ");
					char respostaUsuario = scan.next().toUpperCase().charAt(0);

					while (respostaUsuario == 'U' || respostaUsuario == 'E' || respostaUsuario == 'P') { 
						if (respostaUsuario == 'U') {

							if (!consultarUsadoJ2) {

								consultarAmigo(perguntas[indicePergunta], alternativas[indicePergunta],
										respostas[indicePergunta]);

								consultarUsadoJ2 = true;

								System.out.println("\nAgora digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}

							else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}
						}
					
						if (respostaUsuario == 'E') {
							if (!eliminarUsadoJ2) {

								eliminarRespostas(perguntas[indicePergunta], alternativas[indicePergunta],
										respostas[indicePergunta]);

								eliminarUsadoJ2 = true;

								System.out.println("\nAgora digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							} else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}
						}
						
						if (respostaUsuario == 'P') {
							if (!pularUsadoJ2) {
								indicePergunta = pularPergunta(indicesUsados, rand, i);

								pularUsadoJ2 = true;
								System.out.println("----------------------------------------");
								System.out.println("            VEZ DO J2: " + jogador2);
								System.out.println("----------------------------------------");

								exibirPerguntas(perguntas[indicePergunta], alternativas[indicePergunta], premio);
								System.out.println("\n ESCOLHA UMA OPÇÃO");
		                        System.out.println("-------------------------------------------");

		                        System.out.println("[A] [B] [C] [D] → Responder");

		                        System.out.println("\nAJUDAS: ");
		                        System.out.println("[U] → Consultar o Amigo");
		                        System.out.println("[P] → Pular Pergunta");
		                        System.out.println("[E] → Eliminar 2 Alternativas");

		                        System.out.println("-------------------------------------------");
		                        System.out.print("➜ Sua escolha: ");
								
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							} else {
								System.out.println("Você já utilizou essa ajuda!");

								System.out.println("Digite sua resposta:");
								respostaUsuario = scan.next().toUpperCase().charAt(0);
							}

						}
					}

					if (verificarResposta(respostaUsuario, respostas[indicePergunta])) {
						System.out.println("\n-> Resposta correta, Jogador 2");
						premioPlayer2 = premio;
						rodadaP2++;
					} else {
						System.out.println("\n-> Resposta errada! Jogador 2 (" + jogador2 + ") foi eliminado");
						p2Ativo = false;
					}
				} else {
					System.out.println("(Jogador 2 (" + jogador2 + ") eliminado - Passando a vez)");
				}
			}
		}
		System.out.println("\n-------------------------------------------");
		System.out.println("|               FIM DE JOGO               |");
		System.out.println("-------------------------------------------");
		System.out.println("Placar Final:");
		System.out.println(jogador1 + " terminou com: R$ " + premioPlayer1);
		System.out.println(jogador2 + " terminou com: R$ " + premioPlayer2);
		System.out.println("-------------------------------------------");

		if (premioPlayer1 > premioPlayer2) {
			System.out.println("Parabéns " + jogador1 + "! Você venceu");
		} else if (premioPlayer2 > premioPlayer1) {
			System.out.println("Parabéns " + jogador2 + "! Você venceu");
		} else {
			System.out.println("Temos um empate!");
		}

		scan.close();
	}

	public static void consultarAmigo(String pergunta, String[] alternativas, char respostaCorreta) {
		Random rand = new Random();
		System.out.println("Estou pensando, aguarde...");
		System.out.println(pergunta);

		System.out.println("A) " + alternativas[0]);
		System.out.println("B) " + alternativas[1]);
		System.out.println("C) " + alternativas[2]);
		System.out.println("D) " + alternativas[3]);

		int indiceCerto, indiceErrado;
		int chance = rand.nextInt(2);
		char sugestao;

		if (respostaCorreta == 'A') {
			indiceCerto = 0;
		} else if (respostaCorreta == 'B') {
			indiceCerto = 1;
		} else if (respostaCorreta == 'C') {
			indiceCerto = 2;
		} else {
			indiceCerto = 3;
		}

		if (chance == 0) {
			sugestao = respostaCorreta;

		} else {

			do {
				indiceErrado = rand.nextInt(4);
			} while (indiceErrado == indiceCerto);

			if (indiceErrado == 0) {
				sugestao = 'A';
			} else if (indiceErrado == 1) {
				sugestao = 'B';
			} else if (indiceErrado == 2) {
				sugestao = 'C';
			} else {
				sugestao = 'D';
			}
		}

		System.out.println("Tente a alternativa: " + sugestao);
	}

	public static int pularPergunta(int[] indicesUsados, Random rand, int rodada) {
		System.out.println("\nPergunta pulada!");
		System.out.println("Sorteando nova pergunta...\n ");

		return sortearPerguntaAleatoria(indicesUsados, rand, rodada);

	}

	public static void eliminarRespostas(String pergunta, String[] alternativas, char respostaCorreta) {

		Random rand = new Random();

		System.out.println(pergunta);

		int indexCerta;
		int indiceErrado;
		String letraAlternativa;

		if (respostaCorreta == 'A') {
			indexCerta = 0;
		} else if (respostaCorreta == 'B') {
			indexCerta = 1;
		} else if (respostaCorreta == 'C') {
			indexCerta = 2;
		} else {
			indexCerta = 3;
		}

		do {
			indiceErrado = rand.nextInt(4);
		} while (indiceErrado == indexCerta);

		for (int i = 0; i < 4; i++) {

			if (i == indexCerta || i == indiceErrado) {

				if (i == 0) {
					letraAlternativa = "A";
				} else if (i == 1) {
					letraAlternativa = "B";
				} else if (i == 2) {
					letraAlternativa = "C";
				} else {
					letraAlternativa = "D";
				}

				System.out.println(letraAlternativa + ") " + alternativas[i]);
	            
			}
		}
	}

	public static void perguntas(String[] perguntas, String[][] alternativas, char[] respostas) {
		perguntas[0] = "Qual a capital do Brasil?";

		alternativas[0][0] = "São Paulo";
		alternativas[0][1] = "Rio de Janeiro";
		alternativas[0][2] = "Brasília";
		alternativas[0][3] = "Salvador";

		respostas[0] = 'C';

		
		perguntas[1] = "O pó-de-mico quando entra em contato com a pele provoca qual tipo de reação?";

		alternativas[1][0] = "Cócegas";
		alternativas[1][1] = "Dor";
		alternativas[1][2] = "Frio";
		alternativas[1][3] = "Tristeza";

		respostas[1] = 'A';

		
		perguntas[2] = "Em qual país reinou a dinastia Romanov?";

		alternativas[2][0] = "Itália";
		alternativas[2][1] = "Espanha";
		alternativas[2][2] = "Alemanha";
		alternativas[2][3] = "Rússia";

		respostas[2] = 'D';

		
		perguntas[3] = "Bagaceira é o nome dado à aguardente de: ";

		alternativas[3][0] = "Jaboticaba";
		alternativas[3][1] = "Pêra";
		alternativas[3][2] = "Maçã";
		alternativas[3][3] = "Uva";

		respostas[3] = 'D';


		perguntas[4] = "Quantos dias tem um ano bissexto? ";

		alternativas[4][0] = "365";
		alternativas[4][1] = "364";
		alternativas[4][2] = "366";
		alternativas[4][3] = "360";

		respostas[4] = 'C';

		
		perguntas[5] = "Qual é o maior oceano da Terra? ";

		alternativas[5][0] = "Atlântico";
		alternativas[5][1] = "Índico";
		alternativas[5][2] = "Ártico";
		alternativas[5][3] = "Pacífico";

		respostas[5] = 'D';

		
		perguntas[6] = "Quem pintou a obra Mona Lisa? ";

		alternativas[6][0] = "Pablo Picasso";
		alternativas[6][1] = "Vicent Van Gogh";
		alternativas[6][2] = "Leonardo da Vinci";
		alternativas[6][3] = "Michelangelo";

		respostas[6] = 'C';

		
		perguntas[7] = "Quem formulou as três leis do movimento clássico? ";

		alternativas[7][0] = "Albert Einstein";
		alternativas[7][1] = "Isaac Newton";
		alternativas[7][2] = "Galileu Galilei";
		alternativas[7][3] = "Johannes Kepler";

		respostas[7] = 'B';

		
		perguntas[8] = "Em qual ano ocorreu a queda do Muro de Berlim? ";

		alternativas[8][0] = "1987";
		alternativas[8][1] = "1988";
		alternativas[8][2] = "1989";
		alternativas[8][3] = "1991";

		respostas[8] = 'C';

		
		perguntas[9] = "Qual é o menor estado do Brasil em território? ";

		alternativas[9][0] = "Sergipe";
		alternativas[9][1] = "Alagoas";
		alternativas[9][2] = "Rio de Janeiro";
		alternativas[9][3] = "Espírito Santo";

		respostas[9] = 'A';

		
		perguntas[10] = "Qual é o nome do processo em que a água vira vapor? ";

		alternativas[10][0] = "Condensação";
		alternativas[10][1] = "Solidificação";
		alternativas[10][2] = "Evaporação";
		alternativas[10][3] = "Fusão";

		respostas[10] = 'C';

		
		perguntas[11] = "Quantos estados tem o Brasil? ";

		alternativas[11][0] = "24";
		alternativas[11][1] = "25";
		alternativas[11][2] = "26";
		alternativas[11][3] = "27";

		respostas[11] = 'D';

		
		perguntas[12] = "Quem foi o primeiro presidente do Brasil? ";

		alternativas[12][0] = "Getúlio Vargas";
		alternativas[12][1] = "Deodoro da Fonseca";
		alternativas[12][2] = "Juscelino Kubitschek";
		alternativas[12][3] = "Floriano Peixoto";

		respostas[12] = 'B';

		
		perguntas[13] = "Qual é o maior animal do planeta? ";

		alternativas[13][0] = "Elefante";
		alternativas[13][1] = "Tubarão-branco";
		alternativas[13][2] = "Baleia-azul";
		alternativas[13][3] = "Girafa";

		respostas[13] = 'C';

		
		perguntas[14] = "Qual é o símbolo químico do ouro? ";

		alternativas[14][0] = "Ag";
		alternativas[14][1] = "Au";
		alternativas[14][2] = "O";
		alternativas[14][3] = "Gd";

		respostas[14] = 'B';

		
		perguntas[15] = "Qual é o país do tango? ";

		alternativas[15][0] = "Brasil";
		alternativas[15][1] = "Espanha";
		alternativas[15][2] = "Argentina";
		alternativas[15][3] = "Cuba";

		respostas[15] = 'C';

		
		perguntas[16] = "Qual destes números é primo? ";

		alternativas[16][0] = "9";
		alternativas[16][1] = "15";
		alternativas[16][2] = "21";
		alternativas[16][3] = "13";

		respostas[16] = 'D';

		
		perguntas[17] = "Qual destes países tem o maior território do mundo? ";

		alternativas[17][0] = "China";
		alternativas[17][1] = "Canadá";
		alternativas[17][2] = "Rússia";
		alternativas[17][3] = "Estados Unidos";

		respostas[17] = 'C';
		

		perguntas[18] = "Lichia é um tipo de: ";

		alternativas[18][0] = "Fruto";
		alternativas[18][1] = "Queijo";
		alternativas[18][2] = "Pedra";
		alternativas[18][3] = "Água";

		respostas[18] = 'A';
		

		perguntas[19] = "Eros era um Deus de qual Mitologia? ";

		alternativas[19][0] = "Germânica";
		alternativas[19][1] = "Japonesa";
		alternativas[19][2] = "Eslava";
		alternativas[19][3] = "Grega";

		respostas[19] = 'D';
		
		
		perguntas[20] = "Qual é o resultado da equação: 5+5x5+5 ";

		alternativas[20][0] = "10";
		alternativas[20][1] = "55";
		alternativas[20][2] = "35";
		alternativas[20][3] = "25";

		respostas[20] = 'C';
		
		
		perguntas[21] = "Quantos estados compõem a região Nordeste do Brasil? ";

		alternativas[21][0] = "7";
		alternativas[21][1] = "8";
		alternativas[21][2] = "9";
		alternativas[21][3] = "10";

		respostas[21] = 'C';
	}
}

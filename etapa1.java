package projetos;
import java.util.Random;
import java.util.Scanner;
public class programa1 {

static int tam = 4;
static String matriz [][] = new String [tam][tam];	 //matriz em formato char

	
public static void main(String[] args) {
		// TODO Auto-generated method stub
		// matriz criada - nome matriz
		// personagem: AGENTE = A, WUMPUS = W, POCO = P e o OURO = O 
		/* Regra de criacao dos objetos.
		 WUMPUS é tamanho total da matriz - 1, multiplicado por 33%
		 POCOS é tamanho total da matriz - 1, subtraido com o total de WUMPUS
		 OURO como já definido teremos apenas 1;
		 e o AGENTE somente um personagem
		 -----
		 Primeiro a ser adicionado é o Agente, depois o Poco, depois o Wumpus e finalmente o Ouro
		*/
		int cont=2000;
		int i=0, mov=0,ouro=1,caiu=0,pego=0,ganhou=0;
		int qtdWUMPUS=(int) ((tam-ouro)*0.333333333333333333);
		int qtdPoco=((tam-ouro)-qtdWUMPUS);
		System.out.println("\nQuantidade de Objs: "+(ouro+qtdWUMPUS+qtdPoco)+"\nOuro "+ouro+", Wumpus "+qtdWUMPUS+" e POCO "+qtdPoco);
		Random gerador = new Random();
		
		System.out.println("Matriz: ");
		do {
			int obj=tam, auxW=qtdWUMPUS,auxP=qtdPoco;
			ouro = 1;
			for (i = 0; i < matriz.length; i++) {
			    for (int j = 0; j<matriz.length;j++ ) {
			    	matriz[i][j] = " ";
			    }
			}
			
			matriz[0][0]="A"; //posição incial do Agente
			//System.out.println(mov);
			//posagente(matriz,vl);
			//Primeiro a ser adicionado é o Agente, depois o Poco, depois o Wumpus e finalmente o Ouro
			int aux=obj;
			while(aux>0) {
				int col = gerador.nextInt(tam); //vai ser definido um valor de 0 ate 3 para coluna
				int lin = gerador.nextInt(tam); //vai ser definido um valor de 0 ate 3 para linha
				if (aux>0) {
					if (lin!=0||col!=0) {
						if (auxP>0) {
							matriz[lin][col]="P";
							auxP--;
							aux--;
						}else {
							if (matriz[lin][col]!="P") {
								if (auxW>0) {
									matriz[lin][col]="W";
									auxW--;
									aux--;
								}
								else {
									if ((matriz[lin][col]!="P")||(matriz[lin][col]!="W")) {
										if (ouro>0) {
											matriz[lin][col]="O";
											ouro--;
											aux--;
										}
									}
								}
							}
						}
					}
				}
			}
			int funcionando=1;
			imprimirmatriz(matriz);
			do {
				mov=gerador.nextInt(4);// movimentacao, no caso 4 movimentos
				//0 = esquerda, 1 = cima, 2 = direita, 3 = baixo
				int[] vl= posagente(matriz); //vetor para posicoes
				String aux1;
				if (mov==0) {//move pra esquerda
					for(i=0;i<2;i++) {
						if (vl[1]==0){ // caso a coluna for a primeira (0) ele identifica q nao pode ir para a outra casa da esquerda.
							break; 
						}else {
							System.out.println("movimenta para esquerda");
							aux1 = matriz[vl[0]][vl[1]];
							matriz[vl[0]][vl[1]]=" ";
							if (matriz[vl[0]][vl[1]-1]=="W") {
								System.out.println("Voce Perdeu, Wumpus te matou");
								pego++;
								funcionando=0;
							}else {
								if(matriz[vl[0]][vl[1]-1]=="P") {
									System.out.println("Voce Perdeu, Caiu no Poco");
									caiu++;
									funcionando=0;
								}else {
									if(matriz[vl[0]][vl[1]-1]=="O") {
										System.out.println("Voce Ganhou, achou o Ouro");
										ganhou++;
										funcionando=0;
									}else;
									
								}
								
							}
							matriz[vl[0]][vl[1]-1]=aux1;
							break;
						}
					}
				}else {
					if (mov==1) {//move pra cima
						for(i=0;i<2;i++) {
							if (vl[0]==(matriz.length-1)){ // caso a linha for a primeira (0) ele identifica q nao pode ir para a outra casa acima.
								break; 
							}else {
								aux1 = matriz[vl[0]][vl[1]];
								System.out.println("movimenta para cima");
								matriz[vl[0]][vl[1]]=" ";
								if (matriz[vl[0]+1][vl[1]]=="W") {
									System.out.println("Voce Perdeu, Wumpus te matou");
									pego++;
									funcionando=0;
									break;
								}else {
									if(matriz[vl[0]+1][vl[1]]=="P") {
										System.out.println("Voce Perdeu, Caiu no Poco");
										caiu++;
										funcionando=0;
										break;
									}else {
										if(matriz[vl[0]+1][vl[1]]=="O") {
											System.out.println("Voce Ganhou, achou o Ouro");
											ganhou++;
											funcionando=0;
											break;
										}else;
									}
								}
								matriz[vl[0]+1][vl[1]]=aux1;
								break;
							}
						}
					}else {
						if (mov==2) {//move pra direita
							for(i=0;i<2;i++) {
								if (vl[1]==matriz.length-1){ // caso a coluna for a ultima (a quarta) ele identifica q nao pode ir para a outra casa da direita.
									break; 
								}else {
									System.out.println("movimenta para direita");
									aux1 = matriz[vl[0]][vl[1]];
									matriz[vl[0]][vl[1]]=" ";
									if (matriz[vl[0]][vl[1]+1]=="W") {
										System.out.println("Voce Perdeu, Wumpus te matou");
										pego++;
										funcionando=0;
										break;
									}else {
										if(matriz[vl[0]][vl[1]+1]=="P") {
											System.out.println("Voce Perdeu, Caiu no Poco");
											caiu++;
											funcionando=0;
											break;
										}else {
											if(matriz[vl[0]][vl[1]+1]=="O") {
												System.out.println("Voce Ganhou, achou o Ouro");
												ganhou++;
												funcionando=0;
												break;
											}else;
											
										}
										
									}
									matriz[vl[0]][vl[1]+1]=aux1;
									break;
								}
							}
						}else {
							if (mov==3) {//move pra baixo
								for(i=0;i<2;i++) {
									if (vl[0]==0){ // caso a linha for a primeira (0) ele identifica q nao pode ir para a outra casa abaixo.
										break; 
									}else {
										System.out.println("movimenta para baixo");
										aux1 = matriz[vl[0]][vl[1]];
										matriz[vl[0]][vl[1]]=" ";
										if (matriz[vl[0]-1][vl[1]]=="W") {
											System.out.println("Voce Perdeu, Wumpus te matou");
											pego++;
											funcionando=0;
											break;
										}else {
											if(matriz[vl[0]-1][vl[1]]=="P") {
												System.out.println("Voce Perdeu, Caiu no Poco");
												caiu++;
												funcionando=0;
												break;
											}else {
												if(matriz[vl[0]-1][vl[1]]=="O") {
													System.out.println("Voce Ganhou, achou o Ouro");
													ganhou++;
													funcionando=0;
													break;
												}else;
											}
										}
										matriz[vl[0]-1][vl[1]]=aux1;
										
										break;
									}
								}
							}
						}
					}
				}
				imprimirmatriz(matriz);
			}while(funcionando!=0);
			cont--;
		} while (cont>0);
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("Agente conseguiu "+ganhou+" vezes encontrar o ouro,\n"+caiu+" vezes cair no poco\n"+pego+" vezes ser pego pelo WUMPUS");
	}

	private static void imprimirmatriz(String [][]matriz)  {
		System.out.println("\n----------------------------------------------------------------");
		int i,tam=matriz.length;
		for (i=tam; i>0;i--) {
			 System.out.println("");
           for (int j=0; j<tam; j++){
               System.out.print("["+matriz[i-1][j]+"] \t");
           }
       }
		System.out.println("");
	}
	private static int[] posagente(String [][]matriz)  {
		int i=0, tam=matriz.length,feito=0;
		int[]vet=new int [2];
		for (i=0; i<tam;i++) {
			for (int j=0; j<tam; j++){
				if (matriz[i][j]=="A") {
					vet[0]=i;
					vet[1]=j;
					feito=1;
					break;
				}
			}
			if (feito==1) {
				//System.out.println("\nPosicao do Agente: ["+vet[0]+"]["+vet[1]+"]");
				break;
			}
		}
	return vet;
	}
}
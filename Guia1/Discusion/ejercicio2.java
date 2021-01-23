import java.util.Scanner;
public class Identificarnotas {
private Scanner teclado;
private Integer[] notas;
int contador1, contador2;
public Identificarnotas() {
teclado = new Scanner(System. in );
notas = new Integer[10];

for (int f = 0; f < notas.length; f++) {
System.out.println("Ingrese la nota " + (f+1));
notas[f] = teclado.nextInt();
}
}
public void recorrernotas() {
for (int f = 0; f < notas.length; f++) {
if (notas[f] >= 7) {
contador1++;
}else if(notas[f] < 7){
    contador2++;
}
}
    System.out.println("Notas mayores o iguales a 7: " + contador1);
    System.out.println("Notas menores que 7: " + contador2);
}


                       
public static void main(String[] ar) {
Identificarnotas cad = new Identificarnotas();
cad.recorrernotas();
}
                       
}
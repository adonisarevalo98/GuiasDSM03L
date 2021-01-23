import java.util.Scanner;

public class Identificarnumeros {
    
private Scanner teclado;
private Integer[] numeros;
int contador1, contador2, contador3;
int sumpares = 0;
int valid1 = 15;
    int valid2 = 2;
public Identificarnumeros() {

teclado = new Scanner(System. in );
numeros = new Integer[5];

for (int f = 0; f < numeros.length; f++) {
System.out.println("Ingrese el numero " + (f+1));
numeros[f] = teclado.nextInt();
}
}
public void recorrernumeros() {
for (int f = 0; f < numeros.length; f++) {
if (numeros[f] > 0) {
contador1++;
}else if(numeros[f] < 0){
    contador2++;
}else if(numeros[f] % valid1 == 0 ){
    contador3++;
}else if (numeros[f] % valid2 == 0){
    sumpares += numeros[f];
}
}
System.out.println("Numeros positivos:" + contador1);
System.out.println("Numeros negativos:" + contador2);
System.out.println("Numeros multiplos de 15:" + contador3);
System.out.println("Suma de pares" + sumpares);
}
public static void main(String[] ar) {
Identificarnumeros cad = new Identificarnumeros();
cad.recorrernumeros();
}
}
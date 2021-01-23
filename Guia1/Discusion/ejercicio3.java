import java.util.Scanner;
public class Identificarpares {
private Scanner teclado;
private Integer[] numeros;
int contador1, contador2,n;
public Identificarpares() {
teclado = new Scanner(System. in );
System.out.println("¿Cuantos numeros desea ingresar? ");
n = teclado.nextInt();
numeros = new Integer[n];

for (int f = 0; f < numeros.length; f++) {
System.out.println("Ingrese el numero " + (f+1));
numeros[f] = teclado.nextInt();
}
}
public void recorrerpares() {
for (int f = 0; f < numeros.length; f++) {
if (numeros[f] % 2 == 0) {
contador1++;
}else {
    contador2++;
}
}
System.out.println("Numeros pares:  " + contador1);
    System.out.println("Numeros impares:  " + contador2);
}


                       
public static void main(String[] ar) {
Identificarpares cad = new Identificarpares();
cad.recorrerpares();
}
                       
}
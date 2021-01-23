import java.util.Scanner;
public class Identificarcoordenadas {
private Scanner teclado;
private Integer[] x;
private Integer[] y;
int contador1, contador2, contador3, contador4, n;
public Identificarcoordenadas() {
teclado = new Scanner(System. in );
System.out.println("¿Cuantas coordenadas desea ingresar? ");
n = teclado.nextInt();
x = new Integer[n];
y = new Integer[n];
for (int f = 0; f < x.length; f++) {
System.out.println("********* Para el punto " + (f+1) + " *******");
    System.out.println("Ingrese el valor de X");
x[f] = teclado.nextInt();
System.out.println("Ingrese el valor de Y");
y[f] = teclado.nextInt();
}
}
public void recorrercoordenadas() {
for (int f = 0; f < x.length; f++) {
if (x[f] > 0 && y[f] > 0) {
contador++;
}
if(x[f] < 0 && y[f] > 0){
    contador2++;
}
if(x[f] < 0 && y[f] < 0){
    contador3++;
}
    if(x[f] > 0 && y[f] < 0){
    contador4++;
}
}
    System.out.println("Puntos en el primer cuadrante: " + contador1);
    System.out.println("Puntos en el segundo cuadrante:  " + contador2);
    System.out.println("Puntos en el tercer cuadrante: " + contador3);
    System.out.println("Puntos en el cuarto cuadrante:  " + contador4);
}


                       
public static void main(String[] ar) {
Identificarcoordenadas cad = new Identificarcoordenadas();
cad.recorrercoordenadas();
}
                       
}

package obj3;

public class Carro {
      int velocidade = 0;

    void acelerar() {
        velocidade += 10;
        System.out.println("Velocidade: " + velocidade);
    }

    void desacelerar() {
        velocidade = Math.max(0, velocidade - 10);
        System.out.println("Velocidade: " + velocidade);
    }
}
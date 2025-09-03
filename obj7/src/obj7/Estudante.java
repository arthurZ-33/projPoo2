
package obj7;

import java.util.ArrayList;

public class Estudante {
      String nome;
    int idade;
    ArrayList<String> disciplinas = new ArrayList<>();

    Estudante(String n, int i) {
        nome = n;
        idade = i;
    }

    void adicionarDisciplina(String d) {
        disciplinas.add(d);
    }

    void exibirDisciplinas() {
        System.out.println("Disciplinas de " + nome + ":");
        for (String d : disciplinas) {
            System.out.println("- " + d);
        }
    }
}


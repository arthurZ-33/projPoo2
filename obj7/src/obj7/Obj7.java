
package obj7;

public class Obj7 {

    public static void main(String[] args) {
           Estudante e = new Estudante("Ana", 20);

        e.adicionarDisciplina("Matemática");
        e.adicionarDisciplina("História");
        e.adicionarDisciplina("Programação");

        e.exibirDisciplinas();
    }
}
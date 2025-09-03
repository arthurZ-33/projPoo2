
package obj4;

public class Obj4 {

    public static void main(String[] args) {
         Livro l = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 96, 2);

        l.emprestar(); // 1 exemplar
        l.emprestar(); // 0 exemplares
        l.emprestar(); // Nenhum exemplar disponível
        l.devolver();  // 1 exemplar
    }
}
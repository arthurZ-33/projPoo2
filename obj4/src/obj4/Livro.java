
package obj4;

public class Livro {
        String titulo, autor;
    int paginas, exemplares;

    Livro(String t, String a, int p, int e) {
        titulo = t;
        autor = a;
        paginas = p;
        exemplares = e;
    }

    void emprestar() {
        if (exemplares > 0) {
            exemplares--;
            System.out.println("Livro emprestado. Restam: " + exemplares);
        } else {
            System.out.println("Nenhum exemplar disponível.");
        }
    }

    void devolver() {
        exemplares++;
        System.out.println("Livro devolvido. Agora há: " + exemplares);
    }
}

